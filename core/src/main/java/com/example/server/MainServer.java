package com.example.server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Server;
import com.example.server.dto.Lobby;
import com.example.server.network.kryo.RegisterHelper;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class MainServer {
    private final Server server;
    private ArrayList<Lobby> allLobbies;

    public MainServer() throws IOException {
        server = new Server();
        allLobbies = new ArrayList<>();

        server.addListener(new ServerListener(this));

        RegisterHelper.registerClasses(server.getKryo());

        server.start();
        server.bind(NetworkConstants.TCP_PORT);
    }

    public ArrayList<Lobby> getAllLobbies(){
        return allLobbies;
    }

    public void addLobby(Lobby Lobby){
        allLobbies.add(Lobby);
    }

    public Server getServer() {
        return server;
    }

    public Connection getConnectionFromIpAddress(String ipAddress){
        for(Connection connection : server.getConnections()){
            if(connection.getRemoteAddressTCP().toString().equals(ipAddress)){
                return connection;
            }
        }
        return null;
    }

    public void removeLobby(Lobby lobby){
        for (Iterator<Lobby> iterator = allLobbies.iterator(); iterator.hasNext();) {
            Lobby lobbyToRemove = iterator.next();
            if(lobbyToRemove.getHostId().equals(String.valueOf(lobby.getHostId()))) {
                iterator.remove();
            }
        }
    }


}
