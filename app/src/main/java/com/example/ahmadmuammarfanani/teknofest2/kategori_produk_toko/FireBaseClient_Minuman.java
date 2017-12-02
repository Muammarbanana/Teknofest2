package com.example.ahmadmuammarfanani.teknofest2.kategori_produk_toko;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ahmadmuammarfanani.teknofest2.tambahan.CustomAdapter;
import com.example.ahmadmuammarfanani.teknofest2.tambahan.Produk;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Ahmad Muammar Fanani on 11/27/2017.
 */

public class FireBaseClient_Minuman {
    Context c;
    String db_url,NamaToko;
    ListView listView;
    DatabaseReference mRef;
    ArrayList<Produk> produks = new ArrayList<>();
    CustomAdapter customAdapter;

    public FireBaseClient_Minuman(Context c, String db_url, ListView listView,String NamaToko) {
        this.c = c;
        this.db_url = db_url;
        this.listView = listView;
        this.NamaToko = NamaToko;

        mRef = FirebaseDatabase.getInstance().getReferenceFromUrl(db_url);
    }

    public void refreshdata(){
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                getupdates(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void getupdates(DataSnapshot dataSnapshot){
        produks.clear();
        for(DataSnapshot ds2:dataSnapshot.getChildren()) {
            for (DataSnapshot ds : ds2.getChildren()) {
                Produk p = new Produk();
                p.setNama(ds.child("Nama").getValue(String.class));
                p.setHarga(ds.child("Harga").getValue(String.class));
                p.setHarga("Rp. "+p.getHarga()+",-");
                p.setUrl(ds.child("IMGUrl").child("APriority").getValue(String.class));
                p.setUrl2(ds2.child("Owner").getValue(String.class));
                p.setJam(ds2.child("Waktu").getValue(String.class));
                p.setLokasi(ds2.child("Lokasi").getValue(String.class));
                p.setNamaToko(ds2.child("Nama").getValue(String.class));
                p.setJenis(ds.child("Jenis").getValue(String.class));
                Log.d("TAG","Message "+p.getJenis());
                if (p.getNama() != null ) {
                    if(p.getNamaToko().equals(NamaToko)){
                        if(p.getJenis().equals("Minuman")) {
                            produks.add(p);
                        }
                    }
                }
            }
        }
        if(produks.size()>0){
            customAdapter = new CustomAdapter(c,produks);
            listView.setAdapter(customAdapter);
        }else{
            Toast.makeText(c,"No Data", Toast.LENGTH_SHORT).show();
        }
    }
}
