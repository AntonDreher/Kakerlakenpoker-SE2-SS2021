package com.example.kakerlakenpoker.game;

import android.util.Log;

import com.example.kakerlakenpoker.PlayerIngameMainActivity;
import com.example.kakerlakenpoker.card.Card;
import com.example.kakerlakenpoker.player.Player;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> players;
    Card playcard;
    PlayerIngameMainActivity playerwindow;

    public Game(){

    }
    //eventuell besser einfach einen setter dafür verwenden!
    public Game(List<Player> players) {
        this.players = players;

    }

    /**
     * gibt Aktuell Spielenden Spieler zurück
     * @return aktueller Spieler (String)
     */
    public Player getCurrentPlayer(){
        //noch zu implementieren
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
    //noch zu implementieren
    }

    /**
     * Karte weitergeben oder behalten
     */
    public void challengeCard( Player otherplayer, String playcard, String guess){

        // Checkt die Funktion!
        Log.e("","" + playcard + " " + guess);

    }
    //Schickt den Spielern eine Nachrichtig, ob sie gewonnen haben oder nicht.
    public void sendPlayerResult (String message){
        playerwindow.setTextforResult(message);
    }

    /**
     * Prüft collected Deck ob Gewonnen oder Verloren
     */
    public void checkCollectedDeck(){
        while(true){
            for(int i = 0; i< players.size(); i++){
                players.get(i).getCollectedDeck().lostGame();
            }
    }
    }

    /**
     * Getter + Setter
     */

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Player> getOtherPlayers(){
       // this.players.remove(); entferne meinen Name ausder Liste und gibt mir nur die Gegner!
        return players;}

     public Player getPlayerbyName(String name){
         for(int i = 0; i<this.players.size(); i++){
             if(players.get(i).getName().contains(name)){
                 return players.get(i);
             }
         }
        return null;
     }


}
