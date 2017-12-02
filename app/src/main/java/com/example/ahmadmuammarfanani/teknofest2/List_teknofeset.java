package com.example.ahmadmuammarfanani.teknofest2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ahmadmuammarfanani.teknofest2.tambahan.FireBaseClient;
import com.example.ahmadmuammarfanani.teknofest2.tambahan.Produk;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

public class List_teknofeset extends AppCompatActivity {

    private String db_url = "https://teknofest2.firebaseio.com/";
    private ListView listView;
    private FireBaseClient fireBaseClient;
    private EditText searchtext2;
    private ImageView btnsrc,backbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_teknofeset);
        Bundle bundle = getIntent().getExtras();
        final String d = bundle.getString("d");
        listView = findViewById(R.id.listView);
        fireBaseClient = new FireBaseClient(this,db_url,listView,d);
        fireBaseClient.refreshdata();

        searchtext2 = findViewById(R.id.searchtext2);
        btnsrc = findViewById(R.id.btnsrc);
        backbutton = findViewById(R.id.backbutton);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnsrc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String f = searchtext2.getText().toString();
                fireBaseClient = new FireBaseClient(view.getContext(),db_url,listView,f);
                fireBaseClient.refreshdata();
            }
        });

        searchtext2.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i== EditorInfo.IME_ACTION_DONE){
                    final String f = searchtext2.getText().toString();
                    fireBaseClient = new FireBaseClient(List_teknofeset.this,db_url,listView,f);
                    fireBaseClient.refreshdata();
                }
                return false;
            }
        });

    }

    public void opendetail(View view){
        TextView textView = view.findViewWithTag("namaproduk");
        TextView textView2 = view.findViewById(R.id.namatoko);
        String produk = (String)textView.getText();
        String toko = (String)textView2.getText();
        setsharedpref(toko,produk);
        view.getContext().startActivity(new Intent(view.getContext(),Detail.class));

    }

    private void setsharedpref(String NamaTokonya , String NamaProduknya){
        SharedPreferences DataToko = this.getSharedPreferences("DataToko" , Context.MODE_PRIVATE);
        SharedPreferences.Editor NowDat = DataToko.edit();
        NowDat.putString("Nama_Toko" , NamaTokonya);
        NowDat.putString("Nama_Produk", NamaProduknya);
        NowDat.commit();

    }

}
