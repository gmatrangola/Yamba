package com.thenewcircle.yamba;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.marakana.android.yamba.clientlib.YambaClient;
import com.marakana.android.yamba.clientlib.YambaClientException;

public class StatusActivity extends Activity {
    public static final int MAX_CHARS = 170;
    private static final String TAG = "Yamba." + StatusActivity.class.getSimpleName();
    private EditText editStatus;
    private TextView charsRemaining;
    private MenuItem submitItem;
    private YambaClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        editStatus = (EditText) findViewById(R.id.editStatus);
        charsRemaining = (TextView) findViewById(R.id.textCharsRemaining);

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
                charsRemaining.setText(numLeft + "");
                submitItem.setEnabled(s.length() > 0 && numLeft > -1);
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
        submitItem = menu.findItem(R.id.submit);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(id) {
            case R.id.submit:
                Intent statusIntent = new Intent(this, StatusService.class);
                statusIntent.putExtra(StatusService.STATUS, editStatus.getText().toString());
                startService(statusIntent);
                editStatus.getText().clear();
                return true;
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
