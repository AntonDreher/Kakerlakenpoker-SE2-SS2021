package com.example.kakerlakenpoker.network.dto;

public class ClientJoinedResponse extends BaseMessage{
    public Lobby lobbyJoined = null;

    public ClientJoinedResponse() {

    }

    public ClientJoinedResponse(Lobby lobby){
        this.lobbyJoined = lobby;
    }

    public Lobby getLobbyJoined(){
        return lobbyJoined;
    }

    @Override
    public String toString(){
        return lobbyJoined.toString();
    }
}
