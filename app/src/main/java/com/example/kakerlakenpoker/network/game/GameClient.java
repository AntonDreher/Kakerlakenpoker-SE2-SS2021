package com.example.kakerlakenpoker.network.game;


import com.esotericsoftware.minlog.Log;
import com.example.kakerlakenpoker.IpListAdapter;
import com.example.kakerlakenpoker.activities.ShowPlayersInLobbyActivity;
import com.example.kakerlakenpoker.network.dto.BaseMessage;
import com.example.kakerlakenpoker.network.dto.ClientJoinedResponse;
import com.example.kakerlakenpoker.network.dto.Lobby;
import com.example.kakerlakenpoker.network.dto.mainservertoclient.SendOpenLobbies;
import com.example.kakerlakenpoker.network.kryo.NetworkClientKryo;
import com.example.kakerlakenpoker.network.kryo.NetworkConstants;
import com.example.kakerlakenpoker.network.kryo.RegisterHelper;

import java.io.IOException;
import java.util.ArrayList;


public class GameClient {
    private static GameClient instance;
    private NetworkClientKryo client;
    private ArrayList<Lobby> openLobbies = new ArrayList<>();
    private Lobby currentLobby;
    private IpListAdapter listAdapter;

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
            Log.info(ip + " sent to " + NetworkConstants.MAIN_SERVER_IP);
        }catch(IOException e){
            Log.info(e.getMessage());
            Log.info("Could not connect to host " + NetworkConstants.MAIN_SERVER_IP);
        }
    }

    private void callback(BaseMessage message){
        if(message instanceof ClientJoinedResponse){
            this.setCurrentLobby(((ClientJoinedResponse) message).getLobbyJoined());

            ((ShowPlayersInLobbyActivity) listAdapter.getContext()).runOnUiThread(
                    () -> {
                        for(int i = 0; i<listAdapter.getCount(); i++){
                            listAdapter.remove(listAdapter.getItem(i));
                        }
                        listAdapter.addAll(this.getCurrentLobby().getPlayersIpList());
                        listAdapter.notifyDataSetChanged();
                    });
            Log.info(this.getCurrentLobby().getPlayersIpList().toString());
        } else if (message instanceof SendOpenLobbies){
            this.openLobbies = ((SendOpenLobbies) message).getLobbies();
        }
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

    public void setListAdapter(IpListAdapter listAdapter) {
        this.listAdapter = listAdapter;
    }

    public void setCurrentLobby(Lobby lobby) {
        currentLobby = lobby;
    }

    public Lobby getCurrentLobby(){
        return currentLobby;
    }
}
