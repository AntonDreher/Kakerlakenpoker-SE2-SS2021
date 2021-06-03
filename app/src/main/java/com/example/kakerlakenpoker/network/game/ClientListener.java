package com.example.kakerlakenpoker.network.game;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;
import com.example.kakerlakenpoker.game.BuildGame;
import com.example.kakerlakenpoker.game.Game;
import com.example.kakerlakenpoker.game.GameState;
import com.example.kakerlakenpoker.network.dto.ClientsInLobby;
import com.example.kakerlakenpoker.network.dto.GameUpdate;
import com.example.kakerlakenpoker.network.dto.InitGame;
import com.example.kakerlakenpoker.network.dto.clienttomainserver.GetOpenLobbies;
import com.example.kakerlakenpoker.network.dto.clienttomainserver.OpenLobby;
import com.example.kakerlakenpoker.network.dto.mainservertoclient.SendOpenLobbies;
import com.example.kakerlakenpoker.server.MainServer;


public class ClientListener extends Listener {
    private final GameClient gameClient;
    private Game game;

    public ClientListener(GameClient gameClient) {
        this.gameClient = gameClient;
        this.game = null;
    }

    @Override
    public void connected(Connection connection) {
        Log.info("Client connected: " + connection.getRemoteAddressTCP());
    }

    @Override
    public void received(Connection connection, Object object) {
        Log.info("Received Object: " + object.getClass());

        if (object instanceof ClientsInLobby) {
            gameClient.getIpList().clear();
            gameClient.getIpList().addAll(((ClientsInLobby) object).ipFromClients);
            Log.info(gameClient.getIpList().toString());
        } else if (object instanceof SendOpenLobbies) {
            gameClient.setOpenLobbies(((SendOpenLobbies) object).getLobbies());
        } else if(object instanceof InitGame){
            BuildGame buildGame = new BuildGame();
            buildGame.setPlayers(((InitGame) object).getGameUpdate().getPlayers());
            game = buildGame.buildGame();
            game.updateGame(((InitGame) object).getGameUpdate());
        }else if (object instanceof GameUpdate){
            game.updateGame((GameUpdate)object);
        }
    }

    @Override
    public void disconnected(Connection connection) {
        Log.info("Client disconnected: " + connection.getRemoteAddressTCP());
        //server.removeLobby(connection);
    }
}
