package com.example.kakerlakenpoker.network.dto;

import com.example.kakerlakenpoker.game.Turn;

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
