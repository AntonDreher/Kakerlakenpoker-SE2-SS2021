package com.example.server.network.dto.clienttogameserver;

import com.example.game.Turn;
import com.example.server.dto.BaseMessage;


public class MakeTurn extends BaseMessage {
    private Turn turn;

    public MakeTurn(){

    }

    public MakeTurn(Turn turn){
        this.turn = turn;
    }

    public Turn getTurn(){
        return this.turn;
    }
}
