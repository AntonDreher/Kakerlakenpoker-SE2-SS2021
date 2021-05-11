package com.example.kakerlakenpoker;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kakerlakenpoker.network.NetworkUtils;
import com.example.kakerlakenpoker.network.dto.clienttomainserver.GetOpenLobbies;
import com.example.kakerlakenpoker.network.game.GameClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SearchLobbyActivity extends AppCompatActivity {
    private Intent intent;
    private FloatingActionButton floatingActionButton;
    private EditText editTextSearchLobby;
    private RecyclerView recyclerView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchlobbyview);

        floatingActionButton = findViewById(R.id.floatingActionButtonSearchLobbyView);
        floatingActionButton.setOnClickListener((View view)->goBack());

        //StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        //StrictMode.setThreadPolicy(policy);

        Thread thread = new Thread(){
            @Override
            public void run() {
                GameClient.getInstance().getClient().sendMessage(new GetOpenLobbies());
            }
        };

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        initRecyclerView();

    }

    public void goBack(){
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void initRecyclerView(){
        recyclerView = findViewById(R.id.lobbiesRecyclerView);
        Log.e("Thats the size", String.valueOf(GameClient.getInstance().getOpenLobbies().size()));
        LobbiesRecyclerViewAdapter adapter = new LobbiesRecyclerViewAdapter(GameClient.getInstance().getOpenLobbies());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


}
