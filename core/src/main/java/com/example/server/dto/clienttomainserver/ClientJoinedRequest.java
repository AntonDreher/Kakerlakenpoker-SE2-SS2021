package com.example.server.dto.clienttomainserver;

import com.example.server.dto.BaseMessage;

public class ClientJoinedRequest extends BaseMessage {
    public String ipAddress = null;
    public String lobbyName = null; //TODO must be unique

    public ClientJoinedRequest() {

    }

    public ClientJoinedRequest(String lobbyName, String ipAddress) {
        this.ipAddress = ipAddress;
        this.lobbyName = lobbyName;
    }

    public String getIpAddress(){
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getLobbyName() {
        return lobbyName;
    }

    public void setLobbyName(String lobbyName) {
        this.lobbyName = lobbyName;
    }

    @Override
    public String toString(){
        return ipAddress;
    }
}
