package com.example.server.dto;

import java.util.HashMap;

public class Lobby extends BaseMessage{
    private String name;
    private String hostId;
    private HashMap<String, String> playersIp = new HashMap<>();

    public Lobby(String name){
        this.name = name;
    }

    public void setHostId(String hostId){
        this.hostId = hostId;
    }


    public Lobby(){}
    public String getName() {
        return name;
    }

    public String getHostId() {
        return hostId;
    }

    public HashMap<String, String> getPlayersIpList() {
        return playersIp;
    }

    @Override
    public String toString() {
        return "Lobby{" +
                "name='" + name + '\'' +
                ", HostIP='" + hostId + '\'' +
                ", playersIpList=" + playersIp +
                '}';
    }
}
