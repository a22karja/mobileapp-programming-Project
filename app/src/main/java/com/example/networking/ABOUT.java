package com.example.networking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ABOUT extends AppCompatActivity {
    private WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        myWebView=findViewById(R.id.webview);
        myWebView.loadUrl("file:///android_asset/BACON.HTML");
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient());
    }

    public void goBack(View v)
    {
        Intent intent = new Intent(ABOUT.this,MainActivity.class);
        startActivity(intent);
    }
}