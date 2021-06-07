package com.example.kakerlakenpoker.network.game;

import android.content.Intent;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;
import com.example.kakerlakenpoker.activities.MainMenuActivity;
import com.example.kakerlakenpoker.activities.ShowPlayersInLobbyActivity;
import com.example.kakerlakenpoker.game.BuildGame;
import com.example.kakerlakenpoker.game.listener.GameListenerClientSide;
import com.example.kakerlakenpoker.network.NetworkUtils;
import com.example.kakerlakenpoker.network.dto.PlayerReady;
import com.example.server.dto.clienttomainserver.GameServerReadyToConnect;
import com.example.server.dto.mainservertoclient.ClientJoinedResponse;
import com.example.server.dto.mainservertoclient.ClientsToJoinGameServer;
import com.example.server.dto.mainservertoclient.DestroyLobby;
import com.example.server.dto.mainservertoclient.ExitLobbyResponse;
import com.example.kakerlakenpoker.network.dto.gameservertoclient.GameOver;
import com.example.kakerlakenpoker.network.dto.gameservertoclient.GameUpdate;
import com.example.kakerlakenpoker.network.dto.gameservertoclient.InitGame;
import com.example.server.dto.mainservertoclient.SendOpenLobbies;
import com.example.server.dto.mainservertoclient.StartUpGameServer;

import java.util.ArrayList;


public class ClientListener extends Listener {
    private final GameClient gameClient;

    public ClientListener(GameClient gameClient) {
        this.gameClient = gameClient;
    }

    @Override
    public void connected(Connection connection) {
        Log.info("Client connected: " + connection.getRemoteAddressTCP());
    }

    @Override
    public void received(Connection connection, Object object) {
        Log.info("Received Object: " + object.getClass());

        if (object instanceof SendOpenLobbies) {
            gameClient.setOpenLobbies(((SendOpenLobbies) object).getLobbies());
        } else if(object instanceof InitGame){
            BuildGame buildGame = new BuildGame();
            buildGame.setPlayers(((InitGame) object).getGameUpdate().getPlayers());
            gameClient.setGame(buildGame.buildGame());
            gameClient.getGame().setGameListener(new GameListenerClientSide(gameClient));
            gameClient.getGame().updateGame(((InitGame) object).getGameUpdate());
        }else if (object instanceof GameUpdate){
            gameClient.getGame().updateGame((GameUpdate)object);
        } else if(object instanceof GameOver){

        } else if(object instanceof ClientJoinedResponse) {
            ClientJoinedResponseHandler(object);
        }else if (object instanceof ExitLobbyResponse){
            ExitLobbyResponseHandler(object);
        }else if (object instanceof DestroyLobby){
            DestroyLobbyHandler();
        }else if (object instanceof StartUpGameServer){
            StartGameServerOnThisDeviceHandler();
        }else if (object instanceof ClientsToJoinGameServer){
            gameClient.connectToNewServer(((ClientsToJoinGameServer) object).getIpToConnect(), this);
            gameClient.getClient().sendMessage(new PlayerReady());
        }
    }

    private void ExitLobbyResponseHandler(Object object){
        String ipAddress = ((ExitLobbyResponse) object).getIpAddress();
        if(ipAddress.equals(NetworkUtils.getIpAddressFromDevice())){
            gameClient.setCurrentLobby(null);
        }
        ((ShowPlayersInLobbyActivity) gameClient.getListAdapter().getContext()).runOnUiThread(
                () ->{
                    for(int i=0; i<gameClient.getListAdapter().getCount(); i++){
                        if(gameClient.getListAdapter().getItem(i).equals(ipAddress)){
                            gameClient.getListAdapter().remove(gameClient.getListAdapter().getItem(i));
                            break;
                        }
                    }
                    gameClient.getListAdapter().notifyDataSetChanged();
                }
        );
    }

    private void ClientJoinedResponseHandler(Object object){
        gameClient.setCurrentLobby(((ClientJoinedResponse) object).getLobbyJoined());

        ((ShowPlayersInLobbyActivity) gameClient.getListAdapter().getContext()).runOnUiThread(
                () -> {
                    for (int i = 0; i < gameClient.getListAdapter().getCount(); i++) {
                        gameClient.getListAdapter().clear();
                    }

                    gameClient.getListAdapter().addAll(gameClient.getCurrentLobby().getPlayersIpList().values());
                    gameClient.getListAdapter().notifyDataSetChanged();
                });
        Log.info(gameClient.getCurrentLobby().getPlayersIpList().toString());
    }

    private void DestroyLobbyHandler(){
        Log.info("Destroy Lobby");
        ((ShowPlayersInLobbyActivity) gameClient.getListAdapter().getContext()).runOnUiThread(
                () -> {
                    gameClient.getListAdapter().getContext().startActivity(new Intent(gameClient.getListAdapter().getContext(), MainMenuActivity.class));
                });
    }

    private void StartGameServerOnThisDeviceHandler(){
        Log.info("Starting Game Server");
        GameServer.getInstance().init();
        gameClient.getClient().sendMessage(new GameServerReadyToConnect());
    }
    @Override
    public void disconnected(Connection connection) {
        Log.info("Client disconnected: " + connection.getRemoteAddressTCP());
    }
}
