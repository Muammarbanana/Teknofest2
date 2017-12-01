package com.example.ahmadmuammarfanani.teknofest2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ahmadmuammarfanani.teknofest2.tambahan.FireBaseClient;

public class List_teknofeset extends AppCompatActivity {

    private String db_url = "https://teknofest2.firebaseio.com/";
    private ListView listView;
    private FireBaseClient fireBaseClient;
    private CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_teknofeset);

        listView = findViewById(R.id.listView);
        fireBaseClient = new FireBaseClient(this,db_url,listView);
        fireBaseClient.refreshdata();

    }

    public void opendetail(View view){
        TextView textView = view.findViewWithTag("namaproduk");
        String produk = (String)textView.getText();
        setsharedpref("Toko1",produk);
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
