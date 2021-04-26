package com.example.kakerlakenpoker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SearchLobbyActivity extends AppCompatActivity {
    Intent intent;
    FloatingActionButton floatingActionButton;
    Button join;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchlobbyview);

        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener((View view)->goBack());

        //join = findViewById(R.id.);
        //join.setOnClickListener((View view)->joinUp());


    }

    public void goBack(){
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void joinUp(){}


}
