package com.example.unsplash.Categries;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.palette.graphics.Palette;

import com.example.unsplash.R;
import com.example.unsplash.WallScreenActivity;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

import static com.example.unsplash.R.drawable.gradlayout;


public class ItemsCatAdapter extends ArrayAdapter<ItemsCat>
{
   Palette.Swatch itemImageColorswatch;
    Palette.Swatch itemImage2Colorswatch;
    Palette.Swatch itemImage3Colorswatch;
    Palette.Swatch itemImage4Colorswatch;
    Palette.Swatch itemImage5Colorswatch;



    LargeWallFragment largeWallFragment;
    public ItemsCatAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ItemsCat> arrayList) {
        super(context, resource, arrayList);
    }
    @NonNull
    @Override
       public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        if(v==null)
        {
            v=LayoutInflater.from(getContext()).inflate(R.layout.list_itemscat,parent,false);
        }


        ItemsCat currentItemsCat=getItem(position);
        ImageView imageView=(ImageView)v.findViewById(R.id.imagewallpaper2);
        Picasso.get().load(currentItemsCat.getImageURL()).into(imageView);

        LinearLayout linearLayout=v.findViewById(R.id.linearlayout);

        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                Picasso.get().load(currentItemsCat.getImageURL()).placeholder(R.drawable.gradfortablayout).into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                            @Override
                            public void onGenerated(@Nullable Palette palette) {
                                imageView.setImageBitmap(bitmap);
                                itemImageColorswatch = palette.getDarkMutedSwatch();
                                itemImage2Colorswatch=palette.getDarkVibrantSwatch();
                                itemImage3Colorswatch=palette.getDominantSwatch();
                                itemImage4Colorswatch=palette.getVibrantSwatch();
                                itemImage5Colorswatch=palette.getMutedSwatch();


                                if(itemImageColorswatch != null) {
                                    linearLayout.setBackgroundColor(itemImageColorswatch.getRgb());
                                    // textView.setTextColor(Color.WHITE);

                                }
                                else if(itemImage2Colorswatch!=null)
                                {
                                    linearLayout .setBackgroundColor(itemImage2Colorswatch.getRgb());

                                }
                                else if(itemImage3Colorswatch!=null)
                                {
                                    linearLayout.setBackgroundColor(itemImage3Colorswatch.getRgb());

                                }
                                else if(itemImage4Colorswatch!=null)
                                {
                                    linearLayout.setBackgroundColor(itemImage4Colorswatch.getRgb());

                                }
                                else if(itemImage5Colorswatch!=null)
                                {
                                    linearLayout.setBackgroundColor(itemImage5Colorswatch.getRgb());

                                }
                                else{
                                    linearLayout.setBackground(getContext().getResources().getDrawable(gradlayout));

                                }


                                /**  Palette.Swatch itemLikeTextColorswatch=palette.getVibrantSwatch();
                                 if(itemLikeTextColorswatch != null) {
                                 textView.setTextColor(itemLikeTextColorswatch.getRgb());
                                 }
                                 else{
                                 textView.setTextColor(Color.WHITE);
                                 }   */


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
        };
        Handler handler=new Handler();
        handler.post(runnable);



        imageView.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {

                        Intent i=new Intent(getContext(), WallScreenActivity.class);
                        Bundle b=new Bundle();
                        b.putString("Hello",currentItemsCat.getLargeImageUrl());
                        b.putString("Downloads",String.valueOf(currentItemsCat.getDownloads()));
                        b.putString("Description",currentItemsCat.getImageDescription());
                        i.putExtras(b);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        getContext().startActivity(i);

                    }
                }
        );

        TextView textLikes=(TextView)v.findViewById(R.id.textviewLikes);
        textLikes.setText(String.valueOf(currentItemsCat.getLikes())+" likes");

        TextView textViews=(TextView)v.findViewById(R.id.textviewView);
        textViews.setText(String.valueOf(currentItemsCat.getViews())+" views");
        
        return v;
       }
}
