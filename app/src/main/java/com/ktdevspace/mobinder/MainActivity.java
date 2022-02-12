package com.ktdevspace.mobinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.content.Intent;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    public Button findCardButton;

//    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        findCardButton = (Button) findViewById(R.id.findCardBtn);

        findCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent findCardIntent = new Intent(MainActivity.this, CardViewer.class);
                startActivity(findCardIntent);
            }
        });
    }
}

