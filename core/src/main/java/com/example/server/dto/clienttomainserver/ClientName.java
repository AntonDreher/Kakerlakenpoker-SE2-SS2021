package com.example.server.dto.clienttomainserver;

import com.example.server.dto.BaseMessage;

public class ClientName extends BaseMessage {
    private String clientsName;

    public ClientName(){

    }

    public ClientName(String clientsName){
        this.clientsName = clientsName;
    }

    public String getClientsName() {
        return clientsName;
    }
}
