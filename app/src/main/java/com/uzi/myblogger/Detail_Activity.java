package com.uzi.myblogger;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Detail_Activity extends AppCompatActivity {
    private static final String TAG = "Detail_Activity";
    WebView webView;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_);

        progressBar =(ProgressBar) findViewById(R.id.progress);
        webView = (WebView) findViewById(R.id.detail_View);

         webView.setVisibility(View.INVISIBLE);
         webView.getSettings().setJavaScriptEnabled(true);
         webView.setWebChromeClient(new WebChromeClient());
         webView.setWebViewClient(new WebViewClient() {

             @Override
             public void onPageStarted(WebView view, String url, Bitmap favicon) {
                 super.onPageStarted(view, url, favicon);
                 Toast.makeText(Detail_Activity.this, " Start Page Loading", Toast.LENGTH_SHORT).show();
             }

             @Override
             public void onPageFinished(WebView view, String url) {
                 super.onPageFinished(view, url);
                 progressBar.setVisibility(View.GONE);
                 webView.setVisibility(View.VISIBLE);
                 Toast.makeText(Detail_Activity.this, "Page Loaded", Toast.LENGTH_SHORT).show();
             }
         });
         webView.loadUrl(getIntent().getStringExtra("url"));



    }
}