package com.example.kakerlakenpoker.network.game;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;
import com.example.kakerlakenpoker.game.BuildGame;
import com.example.kakerlakenpoker.game.GameConstants;
import com.example.kakerlakenpoker.game.listener.GameListenerServerSide;
import com.example.kakerlakenpoker.network.dto.gameservertoclient.GameOver;
import com.example.kakerlakenpoker.network.dto.clienttogameserver.MakeDecision;
import com.example.kakerlakenpoker.network.dto.clienttogameserver.MakeTurn;
import com.example.kakerlakenpoker.network.dto.PlayerReady;
import com.example.kakerlakenpoker.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

public class ServerListener extends Listener {
    private GameServer gameServer;
    private HashMap<Connection, Player> players = new HashMap<>();

    public ServerListener(GameServer gameServer){
        this.gameServer = gameServer;
    }
    @Override
    public void received(Connection connection, Object object) {
        if(!(object instanceof com.esotericsoftware.kryonet.FrameworkMessage))
        Log.info("Received Object: " + object.getClass());
        if(object instanceof MakeTurn){
            gameServer.getGame().makeTurn(players.get(connection), ((MakeTurn) object).getTurn());
        } else if(object instanceof MakeDecision){
            gameServer.getGame().makeDecision(players.get(connection),((MakeDecision) object).getDecision());
        } else if(object instanceof GameOver){
            gameServer.getGame().gameOver(players.get(connection));
        }else if(object instanceof PlayerReady){
            players.put(connection, new Player(String.valueOf(connection.getID()),null, null));
            if(players.size()== GameConstants.NEEDED_PLAYERS_TO_PLAY){
                ArrayList<Player> playersList = new ArrayList<>(players.values());
                BuildGame buildGame = new BuildGame();
                buildGame.setPlayers(playersList);
                gameServer.setGame(buildGame.buildGame());
                gameServer.getGame().setGameListener(new GameListenerServerSide(gameServer));
                gameServer.getGame().startGame();
            }
        }
    }
    @Override
    public void connected(Connection connection) {
        Log.info("Client connected: " + connection.getRemoteAddressTCP());
    }

    @Override
    public void disconnected(Connection connection) {
        Log.info("Client disconnected: " + connection.getRemoteAddressTCP());
    }
}
