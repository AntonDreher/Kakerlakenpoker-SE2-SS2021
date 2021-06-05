package com.example.kakerlakenpoker.network.game;


import com.esotericsoftware.minlog.Log;

import com.example.kakerlakenpoker.IpListAdapter;
import com.example.kakerlakenpoker.game.Game;
import com.example.kakerlakenpoker.network.dto.Lobby;
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
    private Game game;
    private String userName;


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
            client.getClient().addListener(new ClientListener(this));
            client.connect(NetworkConstants.MAIN_SERVER_IP);
       //TODO     client.sendMessage(new ClientJoined(NetworkConstants.MAIN_SERVER_IP));
       //     client.sendMessage(new PlayerReady());
            Log.info(ip + " sent to " + NetworkConstants.MAIN_SERVER_IP);
        }catch(IOException e){
            Log.info(e.getMessage());
            Log.info("Could not connect to host " + NetworkConstants.MAIN_SERVER_IP);
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

    public void setCurrentLobby(Lobby lobby){
            currentLobby = lobby;
        }
    public void setOpenLobbies(ArrayList<Lobby> openLobbies) {
        this.openLobbies = openLobbies;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game){
        this.game = game;
    }

    public Lobby getCurrentLobby(){
        return currentLobby;
    }

    public IpListAdapter getListAdapter() {
        return listAdapter;
    }
}
