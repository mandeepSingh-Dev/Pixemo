package com.example.unsplash;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.palette.graphics.Palette;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorSpace;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unsplash.Categries.WallCategoryActivity;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Cache;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class WallScreenActivity extends AppCompatActivity {
    String key;
    Drawable drawable;

    private Palette.Swatch colorswatch=null;
    private Palette.Swatch colorswatch2=null;
    private Palette.Swatch colorswatch3=null;
    private Palette.Swatch colorswatch4=null;
    private Palette.Swatch colorswatch5=null;

      private static final String[] STORAGE_PERMISSIONS = { Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wall_screen2);
        LinearLayout rootLayout = findViewById(R.id.rootlayout);



        Bundle b = getIntent().getExtras();
        key = b.getString("Hello");
        String downloads = b.getString("Downloads");
        String description = b.getString("Description");
        //Toast.makeText(this,key, Toast.LENGTH_SHORT).show();

        ImageView imageView = (ImageView) findViewById(R.id.imageViewwallscreen);

        TextView textDownload = (TextView) findViewById(R.id.downloadTEXT);
        TextView textDescription = (TextView) findViewById(R.id.imageDescription);


        if (downloads != null && description != null) {
            textDownload.setText("downloads:" + downloads);
            textDescription.setText("Tags: " + description);
        }
        Picasso.get().load(key).placeholder(R.drawable.loading).into(imageView);


        //Palette code is in this thread
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                Picasso.get().load(key).into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {



                        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                            @Override
                            public void onGenerated(@Nullable Palette palette) {
                                colorswatch = palette.getDarkMutedSwatch();
                                colorswatch2= palette.getDarkVibrantSwatch();
                                colorswatch3 = palette.getDominantSwatch();
                                colorswatch4 = palette.getVibrantSwatch();
                                colorswatch5 = palette.getMutedSwatch();
                                if (colorswatch != null) {
                                    rootLayout.setBackgroundColor(colorswatch.getRgb());
                                    textDownload.setTextColor(Color.WHITE);
                                    textDescription.setTextColor(Color.WHITE);
                                    //draw drawable from colorswatch
                                    drawable=new Drawable() {
                                        @Override
                                        public void draw(@NonNull Canvas canvas) {
                                            canvas.drawColor(colorswatch.getRgb());
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
                                    };     ///finish drawing colorswatch drawable.
                                    getSupportActionBar().setBackgroundDrawable(drawable);
                                    getWindow().setNavigationBarColor(colorswatch.getRgb());
                                    getWindow().setStatusBarColor(colorswatch.getRgb());


                                }
                                else if(colorswatch2!=null){
                                    rootLayout.setBackgroundColor(colorswatch2.getRgb());
                                    textDownload.setTextColor(Color.WHITE);
                                    textDescription.setTextColor(Color.WHITE);

                                    //draw drawable from colorswatch
                                    drawable=new Drawable() {
                                        @Override
                                        public void draw(@NonNull Canvas canvas) {
                                            canvas.drawColor(colorswatch2.getRgb());
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
                                    };     ///finish drawing colorswatch drawable.
                                    getSupportActionBar().setBackgroundDrawable(drawable);
                                    getWindow().setNavigationBarColor(colorswatch2.getRgb());
                                    getWindow().setStatusBarColor(colorswatch2.getRgb());

                                }
                                else if(colorswatch3!=null){
                                    rootLayout.setBackgroundColor(colorswatch3.getRgb());
                                    textDownload.setTextColor(Color.BLACK);
                                    textDescription.setTextColor(Color.BLACK);

                                    //draw drawable from colorswatch
                                    drawable=new Drawable() {
                                        @Override
                                        public void draw(@NonNull Canvas canvas) {
                                            canvas.drawColor(colorswatch3.getRgb());
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
                                    };     ///finish drawing colorswatch drawable.
                                    getSupportActionBar().setBackgroundDrawable(drawable);
                                    getWindow().setNavigationBarColor(colorswatch3.getRgb());
                                    getWindow().setStatusBarColor(colorswatch3.getRgb());

                                }
                                else if(colorswatch4!=null){
                                    rootLayout.setBackgroundColor(colorswatch4.getRgb());
                                    textDownload.setTextColor(Color.BLACK);
                                    textDescription.setTextColor(Color.BLACK);

                                    //draw drawable from colorswatch
                                    drawable=new Drawable() {
                                        @Override
                                        public void draw(@NonNull Canvas canvas) {
                                            canvas.drawColor(colorswatch4.getRgb());
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
                                    };     ///finish drawing colorswatch drawable.
                                    getSupportActionBar().setBackgroundDrawable(drawable);
                                    getWindow().setNavigationBarColor(colorswatch4.getRgb());
                                    getWindow().setStatusBarColor(colorswatch4.getRgb());

                                }
                                else if(colorswatch5!=null){
                                    rootLayout.setBackgroundColor(colorswatch5.getRgb());
                                    textDownload.setTextColor(Color.BLACK);
                                    textDescription.setTextColor(Color.BLACK);

                                    //draw drawable from colorswatch
                                    drawable=new Drawable() {
                                        @Override
                                        public void draw(@NonNull Canvas canvas) {
                                            canvas.drawColor(colorswatch5.getRgb());
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
                                    };     ///finish drawing colorswatch drawable.
                                    getSupportActionBar().setBackgroundDrawable(drawable);
                                    getWindow().setNavigationBarColor(colorswatch5.getRgb());
                                    getWindow().setStatusBarColor(colorswatch5.getRgb());

                                }
                                else {

                                    rootLayout.setBackground(getResources().getDrawable(R.drawable.gradientcolorforlayouts));
                                    textDownload.setTextColor(Color.BLACK);
                                    textDescription.setTextColor(Color.BLACK);
                             getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.gradientcolorforlayouts));
                                }
                            }
                        });  //palette closed...

                    }
                    @Override
                    public void onBitmapFailed (Exception e, Drawable errorDrawable){

                    }

                    @Override
                    public void onPrepareLoad (Drawable placeHolderDrawable){

                    }
                });
            }
        };
        Handler handler=new Handler();
        handler.post(runnable);          //Palette code is in this thread

        Button download=findViewById(R.id.download);

        //download button in this thread
      Runnable runnable1=new Runnable() {
    @Override
    public void run() {
        download.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
               /** NotificationManager notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Notification notification=new Notification(R.drawable.plusicon,"Hello",System.currentTimeMillis());

            Context context = getApplicationContext();
                CharSequence contentTitle = "My notification";
                CharSequence contentText = "Hello World!";
                Intent notificationIntent = new Intent(getApplicationContext(),WallScreenActivity.class);
                PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);

                notificationManager.setLatestEventInfo(context, contentTitle, contentText, contentIntent);

                private static final int HELLO_ID = 1;

                notificationManager.notify(1, notification);
*/

                Picasso.get().load(key).into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    OutputStream os;
                        try {

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                                ContentResolver resolver = getContentResolver();
                                ContentValues values = new ContentValues();
                                values.put(MediaStore.MediaColumns.DISPLAY_NAME, "Image_" + ".jpg");
                                values.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg");
                                values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + File.separator + "PicassoImages");

                                Uri imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                                os = resolver.openOutputStream(Objects.requireNonNull(imageUri));
                                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
                                Objects.requireNonNull(os);

                            } else {
                                File mydir = new File(Environment.getExternalStorageDirectory() + "/11zon");
                                if (!mydir.exists()) {
                                    mydir.mkdirs();
                                }

                                String fileUri = mydir.getAbsolutePath() + File.separator + System.currentTimeMillis() + ".jpg";
                                FileOutputStream outputStream = new FileOutputStream(fileUri);

                                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                                outputStream.flush();
                                outputStream.close();

                            }
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
Snackbar.make(textDownload,"Wallpaper Downloaded",BaseTransientBottomBar.LENGTH_LONG).setTextColor(getResources().getColor(R.color.mycolor12)).show();
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
            }
        });

    }
};   Handler handler1=new Handler();
handler1.post(runnable1);       //download button code is in this Thread.



           verifyPermissions();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Pixabay");

    }    //onCreate method closed...
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
    public void verifyPermissions() {
        // This will return the current Status
        int permissionExternalMemory = ActivityCompat.checkSelfPermission(WallScreenActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permissionExternalMemory != PackageManager.PERMISSION_GRANTED) {
            // If permission not granted then ask for permission real time.
            ActivityCompat.requestPermissions(WallScreenActivity.this, STORAGE_PERMISSIONS, 1);
        }
    }
}