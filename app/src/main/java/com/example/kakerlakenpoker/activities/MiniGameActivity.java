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
    Hand hand;
    MiniGame game;
    Counter counter;

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
        Log.d("RockPaperScissors", "onCreate call");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        Log.d("RockPaperScissors", "set text method");
        gameResult.setText(Html.fromHtml(game.game(counter)));
        winCounter.setText("Your Wins: " + counter.getWinCounter());
        lossCounter.setText("Computer Wins: " + counter.getLossCounter());
        drawCounter.setText("Draws: " + counter.getDrawCounter());
    }

    // onClickListener method Rock
    public void onRockButtonClick() {
        Log.d("RockPaperScissors", "Rock button clicked");
        game = new MiniGame(hand = new Hand("Rock"));
        Log.d("RockPaperScissors", "Hand: " + hand.getPlay() + ", Computer: " + hand.computerPlay());
        executeGame();
    }

    // onClickListener method Paper
    public void onPaperButtonClick() {
        Log.d("RockPaperScissors", "Paper button clicked");
        game = new MiniGame(hand = new Hand("Paper"));
        Log.d("RockPaperScissors", "Hand: " + hand.getPlay() + ", Computer: " + hand.computerPlay());
        executeGame();
    }

    // onClickListener method Scissors
    public void onScissorsButtonClick() {
        Log.d("RockPaperScissors", "Scissors button clicked");
        game = new MiniGame(hand = new Hand("Scissors"));
        Log.d("RockPaperScissors", "Hand: " + hand.getPlay() + ", Computer: " + hand.computerPlay());
        executeGame();
    }

    // onClickListener method Lizard
    public void onLizardButtonClick() {
        Log.d("RockPaperScissors", "Lizard button clicked");
        game = new MiniGame(hand = new Hand("Lizard"));
        Log.d("RockPaperScissors", "Hand: " + hand.getPlay() + ", Computer: " + hand.computerPlay());
        executeGame();
    }

    // onClickListener method Spock
    public void onSpockButtonClick() {
        Log.d("RockPaperScissors", "Spock button clicked");
        game = new MiniGame(hand = new Hand("Spock"));
        Log.d("RockPaperScissors", "Hand: " + hand.getPlay() + ", Computer: " + hand.computerPlay());
        executeGame();
    }
}
