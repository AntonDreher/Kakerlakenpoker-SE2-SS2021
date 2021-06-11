package com.example.kakerlakenpoker.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;
import com.example.game.BuildGame;
import com.example.game.Game;
import com.example.server.network.NetworkUtils;
import com.example.server.network.dto.PlayerReady;
import com.example.server.network.game.GameData;
import com.example.server.dto.clienttomainserver.GameServerReadyToConnect;
import com.example.server.dto.mainservertoclient.ClientJoinedResponse;
import com.example.server.dto.mainservertoclient.ClientsToJoinGameServer;
import com.example.server.dto.mainservertoclient.DestroyLobby;
import com.example.server.dto.mainservertoclient.ExitLobbyResponse;
import com.example.server.network.dto.gameservertoclient.GameOver;
import com.example.server.network.dto.gameservertoclient.GameUpdate;
import com.example.server.network.dto.gameservertoclient.InitGame;
import com.example.server.dto.mainservertoclient.SendOpenLobbies;
import com.example.server.dto.mainservertoclient.StartUpGameServer;


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
            Game game = ((InitGame) object).getGame();
            gameClient.setGame(game);
            gameClient.getGame().setGameListener(new GameListenerClientSide(gameClient));
            if(gameClient.getActivity() instanceof ShowPlayersInLobbyActivity)((ShowPlayersInLobbyActivity) gameClient.getActivity()).showGame();

        }else if (object instanceof GameUpdate){
            gameClient.getGame().updateGame((GameUpdate)object);
        } else if(object instanceof GameOver){
            gameClient.getGame().gameOver(((GameOver) object).getLoser());
        } else if(object instanceof ClientJoinedResponse) {
            ClientJoinedResponseHandler(object);
        }else if (object instanceof ExitLobbyResponse){
            ExitLobbyResponseHandler(object);
        }else if (object instanceof DestroyLobby){
            DestroyLobbyHandler();
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
                        if(gameClient.getListAdapter().getItem(i).equals(gameClient.getCurrentLobby().getPlayersIpList().get(ipAddress))){
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


    @Override
    public void disconnected(Connection connection) {
        Log.info("Client disconnected: " + connection.getRemoteAddressTCP());
    }
}
