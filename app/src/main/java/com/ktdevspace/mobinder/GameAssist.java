package com.ktdevspace.mobinder;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;


public class GameAssist extends AppCompatActivity {

    public Button gameAssistBackButton, twoPlayerButton, fourPlayerButton;

    Animation increaseScale, decreaseScale;

    private Handler myHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_assist);

        gameAssistBackButton = (Button) findViewById(R.id.gameAssistBackBtn);
        twoPlayerButton = (Button) findViewById(R.id.twoPlayerBtn);
        fourPlayerButton = (Button) findViewById(R.id.fourPlayerBtn);

        increaseScale = AnimationUtils.loadAnimation(this,R.anim.increase_scale);
        decreaseScale = AnimationUtils.loadAnimation(this,R.anim.decrease_scale);

        //Back button
        gameAssistBackButton.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()== MotionEvent.ACTION_DOWN){
                    gameAssistBackButton.startAnimation(increaseScale);
                }else if(motionEvent.getAction()== MotionEvent.ACTION_UP){
                    gameAssistBackButton.startAnimation(decreaseScale);
                }
                gameAssistBackButton.setEnabled(false);
                myHandler.postDelayed(gameAssistBackBtnDelay, 100);
                return true;
            }
        });

        //two player button
        twoPlayerButton.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()== MotionEvent.ACTION_DOWN){
                    twoPlayerButton.startAnimation(increaseScale);
                }else if(motionEvent.getAction()== MotionEvent.ACTION_UP){
                    twoPlayerButton.startAnimation(decreaseScale);
                }
                twoPlayerButton.setEnabled(false);
                myHandler.postDelayed(twoPlayerBtnDelay, 300);
                return true;
            }
        });

        //Four player button
        fourPlayerButton.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()== MotionEvent.ACTION_DOWN){
                    fourPlayerButton.startAnimation(increaseScale);
                }else if(motionEvent.getAction()== MotionEvent.ACTION_UP){
                    fourPlayerButton.startAnimation(decreaseScale);
                }
                fourPlayerButton.setEnabled(false);
                myHandler.postDelayed(fourPlayerBtnDelay, 300);
                return true;
            }
        });

    }
    //Back Btn delay runnable
    private Runnable gameAssistBackBtnDelay = new Runnable() {
        public void run() {
            Intent cardViewPageIntent = new Intent(GameAssist.this, MainActivity.class);
            startActivity(cardViewPageIntent);
        }
    };
    //Two player Btn delay runnable
    private Runnable twoPlayerBtnDelay = new Runnable() {
        public void run() {
            Intent cardViewPageIntent = new Intent(GameAssist.this, TwoPlayerCounter.class);
            startActivity(cardViewPageIntent);
        }
    };
    //Four player delay runnable
    private Runnable fourPlayerBtnDelay = new Runnable() {
        public void run() {
            Intent cardViewPageIntent = new Intent(GameAssist.this, FourPlayerCounter.class);
            startActivity(cardViewPageIntent);
        }
    };
}