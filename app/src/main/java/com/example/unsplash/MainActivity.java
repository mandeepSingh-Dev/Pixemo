package com.example.unsplash;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    int length;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.gradyy));

        ViewPager viewPager=(ViewPager)findViewById(R.id.viewpager);
        MyFragmentAdapter myFragmentAdapter=new MyFragmentAdapter(getSupportFragmentManager());


        viewPager.setAdapter(myFragmentAdapter);


        TabLayout tabLayout=(TabLayout)findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Pixabay");
getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.gradlayout));

tabLayout.setTabTextColors(getResources().getColor(R.color.cate1),getResources().getColor(R.color.cate));

getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolor1));
        getWindow().setNavigationBarColor(getResources().getColor(R.color.statuscolor1));

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}