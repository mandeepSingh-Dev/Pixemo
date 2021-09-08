package com.example.unsplash;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.unsplash.Categries.WallCategoryActivity;
import com.example.unsplash.Categries.WallpListFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoriesFragment extends Fragment {
    ImageButton b;
    ImageButton b1;
    ImageButton b2;
    ImageButton b3;
    ImageButton b4;
    ImageButton b5;
    ImageButton b6;
    ImageButton b7;
    ImageButton b8;
    ImageButton b9;
    ImageButton b10;
    ImageButton b11;
    ImageButton b12;
    ImageButton b13;
    ImageButton b14;
    ImageButton b15;
    ImageButton b16;
    ImageButton b17;

    ImageButton b18;
    ImageButton b19;

    String burl;
    String b1url;
    String b2url;
    String b3url;
    String b4url;
    String b5url;
    String b6url;
    String b7url;
    String b8url;
    String b9url;
    String b10url;
    String b11url;
    String b12url;
    String b13url;
    String b14url;
    String b15url;
    String b16url;
    String b17url;
    String b18url;
    String b19url;

String backgrounds="backgrounds";
    String fashion="fashion";
    String nature="nature";
    String science="science";
    String education="education";
    String feelings="feelings";
    String health="health";
    String people="people";
    String religion="religion";
    String places="places";
    String animals="animals";
    String industry="industry";
    String computer="computer";
    String food="food";
    String sports="sports";
    String transportation="transportation";
    String travel="travel";
    String buildings="buildings";
    String business="business";
    String music="music";

    String orientation;
    String colors;
    String order;
    String imagetype;






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_categories, container, false);
        b= v.findViewById(R.id.button);
        b1=v.findViewById(R.id.button2);
        b2=v.findViewById(R.id.button3);
        b3=v.findViewById(R.id.button4);
        b4=v.findViewById(R.id.button5);
        b5=v.findViewById(R.id.button6);
        b6=v.findViewById(R.id.button7);
        b7=v.findViewById(R.id.button8);
        b8=v.findViewById(R.id.button9);
        b9=v.findViewById(R.id.button10);

        b10 =v.findViewById(R.id.button11);
        b11= v.findViewById(R.id.button12);
        b12 =v.findViewById(R.id.button13);
        b13= v.findViewById(R.id.button14);
        b14= v.findViewById(R.id.button15);
        b15= v.findViewById(R.id.button16);
        b16= v.findViewById(R.id.button17);
        b17 =v.findViewById(R.id.button18);

        b18= v.findViewById(R.id.button19);
        b19= v.findViewById(R.id.button20);






        Picasso.get().load("https://cdn.pixabay.com/photo/2016/08/17/01/39/mystery-1599527_960_720.jpg").into(b);
        Picasso.get().load("https://cdn.pixabay.com/photo/2018/01/15/07/52/portrait-3083402_960_720.jpg").into(b1);
        Picasso.get().load("https://cdn.pixabay.com/photo/2018/04/27/08/56/water-3354063_960_720.jpg").into(b2);
        Picasso.get().load("https://cdn.pixabay.com/photo/2017/09/27/10/31/robot-2791677_960_720.jpg").into(b3);
        Picasso.get().load("https://cdn.pixabay.com/photo/2017/08/01/09/52/books-2564101_960_720.jpg").into(b4);
        Picasso.get().load("https://cdn.pixabay.com/photo/2017/09/07/08/28/girl-2724172_960_720.jpg").into(b5);
        Picasso.get().load("https://cdn.pixabay.com/photo/2016/11/29/10/02/apples-1868892_960_720.jpg").into(b6);
        Picasso.get().load("https://cdn.pixabay.com/photo/2016/02/19/11/55/autumn-1210058_960_720.jpg").into(b7);
        Picasso.get().load("https://cdn.pixabay.com/photo/2020/10/27/09/52/candles-5690190_960_720.jpg").into(b8);
        Picasso.get().load("https://cdn.pixabay.com/photo/2017/08/17/16/24/castle-2651868_960_720.jpg").into(b9);
        Picasso.get().load("https://cdn.pixabay.com/photo/2018/07/31/21/58/lion-3576031_960_720.jpg").into(b10);
        Picasso.get().load("https://cdn.pixabay.com/photo/2015/12/07/10/51/electrician-1080570_960_720.jpg").into(b11);
        Picasso.get().load("https://cdn.pixabay.com/photo/2017/08/01/22/15/imac-2568270_960_720.jpg").into(b12);
        Picasso.get().load("https://cdn.pixabay.com/photo/2015/11/19/10/38/food-1050813_960_720.jpg").into(b13);
        Picasso.get().load("https://cdn.pixabay.com/photo/2017/08/02/14/32/golf-2571830_960_720.jpg").into(b14);
        Picasso.get().load("https://cdn.pixabay.com/photo/2017/12/24/09/09/road-3036620_960_720.jpg").into(b15);
        Picasso.get().load("https://cdn.pixabay.com/photo/2017/09/26/20/13/eiffel-2789943_960_720.jpg").into(b16);
        Picasso.get().load("https://cdn.pixabay.com/photo/2014/01/16/08/06/skyscrapers-246224_960_720.jpg").into(b17);
        Picasso.get().load("https://cdn.pixabay.com/photo/2017/03/01/10/03/success-2108032_960_720.jpg").into(b18);
        Picasso.get().load("https://cdn.pixabay.com/photo/2017/10/27/20/26/blue-2895451_960_720.jpg").into(b19);


      /*  ArrayList<Category> arrayList=new ArrayList<>();
        arrayList.add(new Category("https://cdn.pixabay.com/photo/2016/08/17/01/39/mystery-1599527_960_720.jpg",backgrounds));
        arrayList.add(new Category( "https://cdn.pixabay.com/photo/2018/01/15/07/52/portrait-3083402_960_720.jpg",fashion));
        arrayList.add(new Category( "https://cdn.pixabay.com/photo/2018/04/27/08/56/water-3354063_960_720.jpg",nature));
        arrayList.add(new Category( "https://cdn.pixabay.com/photo/2017/09/27/10/31/robot-2791677_960_720.jpg",science));
        arrayList.add(new Category( "https://cdn.pixabay.com/photo/2017/08/01/09/52/books-2564101_960_720.jpg",education));
        arrayList.add(new Category(  "https://cdn.pixabay.com/photo/2017/09/07/08/28/girl-2724172_960_720.jpg",feelings));
        arrayList.add(new Category("https://cdn.pixabay.com/photo/2016/11/29/10/02/apples-1868892_960_720.jpg",health));
        arrayList.add(new Category(  "https://cdn.pixabay.com/photo/2016/02/19/11/55/autumn-1210058_960_720.jpg",people));
        arrayList.add(new Category(  "https://cdn.pixabay.com/photo/2020/10/27/09/52/candles-5690190_960_720.jpg",religion));
        arrayList.add(new Category(  "https://cdn.pixabay.com/photo/2017/08/17/16/24/castle-2651868_960_720.jpg",places));
        arrayList.add(new Category(  "https://cdn.pixabay.com/photo/2018/07/31/21/58/lion-3576031_960_720.jpg",animals));
        arrayList.add(new Category(    "https://cdn.pixabay.com/photo/2015/12/07/10/51/electrician-1080570_960_720.jpg",industry));
        arrayList.add(new Category(    "https://cdn.pixabay.com/photo/2017/08/01/22/15/imac-2568270_960_720.jpg",computer));
        arrayList.add(new Category(    "https://cdn.pixabay.com/photo/2015/11/19/10/38/food-1050813_960_720.jpg",food));
        arrayList.add(new Category(    "https://cdn.pixabay.com/photo/2017/08/02/14/32/golf-2571830_960_720.jpg",sports));
        arrayList.add(new Category(   "https://cdn.pixabay.com/photo/2017/12/24/09/09/road-3036620_960_720.jpg",transportation));
        arrayList.add(new Category(   "https://cdn.pixabay.com/photo/2017/09/26/20/13/eiffel-2789943_960_720.jpg",travel));
        arrayList.add(new Category(  "https://cdn.pixabay.com/photo/2014/01/16/08/06/skyscrapers-246224_960_720.jpg",buildings));
        arrayList.add(new Category(  "https://cdn.pixabay.com/photo/2017/03/01/10/03/success-2108032_960_720.jpg",business));
        arrayList.add(new Category(  "https://cdn.pixabay.com/photo/2017/10/27/20/26/blue-2895451_960_720.jpg",music));

MyRecyclerAdapter2 myRecyclerAdapter2=new MyRecyclerAdapter2(arrayList);
        RecyclerView recyclerView=v.findViewById(R.id.recyclerview2);
      //  recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(myRecyclerAdapter2);

*/
       Spinner orientationspin=(Spinner) v.findViewById(R.id.orientationspinner);
        Spinner orderspin=(Spinner)v.findViewById(R.id.Orderspinner);
        Spinner colorspin=(Spinner)v.findViewById(R.id.Colorsspinner);
        Spinner imageTypespin=(Spinner)v.findViewById(R.id.Image_Typespinner);



        b.setOnClickListener(
              new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {

                      Intent i = new Intent(getContext(), WallCategoryActivity.class);
                      Bundle b = new Bundle();
                      b.putString("Category", backgrounds);
                      i.putExtras(b);
                      startActivity(i);

                  }
              }
              );

        b1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getContext(), WallCategoryActivity.class);
                        Bundle b = new Bundle();
                        b.putString("Category", fashion);
                        i.putExtras(b);
                        startActivity(i);
                    }
                }
        );
        b2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getContext(), WallCategoryActivity.class);
                        Bundle b = new Bundle();
                        b.putString("Category", nature);
                        i.putExtras(b);
                        startActivity(i);
                    }
                }
        );
        b3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getContext(), WallCategoryActivity.class);
                        Bundle b = new Bundle();
                        b.putString("Category", science);
                        i.putExtras(b);
                        startActivity(i);
                    }
                }
        );
        b4.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getContext(), WallCategoryActivity.class);
                        Bundle b = new Bundle();
                        b.putString("Category", education);
                        i.putExtras(b);
                        startActivity(i);
                    }
                }
        );
        b5.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getContext(), WallCategoryActivity.class);
                        Bundle b = new Bundle();
                        b.putString("Category", feelings);
                        i.putExtras(b);
                        startActivity(i);
                    }
                }
        );
        b6.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getContext(), WallCategoryActivity.class);
                        Bundle b = new Bundle();
                        b.putString("Category", health);
                        i.putExtras(b);
                        startActivity(i);
                    }
                }
        );
        b7.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getContext(), WallCategoryActivity.class);
                        Bundle b = new Bundle();
                        b.putString("Category", people);
                        i.putExtras(b);
                        startActivity(i);
                    }
                }
        );
        b8.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getContext(), WallCategoryActivity.class);
                        Bundle b = new Bundle();
                        b.putString("Category", religion);
                        i.putExtras(b);
                        startActivity(i);
                    }
                }
        );
        b9.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getContext(), WallCategoryActivity.class);
                        Bundle b = new Bundle();
                        b.putString("Category", places);
                        i.putExtras(b);
                        startActivity(i);
                    }
                }
        );
        b10.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getContext(), WallCategoryActivity.class);
                        Bundle b = new Bundle();
                        b.putString("Category", animals);
                        i.putExtras(b);
                        startActivity(i);
                    }
                }
        );
        b11.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getContext(), WallCategoryActivity.class);
                        Bundle b = new Bundle();
                        b.putString("Category", industry);
                        i.putExtras(b);
                        startActivity(i);
                    }
                }
        );
        b12.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getContext(), WallCategoryActivity.class);
                        Bundle b = new Bundle();
                        b.putString("Category", computer);
                        i.putExtras(b);
                        startActivity(i);
                    }
                }
        );
        b13.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getContext(), WallCategoryActivity.class);
                        Bundle b = new Bundle();
                        b.putString("Category", food);
                        i.putExtras(b);
                        startActivity(i);
                    }
                }
        );
        b14.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getContext(), WallCategoryActivity.class);
                        Bundle b = new Bundle();
                        b.putString("Category", sports);

                        i.putExtras(b);
                        startActivity(i);
                    }
                }
        );
        b15.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getContext(), WallCategoryActivity.class);
                        Bundle b = new Bundle();
                        b.putString("Category", transportation);

                        i.putExtras(b);
                        startActivity(i);
                    }
                }
        );
        b16.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getContext(), WallCategoryActivity.class);
                        Bundle b = new Bundle();
                        b.putString("Category", travel);
                        i.putExtras(b);
                        startActivity(i);
                    }
                }
        );
        b17.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getContext(), WallCategoryActivity.class);
                        Bundle b = new Bundle();
                        b.putString("Category", buildings);
                        i.putExtras(b);
                        startActivity(i);
                    }
                }
        );
        b18.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getContext(), WallCategoryActivity.class);
                        Bundle b = new Bundle();
                        b.putString("Category", business);
                        i.putExtras(b);
                        startActivity(i);
                    }
                }
        );
        b19.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getContext(), WallCategoryActivity.class);
                        Bundle b = new Bundle();
                        b.putString("Category", music);
                        i.putExtras(b);
                        startActivity(i);
                    }
                }
        );

        return v;
    }


}