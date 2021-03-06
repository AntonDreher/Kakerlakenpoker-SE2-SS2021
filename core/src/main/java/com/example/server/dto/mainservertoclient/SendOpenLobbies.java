package com.example.server.dto.mainservertoclient;

import com.example.server.dto.BaseMessage;
import com.example.server.dto.Lobby;

import java.util.ArrayList;

public class SendOpenLobbies extends BaseMessage {
    public SendOpenLobbies(){}
    private ArrayList<Lobby> lobbies;

    public SendOpenLobbies(ArrayList<Lobby> lobbies){
        this.lobbies = lobbies;
    }

    public ArrayList<Lobby> getLobbies() {
        return lobbies;
    }
}
