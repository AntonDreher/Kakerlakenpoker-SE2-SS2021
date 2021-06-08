package com.example.server.dto;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Lobby extends BaseMessage{
    private String name;
    private String hostIP;
    private HashMap<String, String> playersIp = new HashMap<>();

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

    public HashMap<String, String> getPlayersIpList() {
        return playersIp;
    }

    @Override
    public String toString() {
        return "Lobby{" +
                "name='" + name + '\'' +
                ", HostIP='" + hostIP + '\'' +
                ", playersIpList=" + playersIp +
                '}';
    }
}
