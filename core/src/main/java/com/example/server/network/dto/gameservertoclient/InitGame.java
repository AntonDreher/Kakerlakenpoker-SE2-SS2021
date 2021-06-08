package com.example.server.network.dto.gameservertoclient;


import com.example.server.dto.BaseMessage;

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
