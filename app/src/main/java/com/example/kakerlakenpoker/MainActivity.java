package com.example.kakerlakenpoker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startview);

        Button searchLobbyButton = (Button) findViewById(R.id.search_button);
        searchLobbyButton.setOnClickListener((View view)-> searchLobby());

    }

    public void searchLobby() {
        intent = new Intent(MainActivity.this, SearchLobbyActivity.class);
        startActivity(intent);
    }

}

