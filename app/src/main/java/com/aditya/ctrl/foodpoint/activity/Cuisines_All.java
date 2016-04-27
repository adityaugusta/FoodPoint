package com.aditya.ctrl.foodpoint.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aditya.ctrl.foodpoint.R;

public class Cuisines_All extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mainview = inflater.inflate(R.layout.frag_c_all, null);

        return mainview;
    }

}