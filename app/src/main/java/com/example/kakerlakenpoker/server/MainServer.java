package com.example.kakerlakenpoker.server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Server;
import com.example.kakerlakenpoker.network.dto.Lobby;
import com.example.kakerlakenpoker.network.kryo.NetworkConstants;
import com.example.kakerlakenpoker.network.kryo.RegisterHelper;

import java.io.IOException;
import java.util.ArrayList;

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

    public void removeLobby(Connection connection){
        for(Lobby lobby: allLobbies){
            if(lobby.getHostIP().equals(connection.getRemoteAddressTCP().toString())) allLobbies.remove(lobby);
        }
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
}
