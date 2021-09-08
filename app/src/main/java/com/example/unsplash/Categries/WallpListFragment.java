package com.example.unsplash.Categries;

import android.app.AlertDialog;
import android.app.VoiceInteractor;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.unsplash.CategoriesFragment;
import com.example.unsplash.Items;
import com.example.unsplash.ItemsAdapter;
import com.example.unsplash.R;
import com.example.unsplash.WallScreenActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class WallpListFragment extends Fragment {

    String data1;
    String category;

    int likes;
    int views;
    int downloads;
    String username;
    String imageURL;
    String imageDescription;
    String largeImageURL;


    String orientation;
    String colors;
    String order;
    String imagetype;

    ArrayList<ItemsCat> arrlist;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        category=getBackground();


        View v=inflater.inflate(R.layout.fragment_wallp_list, container, false);

        Spinner orienatationspinner=(Spinner)v.findViewById(R.id.orientationspinner);
        Spinner orderspinner=(Spinner)v.findViewById(R.id.Orderspinner);
        Spinner colorspinner=(Spinner)v.findViewById(R.id.Colorsspinner);
        Spinner typespinner=(Spinner)v.findViewById(R.id.Image_Typespinner);


        ListView listView=(ListView)v.findViewById(R.id.listviewforANYCATegory1);

         arrlist=new ArrayList<>();

        RequestQueue requestQueue;
        requestQueue= Volley.newRequestQueue(getContext());

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, "https://pixabay.com/api/?safesearch=true&category="+category+"&colors=all&per_page=200&key=17284571-9dc44bcf97e2f82106c65a55e", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray jsonArray = null;
                try {
                    jsonArray = response.getJSONArray("hits");

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        likes = jsonObject.getInt("likes");
                        views = jsonObject.getInt("views");
                        downloads = jsonObject.getInt("downloads");
                        username = jsonObject.getString("user");
                        imageURL = jsonObject.getString("webformatURL");
                        imageDescription = jsonObject.getString("tags");
                    largeImageURL = jsonObject.getString("largeImageURL");

                        arrlist.add(new ItemsCat(likes, views, downloads, username, imageURL, imageDescription, largeImageURL));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                ItemsCatAdapter itemsAdapter = new ItemsCatAdapter(getActivity().getApplicationContext(), 0, arrlist);
                listView.setAdapter(itemsAdapter);




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);

              //Here filtering Backend is start...

        ArrayList<ItemsCat2> itemsCat2ArrayList=new ArrayList<>();
        Button b=(Button)v.findViewById(R.id.FilterButton);
        b.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {

                        itemsCat2ArrayList.clear();

                       orientation=String.valueOf(orienatationspinner.getSelectedItem());
                       order= String.valueOf(orderspinner.getSelectedItem());
                       imagetype=String.valueOf(typespinner.getSelectedItem());
                        colors=String.valueOf(colorspinner.getSelectedItem());

                      if(orientation.equals("orientation"))
                      {
                          orientation="all";
                      }
                       if(order.equals("order"))
                      {
                          order="popular";
                      }
                       if(imagetype.equals("Type"))
                      {
                          imagetype="all";
                      }
                       if(colors.equals("colors"))
                      {
                          colors="";
                      }
                      Log.d("cate",category);
                      Log.d("orien",orientation);
                      Log.d("order",order);
                      Log.d("colorss",colors);
                      Log.d("type",imagetype);


                        RequestQueue requestQueue;
                        requestQueue= Volley.newRequestQueue(getContext());

                        JsonObjectRequest jsonObjectRequest2=new JsonObjectRequest(Request.Method.GET, "https://pixabay.com/api/?safesearch=true&category="+category+"orientation="+orientation+"&order="+order+"&image_type="+imagetype+"&colors="+colors+"&per_page=200&key=17284571-9dc44bcf97e2f82106c65a55e", null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                JSONArray jsonArray = null;
                                try {
                                    jsonArray = response.getJSONArray("hits");

                                    for (int i = 0; i < jsonArray.length(); i++) {

                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                                        likes = jsonObject.getInt("likes");
                                        views = jsonObject.getInt("views");
                                        downloads = jsonObject.getInt("downloads");
                                        username = jsonObject.getString("user");
                                        imageURL = jsonObject.getString("webformatURL");
                                        imageDescription = jsonObject.getString("tags");
                                        largeImageURL = jsonObject.getString("largeImageURL");

                                        itemsCat2ArrayList.add(new ItemsCat2(likes, views, downloads, username, imageURL, imageDescription, largeImageURL));
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                                ItemsCat2Adapter itemsAdapter = new ItemsCat2Adapter(getActivity().getApplicationContext(), 0, itemsCat2ArrayList);
                                listView.setAdapter(itemsAdapter);


                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });
                        requestQueue.add(jsonObjectRequest2);

                    }
                }
        );

        ConnectivityManager connectivityManager=(ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null&&networkInfo.isConnected())
        {
        }else{
            AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
            builder.setIcon(R.drawable.wifi).setMessage("Please Check Your Internet Connection. Or May be slow Internet Connection.").setTitle("Connection problem!").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog alertDialog=builder.create();
            alertDialog.show();
        }

return v;
    }      //main mainfunction is closed;;;


    public void setBackground(String data)   //To set data from MyFragmentAdapter2 it is viewpager fragmnets adapter.
    {
         data1=data;
    }
    public String getBackground() {
        return data1;
    }
}
