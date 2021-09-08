package com.example.unsplash.Categries;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.unsplash.R;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class WebViewNews extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_news);
         webView=(WebView)findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());


        Bundle b=getIntent().getExtras();
        String webUrl=b.getString("webURL");
        webView.loadUrl(webUrl);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        webView.clearCache(true);
        webView.clearHistory();
        webView.clearFormData();

        onBackPressed();

        return super.onSupportNavigateUp();
    }
}