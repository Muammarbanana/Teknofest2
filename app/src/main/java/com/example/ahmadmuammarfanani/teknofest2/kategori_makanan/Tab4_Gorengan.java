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

public class Tab4_Gorengan extends Fragment {
    private static final String TAG = "tab1Fragment";
    private String db_url = "https://teknofest2.firebaseio.com/";
    private FireBaseClient_Makanan_Gorengan fireBaseClient;
    private ListView listView_Makanan_gorengan;
    private CardView cardView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.tab4_makanan_gorengan,container,false);

        listView_Makanan_gorengan = view.findViewById(R.id.listView_makanan_gorengan);
        fireBaseClient = new FireBaseClient_Makanan_Gorengan(getActivity(),db_url,listView_Makanan_gorengan);
        fireBaseClient.refreshdata();

        return view;
    }

}
