package com.berkaysevim.counterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView value;
    Button minus, plus, setup;

    SharedPref sharedPref;
    MediaPlayer mediaPlayer;
    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        value = (TextView) findViewById(R.id.value);
        minus = (Button) findViewById(R.id.minus);
        plus = (Button) findViewById((R.id.plus));
        setup = (Button) findViewById(R.id.setup);
        mediaPlayer = MediaPlayer.create(this, R.raw.ses);
        sharedPref = SharedPref.getInstance(this);
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sharedPref.counter < sharedPref.altLimit) {
                    if (sharedPref.sound)
                        ses();
                    if (sharedPref.titresim)
                        Vibrate();
                    value.setText(String.valueOf(sharedPref.counter));
                } else
                    sharedPref.counter--;
                value.setText(String.valueOf(sharedPref.counter));


            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sharedPref.counter > sharedPref.ustLimit) {
                    if (sharedPref.sound)
                        ses();
                    if (sharedPref.titresim)
                        Vibrate();
                    value.setText((String.valueOf(sharedPref.ustLimit)));

                } else
                    sharedPref.counter++;
                value.setText((String.valueOf(sharedPref.counter)));

            }
        });

        setup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SetupActivity.class));
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        sharedPref.saveValue();
    }

    public void ses() {
        mediaPlayer.start();
    }

    public void Vibrate() {
        if (!vibrator.hasVibrator())
            return;
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE));
        } else
            vibrator.vibrate(1000);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP)
            sharedPref.counter += 5;
        else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)
            sharedPref.counter -= 5;

        {
            return true;
        }
    }
}