package com.example.unsplash.MEMOApp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unsplash.R;
import com.example.unsplash.SelectMainActvity;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import static com.google.android.material.snackbar.Snackbar.make;

public class MemoMainActivity extends AppCompatActivity {
    ListView listview;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_main);
        MySqLiteOpenHelper mySqLiteOpenHelper=new MySqLiteOpenHelper(this);
        List<NoteData> list=mySqLiteOpenHelper.getData();
       // LinearLayout memolistLayout=(LinearLayout)findViewById(R.id.MEMOlistviewLayout);

        ListAdapters arrayAdapter=new ListAdapters(this, R.layout.list_notes,list);
         listview=(ListView)findViewById(R.id.MEMOlistview);
         listview.setAdapter(arrayAdapter);

       TextView textView=(TextView)findViewById(R.id.PressTEXTVIEW);
       int count= listview.getAdapter().getCount();
         Log.d("count",String.valueOf(count));
        if(count==0)
        {
            textView.setText("Press + to Add your Memos");
            textView.setTextColor(Color.BLACK);
        }
        setLisViewReference(listview);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Memo ");
Drawable drawable=new Drawable() {
    @Override
    public void draw(@NonNull Canvas canvas) {
        canvas.drawColor(getResources().getColor(R.color.mycolor7p));
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
        getWindow().setNavigationBarColor(getResources().getColor(R.color.mycolor7));
        getWindow().setStatusBarColor(getResources().getColor(R.color.mycolor7p));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mybutton: {
                Intent i = new Intent(MemoMainActivity.this, MemoEditActivity.class);
                startActivity(i);
                break;
            }
            case R.id.homebutton:
            {
                Intent i=new Intent(MemoMainActivity.this, SelectMainActvity.class);
                startActivity(i);
                break;
            }

            /** for(int p=174;p<175;p++) {
             mySqLiteOpenHelper.deleteData(p);
             }
             */

        default: {
            return super.onOptionsItemSelected(item);
        }

        }
        return super.onOptionsItemSelected(item);
    }
    ListView listView1;    //initialised globally
    public void setLisViewReference(ListView listview)
    {
        listView1=listview;
    }
    public ListView getListview()
    {
        return listView1;
    }
}