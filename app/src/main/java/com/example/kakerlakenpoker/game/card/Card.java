package com.example.kakerlakenpoker.game.card;

/**
 * Card Klasse repräsentiert eine Karte im Spiel.
 * Parameter ist ein ENUM der Klasse Type.
 */
public class Card {
    private Type type;

    public Card(){}

    public Card(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
