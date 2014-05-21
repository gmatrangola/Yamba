package com.thenewcircle.yamba;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;


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
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandleIntent");
    }

}
