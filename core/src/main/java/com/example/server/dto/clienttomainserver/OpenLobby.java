package com.example.server.dto.clienttomainserver;

import com.example.server.dto.BaseMessage;
import com.example.server.dto.Lobby;

public class OpenLobby extends BaseMessage {
    private Lobby lobby;
    public OpenLobby(Lobby lobby){
        this.lobby = lobby;
    }
    public OpenLobby(){}

    public Lobby getLobby(){
        return lobby;
    }
}
