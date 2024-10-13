package com.sbi.oneview.network;



import com.sbi.oneview.R;
import com.sbi.oneview.base.App;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class CustomTrustManager2 implements X509TrustManager {
    private X509TrustManager defaultTrustManager;
    private List<X509Certificate> acceptedIssuers = new ArrayList<>();

    public CustomTrustManager2(InputStream[] caInputStreams) throws Exception {
        // Load the CA certificates
        for (InputStream caInputStream : caInputStreams) {
            CertificateFactory certificateFactory = CertificateFactory.getInstance(App.getAppContext().getString(R.string.certificate_factory_type));
            Certificate caCertificate = certificateFactory.generateCertificate(caInputStream);
            acceptedIssuers.add((X509Certificate) caCertificate);
        }

        // Create a KeyStore and add the CA certificates
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(null, null);

        int i = 0;
        for (X509Certificate caCertificate : acceptedIssuers) {
            String alias = App.getAppContext().getString(R.string.ca) + i;
            keyStore.setCertificateEntry(alias, caCertificate);
            i++;
        }

        // Create a TrustManager that trusts the CA certificates
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keyStore);

        // Get the default TrustManager
        defaultTrustManager = findDefaultTrustManager(trustManagerFactory.getTrustManagers());
    }

    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        defaultTrustManager.checkClientTrusted(chain, authType);
    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) {
        // Check the server certificate chain against the custom truststore
        try {
            defaultTrustManager.checkServerTrusted(chain, authType);
        } catch (Exception e) {
            // Handle the custom trust logic here
            // You can choose to trust or reject the server certificate here
            if (!isCertificateTrusted(chain[0])) {
                try {
                    throw new Exception(App.getAppContext().getString(R.string.server_certificate_is_not_trusted));
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return acceptedIssuers.toArray(new X509Certificate[0]);
    }

    private X509TrustManager findDefaultTrustManager(TrustManager[] trustManagers) {
        for (TrustManager tm : trustManagers) {
            if (tm instanceof X509TrustManager) {
                return (X509TrustManager) tm;
            }
        }
        return null;
    }

    private boolean isCertificateTrusted(X509Certificate serverCertificate) {
        // Check if the server certificate is one of the accepted issuers
        for (X509Certificate acceptedIssuer : acceptedIssuers) {
            try {
                if (Arrays.equals(acceptedIssuer.getEncoded(), serverCertificate.getEncoded())) {
                    return true;
                }
            } catch (CertificateEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }
}
