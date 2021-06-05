package com.example.kakerlakenpoker.network.dto;

import com.example.kakerlakenpoker.game.Decision;

public class MakeDecision extends BaseMessage {
    private Decision decision;
    public MakeDecision(){
    }

    public MakeDecision(Decision decision){
        this.decision = decision;
    }

    public Decision getDecision() {
        return decision;
    }
}
