package com.example.kakerlakenpoker;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kakerlakenpoker.network.NetworkUtils;
import com.example.kakerlakenpoker.network.game.GameClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SearchLobbyActivity extends AppCompatActivity {
    Intent intent;
    FloatingActionButton floatingActionButton;
    Button join;
    EditText editTextSearchLobby;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchlobbyview);

        floatingActionButton = findViewById(R.id.floatingActionButtonEnterSearchLobby);
        floatingActionButton.setOnClickListener((View view)->goBack());

        join = findViewById(R.id.searchLobbyBtn);
        join.setOnClickListener((View view)->joinUp());

        editTextSearchLobby = findViewById(R.id.editTextSearchLobby);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void goBack(){
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void joinUp(){
        new Thread(() -> {
            GameClient client = GameClient.getInstance();
            client.init(editTextSearchLobby.getText().toString(), NetworkUtils.getIpAddressFromDevice(getApplicationContext()));
            Intent intent = new Intent(this, ShowPlayersInLobbyActivity.class);
            startActivity(intent);
        }).start();
    }


}
