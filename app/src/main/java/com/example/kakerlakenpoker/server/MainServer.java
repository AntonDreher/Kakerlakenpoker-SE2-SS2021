package com.example.kakerlakenpoker.server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Server;
import com.example.kakerlakenpoker.network.dto.Lobby;
import com.example.kakerlakenpoker.network.dto.clienttomainserver.OpenLobby;
import com.example.kakerlakenpoker.network.dto.clienttomainserver.GetOpenLobbies;
import com.example.kakerlakenpoker.network.game.GameClient;
import com.example.kakerlakenpoker.network.kryo.NetworkConstants;
import com.example.kakerlakenpoker.network.kryo.RegisterHelper;

import java.io.IOException;
import java.util.ArrayList;

public class MainServer {
    private final Server server;
    private ArrayList<Lobby> openLobbies;

    public MainServer() throws IOException {
        server = new Server();
        openLobbies = new ArrayList<>();

        server.addListener(new ServerListener(this));

        RegisterHelper.registerClasses(server.getKryo());
        server.start();
        server.bind(NetworkConstants.TCP_PORT);
    }

    public void removeLobby(Connection connection){
        for(Lobby lobby: openLobbies){
            if(lobby.getHostIP().equals(connection.getRemoteAddressTCP().toString())) openLobbies.remove(lobby);
        }
    }

    public ArrayList<Lobby> getOpenLobbies(){
        return openLobbies;
    }

    public void addLobby(Lobby Lobby){
        openLobbies.add(Lobby);
    }
}
