package com.thenewcircle.yamba;

import android.net.Uri;

/**
 * Created by geoff on 5/21/14.
 */
public class TimelineContract {

    public static final String AUTHORITY = "com.thenewcircle.yamba.provider";
    public static final String PATH = "/timeline";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + PATH);

    public static final String MULTIPLE_RECORDS_MIME_TYPE = "vnd.android.cursor.dir/vnd.thenewcircle.yamba.timeline.status";
    public static final String SINGLE_RECORDS_MIME_TYPE = "vnd.android.cursor.itemvnd.thenewcircle.yamba.timeline.status";

    public static class Columns {
        // TODO put constants for column names here
        private Columns() {

        }
    }

    private TimelineContract() {}
}
