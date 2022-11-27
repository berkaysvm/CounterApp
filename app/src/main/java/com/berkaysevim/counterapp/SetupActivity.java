package com.berkaysevim.counterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

public class SetupActivity extends AppCompatActivity {

    EditText altLimitDeger,ustLimitDeger;
    Button altLimitAzalt,altLimitArttır,ustLimitAzalt,ustLimitArttır;
    Switch sound,titresim;

    SharedPref sharedPref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        altLimitDeger = (EditText) findViewById(R.id.altLimitdeger);
        ustLimitDeger = (EditText) findViewById(R.id.ustLimitdeger);
        altLimitAzalt = (Button)  findViewById(R.id.altLimitazalt);
        altLimitArttır = (Button) findViewById(R.id.altLimitArttir);
        ustLimitAzalt = (Button) findViewById(R.id.ustLimitazalt);
        ustLimitArttır = (Button) findViewById(R.id.ustLimitArttir);
        sound = (Switch) findViewById(R.id.sound);
        titresim = (Switch) findViewById(R.id.titresim);

        sharedPref = SharedPref.getInstance(this);

        altLimitAzalt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPref.altLimit--;
                altLimitDeger.setText(String.valueOf(sharedPref.altLimit));
            }
        });
        altLimitArttır.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPref.altLimit++;
                altLimitDeger.setText(String.valueOf(sharedPref.altLimit));
            }
        });

        ustLimitAzalt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPref.ustLimit--;
                ustLimitDeger.setText(String.valueOf(sharedPref.ustLimit));
            }
        });
        ustLimitArttır.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPref.ustLimit++;
                ustLimitDeger.setText(String.valueOf(sharedPref.ustLimit));
            }
        });

        sound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                sharedPref.sound = b;
            }
        });
        titresim.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                sharedPref.titresim = b;
            }
        });



    }
    @Override
    protected void onResume() {
        super.onResume();

        altLimitDeger.setText(String.valueOf(sharedPref.altLimit));
        ustLimitDeger.setText(String.valueOf(sharedPref.ustLimit));
        sound.setChecked(sharedPref.sound);
        titresim.setChecked(sharedPref.titresim);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sharedPref.saveValue();

    }
}