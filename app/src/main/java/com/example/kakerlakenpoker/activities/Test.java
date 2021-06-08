package com.example.kakerlakenpoker.activities;

import com.example.game.card.Card;
import com.example.game.card.Type;
import com.example.game.BuildGame;
import com.example.game.Decision;
import com.example.game.Game;
import com.example.game.Turn;
import com.example.game.player.CollectedDeck;
import com.example.game.player.HandDeck;
import com.example.game.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {
    /*
    public static void main(String[] args) {
        Player player1 = new Player(1, new HandDeck(), new CollectedDeck());
        Player player2 = new Player(2, new HandDeck(), new CollectedDeck());
        Player player3 = new Player(3, new HandDeck(), new CollectedDeck());
        Player player4 = new Player(4, new HandDeck(), new CollectedDeck());
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

            while (next) {
                next = false;
                if(game.getCurrentPlayer().getCollectedDeck().hasLost()){
<<<<<<< HEAD
                    System.out.println("Player " + game.getCurrentPlayer().getId() +" lost the game!");
=======
                    System.out.println("Player " + game.getCurrentPlayer().getID() +" lost the game!");
>>>>>>> origin/develop
                    System.out.println(game.getCurrentPlayer().getCollectedDeck().showAllCards().toString());
                    break;
                }
                System.out.println("####ROUND " + round + "#####");
                game.resetPlayerStatus();
                int local = round;
<<<<<<< HEAD
                System.out.println("Turn of player: " + game.getCurrentPlayer().getId());
=======
                System.out.println("Turn of player: " + game.getCurrentPlayer().getID());
>>>>>>> origin/develop
                while (displ) {
                    System.out.println("Your Handcards are: " + game.getCurrentPlayer().getHandDeck().showAllCards().toString());
                    System.out.println("Choose a Card to play or enter display to see the collected Cards of your enemys: ");
                    card = scanner.nextLine();
                    if (card.equals("display")) {
                        for (Player p : player) {
                            System.out.println("---------------------------------");
<<<<<<< HEAD
                            System.out.println("Collectded Deck of player: " + p.getId());
=======
                            System.out.println("Collectded Deck of player: " + p.getID());
>>>>>>> origin/develop
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
<<<<<<< HEAD
                    System.out.println("Current Player: " + game.getCurrentPlayer().getId());
=======
                    System.out.println("Current Player: " + game.getCurrentPlayer().getID());
>>>>>>> origin/develop
                    System.out.println();
                    System.out.println("Possible Enemys: " );
                    possible = game.getAvailablePlayer();
                    for (Player p: possible) {
<<<<<<< HEAD
                        if(p.getId()!=(game.getCurrentPlayer().getId())){
                        System.out.println("Player: " + p.getId());
=======
                        if(!p.getID().equals(game.getCurrentPlayer().getID())){
                        System.out.println("Player: " + p.getID());
>>>>>>> origin/develop
                        }
                    }
                    System.out.println();
                    System.out.println("Choose an Enemy: ");
                    enemy = scanner.nextLine();
                    System.out.println("You choose: " + enemy);
                    System.out.println("What do you say to your enemy? ");
                    say = scanner.nextLine();
                    game.makeTurn(game.getCurrentPlayer(), new Turn(currCard, Type.valueOf(say), game.getPlayerbyName(enemy)));
<<<<<<< HEAD
                    System.out.println(enemy + ": "  + game.getCurrentPlayer().getId() + " played " + say + "!");
=======
                    System.out.println(enemy + ": "  + game.getCurrentPlayer().getID() + " played " + say + "!");
>>>>>>> origin/develop
                    System.out.println("Enter decision or reject!");
                    decission = scanner.nextLine();

                    if (decission.equals("decision")) {
                        System.out.println("Enter TRUTH or LIE");
<<<<<<< HEAD
                        int printer = game.getCurrentPlayer().getId();
=======
                        String printer = game.getCurrentPlayer().getID();
>>>>>>> origin/develop
                        decission = scanner.nextLine();
                        game.makeDecision(game.getPlayerbyName(enemy), Decision.valueOf(decission));
                        System.out.println("---------CHALLENGE---------");
                        System.out.println(printer + " played " + card + " and said " + say);
                        System.out.println(enemy + " said " + decission);
<<<<<<< HEAD
                        System.out.println("New curr Player: = " + game.getCurrentPlayer().getId());
                        next = true;
                        round++;
                    } else if (decission.equals("reject")) {
                        System.out.println("Player " + game.getCurrentPlayer().getId() + " played: " + card);
=======
                        System.out.println("New curr Player: = " + game.getCurrentPlayer().getID());
                        next = true;
                        round++;
                    } else if (decission.equals("reject")) {
                        System.out.println("Player " + game.getCurrentPlayer().getID() + " played: " + card);
>>>>>>> origin/develop
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
    */
}

