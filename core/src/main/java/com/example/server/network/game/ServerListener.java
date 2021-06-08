package com.example.server.network.game;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;
import com.example.game.BuildGame;
import com.example.game.GameConstants;
import com.example.game.listener.GameListenerServerSide;
import com.example.server.network.dto.gameservertoclient.GameOver;
import com.example.server.network.dto.clienttogameserver.MakeDecision;
import com.example.server.network.dto.clienttogameserver.MakeTurn;
import com.example.server.network.dto.PlayerReady;
import com.example.game.player.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class ServerListener extends Listener {
    private GameData gameData;
    private HashMap<Connection, Player> players = new HashMap<>();

    public ServerListener(GameData gameData){
        this.gameData = gameData;
    }
    @Override
    public void received(Connection connection, Object object) {
        if(!(object instanceof com.esotericsoftware.kryonet.FrameworkMessage))
        Log.info("Received Object: " + object.getClass());
        if(object instanceof MakeTurn){
            gameData.getGame().makeTurn(players.get(connection), ((MakeTurn) object).getTurn());
        } else if(object instanceof MakeDecision){
            gameData.getGame().makeDecision(players.get(connection),((MakeDecision) object).getDecision());
        } else if(object instanceof GameOver){
            gameData.getGame().gameOver(players.get(connection));
        }else if(object instanceof PlayerReady){
            players.put(connection, new Player(connection.getID(),null, null));
            if(players.size()== GameConstants.NEEDED_PLAYERS_TO_PLAY){
                ArrayList<Player> playersList = new ArrayList<>(players.values());
                BuildGame buildGame = new BuildGame();
                buildGame.setPlayers(playersList);
                gameData.setGame(buildGame.buildGame());
                //gameData.getGame().setGameListener(new GameListenerServerSide(gameData));
                gameData.getGame().startGame();
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
