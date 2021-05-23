package com.example.kakerlakenpoker.network.dto;

import java.util.List;

public class ClientsInLobby extends BaseMessage {
    public List<String> ipFromClients;

    public ClientsInLobby(){

    }

    public ClientsInLobby(List<String> ipFromClients){
        this.ipFromClients = ipFromClients;
    }

    public List<String> getIpFromClients() {
        return ipFromClients;
    }


}
