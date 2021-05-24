package com.example.kakerlakenpoker.network.dto;


public class ClientJoined extends BaseMessage{
    public String ipAddress = null;

    public ClientJoined() {

    }

    public ClientJoined(String ipAddress){
        this.ipAddress = ipAddress;
    }

    public String getIpAddress(){
        return ipAddress;
    }

    @Override
    public String toString(){
        return ipAddress;
    }
}
