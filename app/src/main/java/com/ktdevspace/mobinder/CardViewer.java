package com.ktdevspace.mobinder;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class CardViewer extends AppCompatActivity {

    public TextView cardNameTextView, cardIDTextView, nameSearchData, cardRarityTextView, cardSetTextView,
            cardCMCTextView, cardColorsTextView, cardPriceTextView, cardFoilPriceTextView;
    public ImageView cardImageView;
    ScrollView background;
    public Button cardViewBackButton,findButton, addCollectionButton, buyButton;
    Animation increaseScale, decreaseScale;
    public String randomCardName, cardURL;

    private Handler myHandler = new Handler();


    SearchAPIService searchAPIService = new SearchAPIService(CardViewer.this);
    RandomAPIService randomAPIService = new RandomAPIService(CardViewer.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_viewer);

        cardNameTextView = (TextView) findViewById(R.id.cardNameTextView);
        cardIDTextView = (TextView) findViewById(R.id.cardIDTextView);
        cardRarityTextView = (TextView) findViewById(R.id.cardRarityTextView);
        cardSetTextView = (TextView) findViewById(R.id.cardSetTextView);
        cardCMCTextView = (TextView) findViewById(R.id.cardCmcTextView);
        cardColorsTextView = (TextView) findViewById(R.id.cardColorTextView);
        cardPriceTextView = (TextView) findViewById(R.id.cardPriceTextView);
        cardFoilPriceTextView = (TextView) findViewById(R.id.cardFoilPriceTextView);
        nameSearchData = (TextView) findViewById(R.id.searchTextView);
        cardImageView = (ImageView) findViewById(R.id.cardImageView);
        cardViewBackButton = (Button) findViewById(R.id.cardViewBackBtn);
        findButton = (Button) findViewById(R.id.searchBtn);
        background = (ScrollView) findViewById(R.id.cardViewScrollView);
        buyButton = (Button) findViewById(R.id.cardPriceBtn);

        increaseScale = AnimationUtils.loadAnimation(this,R.anim.increase_scale);
        decreaseScale = AnimationUtils.loadAnimation(this,R.anim.decrease_scale);

        randomCard();

        //Back button
        cardViewBackButton.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()== MotionEvent.ACTION_DOWN){
                    cardViewBackButton.startAnimation(increaseScale);
                }else if(motionEvent.getAction()== MotionEvent.ACTION_UP){
                    cardViewBackButton.startAnimation(decreaseScale);
                }
                cardViewBackButton.setEnabled(false);
                myHandler.postDelayed(cardViewBackBtnDelay, 100);
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
                nameSearchData.onEditorAction(EditorInfo.IME_ACTION_DONE);
                myHandler.postDelayed(findBtnDelay, 100);
                return true;
            }
        });

        //Buy Button
        buyButton.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()== MotionEvent.ACTION_DOWN){
                    buyButton.startAnimation(increaseScale);
                }else if(motionEvent.getAction()== MotionEvent.ACTION_UP){
                    buyButton.startAnimation(decreaseScale);
                }
                String url = cardURL;
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
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
                //setting randomCardName to returned cards name
                randomCardName = cardNameRandom;

                //calling handler function to then display cards data on layout
                myHandler.postDelayed(randomDelay,100);
            }
        });
    }

    //Delay to get and set cards data using returned random cards name
    private Runnable randomDelay = new Runnable() {
        public void run() {
            //Get card name and add to textview
            searchAPIService.getCardName(randomCardName, new SearchAPIService.VolleyResponseListener() {
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
            searchAPIService.getCardID(randomCardName, new SearchAPIService.VolleyResponseListener() {
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
            searchAPIService.getCardImage(randomCardName, new SearchAPIService.VolleyResponseListener() {
                @Override
                public void onError(String message) {

                    Toast.makeText(CardViewer.this, "Something broke!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(String cardImgURL) {

                    Picasso.get().load(cardImgURL).into(cardImageView);
                }
            });

            //Get card rarity and add to text view
            searchAPIService.getCardRarity(randomCardName, new SearchAPIService.VolleyResponseListener() {
                @Override
                public void onError(String message) {

                    Toast.makeText(CardViewer.this, "Something broke!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(String cardRarity) {

                    cardRarityTextView.setText("Rarity: " + cardRarity);
                }
            });

            //Get card set name and add to text view
            searchAPIService.getCardSetName(randomCardName, new SearchAPIService.VolleyResponseListener() {
                @Override
                public void onError(String message) {

                    Toast.makeText(CardViewer.this, "Something broke!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(String cardSetName) {

                    cardSetTextView.setText("Set Name: " + cardSetName);
                }
            });

            //Get card CMC and add to text view
            searchAPIService.getCardCMC(randomCardName, new SearchAPIService.VolleyResponseListener() {
                @Override
                public void onError(String message) {

                    Toast.makeText(CardViewer.this, "Something broke!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(String cardCMC) {

                    cardCMCTextView.setText("CMC: " + cardCMC);
                }
            });

            //Get card color and add to text view
            searchAPIService.getCardColor(randomCardName, new SearchAPIService.VolleyResponseListener() {
                @Override
                public void onError(String message) {

                    Toast.makeText(CardViewer.this, "Something broke!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(String cardColor) {

                    cardColorsTextView.setText("Color: " + cardColor);

                    if (cardColor.equals("W")){
                        background.setBackgroundResource(R.drawable.plainsbackground);
                    }
                    else if (cardColor.equals("U")){
                        background.setBackgroundResource(R.drawable.islandbackground);
                    }
                    else if (cardColor.equals("B")){
                        background.setBackgroundResource(R.drawable.swampbackground);
                    }
                    else if (cardColor.equals("R")){
                        background.setBackgroundResource(R.drawable.mountainbackground);
                    }
                    else{
                        background.setBackgroundResource(R.drawable.forestbackground);
                    }
                }
            });

            //Get card price and add to text view
            searchAPIService.getCardPrice(randomCardName, new SearchAPIService.VolleyResponseListener() {
                @Override
                public void onError(String message) {

                    Toast.makeText(CardViewer.this, "Something broke!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(String cardPrice) {

                    cardPriceTextView.setText("Price: $" + cardPrice);
                }
            });

            //Get card foil price and add to text view
            searchAPIService.getCardFoilPrice(randomCardName, new SearchAPIService.VolleyResponseListener() {
                @Override
                public void onError(String message) {

                    Toast.makeText(CardViewer.this, "Something broke!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(String cardFoilPrice) {

                    cardFoilPriceTextView.setText("Foil Price: $" + cardFoilPrice);
                }
            });

            //Get card URL price and save it to cardURL
            searchAPIService.getCardURL(randomCardName, new SearchAPIService.VolleyResponseListener() {
                @Override
                public void onError(String message) {

                    Toast.makeText(CardViewer.this, "Something broke!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(String cardReturnedURL) {

                    cardURL = cardReturnedURL;

                }
            });
        }
    };

    //Back Btn delay runnable
    private Runnable cardViewBackBtnDelay = new Runnable() {
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

            //Get card rarity and add to text view
            searchAPIService.getCardRarity(nameSearchData.getText().toString(), new SearchAPIService.VolleyResponseListener() {
                @Override
                public void onError(String message) {

                    Toast.makeText(CardViewer.this, "Something broke!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(String cardRarity) {

                    cardRarityTextView.setText("Rarity: " + cardRarity);
                }
            });

            //Get card set name and add to text view
            searchAPIService.getCardSetName(nameSearchData.getText().toString(), new SearchAPIService.VolleyResponseListener() {
                @Override
                public void onError(String message) {

                    Toast.makeText(CardViewer.this, "Something broke!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(String cardSetName) {

                    cardSetTextView.setText("Set Name: " + cardSetName);
                }
            });

            //Get card CMC and add to text view
            searchAPIService.getCardCMC(nameSearchData.getText().toString(), new SearchAPIService.VolleyResponseListener() {
                @Override
                public void onError(String message) {

                    Toast.makeText(CardViewer.this, "Something broke!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(String cardCMC) {

                    cardCMCTextView.setText("CMC: " + cardCMC);
                }
            });

            //Get card color and add to text view
            searchAPIService.getCardColor(nameSearchData.getText().toString(), new SearchAPIService.VolleyResponseListener() {
                @Override
                public void onError(String message) {

                    Toast.makeText(CardViewer.this, "Something broke!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(String cardColor) {

                    cardColorsTextView.setText("Color: " + cardColor);

                    if (cardColor.equals("W")){
                        background.setBackgroundResource(R.drawable.plainsbackground);
                    }
                    else if (cardColor.equals("U")){
                        background.setBackgroundResource(R.drawable.islandbackground);
                    }
                    else if (cardColor.equals("B")){
                        background.setBackgroundResource(R.drawable.swampbackground);
                    }
                    else if (cardColor.equals("R")){
                        background.setBackgroundResource(R.drawable.mountainbackground);
                    }
                    else{
                        background.setBackgroundResource(R.drawable.forestbackground);
                    }
                }
            });

            //Get card price and add to text view
            searchAPIService.getCardPrice(nameSearchData.getText().toString(), new SearchAPIService.VolleyResponseListener() {
                @Override
                public void onError(String message) {

                    Toast.makeText(CardViewer.this, "Something broke!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(String cardPrice) {

                    cardPriceTextView.setText("Price: $" + cardPrice);
                }
            });

            //Get card foil price and add to text view
            searchAPIService.getCardFoilPrice(nameSearchData.getText().toString(), new SearchAPIService.VolleyResponseListener() {
                @Override
                public void onError(String message) {

                    Toast.makeText(CardViewer.this, "Something broke!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(String cardFoilPrice) {

                    cardFoilPriceTextView.setText("Foil Price: $" + cardFoilPrice);
                }
            });

            //Get card URL price and save it to cardURL
            searchAPIService.getCardURL(nameSearchData.getText().toString(), new SearchAPIService.VolleyResponseListener() {
                @Override
                public void onError(String message) {

                    Toast.makeText(CardViewer.this, "Something broke!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(String cardReturnedURL) {

                    cardURL = cardReturnedURL;

                }
            });
        }
    };
}