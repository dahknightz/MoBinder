package com.ktdevspace.mobinder;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class FourPlayerCounter extends AppCompatActivity {

    public Button endGameButton, pOneUpButton, pOneDownButton, pTwoUpButton, pTwoDownButton, pThreeUpButton,
    pThreeDownButton, pFourUpButton, pFourDownButton;
    public TextView pOneLifeTextView, pTwoLifeTextView, pThreeLifeTextView, pFourLifeTextView;
    int pOneNumber = 0, pTwoNumber =0, pThreeNumber =0, pFourNumber =0;
    String pOneNum ="", pTwoNum ="", pThreeNum ="", pFourNum ="";

    Animation increaseScale, decreaseScale;

    private Handler myHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_player_counter);

        endGameButton = (Button) findViewById(R.id.endFourGameBtn);
        pOneUpButton = (Button) findViewById(R.id.fourPlayerOneUpBtn);
        pOneDownButton = (Button) findViewById(R.id.fourPlayerOneDownBtn);
        pTwoUpButton = (Button) findViewById(R.id.fourPlayerTwoUpBtn);
        pTwoDownButton = (Button) findViewById(R.id.fourPlayerTwoDownBtn);
        pThreeUpButton = (Button) findViewById(R.id.playerThreeUpBtn);
        pThreeDownButton = (Button) findViewById(R.id.playerThreeDownBtn);
        pFourUpButton = (Button) findViewById(R.id.playerFourUpBtn);
        pFourDownButton = (Button) findViewById(R.id.playerFourDownBtn);
        pOneLifeTextView = (TextView) findViewById(R.id.fourPlayerOneTextView);
        pTwoLifeTextView = (TextView) findViewById(R.id.fourPlayerTwoTextView);
        pThreeLifeTextView = (TextView) findViewById(R.id.playerThreeTextView);
        pFourLifeTextView = (TextView) findViewById(R.id.playerFourTextView);

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
        pThreeUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pThreeNumber = Integer.parseInt(pThreeLifeTextView.getText().toString()) + 1;
                pThreeNum = String.valueOf(pThreeNumber);
                pThreeLifeTextView.setText(pThreeNum);

                if(pThreeNumber <= 10){
                    pThreeLifeTextView.setTextColor(getResources().getColor(R.color.red));
                }
                else{
                    pThreeLifeTextView.setTextColor(getResources().getColor(R.color.black));
                }
            }
        });
        pThreeDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pThreeNumber = Integer.parseInt(pThreeLifeTextView.getText().toString()) - 1;
                pThreeNum = String.valueOf(pThreeNumber);
                pThreeLifeTextView.setText(pThreeNum);

                if(pThreeNumber <= 10){
                    pThreeLifeTextView.setTextColor(getResources().getColor(R.color.red));
                }
                else{
                    pThreeLifeTextView.setTextColor(getResources().getColor(R.color.black));
                }
            }
        });
        pFourUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pFourNumber = Integer.parseInt(pFourLifeTextView.getText().toString()) + 1;
                pFourNum = String.valueOf(pFourNumber);
                pFourLifeTextView.setText(pFourNum);

                if(pFourNumber <= 10){
                    pFourLifeTextView.setTextColor(getResources().getColor(R.color.red));
                }
                else{
                    pFourLifeTextView.setTextColor(getResources().getColor(R.color.black));
                }
            }
        });
        pFourDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pFourNumber = Integer.parseInt(pFourLifeTextView.getText().toString()) - 1;
                pFourNum = String.valueOf(pFourNumber);
                pFourLifeTextView.setText(pFourNum);

                if(pFourNumber <= 10){
                    pFourLifeTextView.setTextColor(getResources().getColor(R.color.red));
                }
                else{
                    pFourLifeTextView.setTextColor(getResources().getColor(R.color.black));
                }
            }
        });
    }
    //End game Btn delay runnable
    private Runnable endGameBtnDelay = new Runnable() {
        public void run() {
            Intent gameAssistPageIntent = new Intent(FourPlayerCounter.this, GameAssist.class);
            startActivity(gameAssistPageIntent);
        }
    };
}