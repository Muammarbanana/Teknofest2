package com.example.ahmadmuammarfanani.teknofest2.tambahan;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ahmadmuammarfanani.teknofest2.R;
import com.example.ahmadmuammarfanani.teknofest2.Tabbed;

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
                itemView.getContext().startActivity(new Intent(itemView.getContext(),Tabbed.class));
                Log.d("Tesaja", "onClick: " + nama.getText().toString());
            }
        });
    }
}
