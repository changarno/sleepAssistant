package com.example.mio.sleepassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ImageButton loopOnButton = (ImageButton) findViewById(R.id.imageButton);

        loopOnButton.setOnClickListener(new View.OnClickListener() {
            // When the button is pressed/clicked, it will run the code below
            @Override
            public void onClick(View v) {
                // Intent is what you use to start another activity
                Intent myIntent = new Intent(SettingsActivity.this, SettingsLoopOnActivity.class);
                startActivity(myIntent);
            }
        });

        ImageButton menuButton = (ImageButton) findViewById(R.id.profileButton);

        menuButton.setOnClickListener(new View.OnClickListener() {
            // When the button is pressed/clicked, it will run the code below
            @Override
            public void onClick(View v) {
                // Intent is what you use to start another activity
                Intent myIntent = new Intent(SettingsActivity.this, MenuActivity.class);
                startActivity(myIntent);
            }
        });

        ImageButton faveButton = (ImageButton) findViewById(R.id.faveButton);

        faveButton.setOnClickListener(new View.OnClickListener() {
            // When the button is pressed/clicked, it will run the code below
            @Override
            public void onClick(View v) {
                // Intent is what you use to start another activity
                Intent myIntent = new Intent(SettingsActivity.this, FaveActivity.class);
                startActivity(myIntent);
            }
        });

        ImageButton returnButton = (ImageButton) findViewById(R.id.profileButton);

        returnButton.setOnClickListener(new View.OnClickListener() {
            // When the button is pressed/clicked, it will run the code below
            @Override
            public void onClick(View v) {
                // Intent is what you use to start another activity
                Intent myIntent = new Intent(SettingsActivity.this, MenuActivity.class);
                startActivity(myIntent);
            }
        });
    }
}
