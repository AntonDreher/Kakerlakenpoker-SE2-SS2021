package com.example.server.network.dto.gameservertoclient;


import com.example.game.Game;
import com.example.server.dto.BaseMessage;

public class InitGame extends BaseMessage {
    Game game;
    public InitGame(Game game){
        this.game = game;
    }
    public InitGame(){}

    public Game getGame(){
        return game;
    }
}
