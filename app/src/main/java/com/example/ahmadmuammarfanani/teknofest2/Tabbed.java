package com.example.ahmadmuammarfanani.teknofest2;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.example.ahmadmuammarfanani.teknofest2.tambahan.SectionPageAdapter;

public class Tabbed extends AppCompatActivity {

    private static final String TAG = "Tabbed";
    private SectionPageAdapter mSectionPageAdapter;
    private ViewPager mViewPager;

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
    }

    public void setupViewPager(ViewPager viewPager){
        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1_Makanan(), "Makanan");
        adapter.addFragment(new Tab2_Minuman(),"Minuman");
        adapter.addFragment(new Tab3_Lain(),"Lainnya");
        viewPager.setAdapter(adapter);
    }
}
