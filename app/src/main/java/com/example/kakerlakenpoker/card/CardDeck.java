package com.example.kakerlakenpoker.card;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstrakte Klasse, die ein Karten Deck repräsentiert
 * Jedes Deck verfügt über eine ArrayList vom Typ Card
 */
public abstract class CardDeck {
    int fledermaus = 0;
    int fliege = 0;
    int ratte = 0;
    int scorpion = 0;
    int kakerlake = 0;
    int kroete = 0;
    int spinne = 0;
    int stinkwanze = 0;

    private  List<Card> deck = new ArrayList<>();


    public int getFledermaus() {
        return fledermaus;
    }

    public void setFledermaus(int fledermaus) {
        this.fledermaus = fledermaus;
    }

    public int getFliege() {
        return fliege;
    }

    public void setFliege(int fliege) {
        this.fliege = fliege;
    }

    public int getRatte() {
        return ratte;
    }

    public void setRatte(int ratte) {
        this.ratte = ratte;
    }

    public int getScorpion() {
        return scorpion;
    }

    public void setScorpion(int scorpion) {
        this.scorpion = scorpion;
    }

    public int getKakerlake() {
        return kakerlake;
    }

    public void setKakerlake(int kakerlake) {
        this.kakerlake = kakerlake;
    }

    public int getKroete() {
        return kroete;
    }

    public void setKroete(int kroete) {
        this.kroete = kroete;
    }

    public int getSpinne() {
        return spinne;
    }

    public void setSpinne(int spinne) {
        this.spinne = spinne;
    }

    public int getStinkwanze() {
        return stinkwanze;
    }

    public void setStinkwanze(int stinkwanze) {
        this.stinkwanze = stinkwanze;
    }

    /**
     * Fügt Karte der Liste hinzu
     * @param card ENUM aus Klasse Card
     */
    public void addCard(Card card){
        deck.add(card);
    }

    /**
     * Entfernt eine Karte aus dem Deck, hier wird nach ENUM gesucht
     * @param type einer der 8 Typen aus der ENUM Klasse
     */
    public void removeCard(Card card){
        deck.remove(card);
    }

    /**
     * Sucht einen Karten aus dem Deck, hier wird nach ENUM gesucht
     * @param type einer der 8 Typen aus der ENUM Klasse
     * @return wenn Typ gefunden eine Karte sons NULL
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
     * Gibt das aktuelle Deck zurück
     * @return Deck
     */
    public List <Card> getDeck() {
        return deck;
    }

    /**
     * Gibt die Größe des Decks zurück
     * @return Int Größe des Decks
     */
    public int size(){
        return deck.size();
    }

    public void setDeck(List<Card> deck) {
        this.deck = deck;
    }

    public void countAllCards(){

        for (Card c : getDeck()) {
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
                default: break;
            }
        }
    }
    

}