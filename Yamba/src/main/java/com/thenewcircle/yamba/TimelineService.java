package com.thenewcircle.yamba;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.marakana.android.yamba.clientlib.YambaClient;
import com.marakana.android.yamba.clientlib.YambaClientException;

import java.util.Date;
import java.util.List;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 */
public class TimelineService extends IntentService {
    private static final String TAG = "Yamba." + TimelineService.class.getSimpleName();

    public TimelineService() {
        super("TimelineService");
    }


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandleIntent");
        List<YambaClient.Status> timeline = null;
        try {
            YambaClient client = ((YambaApp)getApplication()).getClient();
            client.fetchFriendsTimeline(new YambaClient.TimelineProcessor() {
                @Override
                public boolean isRunnable() {
                    return true;
                }

                @Override
                public void onStartProcessingTimeline() {
                    Log.d(TAG, "onStartProcessingTimeline");
                }

                @Override
                public void onEndProcessingTimeline() {
                    Log.d(TAG, "onEndProcessingTimeline");
                }

                @Override
                public void onTimelineStatus(long id, Date createdAt, String user, String msg) {
                    Log.d(TAG, "onTimelineStatus " + user + ": " + msg);
                }
            });
        } catch (YambaClientException e) {
            Log.e(TAG, "Unable to get Timeline: " + e.getMessage(), e);
        }
    }

}
