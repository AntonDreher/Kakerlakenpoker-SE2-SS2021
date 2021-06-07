package com.example.kakerlakenpoker;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.os.Bundle;
import android.util.Log;
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

import com.example.kakerlakenpoker.card.Card;
import com.example.kakerlakenpoker.card.Type;
import com.example.kakerlakenpoker.game.Game;
import com.example.kakerlakenpoker.player.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerIngameMainActivity extends AppCompatActivity {

    private Game gameplay;
    private LinearLayout dragView;
    private LinearLayout dragView1;
    private LinearLayout dropViewPlayer1;
    private LinearLayout popUp;
    private LinearLayout messageview;

    private Button sendChallange;
    private Button goBack;
    private EditText writeCardText;
    private Spinner choosePlayer;

    //TextViews für die ausgabe der vorläufigen zahlen oder Nachrichten
    private TextView messageText;
    private TextView krötenView;

    private String playedcard;
    private String guessText;

    private List<String> namesOfPlayer = new ArrayList<String>();
    private Boolean check;

    private List a = new ArrayList<>();

    private Game game;
    private Player me;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_ingameview);

        gameplay = new Game();
        messageText = (TextView) findViewById(R.id.messageText);
        krötenView = (TextView) findViewById(R.id.krotenView);

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
        dragView = (LinearLayout) findViewById(R.id.dragView);
        dragView1 = (LinearLayout) findViewById(R.id.dragView2);
        dropViewPlayer1 = (LinearLayout) findViewById(R.id.Player1);
        choosePlayer = (Spinner) findViewById(R.id.spinnerPlayer);

        setUpSpinner();

        //Hier wird die View touchable
        dragView.setOnTouchListener(new TouchListener());
        dragView1.setOnTouchListener(new TouchListener());

        //Hier wird die Fläche, wo ein Object hineingezogen wird, aktiviert!
        dropViewPlayer1.setOnDragListener(new DragListener());

        //Buttons werden mit funktionen belegt. Back- PopUp Fenster wird geschlossen
        goBack.setOnClickListener((View view)-> setInvisible(popUp));
        sendChallange.setOnClickListener((View view)-> sendChallengeInputs());



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
                    //game.getCurrentPlayer().getHandDeck().removeCard(dragEvent.toString());
                    popUp.setVisibility(View.VISIBLE);
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

    public void setInvisible(LinearLayout linlayout){
        linlayout.setVisibility(View.INVISIBLE);
    }

    public void setTextforResult(String result){
        messageText.setText(result);
        messageview.setVisibility(View.VISIBLE);
    }

    //hollt sich alle Namen der anderen Spieler und fügt die Namen in den Spinner!
    public void setUpSpinner(){
        namesOfPlayer.add("hans");
        namesOfPlayer.add("peter");
        namesOfPlayer.add("susi");
        gameplay.setPlayers(a);
        /*for (Player n : gameplay.getOtherPlayers()) {
            if(null
            namesOfPlayer.add(n.getName());
        }*/
        ArrayAdapter chooser = new ArrayAdapter(PlayerIngameMainActivity.this, android.R.layout.simple_spinner_dropdown_item, namesOfPlayer);
        choosePlayer.setAdapter(chooser);
    }

    public void sendChallengeInputs(){

        checkEditTextInput();
        if(check){
        gameplay.challengeCard(gameplay.getPlayerbyName(choosePlayer.getSelectedItem().toString()), playedcard, guessText);
        this.popUp.setVisibility(View.INVISIBLE);
        }
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

    //möchte man den Stand verändern (Display), ruft man diese Klasse auf.
    public void updateTheCollectedCards(){

    }



    }





