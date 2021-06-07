package com.example.kakerlakenpoker.game;

import com.example.kakerlakenpoker.game.card.Card;
import com.example.kakerlakenpoker.game.card.Type;
import com.example.kakerlakenpoker.game.player.Player;

public class Turn {
    private Card selectedCard;
    private Type selectedType;
    private Player selectedEnemy;

    public Turn(Card selectedCard, Type selectedType, Player selectedEnemy) {
        this.selectedCard = selectedCard;
        this.selectedType = selectedType;
        this.selectedEnemy = selectedEnemy;

    }

    public Turn(){}

    public Card getSelectedCard() {
        return selectedCard;
    }

    public Type getSelectedType() {
        return selectedType;
    }

    public Player getSelectedEnemy() {
        return selectedEnemy;
    }
}
