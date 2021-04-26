package com.example.kakerlakenpoker.player;
import com.example.kakerlakenpoker.card.Card;
import java.util.ArrayList;

/**
 * Repräsentiert die Handkarten eines Spielers
 */

public class HandDeck {
    private final ArrayList<Card> deck = new ArrayList<>();

    /**
     * Fügt dem Handdeck eine Karte hinzu
     * @param card Kommt aus dem CardDeck oder von einem anderen Spieler
     */
    public void addCard(Card card){
        deck.add(card);
    }
}
