package com.example.kakerlakenpoker.game;

import com.example.kakerlakenpoker.network.dto.GameUpdate;
import com.example.kakerlakenpoker.network.game.GameServer;
import com.example.kakerlakenpoker.player.Player;

public class StateListener {
    private GameServer server;

    public StateListener(GameServer server){
        this.server = server;
    }

    public void notifyPlayers(GameUpdate gameUpdate){
        server.broadcastMessage(gameUpdate);
    }
}
