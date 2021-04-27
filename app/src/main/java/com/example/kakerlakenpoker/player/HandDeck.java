package com.example.kakerlakenpoker.player;
import com.example.kakerlakenpoker.card.Card;
import com.example.kakerlakenpoker.card.Type;

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

    /**
     * Entfernt eine Karte aus dem HandDeck
     * @param type Handdeck wird nach ENUM durchsucht
     */
    public void removeCard(Type type){
        Card c = findCard(type);
        if(c != null){
            deck.remove(c);
        }
    }

    /**
     * Durchsucht das Deck nach einer Karte des gesuchten Types
     * @param type eines der 8 ENUMS
     * @return Karte, wenn gefunden. Ansonsten null
     */
    public Card findCard(Type type){
        for(Card c: deck){
            if(c.getType().equals(type)){
                return c;
            }
        }
        return null;
    }

    /**
     * Methode, die die Anzahl der HandKarten zurück gibt
     * @return int, Anzahl der Handkarten
     */
    public int size(){
        return deck.size();
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }
}
