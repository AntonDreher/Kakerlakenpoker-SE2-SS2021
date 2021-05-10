package com.example.kakerlakenpoker.network.game;


import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.minlog.Log;
import com.example.kakerlakenpoker.R;
import com.example.kakerlakenpoker.network.Callback;
import com.example.kakerlakenpoker.network.dto.BaseMessage;
import com.example.kakerlakenpoker.network.dto.ClientJoined;
import com.example.kakerlakenpoker.network.dto.ClientsInLobby;
import com.example.kakerlakenpoker.network.kryo.NetworkServerKryo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class GameServer {
    private static GameServer instance;
    private NetworkServerKryo server;
    private String ip;
    private boolean waitingForClients = true;

    private GameServer(){
    }

    public static GameServer getInstance(){
        if(instance == null){
            instance = new GameServer();
        }
        return instance;
    }

    public void init(String ip){
        server = new NetworkServerKryo();
        try {
            this.ip = ip;
            server.registerCallback(this::callback);
            this.registerClasses();
            server.start();
            Log.info("Server started successful");
        }catch(IOException e){
            Log.info("Server couldn't start");
        }
    }

    private void registerClasses(){
        server.registerClass(BaseMessage.class);
        server.registerClass(ClientJoined.class);
        server.registerClass(ClientsInLobby.class);
        server.registerClass(ArrayList.class);
    }

    private void callback(BaseMessage message){
        if (message instanceof ClientJoined){
            ArrayList<String> ipList = new ArrayList<>();
            for(Connection connection : server.getConnections()){
                ipList.add(connection.getRemoteAddressTCP().toString());
                Log.info(connection.getRemoteAddressTCP().toString());
            }
            server.broadcastMessage(new ClientsInLobby(ipList));
        }
    }

    public boolean isWaitingForClients(){
        return waitingForClients;
    }
}
