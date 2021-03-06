package com.example.server.dto.clienttomainserver;

import com.example.server.dto.BaseMessage;

public class ExitLobby extends BaseMessage {
    private String ipAddress;
    private String lobbyToExitName;

    public ExitLobby(){

    }

    public ExitLobby(String lobbyToExitName) {
        this.ipAddress = ipAddress;
        this.lobbyToExitName = lobbyToExitName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getLobbyToExitName() {
        return lobbyToExitName;
    }

    public void setLobbyToExitName(String lobbyToExitName) {
        this.lobbyToExitName = lobbyToExitName;
    }

    @Override
    public String toString() {
        return "ExitLobby{" +
                "ipAddress='" + ipAddress + '\'' +
                ", lobbyToExitName='" + lobbyToExitName + '\'' +
                '}';
    }
}
