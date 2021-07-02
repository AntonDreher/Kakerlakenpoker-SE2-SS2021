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
        int counterRound = counter.getRoundCounter();
        int counterWinOutOf5 = counter.getWinOutOf5Counter();

        // count rounds
        counterRound++;
        counter.setRoundCounter(counterRound);

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
            counter.setWinCounter(counterWin);
        }

        //counts the wins of a whole game with 5 rounds
        if (counterRound == 5 && counterWin > 2){
            counterWinOutOf5++;
            counter.setWinOutOf5Counter(counterWinOutOf5);
        }

        return win;
    }
}
