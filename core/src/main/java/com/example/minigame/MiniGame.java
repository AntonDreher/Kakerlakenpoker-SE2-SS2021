package com.example.minigame;

public class MiniGame {
    Hand hand;

    public MiniGame(Hand hand) {
        this.hand = hand;
    }

    //Rock, Paper, Scissors, Lizard, Spock
    public String game(Counter counter) {
        String computerHand = hand.computerPlay();
        String playerHand = hand.getPlay();
        String play = "Computer played: " + computerHand + "<br/><br/>You played: " + playerHand + "<br/><br/><b>";
        String win;
        int counterLoss = counter.getLossCounter();
        int counterWin = counter.getWinCounter();
        int counterDraw = counter.getDrawCounter();
        int winOutOf5 = counter.getWinOutOf5();

        //RPSLS game play
        if (
                (computerHand.equals("Rock") && playerHand.equals("Scissors")) |
                        (computerHand.equals("Rock") && playerHand.equals("Lizard")) |
                        (computerHand.equals("Paper") && playerHand.equals("Rock")) |
                        (computerHand.equals("Paper") && playerHand.equals("Spock")) |
                        (computerHand.equals("Scissors") && playerHand.equals("Paper")) |
                        (computerHand.equals("Scissors") && playerHand.equals("Lizard")) |
                        (computerHand.equals("Lizard") && playerHand.equals("Paper")) |
                        (computerHand.equals("Lizard") && playerHand.equals("Spock")) |
                        (computerHand.equals("Spock") && playerHand.equals("Rock")) |
                        (computerHand.equals("Spock") && playerHand.equals("Scissors"))
        ) {
            //Computer wins
            win = play + "COMPUTER WINS.";
            counterLoss++;
            counter.setLossCounter(counterLoss);
        } else if (playerHand == computerHand) {
            // Draw
            win = play + "IT'S A DRAW.";
            counterDraw++;
            counter.setDrawCounter(counterDraw);
        } else {
            // Player wins
            win = play + "YOU WIN.";
            counterWin++;
            winOutOf5++;
            counter.setWinCounter(counterWin);
            counter.setWinOutOf5(winOutOf5);
        }
        return win;
    }
}
