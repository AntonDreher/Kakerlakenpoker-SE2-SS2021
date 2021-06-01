package com.example.kakerlakenpoker.network.game;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;
import com.example.kakerlakenpoker.game.Game;
import com.example.kakerlakenpoker.network.dto.ClientsInLobby;
import com.example.kakerlakenpoker.network.dto.GameUpdate;
import com.example.kakerlakenpoker.network.dto.clienttomainserver.GetOpenLobbies;
import com.example.kakerlakenpoker.network.dto.clienttomainserver.OpenLobby;
import com.example.kakerlakenpoker.network.dto.mainservertoclient.SendOpenLobbies;
import com.example.kakerlakenpoker.server.MainServer;


public class ClientListener extends Listener {
    private final GameClient server;
    private Game game;

    public ClientListener(GameClient server) {
        this.server = server;
        this.game = null;
    }

    @Override
    public void connected(Connection connection) {
        Log.info("Client connected: " + connection.getRemoteAddressTCP());
    }

    @Override
    public void received(Connection connection, Object object) {
        if (object instanceof ClientsInLobby) {
            server.getIpList().clear();
            server.getIpList().addAll(((ClientsInLobby) object).ipFromClients);
            Log.info(server.getIpList().toString());
        } else if (object instanceof SendOpenLobbies) {
            server.setOpenLobbies(((SendOpenLobbies) object).getLobbies());
        } else if (object instanceof GameUpdate){
            game.updateGame((GameUpdate)object);
        }
    }

    @Override
    public void disconnected(Connection connection) {
        Log.info("Client disconnected: " + connection.getRemoteAddressTCP());
        //server.removeLobby(connection);
    }
}
