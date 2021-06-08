package com.example.server.dto.mainservertoclient;
import com.example.server.dto.BaseMessage;

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
