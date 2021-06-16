package com.example.game;


import com.esotericsoftware.minlog.Log;
import com.example.game.card.Card;
import com.example.game.card.Type;
import com.example.game.listener.GameListener;
import com.example.game.listener.StateListener;
import com.example.server.network.dto.clienttogameserver.HandOver;
import com.example.server.network.dto.gameservertoclient.GameUpdate;
import com.example.game.player.Player;
import com.example.game.player.PlayerState;
import com.example.server.network.game.GameData;

import java.lang.management.PlatformLoggingMXBean;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.plaf.nimbus.State;

public class Game {
    private List<Player> players;
    private Player currentPlayer;
    private GameState currentState;
    private Turn turn;
    private Decision decision;
    private GameData gameData;
    private GameListener listener;
    private StateListener stateListener;

    Card playcard;


    public Game() {

    }

    //eventuell besser einfach einen setter dafür verwenden!
    public Game(List<Player> players) {
        this.players = players;
        Random random = new Random();
        int randomNumber = random.nextInt(players.size());
        currentPlayer = players.get(randomNumber);
        changeState(GameState.AWAITING_GO);
    }

    public void changeState(GameState state) {
        GameUpdate gameUpdate = new GameUpdate(players, currentPlayer, state, turn, decision);
        if(listener!=null)listener.notify(gameUpdate, this.currentState);
        this.currentState = state;
        if(stateListener!=null)stateListener.stateChanged();
        Log.info("GameState changed to " + state.name() + " and current players name is: " + currentPlayer.getId());
        if(turn != null) Log.info("Current turn contains: Card: "+turn.getSelectedCard().getType()+", Type: "+ turn.getSelectedType()+", enemy: "+turn.getSelectedEnemy().getId()+" name of currentplayer:" +currentPlayer.getName());

    }

    public void makeTurn(Player player, Turn turn) {
        Log.info("making a turn");
        if(turn==null||turn.getSelectedCard()==null||turn.getSelectedType()==null||turn.getSelectedEnemy()==null||currentPlayer.getHandDeck().findCard(turn.getSelectedType().toString())==null)return;
        if (currentState == GameState.AWAITING_TURN && player.getId()==currentPlayer.getId()) {
            currentPlayer.getHandDeck().removeCard(turn.getSelectedCard());
            this.turn = turn;
            currentPlayer.setState(PlayerState.PLAYED);
            changeState(GameState.AWAITING_DECISION);

        }
        Log.info("making a turn");
    }

    public void makeDecision(Player player, Decision decision) {
        if(decision==null)return;
        if (currentState == GameState.AWAITING_DECISION && player.getId()==(turn.getSelectedEnemy().getId())) {
            this.decision=decision;
            if ((turn.getSelectedCard().getType() != turn.getSelectedType() && decision == Decision.TRUTH) ||
                    (turn.getSelectedCard().getType() == turn.getSelectedType() && decision == Decision.LIE)) {
                currentPlayer = getPlayer(turn.getSelectedEnemy());
            }
            currentPlayer.getCollectedDeck().addCard(turn.getSelectedCard());
            if(currentPlayer.getCollectedDeck().hasLost())changeState(GameState.GAME_OVER);
            else {
                resetPlayerStatus();
                changeState(GameState.AWAITING_TURN);
            }
        } else  Log.info("not permitted to make a decision player: "+player.getId());

    }

    public void startGame(){
        changeState(GameState.AWAITING_TURN);
    }

    public void handOver(Player player, HandOver handOver){
        if(currentState!=GameState.AWAITING_DECISION || handOver.getDecision()==null||handOver.getEnemy()==null||handOver.getEnemy().getState()==PlayerState.PLAYED||getPlayer(handOver.getEnemy())==null)return;
        currentPlayer.getHandDeck().removeCard(turn.getSelectedCard());
        decision=handOver.getDecision();
        currentPlayer = getPlayer(player);
        currentPlayer.getHandDeck().addCard(turn.getSelectedCard());
        currentPlayer.setState(PlayerState.PLAYED);
        decision=handOver.getDecision();
        turn.setSelectedEnemy(getPlayer(handOver.getEnemy()));
        changeState(GameState.AWAITING_DECISION);
    }

    public Player getPlayer(Player player){
        for(Player player1: players){
            if(player.getId()==player1.getId())return player1;
        }

        return null;
    }

    public void reject(Player player) {
        currentPlayer = player;
        currentState = GameState.AWAITING_TURN;
    }

    public Type getSelectedType() {
        return turn.getSelectedType();
    }

    /**
     * gibt Aktuell Spielenden Spieler zurück
     *
     * @return aktueller Spieler (String)
     */
    public Player getCurrentPlayer() {
        return currentPlayer;

    }

    public void gameOver(Player player){
        currentPlayer = player;
        this.currentState = GameState.GAME_OVER;
        if(stateListener!=null)stateListener.stateChanged();

    }

    public boolean checkRoundOver() {
        int count = 0;
        for (Player p : players) {
            if (p.getState().equals(PlayerState.PLAYED)) {
                count++;
            }
        }
        if (count == 3) {
            currentPlayer.getCollectedDeck().addCard(turn.getSelectedCard());
            return true;
        }
        return false;
    }

    public List<Player> getAvailablePlayer() {
        List<Player> available = new ArrayList<>();
        for (Player p : players) {
            if (p.getState().equals(PlayerState.READY)) {
                available.add(p);
            }
        }
        return available;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void resetPlayerStatus() {
        for (Player p : players) {
            p.setState(PlayerState.READY);
        }
    }

    /**
     * Spieler wählt einen anderen Spieler aus, dem er eine Karte anzudrehen versucht
     *
     * @param otherPlayer Name des gegnerischen Spielers
     * @param card        Karte, die der Spieler außspielen möchte
     */
    public void playCard(Player otherPlayer, Card card) {
        otherPlayer.getHandDeck().useCard(card);
    }


    /**
     * Zeigt alle Karten der Spieler an (Collected Deck)
     */
    public void showCards() {
        //noch zu implementieren
    }

    /**
     * Karte weitergeben oder behalten
     */
    public void challengeCard(Player otherplayer, String playcard, String guess) {

        // Checkt die Funktion!
        Log.info("", "" + playcard + " " + guess);

    }

    //Schickt den Spielern eine Nachrichtig, ob sie gewonnen haben oder nicht.


    /**
     * Prüft collected Deck ob Gewonnen oder Verloren
     */
    public void checkCollectedDeck() {
        for (int i = 0; i < players.size(); i++) {
            players.get(i).getCollectedDeck().hasLost();
        }
    }

    public Turn getTurn() {
        return turn;
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

    public List<Player> getOtherPlayers() {
        // this.players.remove(); entferne meinen Name ausder Liste und gibt mir nur die Gegner!
        return players;
    }

    public Player getPlayerbyName(String name) {
        for (int i = 0; i < this.players.size(); i++) {
            if (players.get(i).getId()==Integer.parseInt(name)) {
                return players.get(i);
            }
        }
        return null;
    }

    public void setGameListener(GameListener listener) {
        this.listener = listener;
        changeState(currentState);
    }

    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }

    public void updateGame(GameUpdate gameUpdate) {
        updatePlayers(gameUpdate.getPlayers());
        this.currentPlayer = gameUpdate.getCurrentPlayer();
        this.turn = gameUpdate.getTurn();
        this.currentState =gameUpdate.getState();
        if(stateListener!=null)stateListener.stateChanged();
    }

    public void updatePlayers(List<Player> players){
        for(Player player: this.players){
            for(Player newPlayer: players){
                if(player.getId()== newPlayer.getId()){
                    player.setHandDeck(newPlayer.getHandDeck());
                    player.getHandDeck().countAllCards();
                    player.setCollectedDeck(newPlayer.getCollectedDeck());
                    player.setState(newPlayer.getState());
                }
            }
        }
    }

    public GameState getCurrentState() {
        return currentState;
    }

    public void setStateListener(StateListener listener){
        this.stateListener = listener;
    }

    public void setCurrentState(GameState currentState) {
        this.currentState = currentState;
    }
}
