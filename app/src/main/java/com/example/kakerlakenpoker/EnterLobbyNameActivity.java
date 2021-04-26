package com.example.kakerlakenpoker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EnterLobbyNameActivity extends AppCompatActivity {
    FloatingActionButton floatingActionButton;
    Button startBtn;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mp_lobbyname);

        floatingActionButton = findViewById(R.id.floatingActionButtonEnterName);
        floatingActionButton.setOnClickListener((View view)->goBack());

        startBtn = findViewById(R.id.startLobbyBtn);
        startBtn.setOnClickListener((View view)->startLobby());

    }

    public void goBack(){
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void startLobby() {
        //intent = new Intent(this, SearchLobbyActivity.class);
        //startActivity(intent);
    }

}
