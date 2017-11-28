package com.example.ahmadmuammarfanani.teknofest2;

import android.content.Context;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ahmad Muammar Fanani on 11/27/2017.
 */

public class FireBaseClient {
    Context c;
    String db_url;
    ListView listView;
    DatabaseReference mRef;
    ArrayList<Produk> produks = new ArrayList<>();
    CustomAdapter customAdapter;

    public FireBaseClient(Context c,String db_url,ListView listView) {
        this.c = c;
        this.db_url = db_url;
        this.listView = listView;

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
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

        for(DataSnapshot ds:dataSnapshot.getChildren()){
            Produk p = new Produk();
            p.setNama(ds.getValue(Produk.class).getNama());
            p.setHarga(ds.getValue(Produk.class).getHarga());
            p.setUrl(ds.getValue(Produk.class).getUrl());
            produks.add(p);
        }
        if(produks.size()>0){
            customAdapter = new CustomAdapter(c,produks);
            listView.setAdapter(customAdapter);
        }else{
            Toast.makeText(c,"No Data", Toast.LENGTH_SHORT).show();
        }
    }
}
