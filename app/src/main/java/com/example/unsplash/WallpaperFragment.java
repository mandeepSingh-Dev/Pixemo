package com.example.unsplash;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.TokenWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class WallpaperFragment extends Fragment {
    //
    int likes;
    int views;
    int downloads;
    String username;
    String imageURL;
    String imageDescription;
    String largeImageURL;

    ListView listView;
    RecyclerView recyclerView;
    int length;

    String searchItem;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       View v=inflater.inflate(R.layout.fragment_wallpaper, container, false);
        //listView=v.findViewById(R.id.listview);

        ConnectivityManager connectivityManager=(ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork=connectivityManager.getActiveNetworkInfo();

   // listView=v.findViewById(R.id.listview);
        recyclerView=v.findViewById(R.id.recyclerview);

        ArrayList<Items> arrlist=new ArrayList<Items>();


        Runnable runnable=new Runnable() {
            @Override
            public void run() {

                RequestQueue requestQueue;
                requestQueue = Volley.newRequestQueue(getContext());

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://pixabay.com/api/?safesearch=true&per_page=200&key=17284571-9dc44bcf97e2f82106c65a55e", null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        JSONArray jsonArray = null;
                        try {
                            jsonArray = response.getJSONArray("hits");
                            length = jsonArray.length();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        for (int i = 0; i < jsonArray.length(); i++) {
                            try {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                likes = jsonObject.getInt("likes");
                                views = jsonObject.getInt("views");
                                downloads = jsonObject.getInt("downloads");
                                username = jsonObject.getString("user");
                                imageURL = jsonObject.getString("webformatURL");
                                imageDescription = jsonObject.getString("tags");
                                largeImageURL = jsonObject.getString("largeImageURL");
                                arrlist.add(new Items(likes, views, downloads, username, imageURL, imageDescription, largeImageURL));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                       // ItemsAdapter itemsAdapter = new ItemsAdapter(getActivity().getApplicationContext(), 0, arrlist);
                       // listView.setAdapter(itemsAdapter);
                       MyRecyclerAdapter myRecyclerAdapter=new MyRecyclerAdapter(getContext(),arrlist);
                        RecyclerView recyclerView=v.findViewById(R.id.recyclerview);

                        recyclerView.setHasFixedSize(true);


                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setAdapter(myRecyclerAdapter);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("errrrror", "something wrong");
                    }
                });
                requestQueue.add(jsonObjectRequest);

                /**  arrayList.add(new Items("dfdhhf","ljskhdfsjk"));
                 arrayList.add(new Items("dfdhhf","ljskhdfsjk"));
                 arrayList.add(new Items("dfdhhf","ljskhdfsjk"));
                 arrayList.add(new Items("dfdhhf","ljskhdfsjk"));
                 */

                /**  ItemsAdapter itemsAdapter=new ItemsAdapter(getActivity().getApplicationContext(),0,arrlist);
                 listView.setAdapter(itemsAdapter);
                 */
                EditText editText = (EditText) v.findViewById(R.id.edittextSearch);
                TextView textViewWarning = (TextView) v.findViewById(R.id.warningtext);

                Button searchbutton = (Button) v.findViewById(R.id.searchbutton);
                searchbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        arrlist.clear();

                        searchItem = editText.getText().toString();
                        RequestQueue requestQueue;
                        requestQueue = Volley.newRequestQueue(getContext());

                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://pixabay.com/api/?q=" + searchItem + "&per_page=200&key=17284571-9dc44bcf97e2f82106c65a55e", null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {

                                JSONArray jsonArray = null;
                                try {
                                    jsonArray = response.getJSONArray("hits");
                                    length = jsonArray.length();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    try {
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                                        likes = jsonObject.getInt("likes");
                                        views = jsonObject.getInt("views");
                                        downloads = jsonObject.getInt("downloads");
                                        username = jsonObject.getString("user");
                                        imageURL = jsonObject.getString("webformatURL");
                                        imageDescription = jsonObject.getString("tags");
                                        largeImageURL = jsonObject.getString("largeImageURL");
                                        arrlist.add(new Items(likes, views, downloads, username, imageURL, imageDescription, largeImageURL));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                                if (arrlist.isEmpty()) {
                                    textViewWarning.setText("Invalid Input: Enter Understandable Input or keywords. OR Maybe this keyword is not in data");

                                    Toast.makeText(getContext(), "Invalid", Toast.LENGTH_SHORT).show();
                                } else {
                                    textViewWarning.setText("");
                                }
                                //ItemsAdapter itemsAdapter = new ItemsAdapter(getActivity().getApplicationContext(), 0, arrlist);
                              //  listView.setAdapter(itemsAdapter);
                               MyRecyclerAdapter myRecyclerAdapter=new MyRecyclerAdapter(getContext(),arrlist);
                                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                                recyclerView.setAdapter(myRecyclerAdapter);



                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("errrrror", "something wrong");
                            }
                        });
                        requestQueue.add(jsonObjectRequest);
                    }
                });

                if (activeNetwork != null && activeNetwork.isConnected()) {
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setIcon(R.drawable.wifi).setMessage("Please Check Your Internet Connection.").setTitle("Connection problem!").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }

            }
        }; Handler handler=new Handler();
        handler.post(runnable);

        return v;
    }


}
