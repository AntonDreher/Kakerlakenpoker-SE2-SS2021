package com.example.kakerlakenpoker.network.game;

import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;
import com.example.kakerlakenpoker.game.Game;
import com.example.kakerlakenpoker.network.dto.BaseMessage;
import com.example.kakerlakenpoker.network.kryo.NetworkServerKryo;
import com.example.kakerlakenpoker.network.kryo.RegisterHelper;

import java.io.IOException;

public class GameServer {
    private static GameServer instance;
    private NetworkServerKryo server;
    private boolean waitingForClients = true;
    private Game game;

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
            server.getServer().addListener(new ServerListener(this));
            server.start();
            Log.info("Server started successful");
        }catch(IOException e){
            Log.info("Server couldn't start");
        }
    }

    public void broadcastMessage(BaseMessage message) {
        server.broadcastMessage(message);
    }

    public boolean isWaitingForClients(){
        return waitingForClients;
    }


    public Server getServer() {
        return server.getServer();
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
