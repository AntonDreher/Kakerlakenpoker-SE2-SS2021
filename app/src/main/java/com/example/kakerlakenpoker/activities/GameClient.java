package com.example.kakerlakenpoker.activities;


import android.app.Activity;

import com.esotericsoftware.minlog.Log;

import com.example.game.Game;
import com.example.server.dto.clienttomainserver.GetRandomNumber;
import com.example.server.network.dto.PlayerReady;
import com.example.server.RegisterClasses;
import com.example.server.dto.clienttomainserver.ClientName;
import com.example.server.dto.clienttomainserver.ExitLobby;
import com.example.server.dto.Lobby;
import com.example.server.network.kryo.NetworkClientKryo;
import com.example.server.network.kryo.RegisterHelper;

import java.io.IOException;
import java.util.ArrayList;


public class GameClient {
    private static GameClient instance;
    private NetworkClientKryo client;
    private ArrayList<Lobby> openLobbies = new ArrayList<>();
    private Lobby currentLobby;
    private Activity activity;
    private IpListAdapter listAdapter;
    private LobbyAdapter lobbyAdapter;
    private String userName;
    private Game game;
    private int valueForMinigame;

    private GameClient() {
    }

    public static GameClient getInstance() {
        if (instance == null) {
            instance = new GameClient();
        }
        return instance;
    }

    public void init(String ipToConnect) {
        try {
            client = new NetworkClientKryo();
            RegisterHelper.registerClasses(client.getClient().getKryo());
            client.getClient().addListener(new ClientListener(this));
            client.connect(ipToConnect);
            client.getClient().sendTCP(new ClientName(userName));
            client.getClient().sendTCP(new GetRandomNumber());

        } catch (IOException e) {
            Log.info(e.getMessage());
            Log.info("Could not connect to host " + ipToConnect);
        }
    }

    public void connect(String ip) {
        try {
            client.connect(ip);
        } catch (IOException e) {
            Log.info(e.getMessage());
        }
    }

    public NetworkClientKryo getClient() {
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

    public void setOpenLobbies(ArrayList<Lobby> openLobbies) {
        this.openLobbies = openLobbies;
        if (lobbyAdapter != null)
            this.lobbyAdapter.notifyAdapter();
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }


    public Lobby getCurrentLobby() {
        return currentLobby;
    }

    public IpListAdapter getListAdapter() {
        return listAdapter;
    }

    public void exitLobby() {
        this.getClient().sendMessage(new ExitLobby(currentLobby.getName()));
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void setLobbyAdapter(LobbyAdapter lobbyAdapter) {
        this.lobbyAdapter = lobbyAdapter;
    }

    public void connectToNewServer(String ip, ClientListener listener) {
        client.getClient().removeListener(listener);
        this.init(ip);
    }

    public int getValueForMinigame() {
        return valueForMinigame;
    }

    public void setValueForMinigame(int valueForMinigame) {
        this.valueForMinigame = valueForMinigame;
    }
}
