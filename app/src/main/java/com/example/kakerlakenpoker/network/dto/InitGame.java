package com.example.kakerlakenpoker.network.dto;

import com.example.kakerlakenpoker.game.Game;

public class InitGame extends BaseMessage {
    private GameUpdate gameUpdate;
    public InitGame(){

    }

    public InitGame(GameUpdate gameUpdate){
        this.gameUpdate=gameUpdate;
    }
    public GameUpdate getGameUpdate(){
        return gameUpdate;
    }
}
