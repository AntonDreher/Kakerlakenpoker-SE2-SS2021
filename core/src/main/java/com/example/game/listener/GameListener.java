package com.example.game.listener;

import com.example.game.GameState;
import com.example.server.network.dto.gameservertoclient.GameUpdate;

public interface GameListener {
   void notify(GameUpdate gameUpdate, GameState previousState);
}
