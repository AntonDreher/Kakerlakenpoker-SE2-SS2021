package com.example.kakerlakenpoker.activities;

import com.example.game.GameState;
import com.example.game.listener.GameListener;
import com.example.server.network.dto.gameservertoclient.GameOver;
import com.example.server.network.dto.gameservertoclient.GameUpdate;
import com.example.server.network.dto.clienttogameserver.MakeDecision;
import com.example.server.network.dto.clienttogameserver.MakeTurn;


public class GameListenerClientSide implements GameListener {
    private GameClient gameClient;

    public GameListenerClientSide(GameClient client){
        gameClient = client;
    }
    @Override
    public void notify(GameUpdate gameUpdate, GameState previousState) {
        if(previousState==GameState.AWAITING_TURN && gameUpdate.getState()==GameState.AWAITING_DECISION)
        gameClient.getClient().sendMessage(new MakeTurn(gameUpdate.getTurn()));
        else if(previousState==GameState.AWAITING_DECISION && gameUpdate.getState()==GameState.AWAITING_TURN)
            gameClient.getClient().sendMessage(new MakeDecision(gameUpdate.getDecision()));
        else if(gameUpdate.getState() ==GameState.GAME_OVER)gameClient.getClient().sendMessage(new GameOver());
    }
}
