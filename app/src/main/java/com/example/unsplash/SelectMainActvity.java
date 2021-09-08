package com.example.unsplash;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unsplash.MEMOApp.MemoMainActivity;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class SelectMainActvity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_main_actvity);
try{
    ImageView memoimageButton = findViewById(R.id.imagebutton1);
    ImageView wallimagebutton = findViewById(R.id.imagebutton2);

    wallimagebutton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(SelectMainActvity.this, MainActivity.class);
            startActivity(i);
        }
    });
    memoimageButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            Intent i = new Intent(SelectMainActvity.this, MemoMainActivity.class);
            startActivity(i);

        }

    });

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
   Drawable drawable=new Drawable() {
       @Override
       public void draw(@NonNull Canvas canvas) {
           canvas.drawColor(getResources().getColor(R.color.statuscolorselect));
       }

       @Override
       public void setAlpha(int alpha) {

       }

       @Override
       public void setColorFilter(@Nullable ColorFilter colorFilter) {

       }

       @SuppressLint("WrongConstant")
       @Override
       public int getOpacity() {
           return 0;
       }
   };
   getSupportActionBar().setBackgroundDrawable(drawable);
     getWindow().setNavigationBarColor(getResources().getColor(R.color.statuscolorselect));
    getWindow().setStatusBarColor(getResources().getColor(R.color.statuscolorselect));


 }catch(OutOfMemoryError e){e.printStackTrace();}
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}