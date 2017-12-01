package com.example.ahmadmuammarfanani.teknofest2;

import android.app.TabActivity;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.example.ahmadmuammarfanani.teknofest2.tambahan.PicassoClient;
import com.example.ahmadmuammarfanani.teknofest2.tambahan.Produk;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

@SuppressWarnings("deprecation")
public class Homepage extends AppCompatActivity {

    private ImageView imgHome;
    private DatabaseReference mRef;
    private RecyclerView list;
    private ArrayList<DataSnapshot> Produk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        RecyclerView list = (RecyclerView) findViewById(R.id.recyclerView);
        list.setLayoutManager(new LinearLayoutManager(Homepage.this , LinearLayoutManager.HORIZONTAL , false));
        list.setAdapter(new HorizontalAdapter(Produk, Homepage.this));

        imgHome = findViewById(R.id.imgHome);
        mRef = FirebaseDatabase.getInstance().getReference();
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s = dataSnapshot.child("Toko1").child("Logo").getValue().toString();
                PicassoClient.downloading(Homepage.this,s,imgHome);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}