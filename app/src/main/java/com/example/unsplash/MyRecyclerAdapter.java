package com.example.unsplash;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static com.example.unsplash.R.drawable.gradlayout;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>
{

    ArrayList<Items> arrayList;
    Context context;

    private static  Palette.Swatch itemImageColorswatch;
    private static Palette.Swatch itemImage2Colorswatch;
    private  static Palette.Swatch itemImage3Colorswatch;
    private static Palette.Swatch itemImage4Colorswatch;
    private  static Palette.Swatch itemImage5Colorswatch;

    public MyRecyclerAdapter(Context context, ArrayList<Items> arrayList)
    {
        this.context=context;
        this.arrayList=arrayList;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items,parent,false);

        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyRecyclerAdapter.MyViewHolder holder, int position) {
        Items items = arrayList.get(position);
        Picasso.get().load(items.getImageURL()).into(holder.imageView);
        holder.textView.setText(String.valueOf(items.getLikes()));

        holder.itemtouch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent i=new Intent(context,WallScreenActivity.class);
              Bundle b=new Bundle();
              b.putString("Hello",items.getLargeImageUrl());
              b.putString("Downloads",String.valueOf(items.getDownloads()));
              b.putString("Description",items.getImageDescription());
              i.putExtras(b);
              context.startActivity(i);
                Snackbar.make(holder.imageView,items.username, BaseTransientBottomBar.LENGTH_SHORT).show();
            }
        });

        Picasso.get().load(items.getImageURL()).placeholder(R.drawable.gradfortablayout).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                    @Override
                    public void onGenerated(@Nullable Palette palette) {

                        itemImageColorswatch=palette.getDarkMutedSwatch();
                        itemImage2Colorswatch=palette.getDarkVibrantSwatch();
                        itemImage3Colorswatch=palette.getDominantSwatch();
                        itemImage4Colorswatch=palette.getVibrantSwatch();
                        itemImage5Colorswatch=palette.getMutedSwatch();


                        if(itemImageColorswatch != null) {
                           holder.linearFirstlayItems.setBackgroundColor(itemImageColorswatch.getRgb());
                            holder. textView.setTextColor(Color.WHITE);

                        }
                        else if(itemImage2Colorswatch!=null)
                        {
                            holder.linearFirstlayItems.setBackgroundColor(itemImage2Colorswatch.getRgb());

                        }
                        else if(itemImage3Colorswatch!=null)
                        {
                            holder.linearFirstlayItems.setBackgroundColor(itemImage3Colorswatch.getRgb());

                        }
                        else if(itemImage4Colorswatch!=null)
                        {
                            holder.linearFirstlayItems.setBackgroundColor(itemImage4Colorswatch.getRgb());

                        }
                        else if(itemImage5Colorswatch!=null)
                        {
                            holder.linearFirstlayItems.setBackgroundColor(itemImage5Colorswatch.getRgb());

                        }
                        else{
                            holder.linearFirstlayItems.setBackground(context.getResources().getDrawable(gradlayout));

                        }


                        Palette.Swatch itemLikeTextColorswatch=palette.getVibrantSwatch();
                        if(itemLikeTextColorswatch != null) {
                            holder.textView.setTextColor(itemLikeTextColorswatch.getRgb());
                        }
                        else{
                            holder.textView.setTextColor(Color.WHITE);
                        }


                    }
                });
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
    }




    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
       LinearLayout linearFirstlayItems;

        ImageView imageView;
            TextView textView;
            View itemtouch;
        public MyViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            itemtouch=itemView;
            imageView=itemView.findViewById(R.id.imagewallpaper);
            textView= itemView.findViewById(R.id.textview);
            linearFirstlayItems=itemView.findViewById(R.id.linearlfirstayitem);

            /**itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(context,WallScreenActivity.class);
                    Bundle b=new Bundle();
                    b.putString("Hello",items.getLargeImageUrl());
                    b.putString("Downloads",String.valueOf(items.getDownloads()));
                    b.putString("Description",items.getImageDescription());
                    i.putExtras(b);
                    context.startActivity(i);

                }
            });*/


        }
    }
}
