package com.example.unsplash.MEMOApp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebHistoryItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.unsplash.R;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.Random;
import java.util.zip.Inflater;

public class ListAdapters extends ArrayAdapter<NoteData>
{

    public ListAdapters(@NonNull Context context, int resource, @NonNull List<NoteData> arrlist) {
        super(context, resource, arrlist);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
View v=convertView;
if(v==null)
{
    v= LayoutInflater.from(getContext()).inflate(R.layout.list_notes,parent,false);
}
MySqLiteOpenHelper mySqLiteOpenHelper=new MySqLiteOpenHelper(getContext());
        MemoMainActivity memoMainActivity=new MemoMainActivity();   //Toget listview from MemoMain activity


NoteData notee=getItem(position);
LinearLayout listItemslay=(LinearLayout)v.findViewById(R.id.listitem111);


        View view=(View)v.findViewById(R.id.viewforlists);


LinearLayout  layout1=(LinearLayout)v.findViewById(R.id.layout1);
LinearLayout layout2=(LinearLayout)v.findViewById(R.id.layout2);


//TextView textView=(TextView)v.findViewById(R.id.idtextview);
//textView.setText(String.valueOf(notee.id)+".");


TextView title=(TextView)v.findViewById(R.id.TITLEtextview);
title.setText(notee.title);


TextView noteText=(TextView)v.findViewById(R.id.NOTEtextview);
if((notee.notes.length())>=80) {
    String subNotess = notee.notes.substring(0, 80);
    noteText.setText(subNotess + " ....");
}else{
    noteText.setText(notee.notes);
}





ImageButton deleteButton=(ImageButton)v.findViewById(R.id.deleteButton);
deleteButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        MySqLiteOpenHelper mySqLiteOpenHelper1 = new MySqLiteOpenHelper(getContext());
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete").setMessage("Do you want to delete this item").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Snackbar.make(v, "Refresh page after Long Click", BaseTransientBottomBar.LENGTH_LONG).show();

                mySqLiteOpenHelper1.deleteData(notee.id);
                
                title.setText("");
                noteText.setText("");
                layout1.setBackgroundColor(getContext().getResources().getColor(R.color.mycolor7));
                layout2.setBackgroundColor(getContext().getResources().getColor(R.color.mycolor7));
                listItemslay.setBackgroundColor(getContext().getResources().getColor(R.color.mycolor7));
                deleteButton.setBackgroundColor(getContext().getResources().getColor(R.color.mycolor7));
            deleteButton.setImageResource(0);
            view.setBackgroundColor(getContext().getResources().getColor(R.color.mycolor7));
            }//getContext().getResources().getColor(R.color.mycolor16)
        }).setNegativeButton("No", null);

        AlertDialog alertDialog = builder.create();
        alertDialog.setIcon(R.drawable.delete);

        alertDialog.show();
        // mySqLiteOpenHelper1.deleteData(notee.id);
        MediaPlayer media = MediaPlayer.create(getContext(), R.raw.notifiactionsimple);
        media.start();
        // listView.removeViewAt(position); //remove view after delete click

    }
});

layout1.setOnClickListener(new View.OnClickListener(){
    @Override
    public void onClick(View v) {
int idd=notee.getId();
       String passtitle=title.getText().toString();
       //String passnotetext=noteText.getText().toString();
        String passnotetext=notee.notes;


        Log.d("IDDDITEM",String.valueOf(idd));

       Intent i=new Intent(getContext(),MemoEditActivity.class);
       Bundle b=new Bundle();
       b.putInt("IDDD",idd);
       b.putString("title",passtitle);
        b.putString("note",passnotetext);
        i.putExtras(b);
        getContext().startActivity(i);
    }
});

        layout2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int idd=notee.getId();
                String passtitle=title.getText().toString();
               // String passnotetext=noteText.getText().toString();
                String passnotetext=notee.notes;

                Log.d("IDDDITEM",String.valueOf(idd));

                Intent i=new Intent(getContext(),MemoEditActivity.class);
                Bundle b=new Bundle();
                b.putInt("IDDD",idd);
                b.putString("title",passtitle);
                b.putString("note",passnotetext);
                i.putExtras(b);
                getContext().startActivity(i);
            }
        });

return v;
    }    //getView method Closed.

   // int color1;  //initialised globall
        //     int color2;

  /**  public void setColorr1(int color)
    {
        color1=color;
    }*/

 /**   public void setColor2(int color2) {
        this.color2 = color2;
    }*/

   /** public int getColorr1()
    {
        return color1;
    }*/

   /** public int getColor2() {
        return color2;
    }*/
}
