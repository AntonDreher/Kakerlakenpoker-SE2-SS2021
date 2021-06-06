package com.example.kakerlakenpoker.network.dto;

import java.util.ArrayList;
import java.util.List;

public class Lobby extends BaseMessage {
    private String name;
    private String HostIP;
    private int playerCount=1;
    private List<String> playersIpList = new ArrayList<>();

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

    public List<String> getPlayersIpList() {
        return playersIpList;
    }

    @Override
    public String toString() {
        return "Lobby{" +
                "name='" + name + '\'' +
                ", HostIP='" + HostIP + '\'' +
                ", playerCount=" + playerCount +
                ", playersIpList=" + playersIpList +
                '}';
    }
}
