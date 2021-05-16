package com.example.kakerlakenpoker.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.kakerlakenpoker.R;


public class MainMenuActivity extends AppCompatActivity {
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);

        Button createLobbyButton = (Button) findViewById(R.id.create_lobby_button);
        createLobbyButton.setOnClickListener((View view)-> createLobby());


        Button searchLobbyButton = (Button) findViewById(R.id.search_button);
        searchLobbyButton.setOnClickListener((View view)-> searchLobby());

    }

    public void searchLobby() {
        intent = new Intent(MainMenuActivity.this, SearchLobbyActivity.class);
        startActivity(intent);
    }

    public void createLobby() {
        intent = new Intent(MainMenuActivity.this, CreateLobbyActivity.class);
        startActivity(intent);
    }

}

