package com.example.kakerlakenpoker.network.dto;

public class Lobby extends BaseMessage {
    private String name;
    private String HostIP;
    private int playerCount=1;

    public Lobby(String name, String HostIP){
        this.name = name;
        this.HostIP = HostIP;
    }

    public Lobby(){}

    public int getPlayerCount(){
        return playerCount;
    }

    public String getName() {
        return name;
    }

    public String getHostIP() {
        return HostIP;
    }
}
