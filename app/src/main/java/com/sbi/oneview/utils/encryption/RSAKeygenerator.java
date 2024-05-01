package com.sbi.oneview.utils.encryption;

import android.util.Log;

import com.google.gson.Gson;
import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEDecrypter;
import com.nimbusds.jose.JWEEncrypter;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.RSADecrypter;
import com.nimbusds.jose.crypto.RSAEncrypter;
import com.nimbusds.jose.util.Base64URL;
import com.sbi.oneview.R;
import com.sbi.oneview.base.App;
import com.sbi.oneview.utils.SecretManager;

import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSAKeygenerator {

    public String EncrypttheData(Object payloadData) {



        byte[] serverPublicKeyBytes = Base64.getDecoder().decode(SecretManager.getPublicKey());
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(serverPublicKeyBytes);

        KeyFactory keyFactory = null;
        try {

            keyFactory = KeyFactory.getInstance(App.getAppContext().getString(R.string.encryption_algorithm_rsa));

            RSAPublicKey serverPublicKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);


            SecureRandom secureRandom = SecureRandom.getInstance(App.getAppContext().getString(R.string.sha1prng));
            byte[] iv = new byte[4]; // 32 bits IV for GCM
            secureRandom.nextBytes(iv);

            // Create a JWE header
            JWEHeader header = new JWEHeader.Builder(JWEAlgorithm.RSA_OAEP_256, EncryptionMethod.A256GCM)
                    .contentType(App.getAppContext().getString((R.string.application_json)))
                    .iv(Base64URL.encode(iv))
                    .build();


            String encrypteddata = new Gson().toJson(payloadData);

            JWEObject jweObject = new JWEObject(header, new Payload(encrypteddata));

            // Create a JWE encrypter
            JWEEncrypter encrypter = new RSAEncrypter(serverPublicKey);

            // Encrypt the payload
            jweObject.encrypt(encrypter);

            // Serialize the JWE to compact format
            String jweString = jweObject.serialize();


            return jweString;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String decryptthedata(String Encrypteddata) {


        try {
            byte[] privateKeyBytes = Base64.getDecoder().decode(SecretManager.getPrivateKey());
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);

            KeyFactory keyFactory = KeyFactory.getInstance(App.getAppContext().getString(R.string.encryption_algorithm_rsa));
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);


            // Parse the JWE
            JWEObject jweObject = JWEObject.parse(Encrypteddata);

            // Create a JWE decrypter
            JWEDecrypter decrypter = new RSADecrypter(privateKey);

            // Set the JWE decrypter's initialization vector (IV)
            jweObject.decrypt(decrypter);

            System.out.println("Decrypted Payload: " + jweObject.getPayload().toString());
            // Get and print the decrypted payload


            return jweObject.getPayload().toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}












