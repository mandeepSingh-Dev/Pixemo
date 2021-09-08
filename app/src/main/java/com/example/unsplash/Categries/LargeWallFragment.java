package com.example.unsplash.Categries;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpResponse;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.unsplash.R;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.nio.channels.ScatteringByteChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;


public class LargeWallFragment extends Fragment
{  
    String title;
    String description;
    String url;
    String imageurl;
    String publishedAt;
    String[] modifypubDate;
    String finalPublishedDate;

    String Categories;
    String query;

    String  statuscode1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_large_wall, container, false);

         Categories="breaking-news";
         query="corona";

         Spinner categSp=v.findViewById(R.id.categoriesspinner);
       Button categoriesButton=v.findViewById(R.id.categoriesButton);
       EditText search=v.findViewById(R.id.searcNews);


        ArrayList<ItemsNews> arrayList=new ArrayList<>();
       ListView listView=v.findViewById(R.id.newsListView);

        RequestQueue requestQueue=Volley.newRequestQueue(getContext());
    JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, "https://gnews.io/api/v4/top-headlines?country=in&lang=en&q="+query+"&topic="+Categories+"&token=c0f47e75b3a39c1b1ef175c690f42873", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONArray jsonArray = response.getJSONArray("articles");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        title = jsonObject.getString("title");
                        description = jsonObject.getString("description");
                        url = jsonObject.getString("url");
                        imageurl = jsonObject.getString("image");
                        publishedAt = jsonObject.getString("publishedAt");
                        modifypubDate=publishedAt.split("T");
                        finalPublishedDate=modifypubDate[0];

                        JSONObject jsonObject1 = jsonObject.getJSONObject("source");
                        String websiteName = jsonObject1.getString("name");

                        arrayList.add(new ItemsNews(title, description, url, imageurl, finalPublishedDate, websiteName));
                    }
                }catch(JSONException e){e.printStackTrace();}

                ItemsNewsAdapter itemsNewsAdapter=new ItemsNewsAdapter(getContext(),0,arrayList);
                listView.setAdapter(itemsNewsAdapter);


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                 statuscode1=String.valueOf(error.networkResponse.statusCode);
                Toast.makeText(getContext(), "Gnews Request limit is reached:"+statuscode1, Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);

        JsonObjectRequest jsonObjectRequest1=new JsonObjectRequest(Request.Method.GET, "https://gnews.io/api/v4/top-headlines?country=in&lang=hi&q=corona&topic="+Categories+"&token=c0f47e75b3a39c1b1ef175c690f42873", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONArray jsonArray = response.getJSONArray("articles");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        title = jsonObject.getString("title");
                        description = jsonObject.getString("description");
                        url = jsonObject.getString("url");
                        imageurl = jsonObject.getString("image");
                        publishedAt = jsonObject.getString("publishedAt");
                        modifypubDate=publishedAt.split("T");
                        finalPublishedDate=modifypubDate[0];

                        JSONObject jsonObject1 = jsonObject.getJSONObject("source");
                        String websiteName = jsonObject1.getString("name");

                        arrayList.add(new ItemsNews(title, description, url, imageurl, finalPublishedDate, websiteName));
                    }
                }catch(JSONException e){e.printStackTrace();}

                ItemsNewsAdapter itemsNewsAdapter=new ItemsNewsAdapter(getContext(),0,arrayList);
                listView.setAdapter(itemsNewsAdapter);

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                statuscode1=String.valueOf(error.networkResponse.statusCode);
                Toast.makeText(getContext(), "Gnews Request limit is reached:"+statuscode1, Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(jsonObjectRequest1);




    JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(Request.Method.GET, "https://content.guardianapis.com/search?&api-key=cebc8155-e456-4341-abc6-6bee98ee630c", null, new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            try {
                JSONObject jsonObject = response.getJSONObject("response");
                JSONArray jsonArray = jsonObject.getJSONArray("results");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    String webtitle = jsonObject1.getString("webTitle");
                    String webUrl = jsonObject1.getString("webUrl");
                    String webPublicationDate = jsonObject1.getString("webPublicationDate");


                    arrayList.add(new ItemsNews(webtitle, webUrl, webPublicationDate, "https://i.pinimg.com/originals/ef/6d/5c/ef6d5ca33d8b749c57a170233b61817d.png"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            ItemsNewsAdapter itemsNewsAdapter=new ItemsNewsAdapter(getContext(),0,arrayList);
            listView.setAdapter(itemsNewsAdapter);
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            statuscode1=String.valueOf(error.networkResponse.statusCode);
          Toast.makeText(getContext(),"Guardian news error ",Toast.LENGTH_SHORT).show();
        }
    });
    requestQueue.add(jsonObjectRequest2);

    // Button working start...
        categoriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Categories=categSp.getSelectedItem().toString();
                query=search.getText().toString();
                arrayList.clear();

                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, "https://gnews.io/api/v4/top-headlines?country=in&lang=en&q="+query+"&topic="+Categories+"&token=c0f47e75b3a39c1b1ef175c690f42873", null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            JSONArray jsonArray = response.getJSONArray("articles");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                title = jsonObject.getString("title");
                                description = jsonObject.getString("description");
                                url = jsonObject.getString("url");
                                imageurl = jsonObject.getString("image");
                                publishedAt = jsonObject.getString("publishedAt");
                                modifypubDate=publishedAt.split("T");
                                finalPublishedDate=modifypubDate[0];

                                JSONObject jsonObject1 = jsonObject.getJSONObject("source");
                                String websiteName = jsonObject1.getString("name");

                                arrayList.add(new ItemsNews(title, description, url, imageurl, finalPublishedDate, websiteName));
                            }
                        }catch(JSONException e){e.printStackTrace();}
                        if(arrayList.isEmpty())
                        {
                            Snackbar.make(search,"Invalid Keyword", BaseTransientBottomBar.LENGTH_SHORT).show();
                        }
                        ItemsNewsAdapter itemsNewsAdapter=new ItemsNewsAdapter(getContext(),0,arrayList);
                        listView.setAdapter(itemsNewsAdapter);


                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        statuscode1=String.valueOf(error.networkResponse.statusCode);
                        Toast.makeText(getContext(), "Gnews Request limit is reached:"+statuscode1, Toast.LENGTH_SHORT).show();
                    }
                });
                requestQueue.add(jsonObjectRequest);

                JsonObjectRequest jsonObjectRequest1=new JsonObjectRequest(Request.Method.GET, "https://gnews.io/api/v4/top-headlines?country=in&lang=hi&q="+query+"&topic="+Categories+"token=c0f47e75b3a39c1b1ef175c690f42873", null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            JSONArray jsonArray = response.getJSONArray("articles");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                title = jsonObject.getString("title");
                                description = jsonObject.getString("description");
                                url = jsonObject.getString("url");
                                imageurl = jsonObject.getString("image");
                                publishedAt = jsonObject.getString("publishedAt");
                                modifypubDate=publishedAt.split("T");
                                finalPublishedDate=modifypubDate[0];

                                JSONObject jsonObject1 = jsonObject.getJSONObject("source");
                                String websiteName = jsonObject1.getString("name");

                                arrayList.add(new ItemsNews(title, description, url, imageurl, finalPublishedDate, websiteName));
                            }
                        }catch(JSONException e){e.printStackTrace();}

                        ItemsNewsAdapter itemsNewsAdapter=new ItemsNewsAdapter(getContext(),0,arrayList);
                        listView.setAdapter(itemsNewsAdapter);

                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "lang=hi Gnews Request limit is reached: 403", Toast.LENGTH_SHORT).show();

                    }
                });
                requestQueue.add(jsonObjectRequest1);

            }
        });     //button closed

        return v;
    }


}