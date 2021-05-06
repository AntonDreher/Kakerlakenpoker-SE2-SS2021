package com.example.kakerlakenpoker.player;

/**
 * Repräsentiert einen Spieler.
 * Diese Klasse verwendet die Klasse HandDeck
 */
public class Player {
    private String name;
    private HandDeck handDeck;
    private CollectedDeck collectedDeck;

    /**
     * Konstruktor für Klasse Player
     * @param name repräsentiert den Spieler
     * @param handDeck repräsentieren seine Handkarten
     */
    public Player(String name, HandDeck handDeck) {
        this.name = name;
        this.handDeck = handDeck;
    }

    /**
     * Getter Methode für Name
     * @return name des Spielers
     */
    public String getName() {
        return name;
    }

    /**
     * Setter Methode für Name (falls benötigt)
     * @param name des Spielers
     */
    public void setName(String name) {
        this.name = name;
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
}
