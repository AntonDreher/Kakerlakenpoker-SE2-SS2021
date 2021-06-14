package com.example.game;

import com.example.game.card.Card;
import com.example.game.card.Type;
import com.example.game.player.Player;

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

    public void setSelectedEnemy(Player selectedEnemy) {
        this.selectedEnemy = selectedEnemy;
    }

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
