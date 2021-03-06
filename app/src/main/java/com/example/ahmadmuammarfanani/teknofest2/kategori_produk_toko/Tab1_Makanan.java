package com.example.ahmadmuammarfanani.teknofest2.kategori_produk_toko;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ahmadmuammarfanani.teknofest2.Kategori_Produk_Toko;
import com.example.ahmadmuammarfanani.teknofest2.R;
import com.example.ahmadmuammarfanani.teknofest2.tambahan.FireBaseClient;

/**
 * Created by Ahmad Muammar Fanani on 11/30/2017.
 */

public class Tab1_Makanan extends Fragment {
    private static final String TAG = "tab1Fragment";
    private String db_url = "https://teknofest2.firebaseio.com/";
    private FireBaseClient_Makanan fireBaseClient;
    private ListView listView_Makanan;
    private CardView cardView;
    private String NamaToko,NamaProduk;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.tab1_makanan,container,false);

        listView_Makanan = view.findViewById(R.id.listView_makanan);
        SharedPreferences DataToko = getActivity().getSharedPreferences("DataToko" , Context.MODE_PRIVATE);

        NamaToko = DataToko.getString("Nama_Toko" , "Toko1");
        NamaProduk = DataToko.getString("Nama_Produk" , "HelloDonut");
        fireBaseClient = new FireBaseClient_Makanan(getActivity(),db_url,listView_Makanan,NamaToko);
        fireBaseClient.refreshdata();

        return view;
    }

}
