package com.example.kakerlakenpoker.network.dto.clienttogameserver;

import com.example.kakerlakenpoker.game.Decision;
import com.example.server.dto.BaseMessage;

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
