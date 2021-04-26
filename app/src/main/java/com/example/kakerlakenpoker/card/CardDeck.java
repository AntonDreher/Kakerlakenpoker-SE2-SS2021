package com.example.kakerlakenpoker.card;

import java.util.ArrayList;

/**
 * Deck Klasse die ein Deck mit allen 64 Karten repräsentiert.
 */
public class CardDeck {
    private final ArrayList<Card> deck = new ArrayList<>(64);

    public CardDeck() {
      createDeck();
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    /**
     * Methode die das Deck mit 64 Karten erstellt
     */
    private void createDeck() {
        for (Type t : Type.values()) {
            for (int i = 0; i < 8; i++) {
                deck.add(new Card(t));
            }
        }
    }
}

