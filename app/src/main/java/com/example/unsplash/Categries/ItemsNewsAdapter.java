package com.example.unsplash.Categries;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.unsplash.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntToDoubleFunction;

public class ItemsNewsAdapter extends ArrayAdapter<ItemsNews>
{
    public ItemsNewsAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ItemsNews> arrayList) {
        super(context, resource, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
     View v=convertView;
     if(v==null) {
          v = LayoutInflater.from(getContext()).inflate(R.layout.list_newsitem, parent, false);
     }
     ItemsNews itemsNews=getItem(position);
     TextView titletext=v.findViewById(R.id.newstitle);
     titletext.setText(itemsNews.getTitleee());

     TextView descriptiontext=v.findViewById(R.id.newsDescription);
     descriptiontext.setText(itemsNews.getDescription());

        TextView publishdatetext=v.findViewById(R.id.publisheddate);
        publishdatetext.setText(itemsNews.getPublishedAt());

        TextView sourcenametext=v.findViewById(R.id.NEWSname);
        if(itemsNews.getName()!=null) {
            sourcenametext.setText(itemsNews.getName());
       }else{
           sourcenametext.setText("The Guardian");
        }
           ImageView imageView=v.findViewById(R.id.newsImage);
        Picasso.get().load(itemsNews.getImageurl()).into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(),WebViewNews.class);
                Bundle b=new Bundle();
                b.putString("webURL",itemsNews.getUrl());
                i.putExtras(b);
                getContext().startActivity(i);
            }
        });







        return v;
    }
}
