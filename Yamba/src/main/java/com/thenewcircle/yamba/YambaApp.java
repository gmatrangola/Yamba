package com.thenewcircle.yamba;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.marakana.android.yamba.clientlib.YambaClient;

/**
 * Created by geoff on 5/21/14.
 */
public class YambaApp extends Application implements SharedPreferences.OnSharedPreferenceChangeListener {
    private static final String TAG = "Yamba." + YambaApp.class.getSimpleName();
    public static final String USER_NAME = "userName";
    public static final String PASSWORD = "password";
    public static final String INTERVAL = "interval";
    private YambaClient client;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.registerOnSharedPreferenceChangeListener(this);
        getPreferences();
        setTimer();

    }

    public YambaClient getClient() {
        if(client == null) getPreferences();
        return client;
    }

    private void getPreferences() {
        Log.d(TAG, "setTimer");
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String userName = prefs.getString(USER_NAME, "student");
        String password = prefs.getString(PASSWORD, "password");

        client = new YambaClient(userName, password);
    }

    private void setTimer() {
        Log.d(TAG, "setTimer");
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        Long interval = Long.valueOf(prefs.getString(INTERVAL, "0"));
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent timelineIntent = new Intent(this, TimelineService.class);
        PendingIntent timelinePending = PendingIntent.getService(this, 0, timelineIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        if(interval == 0) {
            alarmManager.cancel(timelinePending);
        }
        else {
            alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, interval, interval, timelinePending);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Log.d(TAG, "onSharedPreferenceChanged " + key);
        if(key.equals(PASSWORD) || key.equals(USER_NAME)) {
            getPreferences();
        }
        if(key.equals(INTERVAL)) {
            setTimer();
        }
    }
}
