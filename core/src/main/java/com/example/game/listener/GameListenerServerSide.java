package com.example.game.listener;

import com.example.game.GameState;
import com.example.game.player.Player;
import com.example.server.MainServer;
import com.example.server.dto.BaseMessage;
import com.example.server.network.dto.gameservertoclient.GameOver;
import com.example.server.network.dto.gameservertoclient.GameUpdate;
import com.example.server.network.dto.gameservertoclient.InitGame;
import com.example.server.network.game.GameData;

import java.net.InetSocketAddress;

import jdk.internal.jline.internal.Log;

public class GameListenerServerSide implements GameListener {
    private MainServer server;
    private GameData gameData;

    public GameListenerServerSide(MainServer server, GameData gameData) {
        this.server = server;
        this.gameData = gameData;
    }

    @Override
    public void notify(GameUpdate gameUpdate, GameState previousState) {
        if (gameUpdate.getState() == GameState.AWAITING_GO) {
        } else if (gameUpdate.getState() == GameState.GAME_OVER) {

            sendToPlayers(new GameOver(gameData
                    .getGame().getCurrentPlayer()));

        } else {
            sendToPlayers(gameUpdate);}
    }

    public void sendToPlayers(BaseMessage message) {
        for (Player player : gameData.getGame().getPlayers()) {
            server.getServer().sendToTCP(player.getId(), message);
        }
    }
}
