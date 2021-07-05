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

public class MiniGameLostActivity extends AppCompatActivity {
    //variable for main menu activity
    public static final String RETURN_WON_COUNTER_GO = "RETURN_WON_COUNTER_GO";
    public static final String RETURN_LOSS_COUNTER_GO = "RETURN_LOSS_COUNTER_GO";
    public static final String RETURN_DRAW_COUNTER_GO = "RETURN_DRAW_COUNTER_GO";
    public static final String RETURN_ROUND_COUNTER_GO = "RETURN_ROUND_COUNTER_GO";
    public static final String RETURN_WINOUTOF5_COUNTER_GO = "RETURN_WINOUTOF5_COUNTER_GO";

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

    TextView winCounterGO;
    TextView lossCounterGO;
    TextView drawCounterGO;

    Button minigameButtonGO;
    Button mainmenuButtonGO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("R.P.S.L.S", "onCreate call MiniGameLostActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minigame_go);

        // get counters from mini game activity
        getIntent = getIntent();
        counterWon = getIntent.getIntExtra(MiniGameActivity.WON_COUNTER,0);
        counterLoss = getIntent.getIntExtra(MiniGameActivity.LOSS_COUNTER, 0);
        counterDraw = getIntent.getIntExtra(MiniGameActivity.DRAW_COUNTER, 0);
        Log.d("R.P.S.L.S.", "MG Lost Activity set counters, " + " ,Win Counter: " + counterWon + " ,counterWinOutOf5: " + counterWinOutOf5);

        // TextViews
        winCounterGO = (TextView) findViewById(R.id.win_counter_go);
        lossCounterGO = (TextView) findViewById(R.id.loss_counter_go);
        drawCounterGO = (TextView) findViewById(R.id.draw_counter_go);

        // set TextViews
        winCounterGO.setText("Your Wins: " + counterWon);
        lossCounterGO.setText("Computer Wins: " + counterLoss);
        drawCounterGO.setText("Draws: " + counterDraw);
        Log.d("R.P.S.L.S.", "MGLA TextViews set");

        // Button MiniGame
        minigameButtonGO = (Button) findViewById(R.id.minigame_go_button);
        minigameButtonGO.setOnClickListener((View view) -> onMinigameButtonGOClick());

        // Button Main Menu
        mainmenuButtonGO = (Button) findViewById(R.id.maingame_go_button);
        mainmenuButtonGO.setOnClickListener((View view) -> onMainMenuGOClick());
        // mainmenuButtonGO.setVisibility(View.INVISIBLE);
    }

    public void resetGameCounters(){
        // reset variables for activity intent
        Log.d("R.P.S.L.S", "MGLA reset counters");
        counterWon = 0;
        counterLoss = 0;
        counterDraw = 0;
        counterRound = 0;
        Log.d("R.P.S.L.S", "counterWon: " + counterWon + " ,counterRound: " + counterRound);
    }

    // onClickListener method MinigameButtonGo
    public void onMinigameButtonGOClick() {
        Log.d("R.P.S.L.S", "MGLA back to miniGame button clicked" + " ,counterWinOutOf5: " + counterWinOutOf5);
        resetGameCounters();
        returnIntent = new Intent(this, MiniGameActivity.class);
        returnIntent.putExtra(RETURN_WINOUTOF5_COUNTER_GO, counterWinOutOf5);
        returnIntent.putExtra(RETURN_WON_COUNTER_GO, counterWon);
        returnIntent.putExtra(RETURN_LOSS_COUNTER_GO, counterLoss);
        returnIntent.putExtra(RETURN_DRAW_COUNTER_GO, counterDraw);
        returnIntent.putExtra(RETURN_ROUND_COUNTER_GO, counterRound);
        setResult(RESULT_OK, returnIntent);
        finish();
    }

    // onClickListener method MainMenuButtonGo
    public void onMainMenuGOClick() {
        Log.d("RR.P.S.L.S", "MGWA EnterUserNameActivity button clicked" + "counterWinOutOf5: " + counterWinOutOf5);
        intent = new Intent(this, MainMenuActivity.class);
        intent.putExtra(RETURN_WINOUTOF5_COUNTER_GO, counterWinOutOf5);
        startActivity(intent);
    }
}
