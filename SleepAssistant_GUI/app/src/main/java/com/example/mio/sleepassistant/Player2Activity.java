package com.example.mio.sleepassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class Player2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player2);

        ImageButton settingsButton = (ImageButton) findViewById(R.id.settingsButton);

        settingsButton.setOnClickListener(new View.OnClickListener() {
            // When the button is pressed/clicked, it will run the code below
            @Override
            public void onClick(View v) {
                // Intent is what you use to start another activity
                Intent myIntent = new Intent(Player2Activity.this, SettingsActivity.class);
                startActivity(myIntent);
            }
        });

        ImageButton turnOnButton = (ImageButton) findViewById(R.id.imageButton7);

        turnOnButton.setOnClickListener(new View.OnClickListener() {
            // When the button is pressed/clicked, it will run the code below
            @Override
            public void onClick(View v) {
                // Intent is what you use to start another activity
                Intent myIntent = new Intent(Player2Activity.this, Player2OnActivity.class);
                startActivity(myIntent);
            }
        });

        ImageButton backButton = (ImageButton) findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            // When the button is pressed/clicked, it will run the code below
            @Override
            public void onClick(View v) {
                // Intent is what you use to start another activity
                Intent myIntent = new Intent(Player2Activity.this, PlaylistActivity.class);
                startActivity(myIntent);
            }
        });

        ImageButton faveButton = (ImageButton) findViewById(R.id.faveButton);

        faveButton.setOnClickListener(new View.OnClickListener() {
            // When the button is pressed/clicked, it will run the code below
            @Override
            public void onClick(View v) {
                // Intent is what you use to start another activity
                Intent myIntent = new Intent(Player2Activity.this, FaveActivity.class);
                startActivity(myIntent);
            }
        });

        ImageButton returnButton = (ImageButton) findViewById(R.id.profileButton);

        returnButton.setOnClickListener(new View.OnClickListener() {
            // When the button is pressed/clicked, it will run the code below
            @Override
            public void onClick(View v) {
                // Intent is what you use to start another activity
                Intent myIntent = new Intent(Player2Activity.this, MenuActivity.class);
                startActivity(myIntent);
            }
        });

        ImageButton leftButton = (ImageButton) findViewById(R.id.leftplay);

        leftButton.setOnClickListener(new View.OnClickListener() {
            // When the button is pressed/clicked, it will run the code below
            @Override
            public void onClick(View v) {
                // Intent is what you use to start another activity
                Intent myIntent = new Intent(Player2Activity.this, PlayerActivity.class);
                startActivity(myIntent);
            }
        });

        ImageButton riteButton = (ImageButton) findViewById(R.id.rightplay);

        riteButton.setOnClickListener(new View.OnClickListener() {
            // When the button is pressed/clicked, it will run the code below
            @Override
            public void onClick(View v) {
                // Intent is what you use to start another activity
                Intent myIntent = new Intent(Player2Activity.this, PlayerActivity.class);
                startActivity(myIntent);
            }
        });


    }
}
