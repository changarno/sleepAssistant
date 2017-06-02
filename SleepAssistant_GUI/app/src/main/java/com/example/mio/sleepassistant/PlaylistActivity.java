package com.example.mio.sleepassistant;

import android.content.DialogInterface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.content.Intent;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;

import static java.util.logging.Logger.global;

public class PlaylistActivity extends AppCompatActivity {
    private static ArrayList<String> buttons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        final Button addButton = (Button) findViewById(R.id.addButton);
        final TextView songText = new TextView(this);
        songText.setText("Name");
        final EditText songEdit = new EditText(this);
        final TextView urlText = new TextView(this);
        urlText.setText("URL");
        final EditText urlEdit = new EditText(this);

        for (String s : PlaylistActivity.buttons) {
            Button newButton = new Button(PlaylistActivity.this);
            newButton.setText(s);
            newButton.setGravity(Gravity.LEFT | Gravity.CENTER);
            newButton.setHeight(64);
            newButton.setTextSize(24);

            LinearLayout mainLayout = (LinearLayout) findViewById(R.id.song_layout);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            mainLayout.addView(newButton, lp);
        }

        final AlertDialog.Builder dBuild = new AlertDialog.Builder(this);
        dBuild.setTitle("Add New Sound");
        dBuild.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface di, int i) {
                PlaylistActivity.buttons.add(songEdit.getText().toString());
                Button newButton = new Button(PlaylistActivity.this);
                newButton.setText(songEdit.getText().toString());
                newButton.setGravity(Gravity.LEFT | Gravity.CENTER);
                newButton.setHeight(64);
                newButton.setTextSize(24);

                newButton.setOnClickListener(new View.OnClickListener() {
                    // When the button is pressed/clicked, it will run the code below
                    @Override
                    public void onClick(View v) {
                        // Intent is what you use to start another activity
                        Intent myIntent = new Intent(PlaylistActivity.this, PlayerActivity.class);
                        startActivity(myIntent);
                    }
                });

                LinearLayout mainLayout = (LinearLayout) findViewById(R.id.song_layout);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                mainLayout.addView(newButton, lp);
                Log.d("abcd", newButton.toString());
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
                Intent myIntent = new Intent(PlaylistActivity.this, SettingsActivity.class);
                startActivity(myIntent);
            }
        });

        ImageButton faveButton = (ImageButton) findViewById(R.id.faveButton);

        faveButton.setOnClickListener(new View.OnClickListener() {
            // When the button is pressed/clicked, it will run the code below
            @Override
            public void onClick(View v) {
                // Intent is what you use to start another activity
                Log.d("abcd", "clicked");
                Intent myIntent = new Intent(PlaylistActivity.this, FaveActivity.class);
                startActivity(myIntent);
            }
        });

        ImageButton returnButton = (ImageButton) findViewById(R.id.profileButton);

        returnButton.setOnClickListener(new View.OnClickListener() {
            // When the button is pressed/clicked, it will run the code below
            @Override
            public void onClick(View v) {
                // Intent is what you use to start another activity
                Intent myIntent = new Intent(PlaylistActivity.this, MenuActivity.class);
                startActivity(myIntent);
            }
        });

        Button dabirdz = (Button) findViewById(R.id.songButton);

        dabirdz.setOnClickListener(new View.OnClickListener() {
            // When the button is pressed/clicked, it will run the code below
            @Override
            public void onClick(View v) {
                // Intent is what you use to start another activity
                Intent myIntent = new Intent(PlaylistActivity.this, Player2Activity.class);
                startActivity(myIntent);
            }
        });


    }
}
