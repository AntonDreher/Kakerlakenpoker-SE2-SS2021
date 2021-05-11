package com.example.kakerlakenpoker.game;

import com.example.kakerlakenpoker.card.GameDeck;
import com.example.kakerlakenpoker.player.CollectedDeck;
import com.example.kakerlakenpoker.player.HandDeck;
import com.example.kakerlakenpoker.player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Wenn sich Spieler in der Lobby befinden
 */
public class BuildGame {
    private ArrayList<Player> players = new ArrayList<>();
    private GameDeck gameDeck = new GameDeck(); //Liste mit allen 64 Karten


    /**
     * Wenn ein player dem Spiel hinzugefügt wurde
     * @param name Name, der in der Lobby eingetragen wurde
     */
    public void addServerPlayer(String name){
        players.add(new Player(name,new HandDeck(),new CollectedDeck()));
    }

    /**
     * Verteilt alle 64 Karten auf die Player
     */
    public void distributeCards(){
        Collections.shuffle(gameDeck.getDeck());
        for (int i = 0; i < players.size(); i++) {
            for (int j = 0; j < 16; j++) {
                players.get(i).getHandDeck().addCard(gameDeck.getDeck().get(0));
                gameDeck.getDeck().remove(0);
            }
        }
    }

    /**
     * Erzeugt ein neues Spiel
     * @return neues spiel mit 4 Spielen in denen 16 Karten verteil wurden
     */
    public Game buildGame(){
        if(players.size()<4){
            throw  new IllegalArgumentException ("Need more players");
        }
        else {
            distributeCards();
            return new Game(players);
        }
    }

    /**
     * Setter + Getter Methoden
     */
    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = (ArrayList<Player>) players;
    }

    public GameDeck getGameDeck() {
        return gameDeck;
    }

    public void setGameDeck(GameDeck gameDeck) {
        this.gameDeck = gameDeck;
    }
}