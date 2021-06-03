package com.example.kakerlakenpoker.network.dto;

import com.example.kakerlakenpoker.game.Decision;
import com.example.kakerlakenpoker.game.GameState;
import com.example.kakerlakenpoker.game.Turn;
import com.example.kakerlakenpoker.player.Player;

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

