package com.example.server.network.dto.gameservertoclient;

import com.example.game.Decision;
import com.example.game.GameState;
import com.example.game.Turn;
import com.example.game.player.Player;
import com.example.server.dto.BaseMessage;

import java.util.List;

public class GameUpdate extends BaseMessage {
    private List<Player> players;
    private Player currentPlayer;
    private GameState state;
    private Turn turn;
    private Decision decision;

    public GameUpdate(List<Player> players, Player currentPlayer, GameState state, Turn turn,Decision decision){
        this.players = players;
        this.currentPlayer = currentPlayer;
        this.state=state;
        this.turn= turn;
        this.decision = decision;
    }
    public GameUpdate(){}

    public List<Player> getPlayers() {
        return players;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public GameState getState() {
        return state;
    }

    public Turn getTurn() {
        return turn;
    }

    public Decision getDecision(){
        return decision;
    }
}

