package com.example.kakerlakenpoker.network.dto;

public class ClientPlayersReady extends BaseMessage {
    public int playerCount = 0;

    public ClientPlayersReady(){

    }

    public ClientPlayersReady(int playerCount){
        this.playerCount = playerCount;
    }

    public int getPlayerCount() {
        return playerCount;
    }

}
