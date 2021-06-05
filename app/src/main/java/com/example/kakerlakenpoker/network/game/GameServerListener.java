package com.example.kakerlakenpoker.network.game;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;
import com.example.kakerlakenpoker.network.dto.ClientJoinedRequest;
import com.example.kakerlakenpoker.network.dto.ClientJoinedResponse;

public class GameServerListener extends Listener {
    private final GameServer server;

    public GameServerListener(GameServer server) {
        this.server = server;
    }

    @Override
    public void received(Connection connection, Object object){

    }
}
