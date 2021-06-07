package com.example.server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;

import com.example.server.dto.Lobby;


import java.io.IOException;
import java.util.ArrayList;

public class MainServer {
    private final Server server;
    private ArrayList<Lobby> allLobbies;

    public MainServer() throws IOException {
        server = new Server();
        allLobbies = new ArrayList<>();

        server.addListener(new ServerListener(this));

        RegisterClasses.registerClasses(server.getKryo());

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
}