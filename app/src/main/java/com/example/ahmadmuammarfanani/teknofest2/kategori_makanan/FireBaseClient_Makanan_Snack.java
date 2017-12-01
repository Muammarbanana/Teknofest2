package com.example.ahmadmuammarfanani.teknofest2.kategori_makanan;

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

import java.util.ArrayList;

/**
 * Created by Ahmad Muammar Fanani on 11/27/2017.
 */

public class FireBaseClient_Makanan_Snack {
    Context c;
    String db_url;
    ListView listView;
    DatabaseReference mRef;
    ArrayList<Produk> produks = new ArrayList<>();
    CustomAdapter customAdapter;

    public FireBaseClient_Makanan_Snack(Context c, String db_url, ListView listView) {
        this.c = c;
        this.db_url = db_url;
        this.listView = listView;

        mRef = FirebaseDatabase.getInstance().getReferenceFromUrl(db_url);
    }

    public void refreshdata(){
        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                getupdates(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                getupdates(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                getupdates(dataSnapshot);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void getupdates(DataSnapshot dataSnapshot){
        produks.clear();
        DataSnapshot dss = dataSnapshot;
        for(DataSnapshot ds:dataSnapshot.getChildren()){
            Produk p = new Produk();
            p.setNama(ds.child("Nama").getValue(String.class));
            p.setHarga(ds.child("Harga").getValue(String.class));
            p.setUrl(ds.child("IMGUrl").child("APriority").getValue(String.class));
            p.setUrl2(dss.child("Owner").getValue(String.class));
            p.setJam(dss.child("Waktu").getValue(String.class));
            p.setLokasi(dss.child("Lokasi").getValue(String.class));
            p.setJenis(ds.child("Jenis").getValue(String.class));
            if(p.getNama()!=null){
                if(p.getJenis().equals("Lainnya")){
                    produks.add(p);
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
