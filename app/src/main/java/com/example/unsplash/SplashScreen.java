package com.example.unsplash;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothClass;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.widget.LinearLayout;

public class SplashScreen extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        LinearLayout splashlayout=findViewById(R.id.spalshlayout);
        MediaPlayer mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.herodecorativecelebrativetwo);
        mediaPlayer.start();
        Thread t1=new Thread(){
            @Override
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {

                    Intent i=new Intent(SplashScreen.this,SelectMainActvity.class);
                    startActivity(i);
                    finish();
                }
            }
        };
        t1.start();
//        Bitmap bitmapDrawable=BitmapFactory.decodeResource(getResources(),R.drawable.gradienta2);

        getSupportActionBar().hide();
        getWindow().setStatusBarColor(getResources().getColor(R.color.statussplash));
        getWindow().setNavigationBarColor(getResources().getColor(R.color.black));

    }
}