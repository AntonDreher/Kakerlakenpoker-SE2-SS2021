package com.example.kakerlakenpoker.server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;
import com.example.kakerlakenpoker.network.dto.ClientJoinedRequest;
import com.example.kakerlakenpoker.network.dto.ClientJoinedResponse;
import com.example.kakerlakenpoker.network.dto.Lobby;
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
            Lobby lobbyToAdd = ((OpenLobby) object).getLobby();
            server.addLobby(lobbyToAdd);
        }else if(object instanceof ClientJoinedRequest){
            String lobbyToJoin = ((ClientJoinedRequest) object).getLobbyName();
            String ipAddress   = ((ClientJoinedRequest) object).getIpAddress();

            for(Lobby openLobby : server.getOpenLobbies()){
                if(openLobby.getName().equals(lobbyToJoin)){
                    openLobby.getPlayersIpList().add(ipAddress);
                    Log.info(openLobby.toString());
                    server.getServer().sendToAllTCP(new ClientJoinedResponse(openLobby));
                    break;
                }
            }
        }
    }

    @Override
    public void disconnected(Connection connection) {
        Log.info("Client disconnected: "+ connection.getRemoteAddressTCP());
    }
}
