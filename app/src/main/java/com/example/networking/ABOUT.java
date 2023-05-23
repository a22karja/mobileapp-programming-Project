package com.example.networking;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
        finish();
    }

    public void clearShared(View v)
    {
        AlertDialog dialog = new AlertDialog.Builder(ABOUT.this)
                .setTitle("Update data from internet")
                .setMessage("If you continue the current data on plants in you phone will dissapear and data from the internet will replace it, this will also restart the app. Do you want to do this?")
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(ABOUT.this,MainActivity.class);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }
                )
                .setNegativeButton("No", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    }
                ).create();
        dialog.show();
    }


}