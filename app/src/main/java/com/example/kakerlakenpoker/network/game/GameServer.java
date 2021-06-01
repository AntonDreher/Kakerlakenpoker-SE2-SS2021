package com.example.kakerlakenpoker.network.game;


import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.minlog.Log;
import com.example.kakerlakenpoker.network.dto.BaseMessage;
import com.example.kakerlakenpoker.network.dto.ClientJoined;
import com.example.kakerlakenpoker.network.dto.ClientsInLobby;
import com.example.kakerlakenpoker.network.kryo.NetworkServerKryo;
import com.example.kakerlakenpoker.network.kryo.RegisterHelper;

import java.io.IOException;
import java.util.ArrayList;

public class GameServer {
    private static GameServer instance;
    private NetworkServerKryo server;
    private boolean waitingForClients = true;

    private GameServer(){
    }

    public static GameServer getInstance(){
        if(instance == null){
            instance = new GameServer();
        }
        return instance;
    }

    public void init(){
        server = new NetworkServerKryo();
        try {
            RegisterHelper.registerClasses(server.getServer().getKryo());
            server.registerCallback(this::callback);
            server.start();
            Log.info("Server started successful");
        }catch(IOException e){
            Log.info("Server couldn't start");
        }
    }

    private void callback(BaseMessage message){
        if (message instanceof ClientJoined){
            Log.info("Client joined received on Server");
            ArrayList<String> ipList = new ArrayList<>();
            for(Connection connection : server.getConnections()){
                ipList.add(connection.getRemoteAddressTCP().toString());
                Log.info(connection.getRemoteAddressTCP().toString());
            }
            server.getServer().sendToAllTCP(new ClientsInLobby(ipList));
        }
    }

    public void broadcastMessage(BaseMessage message) {
        server.broadcastMessage(message);
    }

    public boolean isWaitingForClients(){
        return waitingForClients;
    }
}
