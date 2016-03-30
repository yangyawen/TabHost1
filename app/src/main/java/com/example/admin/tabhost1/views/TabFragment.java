package com.example.admin.tabhost1.views;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by admin on 2016/3/30.
 */
public class TabFragment extends Fragment {

    private String title = "Default";
    public static final String TITLE = "title";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        if (getArguments() != null){
            title = getArguments().getString(TITLE);
        }


        TextView tv = new TextView(getActivity());
        tv.setTextSize(20);
        tv.setBackgroundColor(Color.parseColor("#ffffffff"));
        tv.setText(title);
        tv.setGravity(Gravity.CENTER);
        return tv;
    }
}
