package com.example.ahmadmuammarfanani.teknofest2;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Ahmad Muammar Fanani on 11/27/2017.
 */

public class MyHolder {

    TextView nama,harga;
    ImageView gambar;

    public MyHolder(View itemView) {
        nama = itemView.findViewById(R.id.nama);
        harga = itemView.findViewById(R.id.harga);
        gambar = itemView.findViewById(R.id.gambarfood);
    }
}
