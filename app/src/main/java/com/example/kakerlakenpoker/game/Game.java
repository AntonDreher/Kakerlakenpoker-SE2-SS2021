package com.example.kakerlakenpoker.game;

import android.util.Log;

import com.example.kakerlakenpoker.PlayerIngameMainActivity;
import com.example.kakerlakenpoker.card.Card;
import com.example.kakerlakenpoker.card.Type;
import com.example.kakerlakenpoker.player.Player;
import com.example.kakerlakenpoker.player.PlayerState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private List<Player> players;
    private Player currentPlayer;
    private GameState state;
    private Turn turn;

    Card playcard;
    PlayerIngameMainActivity playerwindow;

    public Game(){


    }
    //eventuell besser einfach einen setter dafür verwenden!
    public Game(List<Player> players) {
        this.players = players;
        Random random = new Random();
        int randomNumber = random.nextInt(players.size());
        currentPlayer = players.get(0);
        state = GameState.AWAITING_TURN;
    }

    public void makeTurn(Player player, Turn turn){
        if(state==GameState.AWAITING_TURN && player==currentPlayer){
            this.turn = turn;
            state = GameState.AWAITING_DECISION;
            //currentPlayer.getHandDeck().removeCard(turn.getSelectedCard());
            currentPlayer.setState(PlayerState.PLAYED);
        }
    }

    public void makeDecision(Player player, Decision decision){
        if(state==GameState.AWAITING_DECISION&& player == turn.getSelectedEnemy()){
            if(turn.getSelectedCard().getType()==turn.getSelectedType() && decision == Decision.TRUTH ||
                    turn.getSelectedCard().getType()!=turn.getSelectedType() && decision == Decision.LIE){
                currentPlayer.getCollectedDeck().addCard(turn.getSelectedCard());

            } else{
                currentPlayer = turn.getSelectedEnemy();
                currentPlayer.getCollectedDeck().addCard(turn.getSelectedCard());
            }
            state = GameState.AWAITING_TURN;
        }

    }

    public void reject(Player player){
        currentPlayer = player;
        state = GameState.AWAITING_TURN;
    }

    public Type getSelectedType(){
        return turn.getSelectedType();
    }

    /**
     * gibt Aktuell Spielenden Spieler zurück
     * @return aktueller Spieler (String)
     */
    public Player getCurrentPlayer(){
        return currentPlayer;

    }

    public boolean checkRoundOver(){
        int count = 0;
        for (Player p: players) {
            if (p.getState().equals(PlayerState.PLAYED)){
                count++;
            }
        }
        if(count == 3){
            currentPlayer.getCollectedDeck().addCard(turn.getSelectedCard());
            return true;
        }
        return false;
    }

    public List<Player> getAvailablePlayer(){
        List<Player> available = new ArrayList<>();
       for(Player p: players){
           if(p.getState().equals(PlayerState.READY)){
               available.add(p);
           }
       }
       return available;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void resetPlayerStatus(){
        for(Player p:players){
            p.setState(PlayerState.READY);
        }
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
