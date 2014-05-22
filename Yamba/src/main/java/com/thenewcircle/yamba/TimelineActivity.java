package com.thenewcircle.yamba;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

/**
 * Created by geoff on 5/22/14.
 */
public class TimelineActivity extends Activity {
    private FrameLayout detailsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        FragmentTransaction tx = getFragmentManager().beginTransaction();

        detailsContainer = (FrameLayout) findViewById(R.id.details_fragment_container);
        if(detailsContainer != null) {
            tx.replace(R.id.details_fragment_container, new TimelineDetails(), "details");
        }

        tx.replace(R.id.list_fragment_container, new TimelineFragment(), "list").
           commit();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.timelineactivitymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.status) {
            Intent statusIntent = new Intent(this, StatusActivity.class);
            startActivity(statusIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
