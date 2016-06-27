package com.nyit.anish.bingohouse;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


public class SplashScreen extends ActionBarActivity {


    MediaPlayer music;
    MediaPlayer buttonClick;
    Button start;
    Button credits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        music=MediaPlayer.create(this,R.raw.music);
        buttonClick=MediaPlayer.create(this,R.raw.click);
        music.start();

        start= (Button) findViewById(R.id.imageButton);
        credits= (Button) findViewById(R.id.imageButton2);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Toast.makeText(SplashScreen.this,"START",Toast.LENGTH_SHORT).show();
                buttonClick.start();
                Intent i=new Intent(SplashScreen.this,MainActivity.class);
                startActivity(i);

            }
        });

        credits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SplashScreen.this,"CREDITS",Toast.LENGTH_SHORT).show();
                buttonClick.start();

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        music.release();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
