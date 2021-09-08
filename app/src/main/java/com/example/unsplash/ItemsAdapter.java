package com.example.unsplash;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.palette.graphics.Palette;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static com.example.unsplash.R.drawable.gradientcolorforlayouts;
import static com.example.unsplash.R.drawable.gradlayout;


public class ItemsAdapter  extends ArrayAdapter<Items>
{
    private static TextView textView;
    private  static LinearLayout linearFirstlayItems;

    private static  Palette.Swatch itemImageColorswatch;
    private static Palette.Swatch itemImage2Colorswatch;
    private  static Palette.Swatch itemImage3Colorswatch;
    private static Palette.Swatch itemImage4Colorswatch;
    private  static Palette.Swatch itemImage5Colorswatch;

    Context context1;
    public ItemsAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Items> arrayList) {
        super(context, resource, arrayList);
        context1=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        if(v==null)
        {
            v= LayoutInflater.from(getContext()).inflate(R.layout.list_items,parent,false);
        }
        Items items=getItem(position);
        linearFirstlayItems=v.findViewById(R.id.linearlfirstayitem);

        ItemsAdapter.textView=(TextView)v.findViewById(R.id.textview);
        textView.setText(String.valueOf(items.getLikes())+" likes");

        ImageView imageView=(ImageView) v.findViewById(R.id.imagewallpaper);

       // Picasso.get().load(items.getImageURL()).placeholder(R.drawable.gradfortablayout).into(imageView);

       /** imageView.setOnClickListener(
        new View.OnClickListener(){
       @Override
       public void onClick(View v) {
       Intent i=new Intent(getContext(),WallScreenActivity.class);
       Bundle b=new Bundle();
       b.putString("Hello",items.getLargeImageUrl());
       b.putString("Downloads",String.valueOf(items.getDownloads()));
       b.putString("Description",items.getImageDescription());
       i.putExtras(b);
       i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
       getContext().startActivity(i);
       }
       }
        );*/
        Picasso.get().load(items.getImageURL()).into(imageView);

            Runnable runnable=new Runnable() {
           @Override
           public void run() {
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
                                   linearFirstlayItems.setBackgroundColor(itemImageColorswatch.getRgb());
                                   textView.setTextColor(Color.WHITE);

                               }
                               else if(itemImage2Colorswatch!=null)
                               {
                                   linearFirstlayItems.setBackgroundColor(itemImage2Colorswatch.getRgb());

                               }
                               else if(itemImage3Colorswatch!=null)
                               {
                                   linearFirstlayItems.setBackgroundColor(itemImage3Colorswatch.getRgb());

                               }
                               else if(itemImage4Colorswatch!=null)
                               {
                                   linearFirstlayItems.setBackgroundColor(itemImage4Colorswatch.getRgb());

                               }
                               else if(itemImage5Colorswatch!=null)
                               {
                                   linearFirstlayItems.setBackgroundColor(itemImage5Colorswatch.getRgb());

                               }
                               else{
                                   linearFirstlayItems.setBackground(getContext().getResources().getDrawable(gradlayout));

                               }


                               Palette.Swatch itemLikeTextColorswatch=palette.getVibrantSwatch();
                               if(itemLikeTextColorswatch != null) {
                                   textView.setTextColor(itemLikeTextColorswatch.getRgb());
                               }
                                else{
                                    textView.setTextColor(Color.WHITE);
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
       };            //Palette code added in runnable thread.
        Handler handler=new Handler();
        handler.post(runnable);

        Runnable runnable1=new Runnable() {
            @Override
            public void run() {
                imageView.setOnClickListener(
                        new View.OnClickListener(){
                            @Override
                            public void onClick(View v) {
                                Intent i=new Intent(getContext(),WallScreenActivity.class);
                                Bundle b=new Bundle();
                                b.putString("Hello",items.getLargeImageUrl());
                                b.putString("Downloads",String.valueOf(items.getDownloads()));
                                b.putString("Description",items.getImageDescription());
                                i.putExtras(b);
                                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                getContext().startActivity(i);
                            }
                        }
                );
            }
        };
       Handler handler1=new Handler();
        handler.post(runnable1);





        return v;
    }
}
