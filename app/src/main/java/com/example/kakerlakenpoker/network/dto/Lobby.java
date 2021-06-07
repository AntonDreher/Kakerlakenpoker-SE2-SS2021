package com.example.kakerlakenpoker.network.dto;

import java.util.ArrayList;
import java.util.List;

public class Lobby extends BaseMessage{
    private String name;
    private String hostIP;
    private List<String> playersIpList = new ArrayList<>();

    public Lobby(String name){
        this.name = name;
    }

    public void setHostIP(String hostIP){
        this.hostIP = hostIP;
    }


    public Lobby(){}
    public String getName() {
        return name;
    }

    public String getHostIP() {
        return hostIP;
    }

    public List<String> getPlayersIpList() {
        return playersIpList;
    }

    @Override
    public String toString() {
        return "Lobby{" +
                "name='" + name + '\'' +
                ", HostIP='" + hostIP + '\'' +
                ", playersIpList=" + playersIpList +
                '}';
    }
}
