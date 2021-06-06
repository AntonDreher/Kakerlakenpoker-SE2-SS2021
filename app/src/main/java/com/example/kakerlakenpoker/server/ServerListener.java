package com.example.kakerlakenpoker.server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;
import com.example.kakerlakenpoker.game.GameConstants;
import com.example.kakerlakenpoker.network.dto.BaseMessage;
import com.example.kakerlakenpoker.network.dto.clienttomainserver.ClientJoinedRequest;
import com.example.kakerlakenpoker.network.dto.mainservertoclient.ClientJoinedResponse;
import com.example.kakerlakenpoker.network.dto.mainservertoclient.DestroyLobby;
import com.example.kakerlakenpoker.network.dto.clienttomainserver.ExitLobby;
import com.example.kakerlakenpoker.network.dto.mainservertoclient.ExitLobbyResponse;
import com.example.kakerlakenpoker.network.dto.Lobby;
import com.example.kakerlakenpoker.network.dto.clienttomainserver.OpenLobby;
import com.example.kakerlakenpoker.network.dto.clienttomainserver.GetOpenLobbies;
import com.example.kakerlakenpoker.network.dto.mainservertoclient.SendOpenLobbies;
import com.example.kakerlakenpoker.network.dto.mainservertoclient.StartUpGameServer;

public class ServerListener extends Listener {
    private final MainServer server;

    public ServerListener(MainServer server){
        this.server = server;
    }

    @Override
    public void connected(Connection connection) {
        Log.info("Client connected: "+ connection.getRemoteAddressTCP());
    }

    @Override
    public void received(Connection connection, Object object) {
        if(object instanceof GetOpenLobbies){
            connection.sendTCP(new SendOpenLobbies(server.getAllLobbies()));
        } else if (object instanceof OpenLobby){
            Lobby lobbyToAdd = ((OpenLobby) object).getLobby();
            server.addLobby(lobbyToAdd);
        }else if(object instanceof ClientJoinedRequest){
            ClientJoinedResponseHandler(object);
        }else if (object instanceof ExitLobby){
            ExitLobbyHandler(object);
        }
    }

    private void ExitLobbyHandler(Object object){
        String lobbyToExitName = ((ExitLobby) object).getLobbyToExitName();
        String ipAddress = ((ExitLobby) object).getIpAddress();
        for(Lobby currentLobby : server.getAllLobbies()){
            if(currentLobby.getName().equals(lobbyToExitName)){
                if(currentLobby.getHostIP().equals(ipAddress)){
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

    private void ClientJoinedResponseHandler(Object object){
        String lobbyToJoinName = ((ClientJoinedRequest) object).getLobbyName();
        String ipAddress   = ((ClientJoinedRequest) object).getIpAddress();

        for(Lobby openLobby : server.getAllLobbies()){
            if(openLobby.getName().equals(lobbyToJoinName)){
                openLobby.getPlayersIpList().add(ipAddress);
                Log.info(openLobby.toString());
                sendMessageToAllClientsInLobby(openLobby, new ClientJoinedResponse(openLobby));

                if(openLobby.getPlayersIpList().size() == GameConstants.NEEDED_PLAYERS_TO_PLAY){
                    sendMessageToHostFromLobby(openLobby, new StartUpGameServer());
                }
                break;
            }
        }

    }
    private void sendMessageToAllClientsInLobby(Lobby lobby, BaseMessage message){
        for(String ipAddress : lobby.getPlayersIpList()){
            server.getServer().sendToTCP(server.getConnectionFromIpAddress(ipAddress).getID(), message);
        }
    }
    private void sendMessageToHostFromLobby(Lobby lobby, BaseMessage message){
        server.getServer().sendToTCP(server.getConnectionFromIpAddress(lobby.getHostIP()).getID(), message);
    }
    @Override
    public void disconnected(Connection connection) {
        Log.info("Client disconnected: "+ connection.getRemoteAddressTCP());
    }
}
