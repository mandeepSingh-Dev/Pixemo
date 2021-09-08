package com.example.unsplash.MEMOApp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.ContentLoadingProgressBar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.icu.text.CaseMap;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.os.IResultReceiver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.unsplash.R;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.snackbar.SnackbarContentLayout;

import java.util.List;
import java.util.Random;
import java.util.zip.Inflater;

public class MemoEditActivity extends AppCompatActivity {
    EditText title;
    EditText notes;

    String titlestr;
    String notestr;

   // List<NoteData> list;
    //LinearLayout listlayout;



   String TITLE;  //These  strings are passed from
   String NOTEE;   //  MemoMainActvity.java class
    int idd;
    LinearLayout editlayout;
    Context context;

    View v;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_edit);

        title=(EditText)findViewById(R.id.titleedittext);
        notes=(EditText)findViewById(R.id.NotesEditttext);
        //Snackbar.make(title,"hfjsjfs",BaseTransientBottomBar.LENGTH_SHORT).show();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Memo ");

         editlayout=(LinearLayout)findViewById(R.id.EditLayout);
context=editlayout.getContext();

        SharedPreferences sharedPreferences=getSharedPreferences("demo",MODE_PRIVATE);
        int COLORLayout=sharedPreferences.getInt("colorforLayout",Color.WHITE);
        int COLORedittextback=sharedPreferences.getInt("edittextbackColor",Color.LTGRAY);
        int COLORNOTESTEXT=sharedPreferences.getInt("notestextcolor",Color.BLACK);
        int ColorEditTExtColor=sharedPreferences.getInt("edittextcolor",Color.WHITE);
        editlayout.setBackgroundColor(COLORLayout);
        title.setBackgroundColor(COLORedittextback);
        notes.setTextColor(COLORNOTESTEXT);
        title.setTextColor(ColorEditTExtColor);
        getWindow().setStatusBarColor(COLORLayout);
        getWindow().setNavigationBarColor(COLORLayout);




        Drawable drawable=new Drawable() {
            @Override
            public void draw(@NonNull Canvas canvas) {
               canvas.drawColor(COLORLayout);

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


        //TAKING TITLE FROM MEMOMAINACTIVITY USING BUNDLE
        try {  //Actually we applied try catch here because when we click plus button from MEMOMAINactivity then
            //MemoEditActivity opens using Intent And this Bundle wala code also runs in which our TITLE and NOTEE
            // sTRINGS ARE empty Because when we dont click textview from listitem then no data willpass and these strings keep empty so we applied Exception handfling here
            Bundle b = getIntent().getExtras();
            setIdd(b.getInt("IDDD"));
             TITLE = b.getString("title");
             NOTEE = b.getString("note");

             Log.d("EDITIDD",String.valueOf(idd));

            title.setText(TITLE);
            notes.setText(NOTEE);
        }catch(NullPointerException e){e.printStackTrace();}

    }

    @Override
    public boolean onSupportNavigateUp() {
       // Toast.makeText(this, titlestr, Toast.LENGTH_SHORT).show();
      onBackPressed();
      Intent i=new Intent(getApplicationContext(),MemoMainActivity.class);
      startActivity(i);
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menuok,menu);
        return  true;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Random rand1 = new Random();
        switch (item.getItemId()) {

            case R.id.okButton: {

                int iddd = getIdd();
                titlestr = title.getText().toString();
                notestr = notes.getText().toString();

                NoteData noteData = new NoteData();
                MySqLiteOpenHelper mySqLiteOpenHelper = new MySqLiteOpenHelper(this);
                if (titlestr.contains(TITLE + "")) {

                    noteData.setId(iddd);
                    noteData.setTitle(titlestr);
                    noteData.setNotes(notestr);

                    mySqLiteOpenHelper.updateData(noteData);

                } else {

                    noteData.setTitle(titlestr);
                    noteData.setNotes(notestr);
                    // mySqLiteOpenHelper=new MySqLiteOpenHelper(this);

                    mySqLiteOpenHelper.AddData(noteData);
                   // Toast.makeText(this, "Previous Title is not Matched So new Entry is added", Toast.LENGTH_LONG).show();
                    // Snackbar.make(title,"Previous",BaseTransientBottomBar.LENGTH_LONG).show();
                }

              //  Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, MemoMainActivity.class);
                startActivity(i);
break;
            }
            case R.id.colorbutton1: {
                SharedPreferences sharedPreferences = getSharedPreferences("demo", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                int colorNumber = rand1.nextInt(10);

                switch (colorNumber) {
                    case 0: {
                        int colorforedittextback = getResources().getColor(R.color.mycolor16);
                        editor.putInt("colorforLayout", Color.BLACK);
                        editor.putInt("edittextbackColor", colorforedittextback);
                        editor.putInt("notestextcolor", Color.WHITE);
                        editor.putInt("edittextcolor", Color.BLACK);
                        editor.apply();
                        editlayout.setBackgroundColor(Color.BLACK);
                        title.setBackgroundColor(colorforedittextback);
                        notes.setTextColor(Color.WHITE);
                        title.setTextColor(Color.BLACK);
                        getWindow().setStatusBarColor(Color.BLACK);
                        getWindow().setNavigationBarColor(Color.BLACK);

                        getSupportActionBar().setBackgroundDrawable(new Drawable() {
                            @Override
                            public void draw(@NonNull Canvas canvas) {
                                canvas.drawColor(Color.BLACK);
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
                        });
                        break;

                    }
                    case 1: {
                        int colorforedittextback = getResources().getColor(R.color.teal_700);
                        editor.putInt("colorforLayout", Color.CYAN);
                        editor.putInt("edittextbackColor", colorforedittextback);
                        editor.putInt("notestextcolor", Color.BLACK);
                        editor.putInt("edittextcolor", Color.BLACK);
                        editor.apply();
                        editlayout.setBackgroundColor(Color.CYAN);
                        title.setBackgroundColor(colorforedittextback);
                        notes.setTextColor(Color.BLACK);
                        title.setTextColor(Color.BLACK);
                        getWindow().setStatusBarColor(Color.CYAN);
                        getWindow().setNavigationBarColor(Color.CYAN);

                        getSupportActionBar().setBackgroundDrawable(new Drawable() {
                            @Override
                            public void draw(@NonNull Canvas canvas) {
                                canvas.drawColor(Color.CYAN);
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
                        });
                        break;
                    }
                    case 2: {
                        int colorforedittextback = getResources().getColor(R.color.mycolor6);
                        editor.putInt("colorforLayout",getResources().getColor(R.color.mycolor6));
                        editor.putInt("edittextbackColor", colorforedittextback);
                        editor.putInt("notestextcolor", Color.BLACK);
                        editor.putInt("edittextcolor", Color.BLACK);
                        editor.apply();
                        editlayout.setBackgroundColor(getResources().getColor(R.color.mycolor6));
                        title.setBackgroundColor(colorforedittextback);
                        notes.setTextColor(Color.BLACK);
                        title.setTextColor(Color.BLACK);

                        getWindow().setStatusBarColor(getResources().getColor(R.color.mycolor6));
                        getWindow().setNavigationBarColor(getResources().getColor(R.color.mycolor6));
                        getSupportActionBar().setBackgroundDrawable(new Drawable() {
                            @Override
                            public void draw(@NonNull Canvas canvas) {
                                canvas.drawColor(getResources().getColor(R.color.mycolor6));
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
                        });
                        break;
                    }
                    case 3: {
                        int colorforedittextback = getResources().getColor(R.color.mycolor11);
                        editor.putInt("colorforLayout", Color.MAGENTA);
                        editor.putInt("edittextbackColor", colorforedittextback);
                        editor.putInt("notestextcolor", Color.BLACK);
                        editor.putInt("edittextcolor", Color.BLACK);
                        editor.apply();
                        editlayout.setBackgroundColor(Color.MAGENTA);
                        title.setBackgroundColor(colorforedittextback);
                        notes.setTextColor(Color.BLACK);
                        title.setTextColor(Color.BLACK);
                        getWindow().setStatusBarColor(Color.MAGENTA);
                        getWindow().setNavigationBarColor(Color.MAGENTA);
                        getSupportActionBar().setBackgroundDrawable(new Drawable() {
                            @Override
                            public void draw(@NonNull Canvas canvas) {
                                canvas.drawColor(Color.MAGENTA);
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
                        });

                        break;
                    }
                    case 4: {
                        int colorforedittextback = getResources().getColor(R.color.mycolor16);
                        editor.putInt("colorforLayout", Color.DKGRAY);
                        editor.putInt("edittextbackColor", colorforedittextback);
                        editor.putInt("notestextcolor", Color.WHITE);
                        editor.putInt("edittextcolor", Color.BLACK);
                        editor.apply();
                        editlayout.setBackgroundColor(Color.DKGRAY);
                        title.setBackgroundColor(colorforedittextback);
                        notes.setTextColor(Color.WHITE);
                        title.setTextColor(Color.BLACK);
                        getWindow().setStatusBarColor(Color.DKGRAY);
                        getWindow().setNavigationBarColor(Color.DKGRAY);
                        getSupportActionBar().setBackgroundDrawable(new Drawable() {
                            @Override
                            public void draw(@NonNull Canvas canvas) {
                                canvas.drawColor(Color.DKGRAY);
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
                        });
                        break;
                    }
                    case 5: {
                        int colorforedittextback = getResources().getColor(R.color.mycolor4);
                        editor.putInt("colorforLayout", Color.RED);
                        editor.putInt("edittextbackColor", colorforedittextback);
                        editor.putInt("notestextcolor", Color.WHITE);
                        editor.putInt("edittextcolor", Color.BLACK);
                        editor.apply();
                        editlayout.setBackgroundColor(Color.RED);
                        title.setBackgroundColor(colorforedittextback);
                        notes.setTextColor(Color.WHITE);
                        title.setTextColor(Color.BLACK);
                        getWindow().setStatusBarColor(Color.RED);
                        getWindow().setNavigationBarColor(Color.RED);
                        getSupportActionBar().setBackgroundDrawable(new Drawable() {
                            @Override
                            public void draw(@NonNull Canvas canvas) {
                                canvas.drawColor( Color.RED);
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
                        });
                        break;
                    }
                    case 6: {
                        int colorforedittextback = getResources().getColor(R.color.mycolor16);
                        editor.putInt("colorforLayout", Color.LTGRAY);
                        editor.putInt("edittextbackColor", colorforedittextback);
                        editor.putInt("notestextcolor", Color.WHITE);
                        editor.putInt("edittextcolor", Color.BLACK);
                        editor.apply();
                        editlayout.setBackgroundColor(Color.LTGRAY);
                        title.setBackgroundColor(colorforedittextback);
                        notes.setTextColor(Color.WHITE);
                        title.setTextColor(Color.BLACK);
                        getWindow().setStatusBarColor(Color.LTGRAY);
                        getWindow().setNavigationBarColor(Color.LTGRAY);
                        getSupportActionBar().setBackgroundDrawable(new Drawable() {
                            @Override
                            public void draw(@NonNull Canvas canvas) {
                                canvas.drawColor(Color.LTGRAY);
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
                        });
                        break;
                    }
                    case 7: {
                        int colorforedittextback = getResources().getColor(R.color.mycolor19);
                        editor.putInt("colorforLayout", Color.BLUE);
                        editor.putInt("edittextbackColor", colorforedittextback);
                        editor.putInt("notestextcolor", Color.WHITE);
                        editor.putInt("edittextcolor", Color.WHITE);
                        editor.apply();
                        editlayout.setBackgroundColor(Color.BLUE);
                        title.setBackgroundColor(colorforedittextback);
                        notes.setTextColor(Color.WHITE);
                        title.setTextColor(Color.WHITE);
                        getWindow().setStatusBarColor(Color.BLUE);
                        getWindow().setNavigationBarColor(Color.BLUE);
                        getSupportActionBar().setBackgroundDrawable(new Drawable() {
                            @Override
                            public void draw(@NonNull Canvas canvas) {
                                canvas.drawColor(Color.BLUE);
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
                        });
                        break;
                    }
                    case 8: {
                        int colorforedittextback = getResources().getColor(R.color.mycolor15);
                        editor.putInt("colorforLayout", Color.GRAY);
                        editor.putInt("edittextbackColor", colorforedittextback);
                        editor.putInt("notestextcolor", Color.WHITE);
                        editor.putInt("edittextcolor", Color.WHITE);
                        editor.apply();
                        editlayout.setBackgroundColor(Color.GRAY);
                        title.setBackgroundColor(colorforedittextback);
                        notes.setTextColor(Color.WHITE);
                        title.setTextColor(Color.WHITE);
                        getWindow().setStatusBarColor(Color.GRAY);
                        getWindow().setNavigationBarColor(Color.GRAY);
                        getSupportActionBar().setBackgroundDrawable(new Drawable() {
                            @Override
                            public void draw(@NonNull Canvas canvas) {
                                canvas.drawColor(Color.GRAY);
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
                        });
                        break;
                    }
                    case 9: {
                        int colorforedittextback = getResources().getColor(R.color.teal_700);
                        editor.putInt("colorforLayout", getResources().getColor(R.color.teal_200));
                        editor.putInt("edittextbackColor", colorforedittextback);
                        editor.putInt("notestextcolor", Color.BLACK);
                        editor.putInt("edittextcolor", Color.BLACK);
                        editor.apply();
                        editlayout.setBackgroundColor(getResources().getColor(R.color.teal_200));
                        title.setBackgroundColor(colorforedittextback);
                        notes.setTextColor(Color.BLACK);
                        title.setTextColor(Color.BLACK);
                        getWindow().setStatusBarColor(getResources().getColor(R.color.teal_200));
                        getWindow().setNavigationBarColor(getResources().getColor(R.color.teal_200));
                        getSupportActionBar().setBackgroundDrawable(new Drawable() {
                            @Override
                            public void draw(@NonNull Canvas canvas) {
                                canvas.drawColor(getResources().getColor(R.color.teal_200));
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
                        });
                        break;
                    }
                    default: {
                        int colorforedittextback = getResources().getColor(R.color.mycolor18);
                        editor.putInt("colorforLayout", Color.LTGRAY);
                        editor.putInt("edittextbackColor", colorforedittextback);
                        editor.putInt("notestextcolor", Color.WHITE);
                        editor.putInt("edittextcolor", Color.WHITE);
                        editor.apply();
                        editlayout.setBackgroundColor(Color.LTGRAY);
                        title.setBackgroundColor(colorforedittextback);
                        notes.setTextColor(Color.WHITE);
                        title.setTextColor(Color.WHITE);
                        getWindow().setStatusBarColor(Color.LTGRAY);
                        getWindow().setNavigationBarColor(Color.LTGRAY);
                        getSupportActionBar().setBackgroundDrawable(new Drawable() {
                            @Override
                            public void draw(@NonNull Canvas canvas) {
                                canvas.drawColor(Color.LTGRAY);
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
                        });
                    }
                }
break;
            }

                default: {
                    return super.onOptionsItemSelected(item);
                }

            }
        return super.onOptionsItemSelected(item);

    }
     //onOPtionSelected method

    public void setIdd(int idd) {
        this.idd = idd;
    }

    public int getIdd() {
        return idd;
    }
}