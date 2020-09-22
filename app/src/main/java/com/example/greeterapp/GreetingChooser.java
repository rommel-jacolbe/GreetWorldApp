package com.example.greeterapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

public class GreetingChooser extends Activity {

    // Button references for button widgets
    private Button mButtonHowdy;
    private Button mButtonHi;
    private Button mButtonHello;
    private Button mButtonWassup;

    // int to hold the return value from Greeting activity via onActivityResult() callback method
    private int mReturnCode = 0;
    // Intent reference to hold the intent that started this activity
    private static Intent mIntent;
    // Intent name to create an intent for this activity
    public static final String GREETING_CHOOSER_INTENT_NAME =
            "GREETING_CHOOSER_INTENT_NAME";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // Always call the super class hook method of activity callback methods
        super.onCreate(savedInstanceState);
        // Set the ContentView (greeting_chooser.xml file under app/res/layout) for this activity
        setContentView(R.layout.greeting_chooser);
        // Get intent reference that started this activity
        mIntent = getIntent();
        // Set reference objects for all Button widgets.
        mButtonHowdy = findViewById(R.id.buttonChooser1);
        mButtonHi = findViewById(R.id.buttonChooser2);
        mButtonHello = findViewById(R.id.buttonChooser3);
        mButtonWassup = findViewById(R.id.buttonChooser4);
        // Create a listener for mButtonHowdy button. View.OnClickListener is an
        // interface with an onClick() method which we need to implement.
        mButtonHowdy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGreetingActivity(
                        mIntent.getStringExtra(GREETING_CHOOSER_INTENT_NAME),
                        mButtonHowdy.getText().toString());
            }
        });
        // Create a listener for mButtonHi button. View.OnClickListener is an
        // interface with an onClick() method which we need to implement.
        mButtonHi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGreetingActivity(
                        mIntent.getStringExtra(GREETING_CHOOSER_INTENT_NAME),
                        mButtonHi.getText().toString());
            }
        });
        // Create a listener for mButtonHello button. View.OnClickListener is an
        // interface with an onClick() method which we need to implement.
        mButtonHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGreetingActivity(
                        mIntent.getStringExtra(GREETING_CHOOSER_INTENT_NAME),
                        mButtonHello.getText().toString());
            }
        });
        // Create a listener for mButtonWassup button. View.OnClickListener is an
        // interface with an onClick() method which we need to implement.
        mButtonWassup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGreetingActivity(
                        mIntent.getStringExtra(GREETING_CHOOSER_INTENT_NAME),
                        mButtonWassup.getText().toString());
            }
        });
    }

    @Override
    protected void onResume() {
        // Always call the super class hook method of activity callback methods
        super.onResume();
        // Check if return code prompts the app to go back to the main activity
        if(mReturnCode == Greeting.RETURN_CODE_BACK_TO_MAIN) {
            // reset mReturnCode
            mReturnCode = 0;
            finish();
        }
    }

    /**
     * This callback method is called when Greeting activity stops and returns a value
     * @param requestCode
     * @param resultCode
     * @param data
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Always call the super class method
        super.onActivityResult(requestCode, resultCode, data);
        // Set the resultCode to ReturnCode for evaluation in onResume()
        mReturnCode = resultCode;
    }

    /**
     * Method to start the Greeting activity
     * @param name - name to be greeted in Greeting activity
     * @param greeting - greeting to be greeted to name in Greeting activity
     */
    private void startGreetingActivity(String name, String greeting) {
        // Call startActivityForResult() in order for this activity to receive
        // a return code from Greeting activity after it finishes its processing.
        startActivityForResult(Greeting.makeGreetingIntent(
                getApplicationContext(), name, greeting),
                Greeting.RETURN_CODE_BACK_TO_MAIN);
    }

    /**
     * This method is called by the MainActivity to create an intent for this activity
     * @param context - context of the calling activity
     * @return - reference to the newly created intent
     */
    public static Intent makeGreetingChooserIntent(Context context) {
        return new Intent(context, GreetingChooser.class);
    }
}
