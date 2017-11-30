package com.example.ahmadmuammarfanani.teknofest2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

public class Tab2_Minuman extends Fragment {
    private static final String TAG = "tab2Fragment";
    private ListView listView2;
    private FireBaseClient fireBaseClient;
    private String db_url = "https://teknofest2.firebaseio.com/";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.tab2_minuman,container,false);

        listView2 = view.findViewById(R.id.listView2);
        fireBaseClient = new FireBaseClient(getActivity(),db_url,listView2);
        fireBaseClient.refreshdata();

        return view;
    }
}
