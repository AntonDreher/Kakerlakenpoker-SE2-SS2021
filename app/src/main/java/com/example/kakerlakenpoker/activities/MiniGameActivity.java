package com.example.kakerlakenpoker.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kakerlakenpoker.R;

public class MiniGameActivity extends AppCompatActivity {
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minigame);

        gameResult = findViewById(R.id.game_result);

        rockButton = findViewById(R.id.rock);
        rockButton.setOnClickListener((View view)-> onRockButtonClick());

        paperButton = findViewById(R.id.paper);
        paperButton.setOnClickListener((View view)-> onPaperButtonClick());

        scissorButton = findViewById(R.id.scissors);
        scissorButton.setOnClickListener((View view)-> onScissorsButtonClick());

        spockButton = findViewById(R.id.spock);
        spockButton.setOnClickListener((View view)-> onSpockButtonClick());

        lizardButton = findViewById(R.id.lizard);
        lizardButton.setOnClickListener((View view)-> onLizardButtonClick());

        winCounter = findViewById(R.id.win_counter);
        lossCounter = findViewById(R.id.loss_counter);
        drawCounter = findViewById(R.id.draw_counter);

    }

    public void onRockButtonClick(){

    }

    public void onPaperButtonClick(){

    }

    public void onScissorsButtonClick(){

    }

    public void onLizardButtonClick(){

    }

    public void onSpockButtonClick(){

    }


}
