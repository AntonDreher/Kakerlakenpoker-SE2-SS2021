package com.example.kakerlakenpoker.server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;
import com.example.kakerlakenpoker.network.dto.clienttomainserver.OpenLobby;
import com.example.kakerlakenpoker.network.dto.clienttomainserver.GetOpenLobbies;
import com.example.kakerlakenpoker.network.dto.mainservertoclient.SendOpenLobbies;

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
            connection.sendTCP(new SendOpenLobbies(server.getOpenLobbies()));
        } else if (object instanceof OpenLobby){
            server.addLobby(((OpenLobby) object).getLobby());
        }
    }

    @Override
    public void disconnected(Connection connection) {
        Log.info("Client disconnected: "+ connection.getRemoteAddressTCP());
        server.removeLobby(connection);
    }
}
