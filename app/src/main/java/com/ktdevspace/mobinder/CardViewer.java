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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class CardViewer extends AppCompatActivity {

    public TextView cardNameTextView, cardIDTextView, nameSearchData;
    public ImageView cardImageView;
    public Button backButton,findButton, addCollectionButton, buyButton;
    Animation increaseScale, decreaseScale;

    private Handler myHandler = new Handler();


    SearchAPIService searchAPIService = new SearchAPIService(CardViewer.this);
    RandomAPIService randomAPIService = new RandomAPIService(CardViewer.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_card_viewer);

        cardNameTextView = (TextView) findViewById(R.id.cardNameTextView);
        cardIDTextView = (TextView) findViewById(R.id.cardIDTextView);
        nameSearchData = (TextView) findViewById(R.id.searchTextView);
        cardImageView = (ImageView) findViewById(R.id.cardImageView);
        backButton = (Button) findViewById(R.id.viewCardBackBtn);
        findButton = (Button) findViewById(R.id.searchBtn);

        increaseScale = AnimationUtils.loadAnimation(this,R.anim.increase_scale);
        decreaseScale = AnimationUtils.loadAnimation(this,R.anim.decrease_scale);

        randomCard();

        //Back button
        backButton.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()== MotionEvent.ACTION_DOWN){
                    backButton.startAnimation(increaseScale);
                }else if(motionEvent.getAction()== MotionEvent.ACTION_UP){
                    backButton.startAnimation(decreaseScale);
                }
                backButton.setEnabled(false);
                myHandler.postDelayed(backBtnDelay, 100);
                return true;
            }
        });

        //Find Button
        findButton.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()== MotionEvent.ACTION_DOWN){
                    findButton.startAnimation(increaseScale);
                }else if(motionEvent.getAction()== MotionEvent.ACTION_UP){
                    findButton.startAnimation(decreaseScale);
                }
                myHandler.postDelayed(findBtnDelay, 100);
                return true;
            }
        });

    }
    public void randomCard() {
        //Get random card and add to add at load
        randomAPIService.getRandomCardName(new SearchAPIService.VolleyResponseListener() {
            @Override
            public void onError(String message) {

                Toast.makeText(CardViewer.this, "Something broke!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String cardNameRandom) {

                cardNameTextView.setText(cardNameRandom);
            }
        });

        //Get card id and add to text view
        randomAPIService.getRandomCardID(new SearchAPIService.VolleyResponseListener() {
            @Override
            public void onError(String message) {

                Toast.makeText(CardViewer.this, "Something broke!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String cardIDRandom) {

                cardIDTextView.setText("Multiverse ID: " + cardIDRandom);
            }
        });

        //Get card image url and add to imageview
        randomAPIService.getRandomCardImage(new SearchAPIService.VolleyResponseListener() {
            @Override
            public void onError(String message) {

                Toast.makeText(CardViewer.this, "Something broke!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String cardImgURLRandom) {

                Picasso.get().load(cardImgURLRandom).into(cardImageView);
            }
        });
    }

    //Back Btn delay runnable
    private Runnable backBtnDelay = new Runnable() {
        public void run() {
            Intent mainPageIntent = new Intent(CardViewer.this, MainActivity.class);
            startActivity(mainPageIntent);
        }
    };

    //Find btn delay runnable
    private Runnable findBtnDelay = new Runnable() {
        public void run() {
            //Get card name and add to textview
            searchAPIService.getCardName(nameSearchData.getText().toString(), new SearchAPIService.VolleyResponseListener() {
                @Override
                public void onError(String message) {

                    Toast.makeText(CardViewer.this, "Something broke!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(String cardName) {

                    cardNameTextView.setText("Card Name: " + cardName);
                }
            });

            //Get card id and add to text view
            searchAPIService.getCardID(nameSearchData.getText().toString(), new SearchAPIService.VolleyResponseListener() {
                @Override
                public void onError(String message) {

                    Toast.makeText(CardViewer.this, "Something broke!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(String cardID) {

                    cardIDTextView.setText("Multiverse ID: " + cardID);
                }
            });

            //Get card image url and add to imageview
            searchAPIService.getCardImage(nameSearchData.getText().toString(), new SearchAPIService.VolleyResponseListener() {
                @Override
                public void onError(String message) {

                    Toast.makeText(CardViewer.this, "Something broke!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(String cardImgURL) {

                    Picasso.get().load(cardImgURL).into(cardImageView);
                }
            });
        }
    };

//    //Search Btn
//    public void searchCard(View view) {
//        //Get card name and add to textview
//        searchAPIService.getCardName(nameSearchData.getText().toString(), new SearchAPIService.VolleyResponseListener() {
//            @Override
//            public void onError(String message) {
//
//                Toast.makeText(CardViewer.this, "Something broke!", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onResponse(String cardName) {
//
//                cardNameTextView.setText("Card Name: " + cardName);
//            }
//        });
//
//        //Get card id and add to text view
//        searchAPIService.getCardID(nameSearchData.getText().toString(), new SearchAPIService.VolleyResponseListener() {
//            @Override
//            public void onError(String message) {
//
//                Toast.makeText(CardViewer.this, "Something broke!", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onResponse(String cardID) {
//
//                cardIDTextView.setText("Multiverse ID: " + cardID);
//            }
//        });
//
//        //Get card image url and add to imageview
//        searchAPIService.getCardImage(nameSearchData.getText().toString(), new SearchAPIService.VolleyResponseListener() {
//            @Override
//            public void onError(String message) {
//
//                Toast.makeText(CardViewer.this, "Something broke!", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onResponse(String cardImgURL) {
//
//                Picasso.get().load(cardImgURL).into(cardImageView);
//            }
//        });
//    }
}