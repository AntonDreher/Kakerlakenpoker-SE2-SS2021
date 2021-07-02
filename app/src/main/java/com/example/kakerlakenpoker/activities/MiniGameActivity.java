package com.example.kakerlakenpoker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kakerlakenpoker.R;
import com.example.minigame.Counter;
import com.example.minigame.Hand;
import com.example.minigame.MiniGame;

public class MiniGameActivity extends AppCompatActivity {
    //values for won and lost activity
    public static final String WON_COUNTER = "WON_COUNTER";
    public static final String LOSS_COUNTER = "LOSS_COUNTER";
    public static final String DRAW_COUNTER = "DRAW_COUNTER";
    public static final String WINOUTOF5_COUNTER = "WINOUTOF5_COUNTER";
    int counterWon;
    int counterLoss;
    int counterDraw;
    int counterWinOutOf5;

    Hand hand;
    MiniGame game;
    Counter counter;
    Intent intent;

    TextView gameResult;
    TextView winCounter;
    TextView lossCounter;
    TextView drawCounter;

    ImageButton rockButton;
    ImageButton paperButton;
    ImageButton scissorButton;
    ImageButton spockButton;
    ImageButton lizardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("R.P.S.L.S.", "onCreate call MiniGame Activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minigame);

        // TextViews
        gameResult = (TextView) findViewById(R.id.game_result_text);
        winCounter = (TextView) findViewById(R.id.win_counter_text);
        lossCounter = (TextView) findViewById(R.id.loss_counter_text);
        drawCounter = (TextView) findViewById(R.id.draw_counter_text);
        counter = new Counter();

        // Button Rock
        rockButton = (ImageButton) findViewById(R.id.rock_imagebutton);
        rockButton.setOnClickListener((View view) -> onRockButtonClick());

        //Button Paper
        paperButton = (ImageButton) findViewById(R.id.paper_imagebutton);
        paperButton.setOnClickListener((View view) -> onPaperButtonClick());

        // Button Scissors
        scissorButton = (ImageButton) findViewById(R.id.scissors_imagebutton);
        scissorButton.setOnClickListener((View view) -> onScissorsButtonClick());

        //Button Lizard
        lizardButton = (ImageButton) findViewById(R.id.lizard_imagebutton);
        lizardButton.setOnClickListener((View view) -> onLizardButtonClick());

        //Button Spock
        spockButton = (ImageButton) findViewById(R.id.spock_imagebutton);
        spockButton.setOnClickListener((View view) -> onSpockButtonClick());
    }
    // execute game for all hand options
    public void executeGame() {
        // set text view values
        gameResult.setText(Html.fromHtml(game.game(counter)));
        winCounter.setText("Your Wins: " + counter.getWinCounter());
        lossCounter.setText("Computer Wins: " + counter.getLossCounter());
        drawCounter.setText("Draws: " + counter.getDrawCounter());
        Log.d("R.P.S.L.S.", "set text method, " + "WinCounter" + counter.getRoundCounter());


        // set variables for activity intent
        counterWon = counter.getWinCounter();
        counterLoss = counter.getLossCounter();
        counterDraw = counter.getDrawCounter();
        counterWinOutOf5 = counter.getWinOutOf5Counter();
        Log.d("R.P.S.L.S.", "winCounter set, " + "Round Counter: " + counter.getRoundCounter() + " ,Win Counter: " + counterWon);

        // switch to won or loss activity
        if (counter.getRoundCounter() == 5 && counter.getWinCounter() > 2) {
            Log.d("R.P.S.L.S.", "intent win activity, " + "Round Counter: " + counter.getRoundCounter() + " ,Win Counter: " + counter.getWinCounter());
            intent = new Intent(this, MiniGameWonActivity.class);
            intent.putExtra(WON_COUNTER, counterWon);
            intent.putExtra(LOSS_COUNTER, counterLoss);
            intent.putExtra(DRAW_COUNTER, counterDraw);
            intent.putExtra(WINOUTOF5_COUNTER, counterWinOutOf5);
            startActivity(intent);
        } else if (counter.getRoundCounter() == 5 && counter.getWinCounter() < 3) {
            Log.d("R.P.S.L.S.", "intent loss activity, " + "Round Counter: " + counter.getRoundCounter() + " ,Win Counter: " + counter.getWinCounter());
            intent = new Intent(this, MiniGameLostActivity.class);
            intent.putExtra(WON_COUNTER, counterWon);
            intent.putExtra(LOSS_COUNTER, counterLoss);
            intent.putExtra(DRAW_COUNTER, counterDraw);
            startActivity(intent);
        }
    }

    // onClickListener method Rock
    public void onRockButtonClick() {
        Log.d("R.P.S.L.S.", "Rock button clicked");
        game = new MiniGame(hand = new Hand("Rock"));
        Log.d("R.P.S.L.S.", "Hand: " + hand.getPlay() + ", Computer: " + hand.computerPlay());
        executeGame();
    }

    // onClickListener method Paper
    public void onPaperButtonClick() {
        Log.d("R.P.S.L.S.", "Paper button clicked");
        game = new MiniGame(hand = new Hand("Paper"));
        Log.d("R.P.S.L.S.", "Hand: " + hand.getPlay() + ", Computer: " + hand.computerPlay());
        executeGame();
    }

    // onClickListener method Scissors
    public void onScissorsButtonClick() {
        Log.d("R.P.S.L.S.", "Scissors button clicked");
        game = new MiniGame(hand = new Hand("Scissors"));
        Log.d("R.P.S.L.S.", "Hand: " + hand.getPlay() + ", Computer: " + hand.computerPlay());
        executeGame();
    }

    // onClickListener method Lizard
    public void onLizardButtonClick() {
        Log.d("R.P.S.L.S.", "Lizard button clicked");
        game = new MiniGame(hand = new Hand("Lizard"));
        Log.d("R.P.S.L.S.", "Hand: " + hand.getPlay() + ", Computer: " + hand.computerPlay());
        executeGame();
    }

    // onClickListener method Spock
    public void onSpockButtonClick() {
        Log.d("R.P.S.L.S.", "Spock button clicked");
        game = new MiniGame(hand = new Hand("Spock"));
        Log.d("R.P.S.L.S.", "Hand: " + hand.getPlay() + ", Computer: " + hand.computerPlay());
        executeGame();
    }
}
