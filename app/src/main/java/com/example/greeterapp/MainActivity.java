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

    private Button mButton;
    private EditText mEditText;
    private Intent mGreetingChooserIntent;
    private final String DEFAULT_NAME = "Mike";
    private String mName = DEFAULT_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGreetingChooserIntent =
                GreetingChooser.makeGreetingChooserIntent(this);

        mButton = findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mEditText != null)
                    mName = mEditText.getText().toString();
                mGreetingChooserIntent.putExtra(GreetingChooser.INTENT_NAME, mName);
                startActivity(mGreetingChooserIntent);
            }
        });

        mEditText = findViewById(R.id.editTextTextPersonName);
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