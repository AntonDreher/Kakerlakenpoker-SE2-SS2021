package com.example.kakerlakenpoker.network.game;

import com.esotericsoftware.minlog.Log;
import com.example.kakerlakenpoker.activities.PlayersInLobbyRecyclerViewAdapter;
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
    private PlayersInLobbyRecyclerViewAdapter adapter;
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
            client.registerCallback(this::callback);
            client.connect(NetworkConstants.MAIN_SERVER_IP);
            client.sendMessage(new ClientJoined(NetworkConstants.MAIN_SERVER_IP));
            Log.info(ip + " sent to " + NetworkConstants.MAIN_SERVER_IP);
            adapter = new PlayersInLobbyRecyclerViewAdapter(getIpList());
        }catch(IOException e){
            Log.info(e.getMessage());
            Log.info("Could not connect to host " + NetworkConstants.MAIN_SERVER_IP);
        }
    }

    private void callback(BaseMessage message){
        if(message instanceof  ClientsInLobby){
            ipList.clear();
            ipList.addAll(((ClientsInLobby) message).ipFromClients);
            Log.info(ipList.toString());
        } else if (message instanceof SendOpenLobbies){
            this.openLobbies = ((SendOpenLobbies) message).getLobbies();
        }
    }

    public ArrayList<String> getIpList(){
        return ipList;
    }

    public void connect(String ip){
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
