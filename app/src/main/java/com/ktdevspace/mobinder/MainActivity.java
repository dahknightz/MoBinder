package com.ktdevspace.mobinder;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.content.Intent;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    public Button findCardButton, gameAssistButton, myCollectionButton, myDecksButton;
    Animation increaseScale, decreaseScale;
    private Handler myHandler = new Handler();

//    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        findCardButton = (Button) findViewById(R.id.findCardBtn);
        gameAssistButton = (Button) findViewById(R.id.gameAssistBtn);
        myCollectionButton = (Button) findViewById(R.id.myCollectionBtn);
        myDecksButton = (Button) findViewById(R.id.myDecksBtn);

        increaseScale = AnimationUtils.loadAnimation(this,R.anim.increase_scale);
        decreaseScale = AnimationUtils.loadAnimation(this,R.anim.decrease_scale);

        findCardButton.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()== MotionEvent.ACTION_DOWN){
                    findCardButton.startAnimation(increaseScale);
                }else if(motionEvent.getAction()== MotionEvent.ACTION_UP){
                    findCardButton.startAnimation(decreaseScale);
                }
                findCardButton.setEnabled(false);
                myHandler.postDelayed(findCardBtnDelay, 500);
                return true;
            }
        });

        gameAssistButton.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()== MotionEvent.ACTION_DOWN){
                    gameAssistButton.startAnimation(increaseScale);
                }else if(motionEvent.getAction()== MotionEvent.ACTION_UP){
                    gameAssistButton.startAnimation(decreaseScale);
                }
                return true;
            }
        });

        myCollectionButton.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()== MotionEvent.ACTION_DOWN){
                    myCollectionButton.startAnimation(increaseScale);
                }else if(motionEvent.getAction()== MotionEvent.ACTION_UP){
                    myCollectionButton.startAnimation(decreaseScale);
                }
                return true;
            }
        });

        myDecksButton.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()== MotionEvent.ACTION_DOWN){
                    myDecksButton.startAnimation(increaseScale);
                }else if(motionEvent.getAction()== MotionEvent.ACTION_UP){
                    myDecksButton.startAnimation(decreaseScale);
                }
                return true;
            }
        });
    }
    private Runnable findCardBtnDelay = new Runnable() {
        public void run() {
            Intent viewCardIntent = new Intent(MainActivity.this, CardViewer.class);
            startActivity(viewCardIntent);
        }
    };
}

