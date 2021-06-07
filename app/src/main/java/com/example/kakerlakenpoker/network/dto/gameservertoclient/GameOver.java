package com.example.kakerlakenpoker.network.dto.gameservertoclient;


import com.example.kakerlakenpoker.player.Player;
import com.example.server.dto.BaseMessage;

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
