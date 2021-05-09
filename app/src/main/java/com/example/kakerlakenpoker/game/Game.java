package com.example.kakerlakenpoker.game;

import com.example.kakerlakenpoker.card.GameDeck;
import com.example.kakerlakenpoker.player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
    private ArrayList<Player> players = new ArrayList<>();
    private GameDeck gameDeck; //Liste mit allen 64 Karten

    public Game(ArrayList<Player> players) {
        this.players = players;
        gameDeck = new GameDeck();
        Collections.shuffle(gameDeck.getDeck());
    }

    /**
     * Methode die alle 64 Karten auf 4 Spieler aufteilt.
     */
    public void kartenverteilen(){
        for (int i = 0; i < players.size(); i++) {
            for (int j = 0; j < 16; j++) {
                players.get(i).getHandDeck().addCard(gameDeck.getDeck().get(0));
                gameDeck.getDeck().remove(0);
            }
        }
    }

    /**
     * Spieler will Karte ausspielen und wählt Spieler aus
     */
    public void playCard(){

    }


    /**
     * Zeigt alle Karten der Spieler an (Collected Deck)
     */
    public void showCards(){

    }

    /**
     * Karte weitergeben oder behalten
     */
    public void challengeCard(){

    }

    /**
     * Prüft collected Deck ob Gewonnen oder Verloren
     */
    public void checkCollectedDeck(){

    }


}
