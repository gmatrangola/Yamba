package com.thenewcircle.yamba;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class StatusActivity extends Activity {
    public static final int MAX_CHARS = 170;
    private EditText editStatus;
    private TextView charsRemaining;
    private Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        editStatus = (EditText) findViewById(R.id.editStatus);
        charsRemaining = (TextView) findViewById(R.id.textCharsRemaining);
        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);

        editStatus.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int numLeft = MAX_CHARS - s.length();
                if(numLeft < 10) {
                    charsRemaining.setTextColor(getResources().getColor(R.color.warning));
                }
                else {
                    charsRemaining.setTextColor(getResources().getColor(R.color.normal));
                }
                buttonSubmit.setEnabled(numLeft > -1);
                charsRemaining.setText(numLeft + "");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.status, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
