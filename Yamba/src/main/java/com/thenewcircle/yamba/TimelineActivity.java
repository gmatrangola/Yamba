package com.thenewcircle.yamba;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
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
public class TimelineActivity extends Activity implements TimelineFragment.DisplayDetails {
    private FrameLayout detailsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);


        detailsContainer = (FrameLayout) findViewById(R.id.details_fragment_container);
        if(detailsContainer != null) {
            FragmentTransaction tx = getFragmentManager().beginTransaction();
            tx.replace(R.id.details_fragment_container, new TimelineDetails(), "details");
            tx.commit();
        }

        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.timelineactivitymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_settings) {
            Intent prefsIntent = new Intent(this, YambaPrefsActivity.class);
            startActivity(prefsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ActionBar actionBar = getActionBar();
        ActionBar.Tab tab = actionBar.newTab();
        tab.setText("Timeline");
        tab.setTabListener(new TabListener<TimelineFragment>(this, "timeline", TimelineFragment.class));
        actionBar.addTab(tab, true);

        tab = actionBar.newTab();
        tab.setText("Status");
        tab.setTabListener(new TabListener<StatusFragment>(this, "status", StatusFragment.class));
        actionBar.addTab(tab);
    }

    @Override
    protected void onPause() {
        getActionBar().removeAllTabs();
        super.onPause();
    }

    @Override
    public void showDetails(Long id) {
        FragmentManager fragmentManager = getFragmentManager();
        TimelineDetails details = (TimelineDetails) fragmentManager.findFragmentByTag("details");
        if(details != null && details.isVisible()) {
            details.updateView(id);
        }
        else {
            FragmentTransaction tx = fragmentManager.beginTransaction();
            TimelineDetails timelineDetails = new TimelineDetails();
            tx.replace(R.id.fragment_container, timelineDetails);
            tx.addToBackStack("Details");
            tx.commit();

            timelineDetails.updateView(id);
        }

    }
}
