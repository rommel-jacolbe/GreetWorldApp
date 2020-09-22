package com.example.greeterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Object reference for widgets
    private Button mButton;
    private EditText mEditText;
    // Intent reference for GreetingChooser intent
    private Intent mGreetingChooserIntent;
    // Default name to be used when user didn't input any name
    private final String DEFAULT_NAME = "Mike";
    // String reference for the inputted name
    private String mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Always call the super class hook method of activity callback methods
        super.onCreate(savedInstanceState);
        // Set the ContentView (activity_main.xml file under app/res/layout) for this activity
        setContentView(R.layout.activity_main);

        // Create an intent to start the GreetingChooser activity
        // by calling makeGreetingChooserIntent() static method
        mGreetingChooserIntent =
                GreetingChooser.makeGreetingChooserIntent(this);

        // Set a reference object for Button widget.
        // buttonConfirmName is the Button ID in activity_main.xml.
        mButton = findViewById(R.id.buttonConfirmName);

        // Set a reference object for EditText widget.
        // editTextTextPersonName is the EditText ID in activity_main.xml
        mEditText = findViewById(R.id.editTextTextPersonName);

        // Create a listener for the button. View.OnClickListener is an
        // interface with an onClick() method which we need to implement.
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the inputted name from EditText
                mName = mEditText.getText().toString();
                // Set the name inputted by user in mName reference. If user
                // didn't input any, then use the value set in DEFAULT_NAME
                if(mEditText.getText().toString().length() > 0)
                    mName = mEditText.getText().toString();
                else
                    mName = DEFAULT_NAME;
                // Add the inputted name as an extra in the intent.
                mGreetingChooserIntent.putExtra(
                        GreetingChooser.GREETING_CHOOSER_INTENT_NAME, mName);
                // Start the GreetingChooser activity
                startActivity(mGreetingChooserIntent);
            }
        });

        // Create an OnEditorActionListener to display the keyboard and allow inputs from user
        mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || keyEvent.getAction() == KeyEvent.ACTION_DOWN
                        && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    InputMethodManager mgr =
                            (InputMethodManager) MainActivity.this.getSystemService
                                    (Context.INPUT_METHOD_SERVICE);
                    mgr.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
                    // Insert default value if no input was specified.
                    if (TextUtils.isEmpty(
                            mEditText.getText().toString().trim())) {
                        mEditText.setText(
                                String.valueOf(DEFAULT_NAME));
                    }
                    return true;
                }
                else
                    return false;
            }
        });
    }
}