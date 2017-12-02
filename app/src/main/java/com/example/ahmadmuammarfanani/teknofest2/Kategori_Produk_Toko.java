package com.example.ahmadmuammarfanani.teknofest2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ahmadmuammarfanani.teknofest2.kategori_produk_toko.Tab1_Makanan;
import com.example.ahmadmuammarfanani.teknofest2.kategori_produk_toko.Tab2_Minuman;
import com.example.ahmadmuammarfanani.teknofest2.kategori_produk_toko.Tab3_Lain;
import com.example.ahmadmuammarfanani.teknofest2.tambahan.SectionPageAdapter;

public class Kategori_Produk_Toko extends AppCompatActivity {

    private static final String TAG = "Kategori_Produk_Toko";
    private SectionPageAdapter mSectionPageAdapter;
    private ViewPager mViewPager;
    private String NamaToko;
    private String NamaProduk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed);

        mSectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setBackgroundColor(Color.parseColor("#38D0A4"));
        tabLayout.setupWithViewPager(mViewPager);

        SharedPreferences DataToko = this.getSharedPreferences("DataToko", Context.MODE_PRIVATE);

        NamaToko = DataToko.getString("Nama_Toko", "Toko1");
        NamaProduk = DataToko.getString("Nama_Produk", "HelloDonut");

        setsharedpref(NamaToko, NamaProduk);

    }

    public void setupViewPager(ViewPager viewPager) {
        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1_Makanan(), "Makanan");
        adapter.addFragment(new Tab2_Minuman(), "Minuman");
        adapter.addFragment(new Tab3_Lain(), "Lainnya");
        viewPager.setAdapter(adapter);
    }

    private void setsharedpref(String NamaTokonya, String NamaProduknya) {
        SharedPreferences DataToko = this.getSharedPreferences("DataToko", Context.MODE_PRIVATE);
        SharedPreferences.Editor NowDat = DataToko.edit();
        NowDat.putString("Nama_Toko", NamaTokonya);
        NowDat.putString("Nama_Produk", NamaProduknya);
        NowDat.commit();

    }
    public void opendetail(View view){
        TextView textView = view.findViewWithTag("namaproduk");
        //TextView textView2 = view.findViewById(R.id.namatoko);
        String produk = (String)textView.getText();
        //String toko = (String)textView2.getText();
        setsharedpref(NamaToko,produk);
        view.getContext().startActivity(new Intent(view.getContext(),Detail.class));

    }
}
