package com.example.kakerlakenpoker.game.listener;

import com.example.kakerlakenpoker.game.GameState;
import com.example.kakerlakenpoker.network.dto.gameservertoclient.GameUpdate;

public interface GameListener {
   void notify(GameUpdate gameUpdate, GameState previousState);
}
