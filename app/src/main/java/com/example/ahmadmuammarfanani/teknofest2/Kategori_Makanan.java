package com.example.ahmadmuammarfanani.teknofest2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.ahmadmuammarfanani.teknofest2.kategori_makanan.Tab1_Donut;
import com.example.ahmadmuammarfanani.teknofest2.kategori_makanan.Tab2_Snack;
import com.example.ahmadmuammarfanani.teknofest2.kategori_makanan.Tab3_Dessert;
import com.example.ahmadmuammarfanani.teknofest2.kategori_makanan.Tab4_Gorengan;
import com.example.ahmadmuammarfanani.teknofest2.tambahan.SectionPageAdapter;

public class Kategori_Makanan extends AppCompatActivity {

    private static final String TAG = "Kategori_Makanan";
    private SectionPageAdapter mSectionPageAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed_makanan);

        mSectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container_makanan);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs_makanan);
        tabLayout.setBackgroundColor(Color.parseColor("#38D0A4"));
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(mViewPager);
    }

    public void setupViewPager(ViewPager viewPager){
        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1_Donut(), "Donut");
        adapter.addFragment(new Tab2_Snack(),"Snack");
        adapter.addFragment(new Tab3_Dessert(),"Dessert");
        adapter.addFragment(new Tab4_Gorengan(),"Gorengan");
        viewPager.setAdapter(adapter);
    }
}
