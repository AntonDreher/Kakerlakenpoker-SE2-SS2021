package com.example.kakerlakenpoker.network.game;


import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;
import com.example.kakerlakenpoker.network.dto.BaseMessage;
import com.example.kakerlakenpoker.network.dto.ClientJoinedRequest;
import com.example.kakerlakenpoker.network.dto.ClientJoinedResponse;
import com.example.kakerlakenpoker.network.kryo.NetworkServerKryo;
import com.example.kakerlakenpoker.network.kryo.RegisterHelper;

import java.io.IOException;

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
            this.getServer().addListener(new GameServerListener(this));
            server.start();
            Log.info("Server started successful");
        }catch(IOException e){
            Log.info("Server couldn't start");
        }
    }

    public boolean isWaitingForClients(){
        return waitingForClients;
    }

    public Server getServer(){
        return server.getServer();
    }
}
