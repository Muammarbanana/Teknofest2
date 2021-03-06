package com.example.ahmadmuammarfanani.teknofest2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.ahmadmuammarfanani.teknofest2.tambahan.HorizontalAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmadmuammarfanani.teknofest2.tambahan.HorizontalAdapter2;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

@SuppressWarnings("deprecation")
public class Homepage extends AppCompatActivity{

    private ImageView imgHome;
    private DatabaseReference mRef;
    private RecyclerView list;
    private EditText Search;
    private ImageButton btnCari;
    private ArrayList<DataSnapshot> ProdukMakan = new ArrayList<>();
    private ArrayList<DataSnapshot> ProdukMinum = new ArrayList<>();
    private ArrayList<DataSnapshot> ProdukLain = new ArrayList<>();
    String Pemilik;
    String logo;
    String waktu;
    String lokasi;
    String deskripsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_homepage);

        btnCari = (ImageButton) findViewById(R.id.btncari);
        btnCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage.this,List_teknofeset.class);
                Search = findViewById(R.id.searchtext);
                String valuecari = Search.getText().toString();
                final Bundle bundle = new Bundle();

                bundle.putString("d",valuecari);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


        imgHome = findViewById(R.id.imgHome);
        mRef = FirebaseDatabase.getInstance().getReference();
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Pemilik = dataSnapshot.child("Owner").getValue(String.class);
                logo = dataSnapshot.child("Logo").getValue(String.class);
                waktu = dataSnapshot.child("Waktu").getValue(String.class);
                lokasi = dataSnapshot.child("Lokasi").getValue(String.class);
                deskripsi = dataSnapshot.child("Deskripsi").getValue(String.class);

                //untuk memisahkan makanan minman dan yg lainnya
                for (DataSnapshot DS : dataSnapshot.getChildren()) {
                    for (DataSnapshot DS2 : DS.getChildren()) {
                        if (DS2.child("Nama").getValue() != null) {
                            if (DS2.child("Jenis").getValue(String.class).equals("Makanan")) {
                                ProdukMakan.add(DS2);
                                //Log.d("TAG", "opendetail: "+DS2.child("Nama").getValue(String.class));
                            } else if (DS2.child("Jenis").getValue(String.class).equals("Minuman")) {
                                ProdukMinum.add(DS2);
                            } else {
                                ProdukLain.add(DS2);
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        RecyclerView list = (RecyclerView) findViewById(R.id.listmakanantoko2);
        list.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false));
        list.setAdapter(new HorizontalAdapter2(ProdukMakan, Homepage.this));

        RecyclerView list2 = (RecyclerView) findViewById(R.id.listminumantoko2);
        list2.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false));
        HorizontalAdapter2 a = new HorizontalAdapter2(ProdukMinum,Homepage.this);
        //a.setListener(this);
        list2.setAdapter(a);

        RecyclerView list3 = (RecyclerView) findViewById(R.id.listlaintoko2);
        list3.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false));
        list3.setAdapter(new HorizontalAdapter2(ProdukLain, Homepage.this));

    }

    private void setsharedpref(String NamaTokonya , String NamaProduknya){
        SharedPreferences DataToko = this.getSharedPreferences("DataToko" , Context.MODE_PRIVATE);
        SharedPreferences.Editor NowDat = DataToko.edit();
        NowDat.putString("Nama_Toko" , NamaTokonya);
        NowDat.putString("Nama_Produk", NamaProduknya);
        NowDat.commit();

    }

    public void opendetail2(View view){

        Toast.makeText(this , "Wait For Open" , Toast.LENGTH_SHORT).show();
        TextView textView = view.findViewWithTag("namaproduk");
        String produk =textView.getText().toString();
        String namatoko = view.findViewById(R.id.gambarmakanan).getTag().toString();
        setsharedpref(namatoko , produk);
        Intent intent = new Intent(this , Detail.class);
        startActivity(intent);


    }
    public  void seemore(View view){

    }
/*
    @Override
    public void onClick(DataSnapshot data) {
        opendetail(data);
    }*/
}