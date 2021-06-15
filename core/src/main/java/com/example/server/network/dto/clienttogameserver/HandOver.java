package com.example.server.network.dto.clienttogameserver;

import com.example.game.Decision;
import com.example.game.player.Player;
import com.example.server.dto.BaseMessage;
import com.example.server.network.dto.gameservertoclient.GameUpdate;

public class HandOver extends BaseMessage {
    private Player enemy;
    private Decision decision;
    private GameUpdate gameUpdate;
    public HandOver(){

    }

    public HandOver(Player enemy, Decision decision){
        this.enemy = enemy;
        this.decision = decision;
    }
    public HandOver(GameUpdate gameUpdate){
        this.gameUpdate = gameUpdate;
    }

    public Decision getDecision() {
        return decision;
    }

    public Player getEnemy() {
        return enemy;
    }
}
