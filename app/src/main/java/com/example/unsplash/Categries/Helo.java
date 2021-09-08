package com.example.unsplash.Categries;

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

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
import com.example.unsplash.Items;
import com.example.unsplash.ItemsAdapter;
import com.example.unsplash.R;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Helo extends Fragment {
    //JsonArrayRequest jsonArrayRequest;
    int likes;
    int views;
    int downloads;
    String username;
    String imageURL;
    String imageDescription;
    String largeImageURL;

    ListView listView;
    int length;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v=inflater.inflate(R.layout.fragment_wallpaper, container, false);
       // listView =(ListView)v.findViewById(R.id.listview);

        ConnectivityManager connectivityManager=(ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork=connectivityManager.getActiveNetworkInfo();



        ArrayList<Items> arrlist=new ArrayList<Items>();

        RequestQueue requestQueue;
        requestQueue= Volley.newRequestQueue(getContext());

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,"https://pixabay.com/api/?safesearch=true&per_page=200&key=17284571-9dc44bcf97e2f82106c65a55e",null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                JSONArray jsonArray= null;
                try {
                    jsonArray = response.getJSONArray("hits");
                    length=  jsonArray.length();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                for(int i=0;i<jsonArray.length();i++)
                {
                    try{
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        likes=  jsonObject.getInt("likes");
                        views= jsonObject.getInt("views");
                        downloads= jsonObject.getInt("downloads");
                        username= jsonObject.getString("user");
                        imageURL=  jsonObject.getString("webformatURL");
                        imageDescription=jsonObject.getString("tags");
                        largeImageURL= jsonObject.getString("largeImageURL");
                        arrlist.add(new Items(likes,views,downloads,username,imageURL,imageDescription,largeImageURL));
                    }


                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                ItemsAdapter itemsAdapter=new ItemsAdapter(getActivity().getApplicationContext(),0,arrlist);
                listView.setAdapter(itemsAdapter);



            }}, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("errrrror","something wrong");
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
        EditText editText=(EditText)v.findViewById(R.id.edittextSearch);
        TextView textViewWarning=(TextView)v.findViewById(R.id.warningtext);

        Button searchbutton=(Button)v.findViewById(R.id.searchbutton);
        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrlist.clear();

                String  searchItem=editText.getText().toString();
                RequestQueue requestQueue;
                requestQueue= Volley.newRequestQueue(getContext());

                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,"https://pixabay.com/api/?q="+searchItem+"&per_page=200&key=17284571-9dc44bcf97e2f82106c65a55e",null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        JSONArray jsonArray= null;
                        try {
                            jsonArray = response.getJSONArray("hits");
                            length=  jsonArray.length();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        for(int i=0;i<jsonArray.length();i++)
                        {
                            try{
                                JSONObject jsonObject=jsonArray.getJSONObject(i);
                                likes=  jsonObject.getInt("likes");
                                views= jsonObject.getInt("views");
                                downloads= jsonObject.getInt("downloads");
                                username= jsonObject.getString("user");
                                imageURL=  jsonObject.getString("webformatURL");
                                imageDescription=jsonObject.getString("tags");
                                largeImageURL= jsonObject.getString("largeImageURL");
                                arrlist.add(new Items(likes,views,downloads,username,imageURL,imageDescription,largeImageURL));
                            }

                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        if(arrlist.isEmpty())
                        {
                            textViewWarning.setText("Invalid Input: Enter Understandable Input or keywords. OR Maybe this keyword is not in data");
                            Toast.makeText(getContext(),"Invalid",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            textViewWarning.setText("");
                        }
                        Log.d("lengthh",String.valueOf(length));
                        ItemsAdapter itemsAdapter=new ItemsAdapter(getActivity().getApplicationContext(),0,arrlist);
                        listView.setAdapter(itemsAdapter);



                    }}, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("errrrror","something wrong");
                    }
                });
                requestQueue.add(jsonObjectRequest);
            }
        });

        if (activeNetwork!=null&&activeNetwork.isConnected())
        {
        }
        else
        {
            AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
            builder.setIcon(R.drawable.wifi).setMessage("Please Check Your Internet Connection.").setTitle("Connection problem!").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog alertDialog=builder.create();
            alertDialog.show();
        }


        return v;
    }


}
