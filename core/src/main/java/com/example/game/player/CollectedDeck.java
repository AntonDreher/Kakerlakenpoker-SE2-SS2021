package com.example.game.player;

import com.example.game.card.CardDeck;

/**
 * Klasse die die gesammelten Karten repräsentiert
 */
public class CollectedDeck extends CardDeck {

    /**
     * Die Methode überprüft die abgelegten Karten auf 4 gleiche Karten des selben Typs oder ob jeder Typ einmal vorkommt.
     */

    public boolean hasLost() {
        super.countAllCards();

        if (this.getFledermaus() == 4 || this.getFliege() == 4 || this.getRatte() == 4 || this.getScorpion() == 4 || this.getKakerlake()== 4 || this.getKroete() == 4 || this.getSpinne() == 4 || this.getStinkwanze() == 4) {
            return true;
        } else
            return this.getFledermaus() ==  1 &&  this.getFliege() ==  1 &&  this.getRatte() ==  1 &&  this.getScorpion() ==  1 && this.getKakerlake()==  1 &&  this.getKroete() ==  1 && this.getSpinne() == 1 && this.getStinkwanze() == 1;
    }

   /* public String toString(){
        return "Kakerlake= " + super.getKakerlake() + "| " +
                "Fledermaus= " + super.getFledermaus() + "| " +
                "Fliege= " + super.getFledermaus() + "| " +
                "Ratte= " + super.getRatte() + "| " +
                "Scorpion= " + super.getScorpion() + "| " +
                "Kroete= " + super.getKroete() + "| " +
                "Spinne= " + super.getSpinne() + "| " +
                "Stinkwanze= " + super.getStinkwanze() + ("\n");
    }*/
}
