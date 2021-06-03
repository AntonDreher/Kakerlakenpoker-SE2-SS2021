package com.example.kakerlakenpoker.network.dto;

import com.example.kakerlakenpoker.player.Player;

public class PlayerReady extends BaseMessage {
    private Player player;
    public PlayerReady(){

    }

    public PlayerReady(Player player){
        this.player=player;
    }

    public Player getPlayer(){
        return player;
    }
}
