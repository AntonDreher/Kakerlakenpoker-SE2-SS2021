package com.example.kakerlakenpoker.activities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.DragEvent;
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

import com.esotericsoftware.minlog.Log;
import com.example.game.Decision;
import com.example.game.GameState;
import com.example.game.Turn;
import com.example.game.card.Card;
import com.example.game.card.Type;
import com.example.kakerlakenpoker.R;
import com.example.game.Game;
import com.example.game.player.Player;

import java.util.ArrayList;
import java.util.Arrays;
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


    private String playedcard;
    private String guessText;


    List<String> namesOfPlayer = new ArrayList<String>();
    Boolean check;
    MutableLiveData<GameState> stateListen = new MutableLiveData<GameState>();
    Player me = null;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_ingameview);

        me = getLocalPlayer();
        me.getHandDeck().countAllCards();

        messageText = (TextView) findViewById(R.id.messageText);

        krötenView = (TextView) findViewById(R.id.krotenView);
        spinnenView = findViewById(R.id.spinnenVIew);
        fliegenView = findViewById(R.id.fliegeView);
        scorpionView = findViewById(R.id.skorpionView);
        kakerlakeView = findViewById(R.id.kakerlakenView);
        ratteView = findViewById(R.id.rattenView);
        fledermausView = findViewById(R.id.fledermausView);
        stinkwanzeView = findViewById(R.id.stinkwanzeView);

        displayCardAmounts();
        checkTurn();


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


        //Observer, der bei Änderung des GameState die Activity neu ladet
        stateListen.setValue(GameClient.getInstance().getGame().getCurrentState());
        stateListen.observe(this, Observer -> {
            displayCardAmounts();
            checkTurn();
        });


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
            if(/*TODO !namesOfPlayer.contains(player.getName()) || */!(player.getId() == me.getId())){
                namesOfPlayer.add(String.valueOf(player.getId())); //TODO
            }

        }
        ArrayAdapter chooser = new ArrayAdapter(PlayerIngameMainActivity.this, android.R.layout.simple_spinner_dropdown_item, namesOfPlayer);
        choosePlayer.setAdapter(chooser);
    }
    /*
    Hier wird ein Spielzug (TURN) gemacht
     */
    public void sendChallengeInputs(){
        checkEditTextInput();
        if(check){
            Turn turn;
            Type selectedType = Type.valueOf(guessText);
            Player enemy = null;
            for(Player player: GameClient.getInstance().getGame().getPlayers()){
                if(player.getId()  == new Integer(choosePlayer.getSelectedItem().toString()))
                    enemy = player;
            }
            Card selectedCard = me.getHandDeck().findCard(playedcard);
            turn = new Turn(selectedCard, selectedType,enemy);
            GameClient.getInstance().getGame().makeTurn(me,turn);
        this.popUp.setVisibility(View.INVISIBLE);
        }
    }
    /*
    Refresh the current view (doesent work!)
     */
    public void refreshView(){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    //Methode, die eine Decision (Truth/Lie) ausführt
    public void decission(Decision decision){
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
    //Gibt das Player Object des spielenden Client zurück
    public Player getLocalPlayer() {
        for (Player p : GameClient.getInstance().getGame().getPlayers()) {
            if (p.getId() == GameClient.getInstance().getClient().getClient().getID()) {
                return p;
            }
        }
        return null;
    }

    /*
    Öffnet einen Dialog, wenn man eine Decision machen muss
     */
    public void showDialogeChallenge(){
        Dialog dia = new Dialog(this);
        dia.setContentView(R.layout.decision_dialoge);
        dia.setCanceledOnTouchOutside(false);
        TextView text = dia.findViewById(R.id.info);
        Button buttonTruth = dia.findViewById(R.id.truth);
        Button buttonLie = dia.findViewById(R.id.lie);
        dia.show();
        buttonTruth.setOnClickListener(view -> {
            decission(Decision.TRUTH);
            dia.dismiss();
        });

        buttonLie.setOnClickListener(view -> {
            decission(Decision.LIE);
            dia.dismiss();
        });

        text.setText("Player: " + /* TODO GameClient.getInstance().getGame().getCurrentPlayer().getName()*/  " played: " + GameClient.getInstance().getGame().getTurn().getSelectedType().toString());

    }

    /*
    Wird geöffnet, wenn man nicht an der Reihe ist
     */
    public void showDialogeWait(){
        Dialog dia = new Dialog(this);
        dia.setContentView(R.layout.waiting_dialoge);
        dia.setCanceledOnTouchOutside(false);
        TextView text = dia.findViewById(R.id.notYoutTurn);
        text.setText("Player: " + /*GameClient.getInstance().getGame().getCurrentPlayer().getName() TODO*/  " turn!");
        dia.show();

    }
        //möchte man den Stand verändern (Display), ruft man diese Klasse auf.
        public void updateTheCollectedCards () {
            krötenView.setText(me.getCollectedDeck().getKroete());
            spinnenView.setText(me.getCollectedDeck().getSpinne());
            fliegenView.setText(me.getCollectedDeck().getFliege());
            scorpionView.setText(me.getCollectedDeck().getScorpion());
            kakerlakeView.setText(me.getCollectedDeck().getKakerlake());
            ratteView.setText(me.getCollectedDeck().getRatte());
            stinkwanzeView.setText(me.getCollectedDeck().getStinkwanze());
            fledermausView.setText(me.getCollectedDeck().getFledermaus());
        }

        //zeigt die gesammelten Karten der Gegner
        public void showEnemyCollectCards(){
        String output = "";

        for(Player all : GameClient.getInstance().getGame().getPlayers()){

            //hier soll die ausgaben meiner karten verhindert werden.
            if(all == me){
            }
            else{ output += all.getCollectedDeck().toString() + ("\n");}
        }
        messageText.setText(output);
        messageview.setVisibility(View.VISIBLE);
        }

        public void displayCardAmounts(){
            krötenView.setText(me.getHandDeck().getKroete());
            spinnenView.setText(me.getHandDeck().getSpinne());
            fliegenView.setText(me.getHandDeck().getFliege());
            scorpionView.setText(me.getHandDeck().getScorpion());
            kakerlakeView.setText(me.getHandDeck().getKakerlake());
            ratteView.setText(me.getHandDeck().getRatte());
            fledermausView.setText(me.getHandDeck().getFledermaus());
            stinkwanzeView.setText(me.getHandDeck().getStinkwanze());
        }

        public void checkTurn(){
            //me ist nicht aktuell am Spiel beteiligt
            if(!(me.getId() == (GameClient.getInstance().getGame().getCurrentPlayer().getId()) || !(me.getId() == GameClient.getInstance().getGame().getTurn().getSelectedEnemy().getId()))){
                Log.debug("Not your turn!");
            //TODO    Log.debug("Current Player: " + GameClient.getInstance().getGame().getCurrentPlayer().getName());
            //TODO    Log.debug("Current Enems: " + GameClient.getInstance().getGame().getTurn().getSelectedEnemy().getName());
                showDialogeWait();
            }

            //Turn wurde ausgeführt und me wurde als Enemy ausgewählt
            if(GameClient.getInstance().getGame().getCurrentState() == GameState.AWAITING_DECISION && !(GameClient.getInstance().getGame().getTurn().getSelectedEnemy().getId() == me.getId())){
            //TODO     Log.debug("Current Player: " + GameClient.getInstance().getGame().getCurrentPlayer().getName());
            //TODO    Log.debug("Current Enems: " + GameClient.getInstance().getGame().getTurn().getSelectedEnemy().getName());
                Log.debug("You have to make a decission!");
                showDialogeChallenge();

            }
        }
    }








