package com.acampora.project1_tempconverter;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class TempConverterActivity extends AppCompatActivity {

    private EditText farenheitEditText;
    private TextView celciusTextView;
    private SharedPreferences savedValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_converter);
        farenheitEditText = (EditText) findViewById(R.id.farenheitEditText);
        celciusTextView = (TextView) findViewById(R.id.celciusTextView);

        farenheitEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE || i == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    updateDisplay ( );
                }
                return false;
            }
        });

        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);
    }

    @Override
    protected void onPause ( ) {

        SharedPreferences.Editor editor = savedValues.edit();
        editor.putString ("farenheitInput", farenheitEditText.getText().toString());
        editor.commit();

        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        farenheitEditText.setText(savedValues.getString("farenheitInput", ""));

        updateDisplay();
    }

    private void updateDisplay() {
        String farenheitString = farenheitEditText.getText().toString();

        float degreesFarenheit = 0;
        if (farenheitString.length() > 0)
            degreesFarenheit = Float.parseFloat(farenheitString);

        float degreesCelcius = (degreesFarenheit - 32) * 5 / 9;

        celciusTextView.setText(degreesCelcius + "");
    }
}
