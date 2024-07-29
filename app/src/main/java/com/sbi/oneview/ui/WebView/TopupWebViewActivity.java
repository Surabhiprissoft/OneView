package com.sbi.oneview.ui.WebView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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

public class TopupWebViewActivity extends AppCompatActivity {

    WebView webView;
    String origin = "https://10.176.56.102:8502";
    String refered = "https://10.176.56.102:8502";
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

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient()); // Open URL in WebView, not in the browser



        // Get the URL and POST data from the intent
        String txnData = getIntent().getStringExtra("txnData");
        String accessKey = getIntent().getStringExtra("accessKey");

        //Log.d("txnData",""+);


        /*String url = "https://10.176.56.102:8502/oneview/epay/topup" + "?transdata=" + Uri.encode(txnData) + "&eval=" + Uri.encode(accessKey);

        // Create the Intent to open the URL in an external browser
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

        // Start the browser with the Intent
        startActivity(browserIntent);*/




       /*webView.setWebViewClient(new WebViewClient() {
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
                "<input type='hidden' name='transdata' value='" + txnData + "' >" +
                "<input type='hidden' name='eval' value='" + accessKey + "'>" +
                "</form>" +
                "</body></html>";

        Log.d("WEB_DATA",htmlContent);

        webView.loadDataWithBaseURL("https://10.176.56.102:8502/oneview/epay/topup", htmlContent, "text/html", "UTF-8", null);
*/


        /*String postData = "EncryptTrans=" + Uri.encode(encryptTrans) + "&merchIdVal=" + Uri.encode(merchIdVal);

        // Construct the URL with POST data
        String url = epayUrl + "?" + postData;

        // Create the Intent to open the URL in an external browser
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

        // Set custom headers for the Intent
        intent.putExtra("Origin", origin);
        intent.putExtra("Referer", refered);

        // Start the browser with the Intent
        startActivity(intent);*/

        /*webView.setWebViewClient(new WebViewClient() {
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                if (request.getUrl().toString().equals(epayUrl)) {
                    String postData = "EncryptTrans=" + encryptTrans + "&merchIdVal=" + merchIdVal;
                    Map<String, String> headers = new HashMap<>();
                    headers.put("Origin", origin);
                    headers.put("Referer", refered);

                    InputStream data = new ByteArrayInputStream(postData.getBytes(StandardCharsets.UTF_8));
                    return new WebResourceResponse("application/x-www-form-urlencoded", "UTF-8", 200, "OK", headers, data);
                }
                return super.shouldInterceptRequest(view, request);
            }
        });

        // POST data
        String postData = "EncryptTrans=" + encryptTrans + "&merchIdVal=" + merchIdVal;
        webView.postUrl(epayUrl, postData.getBytes(StandardCharsets.UTF_8));*/




        /*String postData = "<html>" +
                "<body>" +
                "<form id='myForm' method='POST' action='" + "https://test.sbiepay.sbi/secure/AggregatorHostedListener" + "'>" +
                "<input type='hidden' name='EncryptTrans' value='" + txnData + "'>" +
                "<input type='hidden' name='merchIdVal' value='" + accessKey + "'>" +
                "</form>" +
                "<script type='text/javascript'>" +
                "function setHeadersAndSubmit() {" +
                "  var xhr = new XMLHttpRequest();" +
                "  xhr.open('POST', '" + "https://test.sbiepay.sbi/secure/AggregatorHostedListener" + "', true);" +
                "  xhr.setRequestHeader('Origin', '" + origin + "');" +
                "  xhr.setRequestHeader('Referer', '" + refered + "');" +
                "  xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');" +
                "  xhr.send(new URLSearchParams(new FormData(document.getElementById('myForm'))).toString());" +
                "}" +
                "window.onload = setHeadersAndSubmit;" +
                "</script>" +
                "</body>" +
                "</html>";*/

       // webView.loadDataWithBaseURL("",postData, "text/html", "UTF-8",null);

        Map<String, String> headers = new HashMap<>();
        headers.put("Origin", origin);
        headers.put("Referer", refered);

        String data = "encryptTrans=" + txnData + "&merchIdVal=" + accessKey;

        webView.postUrl("https://test.sbiepay.sbi/secure/AggregatorHostedListener", data.getBytes());
        webView.loadUrl("https://test.sbiepay.sbi/secure/AggregatorHostedListener", headers);

    }




}