package com.example.kakerlakenpoker.player;

/**
 * Repräsentiert einen Spieler.
 * Diese Klasse verwendet die Klasse HandDeck
 */
public class Player {
    private String name;
    private HandDeck handDeck;

    public Player(String name, HandDeck handDeck) {
        this.name = name;
        this.handDeck = handDeck;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HandDeck getHandDeck() {
        return handDeck;
    }

    public void setHandDeck(HandDeck handDeck) {
        this.handDeck = handDeck;
    }
}
