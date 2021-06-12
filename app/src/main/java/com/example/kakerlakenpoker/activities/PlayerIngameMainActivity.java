package com.example.kakerlakenpoker.activities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import com.esotericsoftware.minlog.Log;
import com.example.game.Decision;
import com.example.game.GameState;
import com.example.game.Turn;
import com.example.game.card.Card;
import com.example.game.card.Type;
import com.example.game.listener.StateListener;
import com.example.game.player.CollectedDeck;
import com.example.game.player.PlayerState;
import com.example.kakerlakenpoker.R;
import com.example.game.player.Player;
import com.example.server.network.dto.clienttogameserver.HandOver;

import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PlayerIngameMainActivity extends AppCompatActivity implements SensorEventListener{


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
    Spinner types;
    ArrayList<Type> typeList;
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
    Dialog diaWait;
    Dialog diaDecision;
    private AlertDialog alertDialog;
    private TextView waitingDialogTextView;

    List<String> namesOfPlayer = new ArrayList<String>();
    Boolean check;
    MutableLiveData<GameState> stateListen = new MutableLiveData<GameState>();
    Player me = null;

    //Werte für den Handy-Shake
    private CheckBox cheatbox;
    private Sensor accelerometer;
    private SensorManager ShakeSensorManager;
    private float wert1;
    private float wert2;
    private float wert3;
    Boolean erlaubnis = false;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_ingameview);
        diaDecision = new Dialog(this);
        diaWait = new Dialog(this);
        me = getLocalPlayer();
        me.getHandDeck().countAllCards();


        messageText = (TextView) findViewById(R.id.messageText);

        krötenView = (TextView) findViewById(R.id.kakerlakenView);
        spinnenView = findViewById(R.id.fliegeView);
        fliegenView = findViewById(R.id.rattenView);
        scorpionView = findViewById(R.id.stinkwanzeView);
        kakerlakeView = findViewById(R.id.fledermausView);
        ratteView = findViewById(R.id.spinnenVIew);
        fledermausView = findViewById(R.id.skorpionView);
        stinkwanzeView = findViewById(R.id.krotenView);

        displayCardAmounts();
        initializeDialogs();
        checkTurn();



        //init des popUp Fensters
        messageview = (LinearLayout) findViewById(R.id.notificationView);
        popUp = (LinearLayout) findViewById(R.id.popupview);
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
        goBack.setOnClickListener((View view) -> setInvisible(popUp));
        sendChallange.setOnClickListener((View view) -> sendChallengeInputs());

        GameClient.getInstance().getGame().setStateListener(new StateListenerImpl());

        //Init SensorManger
        ShakeSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        //Observer, der bei Änderung des GameState die Activity neu ladet
        stateListen.setValue(GameClient.getInstance().getGame().getCurrentState());
        stateListen.observe(this, Observer -> {

        });

        //Handling und init für den Cheat
        cheatbox = (CheckBox) findViewById(R.id.checkBox);
        ShakeSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = ShakeSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        cheatbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Bitte Handy schütteln!!!", Toast.LENGTH_SHORT).show();
                erlaubnis = true;
            }
        });


    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    class DragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent dragEvent) {
            switch (dragEvent.getAction()) {

                case DragEvent.ACTION_DRAG_STARTED: {
                    View view = (View) dragEvent.getLocalState();
                    playedcard = String.valueOf(view.getTag());
                    break;
                }

                case DragEvent.ACTION_DRAG_ENTERED: {
                    break;
                }

                case DragEvent.ACTION_DRAG_LOCATION: {

                    break;
                }

                case DragEvent.ACTION_DRAG_EXITED: {
                    break;
                }

                case DragEvent.ACTION_DRAG_ENDED:


                case DragEvent.ACTION_DROP: {
                    dropCorrect(dragEvent);
                    return (true);
                }

                default: {
                    break;
                }
            }
            return true;
        }

    }

    class TouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder dragShadow = new View.DragShadowBuilder(view);
                view.startDrag(data, dragShadow, view, 0);

                return true;
            } else {
                return false;
            }
        }
    }

    public void dropCorrect(DragEvent de) {

        if (de.getResult() == true) {
            popUp.setVisibility(View.VISIBLE);
        } else {
            popUp.setVisibility(View.INVISIBLE);
        }
    }

    public void setInvisible(LinearLayout linlayout) {
        linlayout.setVisibility(View.INVISIBLE);
    }

    public void setTextforResult(String result) {
        messageText.setText(result);
        messageview.setVisibility(View.VISIBLE);
    }

    //hollt sich alle Namen der anderen Spieler und fügt die Namen in den Spinner!
    public void setUpSpinner() {
        for (Player player : GameClient.getInstance().getGame().getPlayers()) {
            if (!(player.getId() == me.getId())) {
                namesOfPlayer.add(String.valueOf(player.getId()));
            }

        }
        ArrayAdapter chooser = new ArrayAdapter(PlayerIngameMainActivity.this, android.R.layout.simple_spinner_dropdown_item, namesOfPlayer);
        choosePlayer.setAdapter(chooser);
    }

    /*
    Hier wird ein Spielzug (TURN) gemacht
     */
    public void sendChallengeInputs() {
        checkEditTextInput();
        if (check) {
            Turn turn;
            Type selectedType = Type.valueOf(guessText);
            Player enemy = null;
            for (Player player : GameClient.getInstance().getGame().getPlayers()) {
                if (player.getId() == Integer.parseInt(choosePlayer.getSelectedItem().toString()))
                    enemy = player;
            }
            assert enemy != null;
            Log.info("This is the selected enemy: " + enemy.getId());

            Card selectedCard = me.getHandDeck().findCard(playedcard);
            Log.info("selected things", selectedType + " " + selectedCard + " " + enemy.getId());
            turn = new Turn(selectedCard, selectedType, enemy);
            GameClient.getInstance().getGame().getCurrentPlayer().getHandDeck().removeCard(selectedCard);
            GameClient.getInstance().getGame().makeTurn(me, turn);
            this.popUp.setVisibility(View.INVISIBLE);
        }
    }

    /*
    Refresh the current view (doesent work!)
     */
    public void refreshView() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    //Methode, die eine Decision (Truth/Lie) ausführt
    public void decission(Decision decision) {
        GameClient.getInstance().getGame().makeDecision(me, decision);
    }


    public void checkEditTextInput() {

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
            default:
                writeCardText.setError("Falscher Type von Karte! Bitte gib eine richtige ein!");
                break;
        }

    }

    //Gibt das Player Object des spielenden Client zurück
    public Player getLocalPlayer() {
        for (Player p : GameClient.getInstance().getGame().getPlayers()) {
            Log.info("This is the players id", String.valueOf(p.getId()));
            Log.info("This is my id", String.valueOf(GameClient.getInstance().getClient().getClient().getID()));
            if (p.getId() == GameClient.getInstance().getClient().getClient().getID()) {
                return p;
            }
        }
        return null;
    }

    /*
    Öffnet einen Dialog, wenn man eine Decision machen muss
     */
    public void showDialogeChallenge() {
        diaDecision = new Dialog(this);
        View view1 = View.inflate(this, R.layout.decision_dialoge, null);
        setUpTypesSpinner(view1);
        diaDecision.setContentView(view1);
        diaDecision.setCanceledOnTouchOutside(false);
        Toast myToast = new Toast(this);
        String selectedCard = GameClient.getInstance().getGame().getTurn().getSelectedCard().getType().toString();
        String chosenTyp = GameClient.getInstance().getGame().getTurn().getSelectedType().toString();
        int player = GameClient.getInstance().getGame().getCurrentPlayer().getId();
        int enemy = GameClient.getInstance().getGame().getTurn().getSelectedEnemy().getId();
        TextView text = diaDecision.findViewById(R.id.info);
        Button buttonTruth = diaDecision.findViewById(R.id.truth);
        Button buttonLie = diaDecision.findViewById(R.id.lie);
        Button buttonHandOver = diaDecision.findViewById(R.id.handOverButton);
        Spinner spinner = view1.findViewById(R.id.handOver);
        ArrayList<String> list = new ArrayList<>();
        for (Player player1 : GameClient.getInstance().getGame().getPlayers()) {
            if (!(player1.getId() == me.getId()) && player1.getState() != PlayerState.PLAYED) {
                list.add(String.valueOf(player1.getId()));
            }
        }

        ArrayAdapter adapter = new ArrayAdapter(PlayerIngameMainActivity.this, android.R.layout.simple_spinner_dropdown_item, list);
        spinner.setAdapter(adapter);
        diaDecision.show();
        myToast.setDuration(Toast.LENGTH_LONG);

        buttonTruth.setOnClickListener(view -> {
            Toast toast = Toast.makeText(this, "Player: " + player + " played: " + selectedCard + " and said: " + chosenTyp + "and you said TRUTH", Toast.LENGTH_LONG);
            toast.show();
            decission(Decision.TRUTH);
            diaDecision.dismiss();
        });

        buttonLie.setOnClickListener(view -> {
            Toast toast = Toast.makeText(this, "Player: " + player + " played: " + selectedCard + " and said: " + chosenTyp + "and you said LIE", Toast.LENGTH_LONG);
            toast.show();
            decission(Decision.LIE);
            diaDecision.dismiss();
        });

        buttonHandOver.setOnClickListener(view -> {

            Player ene = null;
            for (Player p : GameClient.getInstance().getGame().getPlayers()) {
                if (p.getId() == Integer.parseInt(spinner.getSelectedItem().toString())){
                    ene = p;
                };
            }
            Card card = GameClient.getInstance().getGame().getTurn().getSelectedCard();
            assert ene != null;
            Log.info("selected things", selectedCard + " " + card + " " + ene.getId());
            GameClient.getInstance().getGame().handOver(me, new HandOver(ene,Decision.TRUTH));

        });


        String myText = "Player: " + GameClient.getInstance().getGame().getCurrentPlayer().getId() + " says " + GameClient.getInstance().getGame().getTurn().getSelectedType().toString();
        text.setText(myText);
    }

    /*
    Wird geöffnet, wenn man nicht an der Reihe ist
     */
    public void showDialogeWait() {
        String myString = "Players: " + GameClient.getInstance().getGame().getCurrentPlayer().getId() + " turn!";
        waitingDialogTextView.setText(myString);
        alertDialog.show();
    }

    /*
    Wird geöffnet, wenn das Spiel vorbei ist
     */
    public void showDialogeGameOver() {
        Dialog dia = new Dialog(this);
        dia.setContentView(R.layout.game_over_dialoge);
        dia.setCanceledOnTouchOutside(false);
        Button button = dia.findViewById(R.id.back2menu);
        TextView text = dia.findViewById(R.id.textGameOver);
        int lostPlayer = GameClient.getInstance().getGame().getCurrentPlayer().getId();
        if (me.getId() == lostPlayer) {
            String lost = "You lost the game!";
            text.setText(lost);
        } else {
            String won = "You won the game and player: " + lostPlayer + " lost!";
            text.setText(won);
        }
        dia.show();
        button.setOnClickListener(view -> {
            Intent intent = new Intent(PlayerIngameMainActivity.this,MainMenuActivity.class);
            startActivity(intent);
        });
    }

    //möchte man den Stand verändern (Display), ruft man diese Klasse auf.
    public void updateTheCollectedCards() {
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
    public void showEnemyCollectCards() {
        String output = "";

        for (Player all : GameClient.getInstance().getGame().getPlayers()) {

            //hier soll die ausgaben meiner karten verhindert werden.
            if (all == me) {
            } else {
                output += all.getCollectedDeck().toString() + ("\n");
            }
        }
        messageText.setText(output);
        messageview.setVisibility(View.VISIBLE);
    }

    public void displayCardAmounts() {
        krötenView.setText(String.valueOf(me.getHandDeck().getKroete()));
        spinnenView.setText(String.valueOf(me.getHandDeck().getSpinne()));
        fliegenView.setText(String.valueOf(me.getHandDeck().getFliege()));
        scorpionView.setText(String.valueOf(me.getHandDeck().getScorpion()));
        kakerlakeView.setText(String.valueOf(me.getHandDeck().getKakerlake()));
        ratteView.setText(String.valueOf(me.getHandDeck().getRatte()));
        fledermausView.setText(String.valueOf(me.getHandDeck().getFledermaus()));
        stinkwanzeView.setText(String.valueOf(me.getHandDeck().getStinkwanze()));
    }

    public void checkTurn() {
        Log.info("The turn is checked here");
        Toast toast = Toast.makeText(this, "!!!", Toast.LENGTH_LONG);


        if(GameClient.getInstance().getGame().getCurrentState() == GameState.AWAITING_TURN){
            if(me.getId()==GameClient.getInstance().getGame().getCurrentPlayer().getId()){
               toast = Toast.makeText(this, "Your Turn!", Toast.LENGTH_LONG);
            } else {
                showDialogeWait();
                toast = Toast.makeText(this, "Player is making a turn!", Toast.LENGTH_LONG);
            }
        } else if(GameClient.getInstance().getGame().getCurrentState() == GameState.AWAITING_DECISION){
            if(me.getId()!=GameClient.getInstance().getGame().getTurn().getSelectedEnemy().getId()){
                toast = Toast.makeText(this, "Waiting for a decision!", Toast.LENGTH_LONG);
                showDialogeWait();
            } else if(me.getId()==GameClient.getInstance().getGame().getTurn().getSelectedEnemy().getId()){
                toast = Toast.makeText(this, "Make a decision!", Toast.LENGTH_LONG);
                showDialogeChallenge();
            }
        } else if (GameClient.getInstance().getGame().getCurrentState() == GameState.GAME_OVER) {
            Log.info("Game ist over!");
            toast = Toast.makeText(this, "Game is over!", Toast.LENGTH_LONG);
            showDialogeGameOver();


        } else  toast = Toast.makeText(this, "unclear state!", Toast.LENGTH_LONG);

        toast.show();

    }

    public void setUpTypesSpinner(View view) {
        types = (Spinner) view.findViewById(R.id.spinnerType);
        typeList = new ArrayList<>();
        typeList.addAll(Arrays.asList(Type.values()));
        ArrayAdapter typAdapter = new ArrayAdapter(PlayerIngameMainActivity.this, android.R.layout.simple_spinner_dropdown_item, typeList);
        types.setAdapter(typAdapter);
    }

    public void initializeDialogs(){
        initializeWaitingDialog();
        initializeChallengeDialog();
        initializeGameOverDialog();
    }

    public void initializeWaitingDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = View.inflate(this, R.layout.waiting_dialoge, null);
        builder.setView(view);
        alertDialog = builder.create();
        alertDialog.setCancelable(false);
        waitingDialogTextView = view.findViewById(R.id.notYoutTurn);

    }

    public void initializeChallengeDialog(){

    }

    public void initializeGameOverDialog(){


    }


    class StateListenerImpl extends StateListener {

        @Override
        public void inform() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (GameClient.getInstance().getGame().checkRoundOver()) {
                        GameClient.getInstance().getGame().resetPlayerStatus();
                    }

                    diaDecision.dismiss();
                    //diaWait.dismiss();
                    if(alertDialog !=null)alertDialog.hide();

                    displayCardAmounts();
                    checkTurn();

                }
            });

        }
    }

    //Methoden für das Verwenden des Shakes und des Cheats
    public void CheckboxClicked(View v) {
        while(true) {
            if (((CheckBox) v).isChecked()) {
                ShakeSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
                accelerometer = ShakeSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            }
        }
    }
    //Methode für das finden eines Shakes!
    @Override
    public void onSensorChanged(SensorEvent e) {

        List changer = new ArrayList();


        float x,y,z;
        x = e.values[0];
        y = e.values[1];
        z = e.values[2];

        float Xextract = Math.abs(wert1 - x );
        float Yextract = Math.abs(wert2 - y );
        float Zextract = Math.abs(wert3 - z );

        if (Xextract < (float) 0.8) {
            Xextract = (float) 0.0;
        }

        if (Yextract < (float) 0.8) {
            Yextract = (float) 0.0;
        }

        if (Zextract < (float) 0.8) {
            Zextract = (float) 0.0;
        }

        //Zwischenspeicher
        wert1 = x;
        wert2 = y;
        wert3 = z;

        if (Xextract > Yextract) {
            if(erlaubnis == true){
                ChangePlayersCollectedDecks();
            }
        }

    }

    //tauscht die Collected Karten unter den Spielern
    public void ChangePlayersCollectedDecks(){

        List changer = new ArrayList();

        Toast.makeText(getApplicationContext(), "Cheat wird ausgeführt!!!", Toast.LENGTH_SHORT).show();

        for (Player player : GameClient.getInstance().getGame().getPlayers()) {
            changer.add(player.getCollectedDeck().getDeck());
        }

        Collections.reverse(changer);

        for (Player player : GameClient.getInstance().getGame().getPlayers()) {
            player.getCollectedDeck().setDeck(changer);
            changer.remove(0);
        }

        erlaubnis = false;
        cheatbox.setVisibility(View.INVISIBLE);
    }

    //Default methode fürs Handling
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    //Sollte wieder geschüttelt werden, akitivert sich der Listener
    @Override
    protected void onResume() {
        super.onResume();
        ShakeSensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }
    //Sollte nicht geschüttelt werden, wird der Listerner abgeschalten.
    @Override
    protected void onPause() {
        super.onPause();
        ShakeSensorManager.unregisterListener(this);
    }
}










