package com.example.kakerlakenpoker.game.listener;

import com.example.kakerlakenpoker.game.GameState;
import com.example.kakerlakenpoker.network.dto.GameUpdate;
import com.example.kakerlakenpoker.network.game.GameClient;

public class GameListenerClientSide implements GameListener {
    private GameClient gameClient;

    public GameListenerClientSide(GameClient client){
        gameClient = client;
    }
    @Override
    public void notify(GameUpdate gameUpdate, GameState previousState) {
        gameClient.getClient().sendMessage(gameUpdate);
    }
}
