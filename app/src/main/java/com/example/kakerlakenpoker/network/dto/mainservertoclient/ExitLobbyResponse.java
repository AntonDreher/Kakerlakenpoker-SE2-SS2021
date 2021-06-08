package com.example.kakerlakenpoker.network.dto.mainservertoclient;

import com.example.kakerlakenpoker.network.dto.BaseMessage;

public class ExitLobbyResponse extends BaseMessage {
    public String ipAddress = null;

    public ExitLobbyResponse() {

    }

    public ExitLobbyResponse(String lobby){
        this.ipAddress = lobby;
    }

    public String getIpAddress(){
        return ipAddress;
    }

    @Override
    public String toString(){
        return ipAddress;
    }
}
