package com.ktdevspace.mobinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class CardViewer extends AppCompatActivity {

    TextView cardNameTextView;
    TextView cardIDTextView;
    TextView nameSearchData;
    ImageView cardImageView;

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

        randomCard();

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

    //Search Btn
    public void searchCard(View view) {
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
}