package com.example.kakerlakenpoker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.esotericsoftware.minlog.Log;
import com.example.kakerlakenpoker.R;
import com.example.game.card.Card;
import com.example.game.card.Type;
import com.example.game.Decision;
import com.example.game.Turn;
import com.example.server.network.game.GameData;

import com.example.game.player.Player;
import com.example.server.NetworkConstants;


import java.util.Collections;

public class TestClientServerConnection extends AppCompatActivity {
    private Button makeServer;
    private Button makeClient;
    private Button makeDecision;
    private Button showGameView;
    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testclientserver);

        makeServer = findViewById(R.id.btnmakeServer);
        makeClient = findViewById(R.id.btnmakeClient);
        makeDecision = findViewById(R.id.btnmakeDecision);
        showGameView = findViewById(R.id.showGameView);
        text = findViewById(R.id.playeridTextView);


        showGameView.setOnClickListener(v->showGameView());
        makeClient.setOnClickListener(v->makeClient());
        /*makeServer.setOnClickListener(v->makeTurn());
        makeDecision.setOnClickListener(v->makeDecision());*/
    }


    public void makeClient() {
        Thread thread = new Thread(){
            @Override
            public void run() {
                GameClient.getInstance().init(NetworkConstants.MAIN_SERVER_IP);

            }
        };

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        text.setText(String.valueOf(GameClient.getInstance().getClient().getClient().getID()));
    }

    public void showGameView(){
        Intent intent = new Intent(this, PlayerIngameMainActivity.class);
        startActivity(intent);
    }

   /* public void makeTurn(){
        Turn turn;
        Player me=null;

        Type selectedType = Type.KAKERLAKE;
        Player enemy=null;

        Collections.shuffle(GameClient.getInstance().getGame().getPlayers());

        for(Player player: GameClient.getInstance().getGame().getPlayers()){
            Log.info(String.valueOf(GameClient.getInstance().getClient().getClient().getID()));
            Log.info(player.getId());
            if(player.getId().equals(String.valueOf(GameClient.getInstance().getClient().getClient().getID()))){
                me = player;
            }
            if(!player.getId().equals(String.valueOf(GameClient.getInstance().getClient().getClient().getID())))enemy= player;
        }

        assert me != null;
        Card selectedCard = me.getHandDeck().getDeck().get(0);


        turn = new Turn(selectedCard, selectedType,enemy);
        GameClient.getInstance().getGame().makeTurn(me,turn);

    }

    public void makeDecision(){
        Collections.shuffle(GameClient.getInstance().getGame().getPlayers());
        Player me=null;
        for(Player player: GameClient.getInstance().getGame().getPlayers()){
            Log.info(String.valueOf(GameClient.getInstance().getClient().getClient().getID()));
            Log.info(player.getId());
            if(player.getId().equals(String.valueOf(GameClient.getInstance().getClient().getClient().getID()))){
                me = player;
            }

        }
        GameClient.getInstance().getGame().makeDecision(me, Decision.TRUTH);
    }*/


}
