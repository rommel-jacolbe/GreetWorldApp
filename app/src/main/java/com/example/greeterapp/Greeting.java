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

    // Extra names to be put in Intent to start this activity
    public static String GREETING_EXTRA_NAME_GREETING = "EXTRA_GREETING";
    public static String GREETING_EXTRA_NAME_NAME = "EXTRA_NAME";
    // Result code for the starting activity
    public static int RETURN_CODE_BACK_TO_MAIN = 1001;
    public static int RETURN_CODE_CHOOSE_GREETING = 1002;
    // TextView references for TextView widgets
    private TextView mGreeting;
    private TextView mName;
    // Button reference for Button widget
    private Button mButtonBackToMain;
    private Button mButtonChangeGreeting;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // Always call the super class hook method of activity callback methods
        super.onCreate(savedInstanceState);
        // Set the ContentView (greeting.xml file under app/res/layout) for this activity
        setContentView(R.layout.greeting);
        // Set reference objects for all TextView widgets
        mGreeting = findViewById(R.id.greetingTextGreeting);
        mName = findViewById(R.id.textGreetingName);
        // Set reference objects for Button widgets
        mButtonBackToMain = findViewById(R.id.buttonBackToMain);
        mButtonChangeGreeting = findViewById(R.id.buttonChooseGreeting);
        // Create an OnClickListener for the widget button to back to the main activity.
        // View.OnClickListener is an interface. Its onClick() method must be implemented.
        mButtonBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When this button is pressed, we set a result code
                // to SetResult() to return back to the calling activity
                setResult(RETURN_CODE_BACK_TO_MAIN, getIntent());
                finish();
            }
        });
        // Create an onClickListener for the widget button to go back to the GreetingChooser activity.
        // View.OnClickListener is an interface. Its onClick() method must be implemented.
        mButtonChangeGreeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When this button is pressed, we set a result code
                // to SetResult() to return back to the calling activity
                setResult(RETURN_CODE_CHOOSE_GREETING, getIntent());
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        // Always call the super class hook method of activity callback methods
        super.onStart();
        // Set the name retrieved from the intent that started this activity
        mName.setText(
                getIntent().getStringExtra(GREETING_EXTRA_NAME_NAME));
        // Set the greeting retrieved from the intent that started this activity
        mGreeting.setText(
                getIntent().getStringExtra(GREETING_EXTRA_NAME_GREETING));
    }

    /**
     * Method to create an intent to start this activity
     * @param context - context of the calling activity
     * @param name - the inputted name of the user
     * @param greeting - the selected greeting of the user
     * @return - the reference to the newly created intent
     */
    public static Intent makeGreetingIntent(
            Context context, String name, String greeting) {
        // Set the name and greeting as String extras in the intent
        return new Intent(context, Greeting.class).
                putExtra(GREETING_EXTRA_NAME_NAME, name).
                putExtra(GREETING_EXTRA_NAME_GREETING, greeting);
    }
}
