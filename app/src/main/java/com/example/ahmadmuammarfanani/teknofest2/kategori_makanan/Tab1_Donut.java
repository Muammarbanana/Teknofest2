package com.example.ahmadmuammarfanani.teknofest2.kategori_makanan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.ahmadmuammarfanani.teknofest2.R;
import com.example.ahmadmuammarfanani.teknofest2.kategori_produk_toko.FireBaseClient_Makanan;

/**
 * Created by Ahmad Muammar Fanani on 11/30/2017.
 */

public class Tab1_Donut extends Fragment {
    private static final String TAG = "tab1Fragment";
    private String db_url = "https://teknofest2.firebaseio.com/";
    private FireBaseClient_Makanan_Donut fireBaseClient;
    private ListView listView_makanan_donut;
    private CardView cardView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.tab1_makanan_donut,container,false);

        listView_makanan_donut = view.findViewById(R.id.listView_makanan_donut);
        fireBaseClient = new FireBaseClient_Makanan_Donut(getActivity(),db_url,listView_makanan_donut);
        fireBaseClient.refreshdata();

        return view;
    }

}
