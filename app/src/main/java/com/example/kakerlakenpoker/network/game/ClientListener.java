package com.example.kakerlakenpoker.network.game;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;
import com.example.kakerlakenpoker.activities.ShowPlayersInLobbyActivity;
import com.example.kakerlakenpoker.game.BuildGame;
import com.example.kakerlakenpoker.game.listener.GameListenerClientSide;
import com.example.kakerlakenpoker.network.dto.ClientJoinedResponse;
import com.example.kakerlakenpoker.network.dto.GameOver;
import com.example.kakerlakenpoker.network.dto.GameUpdate;
import com.example.kakerlakenpoker.network.dto.InitGame;
import com.example.kakerlakenpoker.network.dto.mainservertoclient.SendOpenLobbies;


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
            gameClient.setCurrentLobby(((ClientJoinedResponse) object).getLobbyJoined());

            ((ShowPlayersInLobbyActivity) gameClient.getListAdapter().getContext()).runOnUiThread(
                    () -> {
                        for (int i = 0; i < gameClient.getListAdapter().getCount(); i++) {
                            gameClient.getListAdapter().remove(gameClient.getListAdapter().getItem(i));
                        }
                        gameClient.getListAdapter().addAll(gameClient.getCurrentLobby().getPlayersIpList());
                        gameClient.getListAdapter().notifyDataSetChanged();
                    });
            Log.info(gameClient.getCurrentLobby().getPlayersIpList().toString());
        }
    }

    @Override
    public void disconnected(Connection connection) {
        Log.info("Client disconnected: " + connection.getRemoteAddressTCP());
    }
}
