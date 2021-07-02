package com.example.kakerlakenpoker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kakerlakenpoker.R;

public class MiniGameWonActivity extends AppCompatActivity {
    //variables from mini game activity
    int counterWon;
    int counterLoss;
    int counterDraw;
    int counterWinOutOf5;

    Intent intent;
    Intent getIntent;

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

        // set TextViews
        winCounterWON.setText("Your Wins: " + counterWon);
        lossCounterWON.setText("Computer Wins: " + counterLoss);
        drawCounterWON.setText("Draws: " + counterDraw);
        winOutOf5WON.setText("Win Points: " + counterWinOutOf5);

        // Button MiniGame
        minigameButtonWON = (Button) findViewById(R.id.minigame_win_button);
        minigameButtonWON.setOnClickListener((View view) -> onMinigameButtonWONClick());

        // Button Main Menu
        mainmenuButtonWON = (Button) findViewById(R.id.mainmenu_win_button);
        mainmenuButtonWON.setOnClickListener((View view) -> onMainMenuWONClick());
        mainmenuButtonWON.setVisibility(View.INVISIBLE);
    }

    // onClickListener method MinigameButtonWON
    public void onMinigameButtonWONClick() {
        Log.d("R.P.S.L.S", "MiniGame button clicked");
        intent = new Intent(this, MiniGameActivity.class);
        startActivity(intent);
    }

    // onClickListener method MainMenuButtonWON
    public void onMainMenuWONClick() {
        Log.d("R.P.S.L.S", "Main Menu button clicked");
        intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }
}
