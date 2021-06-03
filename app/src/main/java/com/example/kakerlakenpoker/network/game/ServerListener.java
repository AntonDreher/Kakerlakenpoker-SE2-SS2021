package com.example.kakerlakenpoker.network.game;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;
import com.example.kakerlakenpoker.game.BuildGame;
import com.example.kakerlakenpoker.game.Game;
import com.example.kakerlakenpoker.game.listener.GameListenerServerSide;
import com.example.kakerlakenpoker.network.dto.MakeTurn;
import com.example.kakerlakenpoker.network.dto.PlayerReady;
import com.example.kakerlakenpoker.player.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class ServerListener extends Listener {
    private GameServer gameServer;
    private Game game;
    private HashMap<Connection, Player> players = new HashMap<>();

    public ServerListener(GameServer gameServer){
        this.gameServer = gameServer;
    }
    @Override
    public void received(Connection connection, Object object) {
        if(object instanceof MakeTurn){
            game.makeTurn(null, ((MakeTurn) object).getTurn());
        } else if(object instanceof PlayerReady){
            players.put(connection, ((PlayerReady) object).getPlayer());
            if(players.size()==4){
                ArrayList<Player> playersList = new ArrayList<>(players.values());
                BuildGame buildGame = new BuildGame();
                buildGame.setPlayers(playersList);
                game = buildGame.buildGame();
                game.setGameListener(new GameListenerServerSide(gameServer));
                game.startGame();
            }
        }
    }
    @Override
    public void connected(Connection connection) {
        Log.info("Client connected: Hashmap size: "+players.size());
        Log.info("Client connected: " + connection.getRemoteAddressTCP());
    }

    @Override
    public void disconnected(Connection connection) {
        players.remove(connection);
        Log.info("Client disconnected: " + connection.getRemoteAddressTCP());

    }
}
