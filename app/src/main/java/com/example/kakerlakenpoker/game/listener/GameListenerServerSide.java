package com.example.kakerlakenpoker.game.listener;

import com.example.kakerlakenpoker.game.GameState;
import com.example.kakerlakenpoker.network.dto.gameservertoclient.GameOver;
import com.example.kakerlakenpoker.network.dto.gameservertoclient.GameUpdate;
import com.example.kakerlakenpoker.network.dto.gameservertoclient.InitGame;
import com.example.kakerlakenpoker.network.game.GameServer;

public class GameListenerServerSide implements GameListener {
    private GameServer server;

    public GameListenerServerSide(GameServer server){
        this.server = server;
    }

    @Override
    public void notify(GameUpdate gameUpdate, GameState previousState){
        if(gameUpdate.getState()==GameState.AWAITING_GO)server.broadcastMessage(new InitGame(gameUpdate));
        else if(gameUpdate.getState()==GameState.GAME_OVER)server.broadcastMessage(new GameOver(gameUpdate.getCurrentPlayer()));
        else server.broadcastMessage(gameUpdate);


    }
}
