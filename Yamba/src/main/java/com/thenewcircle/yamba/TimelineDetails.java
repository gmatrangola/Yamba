package com.thenewcircle.yamba;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by geoff on 5/22/14.
 */
public class TimelineDetails extends Fragment {

    private TextView textUser;
    private TextView textStatus;
    private TextView textTime;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.fragment_details, container, false);

        textUser =(TextView) layout.findViewById(R.id.textUsername);
        textStatus = (TextView) layout.findViewById(R.id.textStatus);
        textTime = (TextView) layout.findViewById(R.id.textTime);

        return layout;
    }
}
