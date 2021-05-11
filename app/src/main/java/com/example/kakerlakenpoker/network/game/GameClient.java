package com.example.kakerlakenpoker.network.game;

import com.esotericsoftware.minlog.Log;
import com.example.kakerlakenpoker.network.dto.BaseMessage;
import com.example.kakerlakenpoker.network.dto.ClientJoined;
import com.example.kakerlakenpoker.network.dto.ClientsInLobby;
import com.example.kakerlakenpoker.network.dto.Lobby;
import com.example.kakerlakenpoker.network.dto.clienttomainserver.OpenLobby;
import com.example.kakerlakenpoker.network.dto.mainservertoclient.SendOpenLobbies;
import com.example.kakerlakenpoker.network.kryo.NetworkClientKryo;
import com.example.kakerlakenpoker.network.kryo.NetworkConstants;
import com.example.kakerlakenpoker.network.kryo.RegisterHelper;

import java.io.IOException;
import java.util.ArrayList;


public class GameClient {
    private static GameClient instance;
    private NetworkClientKryo client;
    private ArrayList<String> ipList = new ArrayList<>();
    private ArrayList<Lobby> openLobbies = new ArrayList<>();

    private GameClient(){

    }

    public static GameClient getInstance(){
        if (instance == null){
            instance = new GameClient();
        }
        return instance;
    }

    public void init(String ip) {
        try {
            client = new NetworkClientKryo();
            RegisterHelper.registerClasses(client.getClient().getKryo());
            //registerClasses();
            client.registerCallback(this::callback);
            client.connect(NetworkConstants.MAIN_SERVER_IP);
            client.sendMessage(new ClientJoined(ip));
            Log.info(ip + " sent to " + NetworkConstants.MAIN_SERVER_IP);
        }catch(IOException e){
            Log.info(e.getMessage());
            Log.info("Could not connect to host " + NetworkConstants.MAIN_SERVER_IP);
        }
    }

    private void registerClasses(){
        client.registerClass(BaseMessage.class);
        client.registerClass(ClientJoined.class);
        client.registerClass(ClientsInLobby.class);
        client.registerClass(ArrayList.class);
        client.registerClass(OpenLobby.class);
    }

    private void callback(BaseMessage message){
        if(message instanceof  ClientsInLobby){
            ipList.clear();
            ipList.addAll(((ClientsInLobby) message).ipFromClients);
        } else if (message instanceof SendOpenLobbies){
            this.openLobbies = ((SendOpenLobbies) message).getLobbies();
        }
    }

    public ArrayList<String> getIpList(){
        return ipList;
    }

    public void reConnect(String ip){
        try {
            client.connect(ip);
        } catch (IOException e) {
            Log.info(e.getMessage());
        }
    }

    public NetworkClientKryo getClient(){
        return client;
    }

    public ArrayList<Lobby> getOpenLobbies() {
        return openLobbies;
    }
}
