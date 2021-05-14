package com.example.kakerlakenpoker;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kakerlakenpoker.game.Game;

public class PlayerIngameMainActivity extends AppCompatActivity {

    LinearLayout dragView;
    LinearLayout dropView;
    LinearLayout dragView1;
    LinearLayout dropView1;
    int count = 0;
    Game game;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_ingameview);

        dragView = (LinearLayout) findViewById(R.id.dragView);
        dropView = (LinearLayout) findViewById(R.id.dropView);
        dragView1 = (LinearLayout) findViewById(R.id.dragView2);
        dropView1 = (LinearLayout) findViewById(R.id.dropView2);

        dragView.setOnTouchListener(new TouchListener());
        dropView.setOnDragListener(new DragListener());
        dragView1.setOnTouchListener(new TouchListener());
        dropView1.setOnDragListener(new DragListener());

    }

    class DragListener implements View.OnDragListener{

        @Override
        public boolean onDrag(View v, DragEvent dragEvent) {
            switch (dragEvent.getAction()) {

                case DragEvent.ACTION_DRAG_STARTED:{
                    break;}

                case DragEvent.ACTION_DRAG_ENTERED:{
                    break;}

                case DragEvent.ACTION_DRAG_LOCATION:{
                    break;}

                case DragEvent.ACTION_DRAG_EXITED:{
                    break;}

                case DragEvent.ACTION_DRAG_ENDED:
                    //Nach dem Drag zum Spieler wird die Karte aus dem Handdeck entfernt und den Spieler übermittelt.

                case DragEvent.ACTION_DROP:{
                    Log.e("H", "Hat funktioniert");
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
                View.DragShadowBuilder dragShadow = new View.DragShadowBuilder(dragView);
                view.startDrag(data, dragShadow, view, 0);
                return true;
            }else {return false;}
        }
    }



    }





