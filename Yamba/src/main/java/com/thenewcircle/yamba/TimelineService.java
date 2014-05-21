package com.thenewcircle.yamba;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.marakana.android.yamba.clientlib.YambaClient;
import com.marakana.android.yamba.clientlib.YambaClientException;

import java.util.List;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 */
public class TimelineService extends IntentService {
    private static final String TAG = "Yamba." + TimelineService.class.getSimpleName();
    private YambaClient client;

    public TimelineService() {
        super("TimelineService");
    }


    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String userName = prefs.getString("userName", "student");
        String password = prefs.getString("password", "password");
        client = new YambaClient(userName, password);

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandleIntent");
        List<YambaClient.Status> timeline = null;
        try {
            timeline = client.getTimeline(10);
            Log.d(TAG, "timeline: " + timeline.size());
            for( YambaClient.Status status : timeline) {
                Log.d(TAG, "status " + status.getMessage());
            }
        } catch (YambaClientException e) {
            Log.e(TAG, "Unable to get Timeline: " + e.getMessage(), e);
        }
    }

}
