package com.example.kakerlakenpoker.network.game;

import com.esotericsoftware.minlog.Log;
import com.example.kakerlakenpoker.network.dto.BaseMessage;
import com.example.kakerlakenpoker.network.dto.ClientJoined;
import com.example.kakerlakenpoker.network.dto.ClientsInLobby;
import com.example.kakerlakenpoker.network.kryo.NetworkClientKryo;

import java.io.IOException;
import java.util.ArrayList;


public class GameClient {
    private static GameClient instance;
    private NetworkClientKryo client;
    private ArrayList<String> ipList = new ArrayList<>();
    private GameClient(){

    }

    public static GameClient getInstance(){
        if (instance == null){
            instance = new GameClient();
        }
        return instance;
    }

    public void init(String host, String ip) {
        try {
            client = new NetworkClientKryo();
            registerClasses();
            client.registerCallback(this::callback);
            client.connect(host);
            client.sendMessage(new ClientJoined(ip));
            Log.info(ip + " sent to " + host);
        }catch(IOException e){
            Log.info(e.getMessage());
            Log.info("Could not connect to host " + host);
        }
    }

    private void registerClasses(){
        client.registerClass(BaseMessage.class);
        client.registerClass(ClientJoined.class);
        client.registerClass(ClientsInLobby.class);
        client.registerClass(ArrayList.class);
    }

    private void callback(BaseMessage message){
        if(message instanceof  ClientsInLobby){
            ipList.clear();
            ipList.addAll(((ClientsInLobby) message).ipFromClients);
        }
    }

    public ArrayList<String> getIpList(){
        return ipList;
    }
}
