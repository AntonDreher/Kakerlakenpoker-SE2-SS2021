package com.example.game.player;

/**
 * Repräsentiert einen Spieler.
 * Diese Klasse verwendet die Klasse HandDeck
 */
public class Player {
    private PlayerState state;
    private String name;
    private int id;
    private HandDeck handDeck;
    private CollectedDeck collectedDeck;

    /**
     * Konstruktor für Klasse Player
     */
    public Player(){

    }
    public Player(int id,String name, HandDeck handDeck, CollectedDeck collectedDeck) {
        this.state = PlayerState.READY;
        this.name = name;
        this.id = id;
        this.handDeck = handDeck;
        this.collectedDeck = collectedDeck;
    }

    /**
     * Getter Methode für Name
     * @return name des Spielers
     */
    public int getId() {
        return id;
    }

    /**
     * Setter Methode für Name (falls benötigt)
     * @param id des Spielers
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter Methode für Hand Karten
     * @return HandDeck des Spielers
     */
    public HandDeck getHandDeck() {
        return handDeck;
    }

    /**
     * Setter Methode für Hand Karten (falls benötigt)
     * @param handDeck für Spieler
     */
    public void setHandDeck(HandDeck handDeck) {
        this.handDeck = handDeck;
    }

    /**
     * Getter Methode für Gesammelte Karten
     * @return Klasse mit den gesammelten Karten
     */
    public CollectedDeck getCollectedDeck() {
        return collectedDeck;
    }

    /**
     * Setter methode für Gesammelte Karten
     * @param collectedDeck für Spieler
     */
    public void setCollectedDeck(CollectedDeck collectedDeck) {
        this.collectedDeck = collectedDeck;
    }

    public PlayerState getState() {
        return state;
    }

    public String getName() {
        return name;
    }

    public void setState(PlayerState state) {
        this.state = state;
    }
}
