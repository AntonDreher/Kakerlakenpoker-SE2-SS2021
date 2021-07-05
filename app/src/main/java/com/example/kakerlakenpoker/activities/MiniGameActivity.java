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

import static com.example.kakerlakenpoker.activities.MiniGameLostActivity.RETURN_WINOUTOF5_COUNTER_GO;
import static com.example.kakerlakenpoker.activities.MiniGameLostActivity.RETURN_WON_COUNTER_GO;
import static com.example.kakerlakenpoker.activities.MiniGameWonActivity.RETURN_WINOUTOF5_COUNTER_WON;
import static com.example.kakerlakenpoker.activities.MiniGameWonActivity.RETURN_WON_COUNTER_WON;

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

    //request variables for onActivityResult
    public static final int INTEGER_REQUEST_GO = 1;
    public static final int INTEGER_REQUEST_WON = 2;

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

    // Call Back method to get counterWinOutOf5 message from MG won and lost activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("R.P.S.L.S.", "onActivityResult MiniGame Activity");
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == INTEGER_REQUEST_GO) {
            Log.d("R.P.S.L.S.", "onActivityResult MiniGame Activity, INTEGER_REQUEST_GO: " + INTEGER_REQUEST_GO);
            if (resultCode == RESULT_OK) {
                Log.d("R.P.S.L.S.", "onActivityResult MiniGame Activity, set counter variables from MG lost activity");
                counter.setWinCounter(data.getIntExtra(RETURN_WON_COUNTER_GO, 0));
                counter.setLossCounter(data.getIntExtra(RETURN_WON_COUNTER_GO, 0));
                counter.setDrawCounter(data.getIntExtra(RETURN_WON_COUNTER_GO, 0));
                counter.setRoundCounter(data.getIntExtra(RETURN_WON_COUNTER_GO, 0));
                counterWinOutOf5 = data.getIntExtra(RETURN_WINOUTOF5_COUNTER_GO, 0);

                winCounter.setText("Your Wins: " + counter.getWinCounter());
                lossCounter.setText("Computer Wins: " + counter.getLossCounter());
                drawCounter.setText("Draws: " + counter.getDrawCounter());

            }
        }
        if (requestCode == INTEGER_REQUEST_WON) {
            Log.d("R.P.S.L.S.", "onActivityResult MiniGame Activity, INTEGER_REQUEST_WON: " + INTEGER_REQUEST_WON);
            if (resultCode == RESULT_OK) {
                Log.d("R.P.S.L.S.", "onActivityResult MiniGame Activity, set counter variables from MG won activity");
                counter.setWinCounter(data.getIntExtra(RETURN_WON_COUNTER_WON, 0));
                counter.setLossCounter(data.getIntExtra(RETURN_WON_COUNTER_WON, 0));
                counter.setDrawCounter(data.getIntExtra(RETURN_WON_COUNTER_WON, 0));
                counter.setRoundCounter(data.getIntExtra(RETURN_WON_COUNTER_WON, 0));
                counterWinOutOf5 = data.getIntExtra(RETURN_WINOUTOF5_COUNTER_WON, 0);

                winCounter.setText("Your Wins: " + counter.getWinCounter());
                lossCounter.setText("Computer Wins: " + counter.getLossCounter());
                drawCounter.setText("Draws: " + counter.getDrawCounter());
            }
        }
    }

    // execute game for all hand options
    public void executeGame() {
        Log.d("R.P.S.L.S.", "executeGame methode");
        // set text view values
        gameResult.setText(Html.fromHtml(game.game(counter)));
        winCounter.setText("Your Wins: " + counter.getWinCounter());
        lossCounter.setText("Computer Wins: " + counter.getLossCounter());
        drawCounter.setText("Draws: " + counter.getDrawCounter());
        Log.d("R.P.S.L.S.", "set text methods in main activity, " + "counterWinOutOf5: " + counter.getWinOutOf5Counter());

        // set variables for activity intent
        counterWon = counter.getWinCounter();
        counterLoss = counter.getLossCounter();
        counterDraw = counter.getDrawCounter();
        counterWinOutOf5 = counter.getWinOutOf5Counter();
        Log.d("R.P.S.L.S.", "counters set, " + "Round Counter: " + counter.getRoundCounter() + " ,Win Counter: " + counterWon + " ,counterWinOutOf5:  " + counterWinOutOf5 + " ,Random Win Value: " + GameClient.getInstance().getValueForMinigame());

        // switch to won or loss activity
        if (counter.getRoundCounter() == 5 && counter.getWinCounter() >= GameClient.getInstance().getValueForMinigame()) {
            Log.d("R.P.S.L.S.", "intent win activity, " + "Round Counter: " + counter.getRoundCounter() + " ,Win Counter: " + counter.getWinCounter() + " ,counterWinOutOf5: " + counterWinOutOf5 + " ,Random Win Value: " + GameClient.getInstance().getValueForMinigame());
            intent = new Intent(this, MiniGameWonActivity.class);
            intent.putExtra(WON_COUNTER, counterWon);
            intent.putExtra(LOSS_COUNTER, counterLoss);
            intent.putExtra(DRAW_COUNTER, counterDraw);
            intent.putExtra(WINOUTOF5_COUNTER, counterWinOutOf5);
            startActivityForResult(intent, INTEGER_REQUEST_WON);
        } else if (counter.getRoundCounter() == 5 && counter.getWinCounter() < GameClient.getInstance().getValueForMinigame()) {
            Log.d("R.P.S.L.S.", "intent loss activity, " + "Round Counter: " + counter.getRoundCounter() + " ,Win Counter: " + counter.getWinCounter() + " ,counterWinOutOf5: " + counterWinOutOf5 + " ,Random Win Value: " + GameClient.getInstance().getValueForMinigame());
            intent = new Intent(this, MiniGameLostActivity.class);
            intent.putExtra(WON_COUNTER, counterWon);
            intent.putExtra(LOSS_COUNTER, counterLoss);
            intent.putExtra(DRAW_COUNTER, counterDraw);
            intent.putExtra(WINOUTOF5_COUNTER, counterWinOutOf5);
            startActivityForResult(intent, INTEGER_REQUEST_GO);
        }
    }

    // onClickListener method Rock
    public void onRockButtonClick() {
        Log.d("R.P.S.L.S.", "Rock button clicked");
        game = new MiniGame(hand = new Hand("Rock"), GameClient.getInstance().getValueForMinigame());
        Log.d("R.P.S.L.S.", "Hand: " + hand.getPlay() + ", Computer: " + hand.computerPlay());
        executeGame();
    }

    // onClickListener method Paper
    public void onPaperButtonClick() {
        Log.d("R.P.S.L.S.", "Paper button clicked");
        game = new MiniGame(hand = new Hand("Paper"), GameClient.getInstance().getValueForMinigame());
        Log.d("R.P.S.L.S.", "Hand: " + hand.getPlay() + ", Computer: " + hand.computerPlay());
        executeGame();
    }

    // onClickListener method Scissors
    public void onScissorsButtonClick() {
        Log.d("R.P.S.L.S.", "Scissors button clicked");
        game = new MiniGame(hand = new Hand("Scissors"), GameClient.getInstance().getValueForMinigame());
        Log.d("R.P.S.L.S.", "Hand: " + hand.getPlay() + ", Computer: " + hand.computerPlay());
        executeGame();
    }

    // onClickListener method Lizard
    public void onLizardButtonClick() {
        Log.d("R.P.S.L.S.", "Lizard button clicked");
        game = new MiniGame(hand = new Hand("Lizard"), GameClient.getInstance().getValueForMinigame());
        Log.d("R.P.S.L.S.", "Hand: " + hand.getPlay() + ", Computer: " + hand.computerPlay());
        executeGame();
    }

    // onClickListener method Spock
    public void onSpockButtonClick() {
        Log.d("R.P.S.L.S.", "Spock button clicked");
        game = new MiniGame(hand = new Hand("Spock"), GameClient.getInstance().getValueForMinigame());
        Log.d("R.P.S.L.S.", "Hand: " + hand.getPlay() + ", Computer: " + hand.computerPlay());
        executeGame();
    }
}
