package com.example.unsplash.Categries;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.unsplash.CategoriesFragment;
import com.example.unsplash.R;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

public class WallCategoryActivity extends AppCompatActivity {
String fdata;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wall_category);
        TabLayout tabLayout=(TabLayout)findViewById(R.id.tabs2);
        ViewPager viewPager=(ViewPager)findViewById(R.id.viewpager2);


        MyFragmentAdapter2 myFragmentAdapter2=new MyFragmentAdapter2(getSupportFragmentManager());
        viewPager.setAdapter(myFragmentAdapter2);

        Bundle b;
        b=getIntent().getExtras();
        fdata= b.getString("Category");

        myFragmentAdapter2.setBackground(fdata);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(getResources().getColor(R.color.mycolor6),getResources().getColor(R.color.mycolor7));

        getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Pixabay");
        getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolorwallcate));
getWindow().setNavigationBarColor(getResources().getColor(R.color.statuscolorwallcate));


    }

    @Override
    public boolean onSupportNavigateUp() {
       onBackPressed();
        return super.onSupportNavigateUp();
    }
}