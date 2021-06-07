package com.example.kakerlakenpoker.network.dto.clienttomainserver;

import com.example.kakerlakenpoker.network.dto.BaseMessage;

public class GameServerReadyToConnect extends BaseMessage {
    public String ipAddressToConnect;

    public GameServerReadyToConnect(){

    }
    public GameServerReadyToConnect(String ipAddressToConnect) {
        this.ipAddressToConnect = ipAddressToConnect;
    }

    public String getIpAddressToConnect() {
        return ipAddressToConnect;
    }

    public void setIpAddressToConnect(String ipAddressToConnect) {
        this.ipAddressToConnect = ipAddressToConnect;
    }
}
