package com.example.ahmadmuammarfanani.teknofest2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ahmadmuammarfanani.teknofest2.tambahan.FireBaseClient;

/**
 * Created by Ahmad Muammar Fanani on 11/30/2017.
 */

public class Tab1_Makanan extends Fragment {
    private static final String TAG = "tab1Fragment";
    private String db_url = "https://teknofest2.firebaseio.com/";
    private FireBaseClient fireBaseClient;
    private ListView listView2;
    private CardView cardView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.tab1_makanan,container,false);
        return view;
    }

}
