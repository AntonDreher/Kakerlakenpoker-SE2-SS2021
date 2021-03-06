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
import android.os.CountDownTimer;
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
import com.example.game.DecisionCheat;
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
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class PlayerIngameMainActivity extends AppCompatActivity implements SensorEventListener{


    private HashMap<String,Integer> userNameToID;
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
    Button goBack2;
    Button enemyCollectedCards;
    EditText writeCardText;
    Spinner choosePlayer;
    Spinner chooseTypesOfCard;
    Spinner types;
    ArrayList<Type> typeList;
    //TextViews f??r die ausgabe der vorl??ufigen zahlen oder Nachrichten
    TextView messageText;

    TextView kr??tenView;
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
    boolean decisionCheat = false;

    List<String> namesOfPlayer = new ArrayList<String>();
    Boolean check;
    MutableLiveData<GameState> stateListen = new MutableLiveData<GameState>();
    Player me = null;

    //Werte f??r den Handy-Shake
    private CheckBox cheatbox;
    private Sensor accelerometer;
    private SensorManager ShakeSensorManager;
    private float wert1;
    private float wert2;
    private float wert3;
    Boolean activate = false;
    Boolean erlaubnis = false;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_ingameview);
        userNameToID = new HashMap<>();
        initHashMap();
        diaDecision = new Dialog(this);
        diaWait = new Dialog(this);
        me = getLocalPlayer();
        me.getHandDeck().countAllCards();


        messageText = (TextView) findViewById(R.id.messageText);

        kr??tenView = (TextView) findViewById(R.id.krotenView);
        spinnenView = findViewById(R.id.spinnenVIew);
        fliegenView = findViewById(R.id.fliegeView);
        scorpionView = findViewById(R.id.skorpionView);
        kakerlakeView = findViewById(R.id.kakerlakenView);
        ratteView = findViewById(R.id.rattenView);
        fledermausView = findViewById(R.id.fledermausView);
        stinkwanzeView = findViewById(R.id.stinkwanzeView);


        initializeDialogs();
        checkTurn();

        //init des popUp Fensters
        messageview = (LinearLayout) findViewById(R.id.notificationView);
        popUp = (LinearLayout) findViewById(R.id.popupview);
        messageview.setVisibility(View.INVISIBLE);
        popUp.setVisibility(View.INVISIBLE);

        //init der Buttons
        sendChallange = (Button) findViewById(R.id.sendButton);
        goBack = (Button) findViewById(R.id.exitButton);
        goBack2 = (Button) findViewById(R.id.exitButton2);
        //writeCardText = (EditText) findViewById(R.id.guessText);

        //init der KartenLayouts, Spinner(Wahl des Spielers) und der PlayerViews
        dragViewKakerlake = (LinearLayout) findViewById(R.id.dragViewKakerlake);
        dragViewBat = (LinearLayout) findViewById(R.id.dragViewBat);
        dragViewFly = (LinearLayout) findViewById(R.id.dragViewFly);
        dragViewFrog = (LinearLayout) findViewById(R.id.dragViewFrog);
        dragViewRat = (LinearLayout) findViewById(R.id.dragViewRat);
        dragViewScorpion = (LinearLayout) findViewById(R.id.dragViewScorpion);
        dragViewSpider = (LinearLayout) findViewById(R.id.dragViewSpider);
        dragViewStink = (LinearLayout) findViewById(R.id.dragViewStink);

        closeDragViewCards();

        dropViewPlayer1 = (LinearLayout) findViewById(R.id.Player1);
        choosePlayer = (Spinner) findViewById(R.id.spinnerPlayer);
        chooseTypesOfCard = (Spinner) findViewById(R.id.spinnerTypeOfCard);

        setUpSpinnerForChallengeView();


        //Hier wird die View touchable
        dragViewStink.setOnTouchListener(new TouchListener());
        dragViewKakerlake.setOnTouchListener(new TouchListener());
        dragViewBat.setOnTouchListener(new TouchListener());
        dragViewFly.setOnTouchListener(new TouchListener());
        dragViewFrog.setOnTouchListener(new TouchListener());
        dragViewRat.setOnTouchListener(new TouchListener());
        dragViewScorpion.setOnTouchListener(new TouchListener());
        dragViewSpider.setOnTouchListener(new TouchListener());


        //Hier wird die Fl??che, wo ein Object hineingezogen wird, aktiviert!
        dropViewPlayer1.setOnDragListener(new DragListener());

        //Buttons werden mit funktionen belegt. Back- PopUp Fenster wird geschlossen
        goBack.setOnClickListener((View view) -> setInvisible(popUp));
        goBack2.setOnClickListener((View view) -> setInvisible(messageview));
        sendChallange.setOnClickListener((View view) -> sendChallengeInputs());

        GameClient.getInstance().getGame().setStateListener(new StateListenerImpl());

        //Init SensorManger
        ShakeSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        //Observer, der bei ??nderung des GameState die Activity neu ladet
        stateListen.setValue(GameClient.getInstance().getGame().getCurrentState());
        stateListen.observe(this, Observer -> { });

        //??ffnen des Displays f??r die Ersichtung der gegenerischen Karten (Collected Cards9

        enemyCollectedCards = (Button) findViewById(R.id.enemyCollectedCards);
        enemyCollectedCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEnemyCollectCards();
            }
        });


        //Handling und init f??r den Cheat
        cheatbox = (CheckBox) findViewById(R.id.checkBox);
        ShakeSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = ShakeSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        cheatbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Bitte Handy sch??tteln!!!", Toast.LENGTH_SHORT).show();
                activate = true;
            }
        });


    }

    public void initHashMap(){
        for(Player player: GameClient.getInstance().getGame().getPlayers()){
            userNameToID.put(player.getName(),player.getId());
        }
    }


    public void closeDragViewCards() {

    if(me.getHandDeck().getStinkwanze() == 0){
        dragViewStink.setVisibility(View.GONE);
    }
    if (me.getHandDeck().getSpinne() == 0){
        dragViewSpider.setVisibility(View.GONE);
    }
    if (me.getHandDeck().getKroete() == 0){
        dragViewFrog.setVisibility(View.GONE);
    }
    if (me.getHandDeck().getKakerlake() == 0){
        dragViewKakerlake.setVisibility(View.GONE);
    }
    if (me.getHandDeck().getScorpion() == 0){
        dragViewScorpion.setVisibility(View.GONE);
    }
    if (me.getHandDeck().getRatte() == 0){
        dragViewRat.setVisibility(View.GONE);
    }
    if (me.getHandDeck().getFliege() == 0){
        dragViewFly.setVisibility(View.GONE);
    }
    if (me.getHandDeck().getFledermaus() == 0){
        dragViewBat.setVisibility(View.GONE);
    }
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
                    Toast.makeText(getApplicationContext(), "Anzahl der Karten: " + checkTextInput(playedcard) , Toast.LENGTH_SHORT).show();
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

    //hollt sich alle Namen der anderen Spieler und f??gt die Namen in den Spinner! + SpinnerTypes
    public void setUpSpinnerForChallengeView() {
        for (Player player : GameClient.getInstance().getGame().getPlayers()) {
            if (!(player.getId() == me.getId())) {
                namesOfPlayer.add(player.getName());
            }

        }
        ArrayAdapter chooser = new ArrayAdapter(PlayerIngameMainActivity.this, android.R.layout.simple_spinner_dropdown_item, namesOfPlayer);
        choosePlayer.setAdapter(chooser);

        typeList = new ArrayList<>();
        typeList.addAll(Arrays.asList(Type.values()));
        ArrayAdapter typAdapter = new ArrayAdapter(PlayerIngameMainActivity.this, android.R.layout.simple_spinner_dropdown_item, typeList);
        chooseTypesOfCard.setAdapter(typAdapter);
    }

    /*
    Hier wird ein Spielzug (TURN) gemacht
     */
    public void sendChallengeInputs() {

            Turn turn;
            Type selectedType = Type.valueOf(chooseTypesOfCard.getSelectedItem().toString());
            Player enemy = null;
            for (Player player : GameClient.getInstance().getGame().getPlayers()) {
                if (player.getId() == userNameToID.get(choosePlayer.getSelectedItem().toString())){
                    enemy = player;
                }

            }
            if(enemy==null)return;

                Card selectedCard = me.getHandDeck().findCard(playedcard);
                Log.info("selected things", selectedType + " " + selectedCard + " " + enemy.getName());
                turn = new Turn(selectedCard, selectedType, enemy);
                GameClient.getInstance().getGame().getCurrentPlayer().getHandDeck().removeCard(selectedCard);
                GameClient.getInstance().getGame().makeTurn(me, turn);
                this.popUp.setVisibility(View.INVISIBLE);
    }

    public void SendRandomChallenge(){
        Turn turn;
        Player gegner = null;
        Card sendCard;
        Type sendType;
        List<Player> enemyPlayer = new ArrayList<Player>();
        List<Type> typeList = new ArrayList<>();


        for (Player player : GameClient.getInstance().getGame().getPlayers()) {
            if (player.getId() == me.getId()) {
            } else {
                enemyPlayer.add(player);
            }
        }

        Random number = new Random();
        int randomPlayerValue = number.nextInt(enemyPlayer.size() + 0) + 0;
        gegner = enemyPlayer.get(randomPlayerValue);

        Log.error(gegner.getName());
        for (Type t : Type.values()) {
            typeList.add(t);
        }

        Random number2 = new Random();
        int randomTypeValue = number2.nextInt(typeList.size() + 0) + 0;
        sendType = typeList.get(randomTypeValue);

        Log.error(sendType.toString());

        Random number3 = new Random();
        int randomCardValue = number3.nextInt(typeList.size() + 0) + 1;
        sendCard = new Card(typeList.get(randomCardValue));

        Log.error(sendCard.getType().toString());

        turn = new Turn(sendCard, sendType, gegner);
        GameClient.getInstance().getGame().makeTurn(me, turn);



    }


    /*
    Refresh the current view (doesent work!)
     */
    public void refreshView() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    //Methode, die eine Decision (Truth/Lie) ausf??hrt
    public void decission(Decision decision) {
        GameClient.getInstance().getGame().makeDecision(me, decision);
    }


    //Ccheck den String und gibt die Anzahl zur??ck des Types
    public int checkTextInput(String input) {

        switch (input) {
            case "FLEDERMAUS":
                return me.getHandDeck().getFledermaus();
            case "FLIEGE":
                return me.getHandDeck().getFliege();
            case "RATTE":
                return me.getHandDeck().getRatte();
            case "SCORPION":
                return me.getHandDeck().getScorpion();
            case "KAKERLAKE":
                return me.getHandDeck().getKakerlake();
            case "KROETE":
                return me.getHandDeck().getKroete();
            case "SPINNE":
                return me.getHandDeck().getSpinne();
            case "STINKWANZE":
                return me.getHandDeck().getStinkwanze();
            default: return 0;
        }

    }

    //Gibt das Player Object des spielenden Client zur??ck
    public Player getLocalPlayer() {
        for (Player p : GameClient.getInstance().getGame().getPlayers()) {
            Log.info("This is my id", String.valueOf(GameClient.getInstance().getClient().getClient().getID()));
            if (p.getId() == GameClient.getInstance().getClient().getClient().getID()) {
                for(Card card: p.getHandDeck().getDeck()){
                    Log.info("Card: ",card.getType().toString());
                }
                return p;
            }
        }
        return null;
    }

    /*
    ??ffnet einen Dialog, wenn man eine Decision machen muss
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
        Button buttonCheat = diaDecision.findViewById(R.id.cheatDecisionButton);
        ArrayList<String> list = new ArrayList<>();
        for (Player player1 : GameClient.getInstance().getGame().getPlayers()) {
            if (!(player1.getId() == me.getId()) && player1.getState() != PlayerState.PLAYED) {
                list.add(String.valueOf(player1.getName()));
            }
        }

        ArrayAdapter adapter = new ArrayAdapter(PlayerIngameMainActivity.this, android.R.layout.simple_spinner_dropdown_item, list);
        spinner.setAdapter(adapter);
        diaDecision.show();
        myToast.setDuration(Toast.LENGTH_SHORT);

        buttonTruth.setOnClickListener(view -> {
            Toast toast = Toast.makeText(this,  player + " has played" + selectedCard + ",said " + chosenTyp + " and you said TRUTH", Toast.LENGTH_SHORT);
            toast.show();
            decission(Decision.TRUTH);
            diaDecision.dismiss();
        });

        buttonLie.setOnClickListener(view -> {
            Toast toast = Toast.makeText(this, player + " has played " + selectedCard + ",said " + chosenTyp + " and you said LIE", Toast.LENGTH_SHORT);
            toast.show();
            decission(Decision.LIE);
            diaDecision.dismiss();
        });

        buttonHandOver.setOnClickListener(view -> {

            Player ene = null;
            for (Player p : GameClient.getInstance().getGame().getPlayers()) {
                if (p.getId() == userNameToID.get(spinner.getSelectedItem().toString())){
                    ene = p;
                };
            }
            Card card = GameClient.getInstance().getGame().getTurn().getSelectedCard();
            assert ene != null;
            Log.info("selected things", selectedCard + " " + card + " " + ene.getId());
            GameClient.getInstance().getGame().handOver(me, new HandOver(ene,Decision.TRUTH));

        });

        buttonCheat.setOnClickListener(view -> {
            if(decisionCheat){
                Toast toast = Toast.makeText(this,"You have already cheated!",Toast.LENGTH_SHORT);
                toast.show();
            }else {
                Dialog cheatDia = new Dialog(this);
                cheatDia.setContentView(R.layout.cheat_decision_dialoge);
                cheatDia.setCanceledOnTouchOutside(true);
                DecisionCheat cheat = new DecisionCheat();
                cheat.createCalc();
                TextView timer = cheatDia.findViewById(R.id.cheatDecisionTimer);

                TextView toCalc = cheatDia.findViewById(R.id.cheatDecisionToCalc);
                EditText editText = cheatDia.findViewById(R.id.cheatDecisionInput);
                int first = cheat.getFirst();
                int sec = cheat.getSecond();
                String calc = Integer.toString(first)  +" + " + Integer.toString(sec);
                toCalc.setText(calc);
                decisionCheat = true;
                cheatDia.show();

                new CountDownTimer(10000,1000){
                    int counter = 10;
                    @Override
                    public void onTick(long l) {
                        timer.setText(String.valueOf(counter));
                        counter--;
                    }

                    @Override
                    public void onFinish() {
                        /*Toast toast = Toast.makeText(cheatDia.getContext(),"Too slow!",Toast.LENGTH_SHORT);
                        toast.show();*/
                        cheatDia.dismiss();
                    }
                }.start();

                Button checkButton = cheatDia.findViewById(R.id.decisionCheatButtonSubmit);
                checkButton.setOnClickListener(view2 -> {
                    int playerCalc = Integer.parseInt(editText.getText().toString());
                    if(cheat.checkCalc(playerCalc)){
                        Toast toast = Toast.makeText(this,"Right answer! "+player + " played " + selectedCard,Toast.LENGTH_LONG);
                        toast.show();
                        cheatDia.dismiss();
                    }else{
                        Toast toast = Toast.makeText(this,"!!!Wrong answer!!!",Toast.LENGTH_SHORT);
                        toast.show();
                        cheatDia.dismiss();
                    }
                });
            }

        });


        String myText = GameClient.getInstance().getGame().getCurrentPlayer().getName() + " says " + GameClient.getInstance().getGame().getTurn().getSelectedType().toString();
        text.setText(myText);
    }





    /*
    Wird ge??ffnet, wenn man nicht an der Reihe ist
     */
    public void showDialogeWait(String message) {
        String myString = GameClient.getInstance().getGame().getCurrentPlayer().getName() + " has to play a card!";
        waitingDialogTextView.setText(message);
        alertDialog.show();
    }

    /*
    Wird ge??ffnet, wenn das Spiel vorbei ist
     */
    public void showDialogeGameOver() {
        Dialog dia = new Dialog(this);
        dia.setContentView(R.layout.game_over_dialoge);
        dia.setCanceledOnTouchOutside(false);
        Button button = dia.findViewById(R.id.back2menu);
        TextView text = dia.findViewById(R.id.textGameOver);
        String lostPlayer = GameClient.getInstance().getGame().getCurrentPlayer().getName();
        if (me.getId() == userNameToID.get(lostPlayer)) {
            String lost = "You lost the game!";
            text.setText(lost);
        } else {
            String won = "Game is over. "+lostPlayer+ " has lost!";
            text.setText(won);
        }
        dia.show();
        button.setOnClickListener(view -> {
            Intent intent = new Intent(PlayerIngameMainActivity.this,MainMenuActivity.class);
            startActivity(intent);
        });
    }

    //m??chte man den Stand ver??ndern (Display), ruft man diese Klasse auf.
    public void updateTheCollectedCards() {

        for (Player p : GameClient.getInstance().getGame().getPlayers()) {
            if (p == me) {
                p.getCollectedDeck().countAllCards();
                kr??tenView.setText(String.valueOf(p.getCollectedDeck().getKroete()));
                spinnenView.setText(String.valueOf(p.getCollectedDeck().getSpinne()));
                fliegenView.setText(String.valueOf(p.getCollectedDeck().getFliege()));
                scorpionView.setText(String.valueOf(p.getCollectedDeck().getScorpion()));
                kakerlakeView.setText(String.valueOf(p.getCollectedDeck().getKakerlake()));
                ratteView.setText(String.valueOf(p.getCollectedDeck().getRatte()));
                fledermausView.setText(String.valueOf(p.getCollectedDeck().getFledermaus()));
                stinkwanzeView.setText(String.valueOf(p.getCollectedDeck().getStinkwanze()));
            }

            kr??tenView.invalidate();
            spinnenView.invalidate();
            fliegenView.invalidate();
            scorpionView.invalidate();
            kakerlakeView.invalidate();
            ratteView.invalidate();
            fledermausView.invalidate();
            stinkwanzeView.invalidate();

        }

    }

    //zeigt die gesammelten Karten der Gegner
    public void showEnemyCollectCards() {
        String output = "";

        for (Player all : GameClient.getInstance().getGame().getPlayers()) {
            //hier soll die ausgaben meiner karten verhindert werden.
            if (all == me) {
            }
            else {
                output += "Player " + all.getName() + ("\n") +
                        "Kakerlake= " + all.getCollectedDeck().getKakerlake() + "| " +
                        "Fledermaus= " + all.getCollectedDeck().getFledermaus() + "| " +
                        "Fliege= " + all.getCollectedDeck().getFledermaus() + "| " +
                        "Ratte= " + all.getCollectedDeck().getRatte() + "| " +
                        "Scorpion= " + all.getCollectedDeck().getScorpion() + "| " +
                        "Kroete= " + all.getCollectedDeck().getKroete() + "| " +
                        "Spinne= " + all.getCollectedDeck().getSpinne() + "| " +
                        "Stinkwanze= " + all.getCollectedDeck().getStinkwanze() + ("\n");
            }
        }
        messageText.setText(output);
        messageview.setVisibility(View.VISIBLE);
    }

    public void displayCardAmounts() {
        kr??tenView.setText(String.valueOf(me.getHandDeck().getKroete()));
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
        Toast toast = null;


        if(GameClient.getInstance().getGame().getCurrentState() == GameState.AWAITING_TURN){
            if(me.getId()==GameClient.getInstance().getGame().getCurrentPlayer().getId()){
               toast = Toast.makeText(this, "Your Turn!", Toast.LENGTH_SHORT);
               toast.show();
            } else {
                showDialogeWait(GameClient.getInstance().getGame().getCurrentPlayer().getName() + " has to play a card!");
            }
        } else if(GameClient.getInstance().getGame().getCurrentState() == GameState.AWAITING_DECISION){
            if(me.getId()!=GameClient.getInstance().getGame().getTurn().getSelectedEnemy().getId()){
                showDialogeWait(GameClient.getInstance().getGame().getTurn().getSelectedEnemy().getName() + " has to make a decision!");
            } else if(me.getId()==GameClient.getInstance().getGame().getTurn().getSelectedEnemy().getId()){
                toast = Toast.makeText(this, "Make a decision!", Toast.LENGTH_SHORT);
                toast.show();
                showDialogeChallenge();
            }
        } else if (GameClient.getInstance().getGame().getCurrentState() == GameState.GAME_OVER) {
            Log.info("Game ist over!");
            showDialogeGameOver();
        }
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

                    diaDecision.dismiss();
                    //diaWait.dismiss();
                    if(alertDialog !=null){alertDialog.hide();}


                    closeDragViewCards();
                    updateTheCollectedCards();
                    checkTurn();

                }
            });

        }
    }

    //Methoden f??r das Verwenden des Shakes und des Cheats
    public void CheckboxClicked(View v) {
        while(true) {
            if (((CheckBox) v).isChecked()) {
                ShakeSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
                accelerometer = ShakeSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            }
        }
    }
    //Methode f??r das finden eines Shakes!
    @Override
    public void onSensorChanged(SensorEvent e) {

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
            if(activate == true){
                Toast.makeText(getApplicationContext(), "Cheat wird ausgef??hrt!!!", Toast.LENGTH_SHORT).show();
                SendRandomChallenge();
                erlaubnis = true;
                activate = false;
                cheatbox.setVisibility(View.INVISIBLE);
            }
        }

    }

    //Setzt den Wert eines Types (Kakerlake) auf einen bestimmten Wert herunter.
    public void ChangePlayersCollectedDecks() {

        Toast.makeText(getApplicationContext(), "Cheaten!!!", Toast.LENGTH_SHORT).show();

        /*
        for (Player p : GameClient.getInstance().getGame().getPlayers()) {
            if (p.getId() == me.getId()) {
                if (p.getCollectedDeck().getKakerlake() > 0) {
                    p.getCollectedDeck().removeCard(new Card(Type.valueOf("KAKERLAKE")));
                    p.getCollectedDeck().setKakerlake(p.getCollectedDeck().getKakerlake()-1);
                }
            }
            }
        }*/
    }


    //Default methode f??rs Handling
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    //Sollte wieder gesch??ttelt werden, akitivert sich der Listener
    @Override
    protected void onResume() {
        super.onResume();
        ShakeSensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }
    //Sollte nicht gesch??ttelt werden, wird der Listerner abgeschalten.
    @Override
    protected void onPause() {
        super.onPause();
        ShakeSensorManager.unregisterListener(this);
    }
}










