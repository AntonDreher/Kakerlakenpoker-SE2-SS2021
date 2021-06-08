package com.example.server.dto.mainservertoclient;

import com.example.server.dto.BaseMessage;


public class ClientsToJoinGameServer extends BaseMessage {
    public String ipToConnect;

    public ClientsToJoinGameServer(){

    }

    public ClientsToJoinGameServer(String ipToConnect) {
        this.ipToConnect = ipToConnect;
    }

    public String getIpToConnect() {
        return ipToConnect;
    }

    public void setIpToConnect(String ipToConnect) {
        this.ipToConnect = ipToConnect;
    }
}
