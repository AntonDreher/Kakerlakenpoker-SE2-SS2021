package com.example.kakerlakenpoker.player;
import com.example.kakerlakenpoker.card.Card;
import com.example.kakerlakenpoker.card.CardDeck;

/**
 * Repräsentiert die Handkarten eines Spielers
 */

public class HandDeck extends CardDeck {

    int fledermaus = 0, fliege = 0, ratte = 0, scorpion = 0, kakerlake = 0, kroete = 0, spinne = 0, stinkwanze = 0;

    public void displayCard(){
        // Updatet die TextView in GameView

        for(int i = 0; i<getDeck().size(); i++) {

            switch (getDeck().get(i).getType().toString()) {

                case "FLEDERMAUS": fledermaus++; break;
                case "FLIEGE": fliege++; break;
                case "RATTE": ratte++; break;
                case "SCORPION": scorpion++; break;
                case "KAKERLAKE": kakerlake++; break;
                case "KROETE": kroete++; break;
                case "SPINNE": spinne++; break;
                case "STINKWANZE": stinkwanze++; break;
            }
            // Set TextView mit den gezählten werten.

        }
    }

    //Die Methode wird aufgerufen, wenn ein Spieler eine Karte ausspielt.
    public void useCard(Card card){ // Die Methodenübergabe müsste noch den Player übergeben.
        removeCard(card.getType());

        //Hier muss dann die ausgespielte Karte weitergegebn werden (Server-Client)
    }

    public int getFledermaus() {
        return fledermaus;
    }

    public int getFliege() {
        return fliege;
    }

    public int getRatte() {
        return ratte;
    }

    public int getScorpion() {
        return scorpion;
    }

    public int getKakerlake() {
        return kakerlake;
    }

    public int getKroete() {
        return kroete;
    }

    public int getSpinne() {
        return spinne;
    }

    public int getStinkwanze() {
        return stinkwanze;
    }

}
