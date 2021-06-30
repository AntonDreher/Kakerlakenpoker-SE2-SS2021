package com.example.kakerlakenpoker.activities;

import android.content.Intent;
import android.os.Bundle;
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
