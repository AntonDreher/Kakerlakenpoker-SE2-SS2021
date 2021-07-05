package com.example.kakerlakenpoker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kakerlakenpoker.R;
import com.example.minigame.Counter;

public class MiniGameWonActivity extends AppCompatActivity {
    //variable for main menu activity startActivityForResult
    public static final String RETURN_WON_COUNTER_WON = "RETURN_WON_COUNTER_WON";
    public static final String RETURN_LOSS_COUNTER_WON = "RETURN_LOSS_COUNTER_WON";
    public static final String RETURN_DRAW_COUNTER_WON = "RETURN_DRAW_COUNTER_WON";
    public static final String RETURN_ROUND_COUNTER_WON = "RETURN_ROUND_COUNTER_WON";
    public static final String RETURN_WINOUTOF5_COUNTER_WON = "RETURN_WINOUTOF5_COUNTER_WON";

    //variables from mini game activity
    int counterWon;
    int counterLoss;
    int counterDraw;
    int counterRound;
    int counterWinOutOf5;

    Intent intent;
    Intent getIntent;
    Intent returnIntent;

    Counter counter;

    TextView winCounterWON;
    TextView lossCounterWON;
    TextView drawCounterWON;
    TextView winOutOf5WON;

    Button minigameButtonWON;
    Button mainmenuButtonWON;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("R.P.S.L.S", "onCreate call MiniGameWonActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minigame_won);

        // get counters from mini game activity
        getIntent = getIntent();
        counterWon = getIntent.getIntExtra(MiniGameActivity.WON_COUNTER,0);
        counterLoss = getIntent.getIntExtra(MiniGameActivity.LOSS_COUNTER, 0);
        counterDraw = getIntent.getIntExtra(MiniGameActivity.DRAW_COUNTER, 0);
        counterWinOutOf5 = getIntent.getIntExtra(MiniGameActivity.WINOUTOF5_COUNTER, 0);

        // TextViews
        winCounterWON = (TextView) findViewById(R.id.win_counter_win);
        lossCounterWON = (TextView) findViewById(R.id.loss_counter_win);
        drawCounterWON = (TextView) findViewById(R.id.draw_counter_win);
        winOutOf5WON = (TextView) findViewById(R.id.winoutof5_counter_win);
        counter = new Counter();

        // set TextViews
        winCounterWON.setText("Your Wins: " + counterWon);
        lossCounterWON.setText("Computer Wins: " + counterLoss);
        drawCounterWON.setText("Draws: " + counterDraw);
        winOutOf5WON.setText("Win Points: " + counterWinOutOf5);
        Log.d("R.P.S.L.S.", "MGWA set counters, " + " ,Win Counter: " + counterWon + " ,counterWinOutOf5: " + counterWinOutOf5);

        // Button MiniGame
        minigameButtonWON = (Button) findViewById(R.id.minigame_win_button);
        minigameButtonWON.setOnClickListener((View view) -> onMinigameButtonWONClick());

        // Button Main Menu
        mainmenuButtonWON = (Button) findViewById(R.id.maingame_win_button);
        mainmenuButtonWON.setOnClickListener((View view) -> onMainMenuWONClick());
        // mainmenuButtonWON.setVisibility(View.INVISIBLE);
    }

    public void resetGameCounters(){
        // reset variables for activity intent
        Log.d("R.P.S.L.S", "MGWA reset counters");
        counterWon = 0;
        counterLoss = 0;
        counterDraw = 0;
        counterRound = 0;
        Log.d("R.P.S.L.S", "counterWon: " + counterWon + " ,counterRound: " + counterRound);
    }

    // onClickListener method MinigameButtonWON
    public void onMinigameButtonWONClick() {
        Log.d("R.P.S.L.S", "MGWA back to mini Game button clicked");
        Log.d("R.P.S.L.S.", "counterWinOutOf: " + counterWinOutOf5);
        resetGameCounters();
        returnIntent = new Intent(this, MiniGameActivity.class);
        returnIntent.putExtra(RETURN_WON_COUNTER_WON, counterWon);
        returnIntent.putExtra(RETURN_LOSS_COUNTER_WON, counterLoss);
        returnIntent.putExtra(RETURN_DRAW_COUNTER_WON, counterDraw);
        returnIntent.putExtra(RETURN_ROUND_COUNTER_WON, counterRound);
        returnIntent.putExtra(RETURN_WINOUTOF5_COUNTER_WON, counterWinOutOf5);
        setResult(RESULT_OK, returnIntent);
        finish();
    }

    // onClickListener method MainMenuButtonWON
    public void onMainMenuWONClick() {
        Log.d("R.P.S.L.S", "MGWA EnterUserName button clicked");
        Log.d("R.P.S.L.S.", "counterWinOutOf: " + counterWinOutOf5);
        intent = new Intent(this, MainMenuActivity.class);
        intent.putExtra(RETURN_WINOUTOF5_COUNTER_WON, counterWinOutOf5);
        startActivity(intent);
    }
}
