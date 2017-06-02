package com.example.mio.sleepassistant;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static java.util.logging.Logger.global;
public class FaveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fave);

        final Button addButton = (Button) findViewById(R.id.addButton);
        final TextView songText = new TextView(this);
        songText.setText("Name");
        final EditText songEdit = new EditText(this);
        final TextView urlText = new TextView(this);
        urlText.setText("URL");
        final EditText urlEdit = new EditText(this);

        final AlertDialog.Builder dBuild = new AlertDialog.Builder(this);
        dBuild.setTitle("Add New Sound");
        dBuild.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface di, int i) {
                Button newButton = new Button(FaveActivity.this);
                newButton.setText(songEdit.getText().toString());
                newButton.setGravity(Gravity.LEFT | Gravity.CENTER);
                newButton.setHeight(64);
                newButton.setTextSize(24);

                LinearLayout mainLayout = (LinearLayout) findViewById(R.id.song_layout);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                mainLayout.addView(newButton, lp);
                Log.d("abcd", newButton.toString());


                newButton.setOnClickListener(new View.OnClickListener() {
                    // When the button is pressed/clicked, it will run the code below
                    @Override
                    public void onClick(View v) {
                        // Intent is what you use to start another activity
                        Intent myIntent = new Intent(FaveActivity.this, PlayerActivity.class);
                        startActivity(myIntent);
                    }
                });
            };
        });
        dBuild.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface di, int i) {
                di.dismiss();
            }
        });

        LinearLayout dLayout = new LinearLayout(this);
        dLayout.setOrientation(1);
        dLayout.addView(songText);
        dLayout.addView(songEdit);
        dLayout.addView(urlText);
        dLayout.addView(urlEdit);
        dBuild.setView(dLayout);

        final AlertDialog addDialog = dBuild.create();


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDialog.show();
            }
        });

        ImageButton settingsButton = (ImageButton) findViewById(R.id.settingsButton);

        settingsButton.setOnClickListener(new View.OnClickListener() {
            // When the button is pressed/clicked, it will run the code below
            @Override
            public void onClick(View v) {
                // Intent is what you use to start another activity
                Log.d("abcd", "clicked");
                Intent myIntent = new Intent(FaveActivity.this, SettingsActivity.class);
                startActivity(myIntent);
            }
        });

        ImageButton returnButton = (ImageButton) findViewById(R.id.profileButton);

        returnButton.setOnClickListener(new View.OnClickListener() {
            // When the button is pressed/clicked, it will run the code below
            @Override
            public void onClick(View v) {
                // Intent is what you use to start another activity
                Intent myIntent = new Intent(FaveActivity.this, MenuActivity.class);
                startActivity(myIntent);
            }
        });

        Button playButton = (Button) findViewById(R.id.songButton);

        playButton.setOnClickListener(new View.OnClickListener() {
            // When the button is pressed/clicked, it will run the code below
            @Override
            public void onClick(View v) {
                // Intent is what you use to start another activity
                Intent myIntent = new Intent(FaveActivity.this, PlayerActivity.class);
                startActivity(myIntent);
            }
        });


    }
}
