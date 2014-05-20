package com.thenewcircle.yamba;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

/**
 * Created by geoff on 5/20/14.
 */
public class YambaPrefsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentTransaction tx = getFragmentManager().beginTransaction();
        tx.replace(android.R.id.content, new YambaPrefsFragment());
        tx.commit();
    }
}
