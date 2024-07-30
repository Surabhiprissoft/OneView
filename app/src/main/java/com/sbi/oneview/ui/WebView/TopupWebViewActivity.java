package com.sbi.oneview.ui.WebView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sbi.oneview.R;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import com.android.volley.toolbox.StringRequest;
import com.sbi.oneview.utils.Constants;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.OkHttpClient;

public class TopupWebViewActivity extends AppCompatActivity {

    WebView webView;
    String origin = "https://10.176.56.102:8502";
    String Referer = "https://10.176.56.102:8502";
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_topup_web_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        webView = findViewById(R.id.wvTopup);
/*
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient()); // Open URL in WebView, not in the browser*/


        // Get the URL and POST data from the intent
        String epayUrl = getIntent().getStringExtra("epayUrl");
        String txnData = getIntent().getStringExtra("txnData");
        String accessKey = getIntent().getStringExtra("accessKey");

        Log.d("URL",""+epayUrl);

        //Log.d("txnData",""+);


        /*String url = "https://10.176.56.102:8502/oneview/epay/topup" + "?transdata=" + Uri.encode(txnData) + "&eval=" + Uri.encode(accessKey);

        // Create the Intent to open the URL in an external browser
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

        // Start the browser with the Intent
        startActivity(browserIntent);*/




       webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });

        // HTML form content
        String htmlContent = "<html>" +
                "<head><title>Submit Form</title></head>" +
                "<body onload=\"document.getElementById('myform').submit();\">" +
                "<form name='myform' id='myform' method='post' action='https://10.176.56.102:8502/oneview/epay/topup' target='_self'>" +
                "<input type='text' name='transdata' value='" + txnData + "' >" +
                "<input type='text' name='eval' value='" + accessKey + "'>" +
                "<button type='submit'> submit </button>" +
                "</form>" +
                "</body></html>";

        Log.d("WEB_DATA",htmlContent);

        webView.loadDataWithBaseURL("https://10.176.56.102:8502/oneview/epay/topup", htmlContent, "text/html", "UTF-8", null);




    }


    private String constructUrl(String baseUrl, String param1Name, String param1Value, String param2Name, String param2Value) {
        final String EQUALS = "=";
        final String APPENDER = "&";

        return new StringBuilder()
                .append(baseUrl)
                .append("?") // Assuming the baseUrl does not already contain a "?" to start the query params
                .append(param1Name).append(EQUALS).append(param1Value).append(APPENDER)
                .append(param2Name).append(EQUALS).append(param2Value)
                .toString();
    }




}