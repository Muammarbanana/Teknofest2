package com.example.ahmadmuammarfanani.teknofest2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Ahmad Muammar Fanani on 11/30/2017.
 */

public class Tab3_Lain extends Fragment {
    private static final String TAG = "tab3Fragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.tab3_lain,container,false);
        return view;
    }
}
