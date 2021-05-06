package com.example.kakerlakenpoker.card;

import java.util.ArrayList;

/**
 * Abstrakte Klasse, die ein Karten Deck repräsentiert
 * Jedes Deck verfügt über eine ArrayList vom Typ Card
 */
public abstract class CardDeck {
    private  ArrayList<Card> deck = new ArrayList<>();

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
    public void removeCard(Type type){
        Card c = findCard(type);
        if(c != null){
            deck.remove(c);
        }
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
    public ArrayList<Card> getDeck() {
        return deck;
    }

    /**
     * Gibt die Größe des Decks zurück
     * @return Int Größe des Decks
     */
    public int size(){
        return deck.size();
    }

    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }
}