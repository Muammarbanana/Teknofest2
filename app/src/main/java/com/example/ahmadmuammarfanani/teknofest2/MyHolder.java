package com.example.ahmadmuammarfanani.teknofest2;

import android.content.Intent;
import android.support.v7.view.menu.MenuView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemView.getContext().startActivity(new Intent(itemView.getContext(),Contohaja.class));
                Log.d("Tesaja", "onClick: " + nama.getText().toString());
            }
        });
    }
}
