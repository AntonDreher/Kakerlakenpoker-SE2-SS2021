package com.example.kakerlakenpoker.game.listener;

import com.example.kakerlakenpoker.game.GameState;
import com.example.kakerlakenpoker.network.dto.gameservertoclient.GameOver;
import com.example.kakerlakenpoker.network.dto.gameservertoclient.GameUpdate;
import com.example.kakerlakenpoker.network.dto.clienttogameserver.MakeDecision;
import com.example.kakerlakenpoker.network.dto.clienttogameserver.MakeTurn;
import com.example.kakerlakenpoker.network.game.GameClient;

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
