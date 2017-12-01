package com.example.ahmadmuammarfanani.teknofest2.tambahan;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ahmadmuammarfanani.teknofest2.Detail;
import com.example.ahmadmuammarfanani.teknofest2.Homepage;
import com.example.ahmadmuammarfanani.teknofest2.Kategori_Makanan;
import com.example.ahmadmuammarfanani.teknofest2.Kategori_Produk_Toko;
import com.example.ahmadmuammarfanani.teknofest2.R;

/**
 * Created by Ahmad Muammar Fanani on 11/27/2017.
 */

public class MyHolder {

    TextView nama,harga,lokasi,jam;
    ImageView gambar,foto;

    public MyHolder(final View itemView) {
        nama = itemView.findViewById(R.id.nama);
        harga = itemView.findViewById(R.id.harga);
        gambar = itemView.findViewById(R.id.gambarfood);
        lokasi = itemView.findViewById(R.id.lokasi);
        jam = itemView.findViewById(R.id.jam);
        foto = itemView.findViewById(R.id.foto);

    }


}
