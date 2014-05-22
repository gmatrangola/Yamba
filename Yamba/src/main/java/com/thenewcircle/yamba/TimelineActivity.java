package com.thenewcircle.yamba;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by geoff on 5/22/14.
 */
public class TimelineActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        getFragmentManager().beginTransaction().
           replace(R.id.list_fragment_container, new TimelineFragment(), "list").
           commit();


    }
}
