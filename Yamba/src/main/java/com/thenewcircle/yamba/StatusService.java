package com.thenewcircle.yamba;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.util.Log;

import com.marakana.android.yamba.clientlib.YambaClient;
import com.marakana.android.yamba.clientlib.YambaClientException;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 */
public class StatusService extends IntentService {

    private static final String TAG = "Yamba." + StatusService.class.getSimpleName();
    public static final int ID = 100;
    public static final String STATUS = "status";
    private YambaClient client;

    public StatusService() {
        super("StatusService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        client = new YambaClient("student", "password");

        Log.d(TAG, "onCreate()");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandleIntent");
        if (intent != null) {
            String status = intent.getStringExtra(STATUS);
            postStatus(status);
        }
    }

    private void postStatus(String status) {
        Log.d(TAG, "posting: " + status);
        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setContentText(status);
        try {
            client.postStatus(status);
            builder.setContentTitle("Posted");
        } catch (YambaClientException e) {
            Log.e(TAG, "Unable to post Status " + status, e);
            builder.setContentTitle("Error posting message");
            builder.setContentInfo(e.getMessage());
        }
        finally {
            NotificationManager notificationManager = (NotificationManager)
                    getSystemService(NOTIFICATION_SERVICE);
            notificationManager.notify(ID, builder.getNotification());
        }
    }

}
