package com.example.kakerlakenpoker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kakerlakenpoker.R;

public class StartScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startscreenview);

        Intent intent = new Intent(this, EnterUserNameActivity.class);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("R.P.S.L.S", "Open Minigame");
                startActivity(intent);
            }
        },4000);
    }
}
