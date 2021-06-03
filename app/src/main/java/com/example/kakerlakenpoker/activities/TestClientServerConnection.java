package com.example.kakerlakenpoker.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.esotericsoftware.minlog.Log;
import com.example.kakerlakenpoker.R;
import com.example.kakerlakenpoker.card.Card;
import com.example.kakerlakenpoker.card.Type;
import com.example.kakerlakenpoker.game.Turn;
import com.example.kakerlakenpoker.network.NetworkUtils;
import com.example.kakerlakenpoker.network.game.GameClient;
import com.example.kakerlakenpoker.network.game.GameServer;
import com.example.kakerlakenpoker.player.Player;
import com.example.kakerlakenpoker.server.Main;

public class TestClientServerConnection extends AppCompatActivity {
    private Button makeServer;
    private Button makeClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testclientserver);

        makeServer = findViewById(R.id.btnmakeServer);
        makeClient = findViewById(R.id.btnmakeClient);

        makeClient.setOnClickListener(v->makeClient());
        makeServer.setOnClickListener(v->makeTurn());
    }


    public void makeClient(){
        Thread thread = new Thread(){
            @Override
            public void run() {
                GameClient.getInstance().init("");

            }
        };

        thread.start();
    }

    public void makeTurn(){
        Turn turn;
        Player me = GameClient.getInstance().getPlayer();

        Type selectedType = Type.KAKERLAKE;
        Player enemy=null;

        for(Player player: GameClient.getInstance().getGame().getPlayers()){
            if(player.getName().equals(me.getName())){
                me = player;
            }
            if(!player.getName().equals(me.getName()))enemy= player;
        }

        Card selectedCard = me.getHandDeck().getDeck().get(0);

        turn = new Turn(selectedCard, selectedType,enemy);
        GameClient.getInstance().getGame().makeTurn(me,turn);

    }

    public static void main(String[] args) {
        GameServer.getInstance().init();
    }

}
