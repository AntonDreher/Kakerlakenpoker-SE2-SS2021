package com.example.kakerlakenpoker;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.esotericsoftware.minlog.Log;
import com.example.kakerlakenpoker.card.Card;
import com.example.kakerlakenpoker.card.Type;
import com.example.kakerlakenpoker.game.Decision;
import com.example.kakerlakenpoker.game.Game;
import com.example.kakerlakenpoker.game.GameState;
import com.example.kakerlakenpoker.game.Turn;
import com.example.kakerlakenpoker.network.game.GameClient;
import com.example.kakerlakenpoker.network.game.GameServer;
import com.example.kakerlakenpoker.player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerIngameMainActivity extends AppCompatActivity {

    LinearLayout dragViewKakerlake;
    LinearLayout dragViewBat;
    LinearLayout dragViewFly;
    LinearLayout dragViewFrog;
    LinearLayout dragViewRat;
    LinearLayout dragViewScorpion;
    LinearLayout dragViewSpider;
    LinearLayout dragViewStink;

    LinearLayout dropViewPlayer1;
    LinearLayout popUp;
    LinearLayout messageview;

    Button sendChallange;
    Button goBack;
    EditText writeCardText;
    Spinner choosePlayer;

    //TextViews für die ausgabe der vorläufigen zahlen oder Nachrichten
    TextView messageText;
    TextView krötenView;
    TextView spinnenView;
    TextView fliegenView;
    TextView scorpionView;
    TextView kakerlakeView;
    TextView ratteView;
    TextView stinkwanzeView;
    TextView fledermausView;

    String playedcard;
    String guessText;

    List<String> namesOfPlayer = new ArrayList<String>();
    Boolean check;

    List a = new ArrayList<>();

    Player me = null;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_ingameview);

        me = getLocalPlayer();

        messageText = (TextView) findViewById(R.id.messageText);

        krötenView = (TextView) findViewById(R.id.krotenView);
        krötenView.setText(me.getHandDeck().getKroete());

        spinnenView = findViewById(R.id.spinnenVIew);
        spinnenView.setText(me.getHandDeck().getSpinne());

        fliegenView = findViewById(R.id.fliegeView);
        fliegenView.setText(me.getHandDeck().getFliege());

        scorpionView = findViewById(R.id.skorpionView);
        scorpionView.setText(me.getHandDeck().getScorpion());

        kakerlakeView = findViewById(R.id.kakerlakenView);
        kakerlakeView.setText(me.getHandDeck().getKakerlake());

        ratteView = findViewById(R.id.rattenView);
        ratteView.setText(me.getHandDeck().getRatte());

        fledermausView = findViewById(R.id.fledermausView);
        fledermausView.setText(me.getHandDeck().getFledermaus());

        stinkwanzeView = findViewById(R.id.stinkwanzeView);
        stinkwanzeView.setText(me.getHandDeck().getStinkwanze());


        //init des popUp Fensters
        messageview = (LinearLayout) findViewById(R.id.notificationView);
        popUp = (LinearLayout)  findViewById(R.id.popupview);
        messageview.setVisibility(View.INVISIBLE);
        popUp.setVisibility(View.INVISIBLE);

        //init der Buttons und Edit Text
        sendChallange = (Button) findViewById(R.id.sendButton);
        goBack = (Button) findViewById(R.id.exitButton);
        writeCardText = (EditText) findViewById(R.id.guessText);

        //init der KartenLayouts, Spinner(Wahl des Spielers) und der PlayerViews
        dragViewKakerlake = (LinearLayout) findViewById(R.id.dragViewKakerlake);
        dragViewBat = (LinearLayout) findViewById(R.id.dragViewBat);
        dragViewFly = (LinearLayout) findViewById(R.id.dragViewFly);
        dragViewFrog = (LinearLayout) findViewById(R.id.dragViewFrog);
        dragViewRat = (LinearLayout) findViewById(R.id.dragViewRat);
        dragViewScorpion = (LinearLayout) findViewById(R.id.dragViewScorpion);
        dragViewSpider = (LinearLayout) findViewById(R.id.dragViewSpider);
        dragViewStink = (LinearLayout) findViewById(R.id.dragViewStink);

        dropViewPlayer1 = (LinearLayout) findViewById(R.id.Player1);
        choosePlayer = (Spinner) findViewById(R.id.spinnerPlayer);

        setUpSpinner();

        //Hier wird die View touchable
        dragViewKakerlake.setOnTouchListener(new TouchListener());
        dragViewBat.setOnTouchListener(new TouchListener());
        dragViewFly.setOnTouchListener(new TouchListener());
        dragViewFrog.setOnTouchListener(new TouchListener());
        dragViewRat.setOnTouchListener(new TouchListener());
        dragViewScorpion.setOnTouchListener(new TouchListener());
        dragViewSpider.setOnTouchListener(new TouchListener());
        dragViewStink.setOnTouchListener(new TouchListener());

        //Hier wird die Fläche, wo ein Object hineingezogen wird, aktiviert!
        dropViewPlayer1.setOnDragListener(new DragListener());

        //Buttons werden mit funktionen belegt. Back- PopUp Fenster wird geschlossen
        goBack.setOnClickListener((View view)-> setInvisible(popUp));
        sendChallange.setOnClickListener((View view)-> sendChallengeInputs());


        if(me!= GameClient.getInstance().getGame().getCurrentPlayer()){
            Log.debug("Not your turn!");
            //Hier könnte ein Fenster aufgehen, welches Zeigt, welcher Spieler gerade an der Reihe ist
        }

        if(GameClient.getInstance().getGame().getCurrentState() == GameState.AWAITING_DECISION && GameClient.getInstance().getGame().getCurrentPlayer() == me){
            //Hier müsste dann ein Fenster oder so aufgehen zum auswählen ob Wahr, Falsch usw.
            Log.debug("You have to make a decission!");

        }

        MutableLiveData<GameState> stateListen = new MutableLiveData<GameState>();
        stateListen.setValue(GameClient.getInstance().getGame().getCurrentState());
        stateListen.observe(this, gameState -> refreshView());


    }

    class DragListener implements View.OnDragListener{

        @Override
        public boolean onDrag(View v, DragEvent dragEvent) {
            switch (dragEvent.getAction()) {

                case DragEvent.ACTION_DRAG_STARTED:{
                    View view = (View) dragEvent.getLocalState();
                    playedcard = String.valueOf(view.getTag());
                    break;}

                case DragEvent.ACTION_DRAG_ENTERED:{
                    break;}

                case DragEvent.ACTION_DRAG_LOCATION:{

                    break;}

                case DragEvent.ACTION_DRAG_EXITED:{
                    break;}

                case DragEvent.ACTION_DRAG_ENDED:


                case DragEvent.ACTION_DROP:{
                    dropCorrect(dragEvent);
                    return (true);}

                default:{
                    break;}
            }
            return true;
        }

        }

    class TouchListener implements View.OnTouchListener{
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder dragShadow = new View.DragShadowBuilder(view);
                view.startDrag(data, dragShadow, view, 0);

                return true;
            }else {return false;}
        }
    }

    public void dropCorrect(DragEvent de){

        if(de.getResult() == true){
            popUp.setVisibility(View.VISIBLE);
        }
        else{ popUp.setVisibility(View.INVISIBLE);}
    }

    public void setInvisible(LinearLayout linlayout){
        linlayout.setVisibility(View.INVISIBLE);
    }

    public void setTextforResult(String result){
        messageText.setText(result);
        messageview.setVisibility(View.VISIBLE);
    }

    //hollt sich alle Namen der anderen Spieler und fügt die Namen in den Spinner!
    public void setUpSpinner(){
        for (Player player : GameClient.getInstance().getGame().getPlayers()){
            namesOfPlayer.add(player.getName());
        }
        ArrayAdapter chooser = new ArrayAdapter(PlayerIngameMainActivity.this, android.R.layout.simple_spinner_dropdown_item, namesOfPlayer);
        choosePlayer.setAdapter(chooser);
    }

    public void sendChallengeInputs(){
        checkEditTextInput();
        if(check){
            Turn turn;
            Type selectedType = Type.valueOf(guessText);
            Player enemy = null;
            for(Player player: GameClient.getInstance().getGame().getPlayers()){
                if(player.getName().equals(choosePlayer.getSelectedItem().toString()))
                    enemy = player;
            }
            Card selectedCard = me.getHandDeck().findCard(playedcard);
            turn = new Turn(selectedCard, selectedType,enemy);
            GameClient.getInstance().getGame().makeTurn(me,turn);

        this.popUp.setVisibility(View.INVISIBLE);
        }
    }
    /*
    Refresh the current view
     */
    public void refreshView(){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    public void decission(){
        Decision decision = null;
        GameClient.getInstance().getGame().makeDecision(me,decision);
    }


    public void checkEditTextInput(){

        check = false;
        String input = writeCardText.getText().toString().toUpperCase();

        switch (input) {
            case "FLEDERMAUS":
                guessText = input;
                check = true;
                break;
            case "FLIEGE":
                guessText = input;
                check = true;
                break;
            case "RATTE":
                guessText = input;
                check = true;
                break;
            case "SCORPION":
                guessText = input;
                check = true;
                break;
            case "KAKERLAKE":
                guessText = input;
                check = true;
                break;
            case "KROETE":
                guessText = input;
                check = true;
                break;
            case "SPINNE":
                guessText = input;
                check = true;
                break;
            case "STINKWANZE":
                guessText = input;
                check = true;
                break;
            default: writeCardText.setError("Falscher Type von Karte! Bitte gib eine richtige ein!"); break;
        }

    }
    public Player getLocalPlayer() {
        for (Player p : GameClient.getInstance().getGame().getPlayers()) {
            if (p.getID().equals(String.valueOf(GameClient.getInstance().getClient().getClient().getID()))) {
                return p;
            }
        }
        return null;
    }
        //möchte man den Stand verändern (Display), ruft man diese Klasse auf.
        public void updateTheCollectedCards () {

        }
    }








