package com.example.kakerlakenpoker.activities;

import com.example.kakerlakenpoker.card.Type;
import com.example.kakerlakenpoker.game.BuildGame;
import com.example.kakerlakenpoker.game.Decision;
import com.example.kakerlakenpoker.game.Game;
import com.example.kakerlakenpoker.game.Turn;
import com.example.kakerlakenpoker.player.CollectedDeck;
import com.example.kakerlakenpoker.player.HandDeck;
import com.example.kakerlakenpoker.player.Player;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        Player player1 = new Player("player1", new HandDeck(), new CollectedDeck());
        Player player2 = new Player("player2", new HandDeck(), new CollectedDeck());
        Player player3 = new Player("player3", new HandDeck(), new CollectedDeck());
        Player player4 = new Player("player4", new HandDeck(), new CollectedDeck());
        ArrayList<Player> player = new ArrayList<>();
        player.add(player1);
        player.add(player2);
        player.add(player3);
        player.add(player4);


        BuildGame buildGame = new BuildGame();
        buildGame.setPlayers(player);
        Game game = buildGame.buildGame();

        game.makeTurn(player1, new Turn(player1.getHandDeck().getDeck().get(5), Type.FLEDERMAUS, player2));
        game.makeDecision(player2, Decision.TRUTH);
        System.out.println(game.getCurrentPlayer().getName());




    }
}
