package com.example.kakerlakenpoker.network.dto.mainservertoclient;

import com.example.kakerlakenpoker.network.dto.BaseMessage;

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
