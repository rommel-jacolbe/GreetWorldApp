package com.example.greeterapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Greeting extends Activity {

    public static String INTENT_GREETING = "INTENT_GREETING";
    public static String INTENT_NAME = "INTENT_NAME";
    public static int RETURN_CODE = 1;
    private TextView mGreeting;
    private TextView mName;
    private Button mButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.greeting);
        mGreeting = findViewById(R.id.greetingTextGreeting);
        mName = findViewById(R.id.textGreetingName);
        mButton = findViewById(R.id.greetingButton);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RETURN_CODE, getIntent());
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGreeting.setText(getIntent().getStringExtra(INTENT_GREETING));
        mName.setText(getIntent().getStringExtra(INTENT_NAME));
    }

    public static Intent makeGreetingIntent(
            Context context, String name, String greeting) {
        return new Intent(context, Greeting.class).
                putExtra(INTENT_NAME, name).
                putExtra(INTENT_GREETING, greeting);
    }
}
