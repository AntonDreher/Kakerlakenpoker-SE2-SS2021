package com.example.server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;
import com.example.server.dto.clienttomainserver.ClientJoinedRequest;
import com.example.server.dto.clienttomainserver.ClientName;
import com.example.server.dto.clienttomainserver.GameServerReadyToConnect;
import com.example.server.dto.mainservertoclient.ClientJoinedResponse;
import com.example.server.dto.mainservertoclient.ClientsToJoinGameServer;
import com.example.server.dto.mainservertoclient.DestroyLobby;
import com.example.server.dto.clienttomainserver.ExitLobby;
import com.example.server.dto.mainservertoclient.ExitLobbyResponse;
import com.example.server.dto.BaseMessage;
import com.example.server.dto.Lobby;
import com.example.server.dto.clienttomainserver.OpenLobby;
import com.example.server.dto.clienttomainserver.GetOpenLobbies;
import com.example.server.dto.mainservertoclient.SendOpenLobbies;
import com.example.server.dto.mainservertoclient.StartUpGameServer;

import java.net.InetSocketAddress;
import java.util.HashMap;

public class ServerListener extends Listener {
    private final MainServer server;
    private HashMap<InetSocketAddress, String> userNames;

    public ServerListener(MainServer server){
        this.server = server;
        userNames = new HashMap<>();
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
            lobbyToAdd.setHostIP(connection.getRemoteAddressTCP().toString());
            server.addLobby(lobbyToAdd);
        }else if(object instanceof ClientJoinedRequest){
            ClientJoinedResponseHandler(object, connection);
        }else if (object instanceof ExitLobby){
            ExitLobbyHandler(object, connection);
        }else if (object instanceof GameServerReadyToConnect){
            Lobby lobby = findLobbyByHostId(connection.getRemoteAddressTCP().toString());
            sendMessageToAllClientsInLobby(lobby, new ClientsToJoinGameServer(connection.getRemoteAddressTCP().getAddress().toString()));
        } else if (object instanceof ClientName){
            userNames.put(connection.getRemoteAddressTCP(), ((ClientName) object).getClientsName());
        }
    }

    private void ExitLobbyHandler(Object object, Connection connection){
        String lobbyToExitName = ((ExitLobby) object).getLobbyToExitName();
        String ipAddress = connection.getRemoteAddressTCP().toString();

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

    private void ClientJoinedResponseHandler(Object object, Connection connection){
        String lobbyToJoinName = ((ClientJoinedRequest) object).getLobbyName();
        String ipAddress   = connection.getRemoteAddressTCP().toString();
        String userName = userNames.get(connection.getRemoteAddressTCP());


        for(Lobby openLobby : server.getAllLobbies()){
            if(openLobby.getName().equals(lobbyToJoinName)){
                openLobby.getPlayersIpList().put(ipAddress, userName);
                Log.info(openLobby.toString());
                sendMessageToAllClientsInLobby(openLobby, new ClientJoinedResponse(openLobby));

                if(openLobby.getPlayersIpList().size() == 4){
                    sendMessageToHostFromLobby(openLobby, new StartUpGameServer());
                }
                break;
            }
        }

    }

    private void sendMessageToAllClientsInLobby(Lobby lobby, BaseMessage message){
        for(String ipAddress : lobby.getPlayersIpList().keySet()){
            server.getServer().sendToTCP(server.getConnectionFromIpAddress(ipAddress).getID(), message);
        }
    }

    private void sendMessageToHostFromLobby(Lobby lobby, BaseMessage message){
        Log.info("Send Message to Host from Lobby");
        server.getServer().sendToTCP(server.getConnectionFromIpAddress(lobby.getHostIP()).getID(), message);
    }

    private Lobby findLobbyByHostId(String ipAddress){
        for(Lobby currentLobby : server.getAllLobbies()){
            if(currentLobby.getHostIP().equals(ipAddress)){
                return currentLobby;
            }
        }
        return null;
    }

    @Override
    public void disconnected(Connection connection) {
        userNames.remove(connection.getRemoteAddressTCP());
        Log.info("Client disconnected: "+ connection.getRemoteAddressTCP());
    }
}
