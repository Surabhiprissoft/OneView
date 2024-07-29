package com.sbi.oneview.ui.WebView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.sbi.oneview.R;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

public class ResetPinWebViewActivity extends AppCompatActivity {

    private static final String RESET_URL = "reset_url";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reset_pin_web_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        final ProgressBar progressBar = findViewById(R.id.progressBar);
        WebView webView = findViewById(R.id.webView);

        webView.setWebViewClient(new WebViewClient()); // Open URL in WebView, not in the browser
        webView.getSettings().setJavaScriptEnabled(true); // Enable JavaScript if needed

        String resetUrl = getIntent().getStringExtra("resetUrl");


        webView.loadUrl(resetUrl);

        /*// Get the URL and POST data from the intent
        String epayUrl = getIntent().getStringExtra("epayUrl");
        String encryptTrans = getIntent().getStringExtra("encryptTrans");
        String merchIdVal = getIntent().getStringExtra("merchIdVal");*/



        /*String postData = "<html>" +
                "<body onload='document.forms[0].submit()'>" +
                "<form method='POST' action='" + epayUrl + "'>" +
                "<input type='hidden' name='EncryptTrans' value='" + encryptTrans + "'>" +
                "<input type='hidden' name='merchIdVal' value='" + merchIdVal + "'>" +
                "<button type='submit' id='epaySubmit'>Submit</button>" +
                "</form>" +
                "</body>" +
                "</html>";

        webView.loadData(postData, "text/html", "UTF-8");*/




        // Get the URL and POST data from the intent
        /*String url = getIntent().getStringExtra("url");
        String postData = getIntent().getStringExtra("postData");

        Log.d("URL:",""+url);


        if (url != null && postData != null) {
            // Post the data to the URL
            webView.postUrl(url, postData.getBytes());
        }*/


      /*  // Enable JavaScript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Set WebViewClient to handle loading within the app
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
            }
        });

        // Set WebChromeClient to update progress
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
        });


        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(RESET_URL)) {

            String webUrl = intent.getStringExtra(RESET_URL);
            webView.loadUrl(webUrl);

        }*/



    }


    private String prepareSecureUrl(String Baseurl,String encryptTrans, String merchIdVal) throws UnsupportedEncodingException {
        // This example demonstrates a basic HTTPS request using URLConnection
        // Replace with your preferred secure communication method (e.g., Volley)

       // String baseUrl = "https://your-backend-url.com/payment"; // Replace with actual URL
        String url = Baseurl + "?EncryptTrans=" + URLEncoder.encode(encryptTrans, "UTF-8") +
                "&merchIdVal=" + URLEncoder.encode(merchIdVal, "UTF-8");

        try {
            URL urlObject = new URL(url);
            HttpsURLConnection connection = (HttpsURLConnection) urlObject.openConnection();
            connection.setRequestMethod("POST");
            // Add any additional headers or request body data if needed
            connection.connect();

            // Handle response (optional)
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Process successful response
                return "success_url"; // Replace with appropriate URL or logic
            } else {
                // Handle error
                Log.e("Error", "Error sending data: " + responseCode);
                return "error_url"; // Replace with appropriate URL or logic
            }
        } catch (Exception e) {
            Log.e("Error", "Error during communication: " + e.getMessage());
            return "error_url"; // Replace with appropriate URL or logic
        }
    }




}