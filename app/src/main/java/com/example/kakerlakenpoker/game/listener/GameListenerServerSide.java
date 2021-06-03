package com.example.kakerlakenpoker.game.listener;

import com.example.kakerlakenpoker.game.GameState;
import com.example.kakerlakenpoker.network.dto.GameUpdate;
import com.example.kakerlakenpoker.network.game.GameServer;
import com.example.kakerlakenpoker.player.Player;

public class GameListenerServerSide implements GameListener {
    private GameServer server;

    public GameListenerServerSide(GameServer server){
        this.server = server;
    }

    @Override
    public void notify(GameUpdate gameUpdate, GameState previousState){
        server.broadcastMessage(gameUpdate);
    }
}
