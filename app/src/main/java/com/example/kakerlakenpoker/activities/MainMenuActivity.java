package com.example.kakerlakenpoker.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.game.Game;
import com.example.kakerlakenpoker.R;
import com.example.server.dto.clienttomainserver.ClientJoinedRequest;
import com.example.server.dto.clienttomainserver.GetOpenLobbies;
import com.example.server.network.NetworkUtils;


public class MainMenuActivity extends AppCompatActivity {
    private Intent intent;
    private Button minigameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);

        //Create Lobby Button
        Button createLobbyButton = (Button) findViewById(R.id.create_lobby_button);
        createLobbyButton.setOnClickListener((View view)-> createLobby());

        //Search Lobby Button
        Button searchLobbyButton = (Button) findViewById(R.id.search_button);
        searchLobbyButton.setOnClickListener((View view)-> searchLobby());

        //Minigame Button
        minigameButton = (Button) findViewById(R.id.minigame_button);
        minigameButton.setOnClickListener((View view) -> onMiniGameButtonClick());
        minigameButton.setVisibility(View.INVISIBLE);
    }

    public void searchLobby() {
        Log.d("Lobby", "Search Lobby button clicked");
        intent = new Intent(MainMenuActivity.this, SearchLobbyActivity.class);
        startActivity(intent);
    }

    public void createLobby() {
        Log.d("Lobby", "Create Lobby button clicked");
        intent = new Intent(MainMenuActivity.this, CreateLobbyActivity.class);
        startActivity(intent);
    }

    public void onMiniGameButtonClick(){
        Log.d("R.P.S.L.S.", "Play button clicked");
        intent = new Intent(this, MiniGameActivity.class);
        startActivity(intent);
    }
}

