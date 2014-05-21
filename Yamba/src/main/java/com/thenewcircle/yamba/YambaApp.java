package com.thenewcircle.yamba;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

/**
 * Created by geoff on 5/21/14.
 */
public class YambaApp extends Application {
    private static final String TAG = "Yamba." + YambaApp.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");

        long interval = AlarmManager.INTERVAL_FIFTEEN_MINUTES;
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent timelineIntent = new Intent(this, TimelineService.class);
        PendingIntent timelinePending = PendingIntent.getService(this, 0, timelineIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, interval, interval, timelinePending);

    }
}
