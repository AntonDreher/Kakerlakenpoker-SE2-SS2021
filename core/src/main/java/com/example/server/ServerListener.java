package com.example.server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;
import com.example.game.BuildGame;
import com.example.game.Game;
import com.example.game.GameConstants;
import com.example.game.listener.GameListenerServerSide;
import com.example.game.player.Player;
import com.example.server.dto.clienttomainserver.ClientJoinedRequest;
import com.example.server.dto.clienttomainserver.ClientName;
import com.example.server.dto.clienttomainserver.GameServerReadyToConnect;
import com.example.server.dto.clienttomainserver.GetRandomNumber;
import com.example.server.dto.mainservertoclient.ClientJoinedResponse;
import com.example.server.dto.mainservertoclient.ClientsToJoinGameServer;
import com.example.server.dto.mainservertoclient.DestroyLobby;
import com.example.server.dto.clienttomainserver.ExitLobby;
import com.example.server.dto.mainservertoclient.ExitLobbyResponse;
import com.example.server.dto.BaseMessage;
import com.example.server.dto.Lobby;
import com.example.server.dto.clienttomainserver.OpenLobby;
import com.example.server.dto.clienttomainserver.GetOpenLobbies;
import com.example.server.dto.mainservertoclient.RandomNumberResponse;
import com.example.server.dto.mainservertoclient.SendOpenLobbies;
import com.example.server.network.dto.clienttogameserver.HandOver;
import com.example.server.network.dto.clienttogameserver.MakeDecision;
import com.example.server.network.dto.clienttogameserver.MakeTurn;
import com.example.server.network.dto.gameservertoclient.GameOver;
import com.example.server.network.dto.gameservertoclient.InitGame;
import com.example.server.network.game.GameData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class ServerListener extends Listener {
    private final MainServer server;
    private HashMap<Connection, String> userNames;
    private ArrayList<GameData> gameDatas;

    public ServerListener(MainServer server){
        this.server = server;
        userNames = new HashMap<>();
        gameDatas = new ArrayList<>();
    }

    @Override
    public void connected(Connection connection) {
        Log.info("Client connected: "+ connection.getRemoteAddressTCP());
    }

    @Override
    public void received(Connection connection, Object object) {
        Log.info("ReceivedObject "+ object.getClass());
        if(object instanceof GetOpenLobbies){
            connection.sendTCP(new SendOpenLobbies(server.getAllLobbies()));
        } else if (object instanceof OpenLobby){
            Lobby lobbyToAdd = ((OpenLobby) object).getLobby();
            lobbyToAdd.setHostId(String.valueOf(connection.getID()));
            server.addLobby(lobbyToAdd);
        }else if(object instanceof ClientJoinedRequest){
            ClientJoinedResponseHandler(object, connection);
        }else if (object instanceof ExitLobby){
            ExitLobbyHandler(object, connection);
        }else if (object instanceof GameServerReadyToConnect){
            Lobby lobby = findLobbyByHostId(connection.getRemoteAddressTCP().toString());
            assert lobby != null;
            sendMessageToAllClientsInLobby(lobby, new ClientsToJoinGameServer(connection.getRemoteAddressTCP().getAddress().toString()));


        } else if (object instanceof ClientName){
            userNames.put(connection, ((ClientName) object).getClientsName());
        } else if(object instanceof MakeTurn||object instanceof MakeDecision|| object instanceof HandOver ||object instanceof GameOver)  {

            ///
            GameData gameData = null;
            Player currentPlayer = null;
            for (GameData data : gameDatas) {
                if (data.getUsers().contains(connection.getRemoteAddressTCP().toString())) {
                    gameData = data;
                    for (Player player : gameData.getGame().getPlayers()) {
                        if (player.getId() == connection.getID()) {
                            currentPlayer = player;
                        }
                    }
                }
            }

            if(gameData==null||currentPlayer==null)return;


            if (object instanceof MakeTurn) {
                gameData.getGame().makeTurn(currentPlayer, ((MakeTurn) object).getTurn());
            } else if (object instanceof MakeDecision) {
                gameData.getGame().makeDecision(currentPlayer, ((MakeDecision) object).getDecision());
            } else if (object instanceof GameOver){
                gameData.getGame().gameOver(currentPlayer);
            } else {
                gameData.getGame().handOver(currentPlayer, (HandOver) object);
            }
        } else if(object instanceof GetRandomNumber){
            //Random win number for MiniGame
            Random randomMinWinCount = new Random();
            // low inclusive, high exclusive
            int low = 2;
            int high = 5;
            int result = randomMinWinCount.nextInt(high-low) + low;
            RandomNumberResponse randomNumber = new RandomNumberResponse(result);
            server.getServer().sendToTCP(connection.getID(), randomNumber);
            Log.info("random number result: " + result);
        }
    }

    private void ExitLobbyHandler(Object object, Connection connection){
        String lobbyToExitName = ((ExitLobby) object).getLobbyToExitName();
        String ipAddress = connection.getRemoteAddressTCP().toString();
        String id = String.valueOf(connection.getID());

        for(Lobby currentLobby : server.getAllLobbies()){
            if(currentLobby.getName().equals(lobbyToExitName)){
                if(currentLobby.getHostId().equals(id)){
                    server.getAllLobbies().remove(currentLobby);
                    sendMessageToAllClientsInLobby(currentLobby, new DestroyLobby());
                }else {
                    sendMessageToAllClientsInLobby(currentLobby, new ExitLobbyResponse(ipAddress));
                    currentLobby.getPlayersIpList().remove(ipAddress);
                }
                break;
            }
        }
    }

    private void ClientJoinedResponseHandler(Object object, Connection connection){
        String lobbyToJoinName = ((ClientJoinedRequest) object).getLobbyName();
        String ipAddress   = connection.getRemoteAddressTCP().toString();
        String userName = userNames.get(connection);


        for(Lobby openLobby : server.getAllLobbies()){
            if(openLobby.getName().equals(lobbyToJoinName)){
                openLobby.getPlayersIpList().put(connection.getRemoteAddressTCP().toString(), userName);
                sendMessageToAllClientsInLobby(openLobby, new ClientJoinedResponse(openLobby));

                if(openLobby.getPlayersIpList().size() == GameConstants.NEEDED_PLAYERS_TO_PLAY){
                    createGameData(openLobby);
                    server.removeLobby(openLobby);
                    //sendMessageToHostFromLobby(openLobby, new StartUpGameServer());
                }
                break;
            }
        }

    }

    private void createGameData(Lobby lobby){
        GameData gameData;
        ArrayList<Player> players = new ArrayList<>();
        Set<String> keySet = lobby.getPlayersIpList().keySet();
        int counterDuplicatedNames = 0;
        for(Map.Entry<String , String> set: lobby.getPlayersIpList().entrySet()){
            boolean playersNameExists = false;
            for(Player checkNamePlayer: players){
                if(checkNamePlayer.getName().equals(userNames.get(server.getConnectionFromIpAddress(set.getKey()))))playersNameExists=true;
            }
            if(!playersNameExists) players.add(new Player(server.getConnectionFromIpAddress(set.getKey().toString()).getID(),userNames.get(server.getConnectionFromIpAddress(set.getKey())),null, null));
            else players.add(new Player(server.getConnectionFromIpAddress(set.getKey().toString()).getID(),userNames.get(server.getConnectionFromIpAddress(set.getKey()))+(++counterDuplicatedNames),null, null));
        }
        BuildGame buildGame = new BuildGame();
        buildGame.setPlayers(players);
        Game game = buildGame.buildGame();
        gameData = new GameData(game);
        gameData.getUsers().addAll(keySet);
        gameData.setIpUserMapping(lobby.getPlayersIpList());


        gameDatas.add(gameData);

        for(String address : lobby.getPlayersIpList().keySet()){
            server.getServer().sendToTCP(server.getConnectionFromIpAddress(address).getID(), new InitGame(game));
        }
        gameData.getGame().setGameListener(new GameListenerServerSide(server,gameData));
        gameData.getGame().startGame();
    }

    private void sendMessageToAllClientsInLobby(Lobby lobby, BaseMessage message){
        for(String address : lobby.getPlayersIpList().keySet()){
            server.getServer().sendToTCP(server.getConnectionFromIpAddress(address.toString()).getID(), message);
        }
    }

    private void sendMessageToHostFromLobby(Lobby lobby, BaseMessage message){
        server.getServer().sendToTCP(server.getConnectionFromIpAddress(lobby.getHostId()).getID(), message);
    }

    private Lobby findLobbyByHostId(String ipAddress){
        for(Lobby currentLobby : server.getAllLobbies()){
            if(currentLobby.getHostId().equals(ipAddress)){
                return currentLobby;
            }
        }
        return null;
    }

    @Override
    public void disconnected(Connection connection) {
        Log.info("Client disconnected: "+connection);
        for (Iterator<Lobby> iterator = server.getAllLobbies().iterator(); iterator.hasNext();) {
            Lobby lobbyToRemove = iterator.next();
            if(lobbyToRemove.getHostId().equals(String.valueOf(connection.getID()))) {
                iterator.remove();
            }
        }

        userNames.remove(connection);
    }
}
