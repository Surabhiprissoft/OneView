package com.sbi.oneview;


import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.KeyguardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MpinActivity extends AppCompatActivity {

    private static final int INTENT_AUTHENTICATE = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpin);
        generateMpin();
    }

    private void generateMpin()
    {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            KeyguardManager km = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);

            if (km.isKeyguardSecure()) {
                Intent authIntent = km.createConfirmDeviceCredentialIntent("set dialog","enter your screen lock");
                startActivityForResult(authIntent, INTENT_AUTHENTICATE);
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == INTENT_AUTHENTICATE)
        {
            Log.e("Result Code",""+resultCode);
            if (resultCode == RESULT_OK) {
                //do something you want when pass the security
                Log.e("YES","VALIDATED");
            }
            else if(resultCode==0)
            {
                showDialog();
            }
        }
    }

    private void showDialog()
    {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(MpinActivity.this);
        builder1.setMessage("One View is Locked");
        builder1.setCancelable(false);

        builder1.setPositiveButton(
                "Unlock",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        generateMpin();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}