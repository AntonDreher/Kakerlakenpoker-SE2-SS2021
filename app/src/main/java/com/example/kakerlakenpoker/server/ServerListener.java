package com.example.kakerlakenpoker.server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;
import com.example.kakerlakenpoker.network.dto.ClientJoinedRequest;
import com.example.kakerlakenpoker.network.dto.ClientJoinedResponse;
import com.example.kakerlakenpoker.network.dto.ExitLobby;
import com.example.kakerlakenpoker.network.dto.ExitLobbyResponse;
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
            connection.sendTCP(new SendOpenLobbies(server.getAllLobbies()));
        } else if (object instanceof OpenLobby){
            Lobby lobbyToAdd = ((OpenLobby) object).getLobby();
            server.addLobby(lobbyToAdd);
        }else if(object instanceof ClientJoinedRequest){
            String lobbyToJoinName = ((ClientJoinedRequest) object).getLobbyName();
            String ipAddress   = ((ClientJoinedRequest) object).getIpAddress();

            for(Lobby openLobby : server.getAllLobbies()){
                if(openLobby.getName().equals(lobbyToJoinName)){
                    openLobby.getPlayersIpList().add(ipAddress);
                    Log.info(openLobby.toString());
                    server.getServer().sendToAllTCP(new ClientJoinedResponse(openLobby));
                    break;
                }
            }
        }else if (object instanceof ExitLobby){
            String lobbyToExitName = ((ExitLobby) object).getLobbyToExitName();
            String ipAddress = ((ExitLobby) object).getIpAddress();
            for(Lobby currentLobby : server.getAllLobbies()){
                if(currentLobby.getName().equals(lobbyToExitName)){
                    if(currentLobby.getHostIP().equals(ipAddress)){
                        server.getAllLobbies().remove(currentLobby);
              //          server.getServer().sendToAllTCP(new DestroyLobby);
                    }else {
                        sendExitLobbyResponseToAllClientsInLobby(currentLobby);
                        currentLobby.getPlayersIpList().remove(ipAddress);
                        break;
                    }
                }
            }
        }
    }

    private void sendExitLobbyResponseToAllClientsInLobby(Lobby lobby){
        for(String ipAddress : lobby.getPlayersIpList()){
            Log.info(ipAddress + "current position");
            server.getServer().sendToTCP(server.getConnectionFromIpAddress(ipAddress).getID(), new ExitLobbyResponse(ipAddress));
        }
    }
    @Override
    public void disconnected(Connection connection) {
        Log.info("Client disconnected: "+ connection.getRemoteAddressTCP());
    }
}
