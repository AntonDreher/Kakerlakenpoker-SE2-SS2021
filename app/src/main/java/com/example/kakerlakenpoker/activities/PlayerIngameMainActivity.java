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
import com.example.game.player.PlayerState;
import com.example.kakerlakenpoker.R;
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

    List<String> namesOfPlayer = new ArrayList<String>();
    Boolean check;
    MutableLiveData<GameState> stateListen = new MutableLiveData<GameState>();
    Player me = null;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_ingameview);
        diaDecision = new Dialog(this);
        diaWait = new Dialog(this);
        me = getLocalPlayer();
        me.getHandDeck().countAllCards();
        setUpTypesSpinner();

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


        //Observer, der bei Änderung des GameState die Activity neu ladet
        stateListen.setValue(GameClient.getInstance().getGame().getCurrentState());
        stateListen.observe(this, Observer -> {

        });


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
                if (player.getId() == Integer.parseInt(choosePlayer.getSelectedItem().toString())) ;
                enemy = player;
            }

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
            Log.info("This is my id",String.valueOf(GameClient.getInstance().getClient().getClient().getID()) );
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

        diaDecision.setContentView(R.layout.decision_dialoge);
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
        Spinner spinner = findViewById(R.id.handOver);
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
            myToast.setText("Player: " + player + " played: " + selectedCard + " and said: " + chosenTyp + "and you said TRUTH");
            myToast.show();
            decission(Decision.TRUTH);
            diaDecision.dismiss();
        });

        buttonLie.setOnClickListener(view -> {
            myToast.setText("Player: " + player + " played: " + selectedCard + " and said: " + chosenTyp + "and you said LIE");
            myToast.show();
            decission(Decision.LIE);
            diaDecision.dismiss();
        });

        buttonHandOver.setOnClickListener(view -> {
            GameClient.getInstance().getGame().handOver();
            Turn turn;
            Type selectedType = Type.valueOf(types.getSelectedItem().toString());
            Player ene = null;
            for (Player p : GameClient.getInstance().getGame().getPlayers()) {
                if (p.getId() == Integer.parseInt(spinner.getSelectedItem().toString())) ;
                ene = p;
            }
            Card card = GameClient.getInstance().getGame().getTurn().getSelectedCard();
            assert ene != null;
            Log.info("selected things", selectedType + " " + card+ " " + ene.getId());
            turn = new Turn(card, selectedType, ene);
            GameClient.getInstance().getGame().makeTurn(me, turn);

        });


        String myText = "Player: " + GameClient.getInstance().getGame().getCurrentPlayer().getId() + " says " + GameClient.getInstance().getGame().getTurn().getSelectedType().toString();
        text.setText(myText);
    }

    /*
    Wird geöffnet, wenn man nicht an der Reihe ist
     */
    public void showDialogeWait() {
        diaWait.setContentView(R.layout.waiting_dialoge);
        diaWait.setCanceledOnTouchOutside(false);
        TextView text = diaWait.findViewById(R.id.notYoutTurn);
        String myString = "Players: " + GameClient.getInstance().getGame().getCurrentPlayer().getId() + " turn!";
        text.setText(myString);
        diaWait.show();
    }
    /*
    Wird geöffnet, wenn das Spiel vorbei ist
     */
    public void showDialogeGameOver(){
        Dialog dia = new Dialog(this);
        dia.setContentView(R.layout.game_over_dialoge);
        dia.setCanceledOnTouchOutside(false);
        TextView text = dia.findViewById(R.id.textGameOver);
        int lostPlayer = GameClient.getInstance().getGame().getCurrentPlayer().getId();
        if(me.getId() == lostPlayer){
            String lost = "You lost the game!";
            text.setText(lost);
        }else {
            String won = "You won the game and player: " + lostPlayer + " lost!";
            text.setText(won);
        }
        dia.show();
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
        //me ist nicht aktuell am Spiel beteiligt
        if (!(me.getId() == (GameClient.getInstance().getGame().getCurrentPlayer().getId()) || GameClient.getInstance().getGame().getTurn() != null && !(me.getId() == GameClient.getInstance().getGame().getTurn().getSelectedEnemy().getId()))) {
            Log.debug("Not your turn!");
              Log.debug("Current Player: " + GameClient.getInstance().getGame().getCurrentPlayer().getId());
             Log.debug("Current Enemys: " + GameClient.getInstance().getGame().getTurn().getSelectedEnemy().getId());
            showDialogeWait();
        }

        //Turn wurde ausgeführt und me wurde als Enemy ausgewählt
        if (GameClient.getInstance().getGame().getCurrentState() == GameState.AWAITING_DECISION && (GameClient.getInstance().getGame().getTurn().getSelectedEnemy().getId() == me.getId())) {
                Log.debug("Current Player: " + GameClient.getInstance().getGame().getCurrentPlayer().getId());
                Log.debug("Current Enems: " + GameClient.getInstance().getGame().getTurn().getSelectedEnemy().getId());
                Log.info("You have to make a decission!");
                showDialogeChallenge();
        }
        //Bei GameOver
        if (GameClient.getInstance().getGame().getCurrentState() == GameState.GAME_OVER){
            showDialogeGameOver();
        }
    }

    public void setUpTypesSpinner(){
        typeList.addAll(Arrays.asList(Type.values()));
        ArrayAdapter typAdapter = new ArrayAdapter(PlayerIngameMainActivity.this, android.R.layout.simple_spinner_dropdown_item, typeList);
        types.setAdapter(typAdapter);
    }



    class StateListenerImpl extends StateListener {

        @Override
        public void inform() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(GameClient.getInstance().getGame().checkRoundOver()){
                        GameClient.getInstance().getGame().resetPlayerStatus();
                    }
                    diaDecision.hide();
                    diaWait.hide();
                    displayCardAmounts();
                    checkTurn();
                }
            });

        }
    }
}










