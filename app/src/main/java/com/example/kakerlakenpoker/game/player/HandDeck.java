package com.example.kakerlakenpoker.game.player;

import com.example.kakerlakenpoker.game.card.Card;
import com.example.kakerlakenpoker.game.card.CardDeck;

/**
 * Repräsentiert die Handkarten eines Spielers
 */

public class HandDeck extends CardDeck {

    public void displayCard(){
        // Updatet die TextView in GameView
        super.countAllCards();
            // Set TextView mit den gezählten werten.
    }

    //Die Methode wird aufgerufen, wenn ein Spieler eine Karte ausspielt.
    public void useCard(Card card){ // Die Methodenübergabe müsste noch den Player übergeben.
        removeCard(card);
        //Hier muss dann die ausgespielte Karte weitergegebn werden (Server-Client)
    }

    }

