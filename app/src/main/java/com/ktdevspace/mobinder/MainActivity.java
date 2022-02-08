package com.ktdevspace.mobinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    TextView cardNameTextView;
    TextView cardIDTextView;
    TextView nameSearchData;
    ImageView cardImageView;

    SearchAPIService searchAPIService = new SearchAPIService(MainActivity.this);

//    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        cardNameTextView = (TextView) findViewById(R.id.cardNameTextView);
        cardIDTextView = (TextView) findViewById(R.id.cardIDTextView);
        nameSearchData = (TextView) findViewById(R.id.searchTextView);
        cardImageView = (ImageView) findViewById(R.id.cardImageView);

    }

    //Search Btn
    public void searchCard(View view) {
        //Get card name and add to textview
        searchAPIService.getCardName(nameSearchData.getText().toString(), new SearchAPIService.VolleyResponseListener() {
            @Override
            public void onError(String message) {

                Toast.makeText(MainActivity.this, "Something broke!", Toast.LENGTH_SHORT).show();
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

                Toast.makeText(MainActivity.this, "Something broke!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String cardID) {

                cardIDTextView.setText("Card ID: " + cardID);
            }
        });

        //Get card image url and add to imageview
        searchAPIService.getCardImage(nameSearchData.getText().toString(), new SearchAPIService.VolleyResponseListener() {
            @Override
            public void onError(String message) {

                Toast.makeText(MainActivity.this, "Something broke!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String cardImgURL) {

                Picasso.get().load(cardImgURL).into(cardImageView);
            }
        });
    }
}

