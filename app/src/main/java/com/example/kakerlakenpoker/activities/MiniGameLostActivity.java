package com.example.kakerlakenpoker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kakerlakenpoker.R;

public class MiniGameLostActivity extends AppCompatActivity {
    Intent intent;

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

        // TextViews
        winCounterGO = (TextView) findViewById(R.id.win_counter_go);
        lossCounterGO = (TextView) findViewById(R.id.loss_counter_go);
        drawCounterGO = (TextView) findViewById(R.id.draw_counter_go);

        // Button MiniGame
        minigameButtonGO = (Button) findViewById(R.id.minigame_go_button);
        minigameButtonGO.setOnClickListener((View view) -> onMinigameButtonGOClick());

        // Button Main Menu
        mainmenuButtonGO = (Button) findViewById(R.id.mainmenu_go_button);
        mainmenuButtonGO.setOnClickListener((View view) -> onMainMenuGOClick());
        mainmenuButtonGO.setVisibility(View.INVISIBLE);
    }

    // onClickListener method MinigameButtonGo
    public void onMinigameButtonGOClick() {
        Log.d("R.P.S.L.S", "MiniGame button clicked");
        intent = new Intent(this, MiniGameActivity.class);
        startActivity(intent);
    }

    // onClickListener method MainMenuButtonGo
    public void onMainMenuGOClick() {
        Log.d("RR.P.S.L.S", "Main Menu button clicked");
        intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }
}
