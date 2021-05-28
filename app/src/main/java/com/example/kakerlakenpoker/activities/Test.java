package com.example.kakerlakenpoker.activities;

import android.net.IpSecManager;

import com.example.kakerlakenpoker.card.Card;
import com.example.kakerlakenpoker.card.Type;
import com.example.kakerlakenpoker.game.BuildGame;
import com.example.kakerlakenpoker.game.Decision;
import com.example.kakerlakenpoker.game.Game;
import com.example.kakerlakenpoker.game.Turn;
import com.example.kakerlakenpoker.player.CollectedDeck;
import com.example.kakerlakenpoker.player.HandDeck;
import com.example.kakerlakenpoker.player.Player;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

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
        int round = 1;
        boolean displ = true;
        String card = "x";
        String enemy;
        String say;
        String decission;
        Scanner scanner = new Scanner(System.in);
        BuildGame buildGame = new BuildGame();
        buildGame.setPlayers(player);
        Game game = buildGame.buildGame();
        Player currPlayer = game.getPlayers().get(0);
        System.out.println("Game build success!");
        while (true) {
            System.out.println("####ROUND " + round +"#####");
            System.out.println("Turn of player: "+ currPlayer.getName());
            while(displ){
                System.out.println("Your Handcards are: " + currPlayer.getHandDeck().showAllCards().toString());
                System.out.println("Choose a Card to play or enter display to see the collected Cards of your enemys: ");
                card = scanner.nextLine();
                if (card.equals("display")){
                    for (Player p: player) {
                        System.out.println("---------------------------------");
                        System.out.println("Collectded Deck of player: " + p.getName());
                        System.out.println(p.getCollectedDeck().showAllCards().toString());
                        System.out.println();
                    }
                }
                else {
                    displ = false;
                }
            }
            System.out.println("You choose: " + card);
            System.out.println("Choose an Enemy: ");
            enemy = scanner.nextLine();
            System.out.println("You choose: " + enemy);
            System.out.println("What do you say to your enemy? ");
            say = scanner.nextLine();
            game.makeTurn(currPlayer, new Turn(currPlayer.getHandDeck().findCard(card), Type.valueOf(say), game.getPlayerbyName(enemy)));
            System.out.println(enemy + ": Player " + currPlayer.getName() + " played " + say  + "!");
            System.out.println("Enter TRUTH or LIE");
            decission = scanner.nextLine();
            game.makeDecision(game.getPlayerbyName(enemy), Decision.valueOf(decission));
            System.out.println();
            System.out.println(currPlayer.getName() + " played " + card + " and said " + say );
            System.out.println(enemy + " said " + decission);
            currPlayer = game.getCurrentPlayer();
            System.out.println("New curr Player: = " + currPlayer.getName());
            round++;
            System.out.println();

        }
    }
}

