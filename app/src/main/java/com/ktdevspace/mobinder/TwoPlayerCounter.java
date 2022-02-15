package com.ktdevspace.mobinder;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class TwoPlayerCounter extends AppCompatActivity {

    public Button endGameButton, pOneUpButton, pOneDownButton, pTwoUpButton, pTwoDownButton;
    public TextView pOneLifeTextView, pTwoLifeTextView;
    int pOneNumber = 0, pTwoNumber =0;
    String pOneNum ="", pTwoNum ="";

    Animation increaseScale, decreaseScale;

    private Handler myHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_player_counter);

        endGameButton = (Button) findViewById(R.id.endGameBtn);
        pOneUpButton = (Button) findViewById(R.id.playerOneUpBtn);
        pOneDownButton = (Button) findViewById(R.id.playerOneDownBtn);
        pTwoUpButton = (Button) findViewById(R.id.playerTwoUpBtn);
        pTwoDownButton = (Button) findViewById(R.id.playerTwoDownBtn);
        pOneLifeTextView = (TextView) findViewById(R.id.playerOneTextView);
        pTwoLifeTextView = (TextView) findViewById(R.id.playerTwoTextView);

        increaseScale = AnimationUtils.loadAnimation(this,R.anim.increase_scale);
        decreaseScale = AnimationUtils.loadAnimation(this,R.anim.decrease_scale);

        //End game button
        endGameButton.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()== MotionEvent.ACTION_DOWN){
                    endGameButton.startAnimation(increaseScale);
                }else if(motionEvent.getAction()== MotionEvent.ACTION_UP){
                    endGameButton.startAnimation(decreaseScale);
                }
                endGameButton.setEnabled(false);
                myHandler.postDelayed(endGameBtnDelay, 100);
                return true;
            }
        });

        pOneUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pOneNumber = Integer.parseInt(pOneLifeTextView.getText().toString()) + 1;
                pOneNum = String.valueOf(pOneNumber);
                pOneLifeTextView.setText(pOneNum);

                if(pOneNumber <= 10){
                    pOneLifeTextView.setTextColor(getResources().getColor(R.color.red));
                }
                else{
                    pOneLifeTextView.setTextColor(getResources().getColor(R.color.black));
                }
            }
        });
        pOneDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pOneNumber = Integer.parseInt(pOneLifeTextView.getText().toString()) - 1;
                pOneNum = String.valueOf(pOneNumber);
                pOneLifeTextView.setText(pOneNum);

                if(pOneNumber <= 10){
                    pOneLifeTextView.setTextColor(getResources().getColor(R.color.red));
                }
                else{
                    pOneLifeTextView.setTextColor(getResources().getColor(R.color.black));
                }
            }
        });
        pTwoUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pTwoNumber = Integer.parseInt(pTwoLifeTextView.getText().toString()) + 1;
                pTwoNum = String.valueOf(pTwoNumber);
                pTwoLifeTextView.setText(pTwoNum);

                if(pTwoNumber <= 10){
                    pTwoLifeTextView.setTextColor(getResources().getColor(R.color.red));
                }
                else{
                    pTwoLifeTextView.setTextColor(getResources().getColor(R.color.black));
                }
            }
        });
        pTwoDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pTwoNumber = Integer.parseInt(pTwoLifeTextView.getText().toString()) - 1;
                pTwoNum = String.valueOf(pTwoNumber);
                pTwoLifeTextView.setText(pTwoNum);

                if(pTwoNumber <= 10){
                    pTwoLifeTextView.setTextColor(getResources().getColor(R.color.red));
                }
                else{
                    pTwoLifeTextView.setTextColor(getResources().getColor(R.color.black));
                }
            }
        });
    }
    //End game Btn delay runnable
    private Runnable endGameBtnDelay = new Runnable() {
        public void run() {
            Intent gameAssistPageIntent = new Intent(TwoPlayerCounter.this, GameAssist.class);
            startActivity(gameAssistPageIntent);
        }
    };
}