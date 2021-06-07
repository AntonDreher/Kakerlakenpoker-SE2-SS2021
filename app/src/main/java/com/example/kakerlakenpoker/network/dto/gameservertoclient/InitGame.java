package com.example.kakerlakenpoker.network.dto.gameservertoclient;

import com.example.kakerlakenpoker.network.dto.BaseMessage;
import com.example.kakerlakenpoker.network.dto.gameservertoclient.GameUpdate;

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
