package com.example.kakerlakenpoker.game.card;


/**
 * Deck Klasse die ein Deck mit allen 64 Karten repräsentiert.
 */
public class GameDeck extends CardDeck {
    boolean createdDeck = false;

    public GameDeck() {
        createDeck();
    }

    /**
     * Methode die das Deck mit 64 Karten erstellt
     */
    private void createDeck() {
        for (Type t : Type.values()) {
            for (int i = 0; i < 8; i++) {
                addCard(new Card(t));
            }
        }
        createdDeck = true;
    }

    /**
     * Checkt in der IF, ob GameDeck erstellt wurde, wenn Ja gibts eine Exception
     * @param card ENUM aus Klasse Card
     */
    @Override
    public void addCard(Card card) {
        if(createdDeck){
            throw new IllegalArgumentException("Cannot add");
        }
        else{
            super.addCard(card);
        }
    }
}

