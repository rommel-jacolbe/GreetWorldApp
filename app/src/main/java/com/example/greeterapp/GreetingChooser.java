package com.example.greeterapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

public class GreetingChooser extends Activity {

    private Button mButtonHowdy;
    private Button mButtonHi;
    private Button mButtonHello;
    private Button mButtonWassup;
    private int ReturnCode = 0;
    private static Intent mIntent;
    public static final String INTENT_NAME = "INTENT_NAME";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.greeting_chooser);
        mIntent = getIntent();

        mButtonHowdy = findViewById(R.id.buttonChooser1);
        mButtonHowdy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGreetingActivity(mIntent.getStringExtra(INTENT_NAME),
                        mButtonHowdy.getText().toString());
            }
        });

        mButtonHi = findViewById(R.id.buttonChooser2);
        mButtonHi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGreetingActivity(mIntent.getStringExtra(INTENT_NAME),
                        mButtonHi.getText().toString());
            }
        });

        mButtonHello = findViewById(R.id.buttonChooser3);
        mButtonHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGreetingActivity(mIntent.getStringExtra(INTENT_NAME),
                        mButtonHello.getText().toString());
            }
        });

        mButtonWassup = findViewById(R.id.buttonChooser4);
        mButtonWassup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGreetingActivity(mIntent.getStringExtra(INTENT_NAME),
                        mButtonWassup.getText().toString());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(ReturnCode == Greeting.RETURN_CODE) {
            ReturnCode = 0;
            finish();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ReturnCode = resultCode;
    }

    private void startGreetingActivity(String name, String greeting) {
        startActivityForResult(Greeting.makeGreetingIntent(
                getApplicationContext(), name, greeting), Greeting.RETURN_CODE);
    }

    public static Intent makeGreetingChooserIntent(Context context) {
        return new Intent(context, GreetingChooser.class);
    }
}
