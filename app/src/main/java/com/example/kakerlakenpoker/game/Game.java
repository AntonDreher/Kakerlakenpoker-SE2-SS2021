package com.example.kakerlakenpoker.game;

import com.example.kakerlakenpoker.card.Card;
import com.example.kakerlakenpoker.card.Type;
import com.example.kakerlakenpoker.player.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private List<Player> players;
    private Player currentPlayer;
    private GameState state;
    private Turn turn;

    public Game(List<Player> players) {
        this.players = players;
        Random random = new Random();
        int randomNumber = random.nextInt(players.size());
        currentPlayer = players.get(randomNumber);
        state = GameState.AWAITING_TURN;
    }

    public void makeTurn(Player player, Turn turn){
        if(state==GameState.AWAITING_TURN && player==currentPlayer){
            this.turn = turn;
            System.out.println("This is the type of the card: "+turn.getSelectedCard().getType());
            state = GameState.AWAITING_DECISION;
        }
    }

    public void makeDecision(Player player, Decision decision){
        if(state==GameState.AWAITING_DECISION&& player == turn.getSelectedEnemy()){
            if(turn.getSelectedCard().getType()==turn.getSelectedType() && decision == Decision.TRUTH ||
                    turn.getSelectedCard().getType()!=turn.getSelectedType() && decision == Decision.LIE){
                currentPlayer.getCollectedDeck().addCard(turn.getSelectedCard());
                currentPlayer.getHandDeck().removeCard(turn.getSelectedCard());
            } else{
                currentPlayer = turn.getSelectedEnemy();
                currentPlayer.getCollectedDeck().addCard(turn.getSelectedCard());
            }
            state = GameState.AWAITING_TURN;
        }

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
    public void challengeCard(){
    //noch zu implementieren
    }

    /**
     * Prüft collected Deck ob Gewonnen oder Verloren
     */
    public void checkCollectedDeck(){
    //noch zu implementieren
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
}
