package com.example.server.network.dto.clienttogameserver;

import com.example.game.Decision;
import com.example.game.player.Player;

public class HandOver {
    private Player enemy;
    private Decision decision;
    public HandOver(){

    }

    public HandOver(Player enemy, Decision decision){
        this.enemy = enemy;
        this.decision = decision;
    }

    public Decision getDecision() {
        return decision;
    }

    public Player getEnemy() {
        return enemy;
    }
}
