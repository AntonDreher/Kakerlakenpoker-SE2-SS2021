package com.example.kakerlakenpoker.game.listener;

import com.example.kakerlakenpoker.game.GameState;
import com.example.kakerlakenpoker.network.dto.GameUpdate;
import com.example.kakerlakenpoker.network.game.GameClient;

public interface GameListener {
   void notify(GameUpdate gameUpdate, GameState previousState);
}
