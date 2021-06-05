package com.example.kakerlakenpoker.network.dto;

import com.example.kakerlakenpoker.player.Player;

public class GameOver extends BaseMessage {
    private Player loser;
    public GameOver(){

    }

    public GameOver(Player loser){
        this.loser = loser;
    }

    public Player getLoser() {
        return loser;
    }
}
