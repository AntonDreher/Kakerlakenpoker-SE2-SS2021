package com.example.kakerlakenpoker.player;

import com.example.kakerlakenpoker.card.Card;
import com.example.kakerlakenpoker.card.CardDeck;
import com.example.kakerlakenpoker.card.GameDeck;

/**
 * Klasse die die Gesammelten Karten repräsentiert
 */
public class CollectedDeck extends CardDeck {

    //Platz für Methoden
    public boolean lostGame() {
        int fledermaus = 0, fliege = 0, ratte = 0, scorpion = 0, kakerlake = 0,  kroete = 0, spinne = 0, stinkwanze = 0;

        for (Card c : super.getDeck()) {
            switch (c.getType().toString()) {
                case "FLEDERMAUS":
                    fledermaus++;
                    break;
                case "FLIEGE":
                    fliege++;
                    break;
                case "RATTE":
                    ratte++;
                    break;
                case "SCORPION":
                    scorpion++;
                    break;
                case "KAKERLAKE":
                    kakerlake++;
                    break;
                case "KROETE":
                    kroete++;
                    break;
                case "SPINNE":
                    spinne++;
                    break;
                case "STINKWANZE":
                    stinkwanze++;
                    break;
            }
        }

        if (fledermaus == 4 || fliege == 4 || ratte == 4 || scorpion == 4 || kakerlake == 4 || kroete == 4 || spinne == 4 || stinkwanze == 4 ) {
            return true;
        } else return fledermaus == 1 && fliege == 1 && ratte == 1 && scorpion == 1 && kakerlake == 1 && kroete == 1 && spinne == 1 && stinkwanze == 1;
    }
}
