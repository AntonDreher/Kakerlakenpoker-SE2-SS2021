package com.example.kakerlakenpoker.network.dto.clienttomainserver;

import com.example.kakerlakenpoker.network.dto.BaseMessage;
import com.example.kakerlakenpoker.network.dto.Lobby;

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
