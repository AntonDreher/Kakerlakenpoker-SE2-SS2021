package com.example.kakerlakenpoker.network.dto;

import java.util.ArrayList;

public class ClientsInLobby extends BaseMessage{
    public ArrayList<String> ipFromClients = null;

    public ClientsInLobby(){

    }

    public ClientsInLobby(ArrayList<String> ipFromClients){
        this.ipFromClients = ipFromClients;
    }

    @Override
    public String toString() {
        return "ClientsInLobby{" +
                "ipFromClients=" + ipFromClients +
                '}';
    }
}
