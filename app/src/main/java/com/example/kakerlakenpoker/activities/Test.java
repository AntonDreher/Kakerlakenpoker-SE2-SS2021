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
import java.util.List;
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
        boolean next = true;
        int round = 1;
        boolean displ = true;
        Card currCard;
        String card = "x";
        String enemy;
        String say;
        String decission;
        List<Player> possible;
        Scanner scanner = new Scanner(System.in);
        BuildGame buildGame = new BuildGame();
        buildGame.setPlayers(player);
        Game game = buildGame.buildGame();
        game.setCurrentPlayer(game.getPlayerbyName("player1"));
        System.out.println("Game build success!");
        while (true) {
            while (next) {
                next = false;
                System.out.println("####ROUND " + round + "#####");
                game.resetPlayerStatus();
                int local = round;
                System.out.println("Turn of player: " + game.getCurrentPlayer().getName());
                while (displ) {
                    System.out.println("Your Handcards are: " + game.getCurrentPlayer().getHandDeck().showAllCards().toString());
                    System.out.println("Choose a Card to play or enter display to see the collected Cards of your enemys: ");
                    card = scanner.nextLine();
                    if (card.equals("display")) {
                        for (Player p : player) {
                            System.out.println("---------------------------------");
                            System.out.println("Collectded Deck of player: " + p.getName());
                            System.out.println(p.getCollectedDeck().showAllCards().toString());
                            System.out.println();
                        }
                    } else {
                        displ = false;
                    }
                }
                displ = true;
                currCard = game.getCurrentPlayer().getHandDeck().findCard(card);
                game.getCurrentPlayer().getHandDeck().removeCard(currCard);
                System.out.println("You choose: " + currCard.getType().toString());
                while (local == round) {
                    System.out.println("Current Player: " + game.getCurrentPlayer().getName());
                    System.out.println();
                    System.out.println("Possible Enemys: " );
                    possible = game.getAvailablePlayer();
                    for (Player p: possible) {
                        if(!p.getName().equals(game.getCurrentPlayer().getName())){
                        System.out.println("Player: " + p.getName());
                        }
                    }
                    System.out.println();
                    System.out.println("Choose an Enemy: ");
                    enemy = scanner.nextLine();
                    System.out.println("You choose: " + enemy);
                    System.out.println("What do you say to your enemy? ");
                    say = scanner.nextLine();
                    game.makeTurn(game.getCurrentPlayer(), new Turn(currCard, Type.valueOf(say), game.getPlayerbyName(enemy)));
                    System.out.println(enemy + ": "  + game.getCurrentPlayer().getName() + " played " + say + "!");
                    System.out.println("Enter decision or reject!");
                    decission = scanner.nextLine();

                    if (decission.equals("decision")) {
                        System.out.println("Enter TRUTH or LIE");
                        decission = scanner.nextLine();
                        game.makeDecision(game.getPlayerbyName(enemy), Decision.valueOf(decission));
                        System.out.println("---------CHALLENGE---------");
                        System.out.println(game.getCurrentPlayer().getName() + " played " + card + " and said " + say);
                        System.out.println(enemy + " said " + decission);
                        System.out.println("New curr Player: = " + game.getCurrentPlayer().getName());
                        next = true;
                        round++;
                    } else if (decission.equals("reject")) {
                        System.out.println("Player " + game.getCurrentPlayer().getName() + " played: " + card);
                        game.reject(game.getPlayerbyName(enemy));
                        System.out.println();
                    }
                    if (game.checkRoundOver()) {
                        next = true;
                        round++;
                    }
                }

                System.out.println();

            }
        }
    }
}

