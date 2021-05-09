package com.example.kakerlakenpoker.game;

import com.example.kakerlakenpoker.card.Card;
import com.example.kakerlakenpoker.player.Player;
import java.util.ArrayList;

public class Game {
    private ArrayList<Player> players;


    public Game(ArrayList<Player> players) {
        this.players = players;

    }

    /**
     * gibt Aktuell Spielenden Spieler zurück
     * @return aktueller Spieler (String)
     */
    public String getCurrentPlayer(){
        return null;
    }

    /**
     * Spieler wählt einen anderen Spieler aus, dem er eine Karte anzudrehen versucht
     * @param  otherPlayer  Name des gegnerischen Spielers
     * @param card Karte, die der Spieler außspielen möchte
     */
    public void playCard(Player otherPlayer, Card card){
        otherPlayer.getHandDeck().useCard(card);
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

    /**
     * Getter + Setter
     */

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
}
