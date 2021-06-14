package com.example.game;

import com.example.game.card.GameDeck;
import com.example.game.player.CollectedDeck;
import com.example.game.player.HandDeck;
import com.example.game.player.Player;

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
     *
     * @param name Name, der in der Lobby eingetragen wurde
     */
    public void addServerPlayer(int name) {
        players.add(new Player(name,null, new HandDeck(), new CollectedDeck()));
    }

    /**
     * Verteilt alle 64 Karten auf die Player
     */
    public void distributeCards() {
        Collections.shuffle(gameDeck.getDeck());
        for (int i = 0; i < players.size(); i++) {
            HandDeck deck = new HandDeck();
            for (int j = 0; j < 16; j++) {
                deck.addCard(gameDeck.getDeck().get(0));
                //players.get(i).getHandDeck().addCard(gameDeck.getDeck().get(0));
                gameDeck.getDeck().remove(0);
            }
            players.get(i).setHandDeck(deck);
            players.get(i).setCollectedDeck(new CollectedDeck());
        }
    }

    /**
     * Erzeugt ein neues Spiel
     *
     * @return neues spiel mit 4 Spielen in denen 16 Karten verteil wurden
     */
    public Game buildGame() {
        if (players.size() < GameConstants.NEEDED_PLAYERS_TO_PLAY) {
            throw new IllegalArgumentException("Need more players");
        } else {
            distributeCards();
            return new Game(players);
        }
    }
    // Routine, die den Text von den View immer aktualisiert.
    public void updateCurrentCount(Player player){
        player.getHandDeck().displayCard();
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




}
