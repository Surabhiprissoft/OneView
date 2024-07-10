package com.sbi.oneview.utils.encryption;

import android.util.Log;
import android.widget.Toast;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbi.oneview.utils.CommonUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CipherEncryption {


    public static String encryptMessage(String value, String key2) {

//        final String key = "1234567890123456"; // 16-byte key
        final String initVector = "73efbfbd4e0aefbr"; // 16-byte IV

        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key2.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            return java.util.Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception ex) {
            Log.d("Error",ex.getLocalizedMessage().toString());
        }

        return null;
    }


    public static Object decryptMessage(String encrypted, String key2) {
        try {

//              final String key = "1234567890123456"; // 16-byte key
            final String initVector = "73efbfbd4e0aefbr"; // 16-byte IV

            Log.d("STRING",encrypted);

            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key2.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(java.util.Base64.getDecoder().decode(encrypted));

            //String res = CommonUtils.convertToJson(new String(original , StandardCharsets.UTF_8 ));
            String res = CommonUtils.convertToJson(new String(original , StandardCharsets.UTF_8 ));
            ObjectMapper om = new ObjectMapper();


            try {
                final ObjectMapper mapper = new ObjectMapper();
                JsonNode node = om.readTree(res);
                return node;
            } catch (IOException e) {
                return res;
            }

        } catch (Exception ex) {
            Log.d("Hello",ex.getLocalizedMessage());
        }

        return null;
    }

}
