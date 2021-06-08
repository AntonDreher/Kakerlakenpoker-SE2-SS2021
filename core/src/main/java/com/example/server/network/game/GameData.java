package com.example.server.network.game;

import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;
import com.example.game.Game;

import com.example.server.network.kryo.NetworkServerKryo;
import com.example.server.network.kryo.RegisterHelper;
import com.example.server.dto.BaseMessage;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.HashSet;

public class GameData {
    private boolean waitingForClients = true;
    private HashSet<String> users;
    private HashMap<String, String> ipUserMapping;
    private Game game;

    public GameData(Game game){
        this.game = game;
        users = new HashSet<>();
    }

    public boolean isWaitingForClients(){
        return waitingForClients;
    }


    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public HashSet<String> getUsers() {
        return users;
    }

    public void setUsers(HashSet<String> users) {
        this.users = users;
    }

    public HashMap<String, String> getIpUserMapping() {
        return ipUserMapping;
    }

    public void setIpUserMapping(HashMap<String, String> ipUserMapping) {
        this.ipUserMapping = ipUserMapping;
    }
}
