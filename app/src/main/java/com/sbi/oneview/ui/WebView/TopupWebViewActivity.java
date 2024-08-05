package com.sbi.oneview.ui.WebView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
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

import com.sbi.oneview.ui.paymentStatus.PaymentStatusActivity;
import com.sbi.oneview.utils.Constants;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.OkHttpClient;

public class TopupWebViewActivity extends AppCompatActivity {

    WebView webView;
    String origin = "https://10.176.56.102:8502";
    String Referer = "https://10.176.56.102:8502/";
    TextView tvCancelTransaction;
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
        tvCancelTransaction = findViewById(R.id.tvCancelTransaction);

        String epayUrl = getIntent().getStringExtra("epayUrl");
        String txnData = getIntent().getStringExtra("encryptTrans");
        String accessKey = getIntent().getStringExtra("merchIdVal");

        tvCancelTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogue();
            }
        });



        webView.getSettings().setJavaScriptEnabled(true);


        StringBuilder str = new StringBuilder().append(epayUrl)
                .append("EncryptTrans")
                .append(txnData)
                .append("merchIdVal")
                .append(accessKey);
        String finalUrl = str.toString();

        String htmlContent = "<html>" +
                "<head><title>Submit Form</title></head>" +
                "<body onload=\"document.getElementById('myform').submit();\">" +
                "<form name='myform' id='myform' method='post' action='https://test.sbiepay.sbi/secure/AggregatorHostedListener' target='_self'>" +
                "<input type='hidden' name='EncryptTrans' value='" + txnData + "' >" +
                "<input type='hidden' name='merchIdVal' value='" + accessKey + "'>" +
                "<b>Please wait, redirecting to payment portal</b>"+
                "</form>" +
                "</body></html>";


        /*String htmlContent = "<html>" +
                "<head><title>Submit Form</title></head>" +
                "<body onload=\"document.getElementById('myform').submit();\">" +
                "<form name='myform' id='myform' method='post' action='https://corpuat.prepaid.sbi:444/oneview/epay/topup' target='_self'>" +
                "<input type='text' name='transdata' value='" + txnData + "' >" +
                "<input type='text' name='eval' value='" + accessKey + "'>" +
                "</form>" +
                "</body></html>";*/


        Map<String, String> headers = new HashMap<>();
        headers.put("Origin", origin);
        headers.put("Referer", Referer);



        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                Log.d("FINISH",""+view.getUrl());
                Log.d("FINISH",""+url);
            }


            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);


                Log.d("STARTED",""+url);

                if (url.equals("https://corpuat.prepaid.sbi:444/oneview/epay/topup-success"))
                {
                    Intent i = new Intent(TopupWebViewActivity.this, PaymentStatusActivity.class);
                    i.putExtra("status","s");
                    startActivity(i);
                }
                if(url.equals("https://corpuat.prepaid.sbi:444/oneview/epay/topup-fail"))
                {
                    Intent i = new Intent(TopupWebViewActivity.this, PaymentStatusActivity.class);
                    i.putExtra("status","f");
                    startActivity(i);
                }
            }
        });

        webView.loadUrl(finalUrl, headers);
        webView.loadDataWithBaseURL(epayUrl,htmlContent,"text/html", "UTF-8",null);










    }


    public void showDialogue()
    {
        Dialog dialog = new Dialog(TopupWebViewActivity.this);

        dialog.setContentView(R.layout.dialogue_cancel_transaction);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.transparent)));


        MaterialButton btnYes,btnNo;
        btnYes = dialog.findViewById(R.id.btnYes);
        btnNo  = dialog.findViewById(R.id.btnNo);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                finish();

            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();

            }
        });

        dialog.show();
    }



}