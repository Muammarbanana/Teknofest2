package com.example.ahmadmuammarfanani.teknofest2.kategori_produk_toko;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ahmadmuammarfanani.teknofest2.R;
import com.example.ahmadmuammarfanani.teknofest2.tambahan.FireBaseClient;

/**
 * Created by Ahmad Muammar Fanani on 11/30/2017.
 */

public class Tab2_Minuman extends Fragment {
    private static final String TAG = "tab2Fragment";
    private ListView listView2;
    private FireBaseClient_Minuman fireBaseClient;
    private String db_url = "https://teknofest2.firebaseio.com/";
    private String NamaToko,NamaProduk;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.tab2_minuman,container,false);

        listView2 = view.findViewById(R.id.listView2);
        SharedPreferences DataToko = getActivity().getSharedPreferences("DataToko" , Context.MODE_PRIVATE);

        NamaToko = DataToko.getString("Nama_Toko" , "Toko1");
        NamaProduk = DataToko.getString("Nama_Produk" , "HelloDonut");
        fireBaseClient = new FireBaseClient_Minuman(getActivity(),db_url,listView2,NamaToko);
        fireBaseClient.refreshdata();

        return view;
    }
}
