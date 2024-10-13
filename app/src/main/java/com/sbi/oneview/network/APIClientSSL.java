package com.sbi.oneview.network;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.sbi.oneview.R;
import com.sbi.oneview.base.App;
import com.sbi.oneview.utils.Constants;

import java.io.InputStream;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class APIClientSSL {


    static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create());




    public static <S> S create(Context context, Class<S> serviceClass) throws Exception {
        // Load your custom certificates from resources
        Resources resources = context.getResources();
        // InputStream certificate1InputStream = resources.openRawResource(R.raw.certnew);
        //InputStream certificate1InputStream = resources.openRawResource(R.raw.transitip);
        InputStream certificate2InputStream = resources.openRawResource(R.raw.one);


        // Create an array of InputStreams containing your custom CA certificates
        InputStream[] caInputStreams = {certificate2InputStream};

        // Create the custom TrustManager with the custom CA certificates
        CustomTrustManager2 customTrustManager2 = new CustomTrustManager2(caInputStreams);

        // Convert the certificate files to X509Certificate objects
        //X509Certificate certificate1 = loadCertificate(certificate1InputStream);
        //X509Certificate certificate2 = loadCertificate(certificate2InputStream);

        // Create a list of your custom certificates
        List<X509Certificate> customCertificates = new ArrayList<>();
        //customCertificates.add(certificate1);
        //customCertificates.add(certificate2);

        // Create a CustomTrustManager with your custom certificates
        // Create an SSLSocketFactory with your custom TrustManager
        SSLSocketFactory sslSocketFactory = getSSLSocketFactory(customTrustManager2);

        // Configure OkHttpClient with the custom SSLSocketFactory
        OkHttpClient okHttpClient = httpClient
                .sslSocketFactory(sslSocketFactory, customTrustManager2)
                .connectionSpecs(getConnectionSpec())
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        if (hostname.equals(Constants.BASE_URL_HOSTNAME)) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                })
                .connectTimeout(10, TimeUnit.MINUTES)
                .writeTimeout(10, TimeUnit.MINUTES)
                .readTimeout(10, TimeUnit.MINUTES)
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        Retrofit retrofit = builder.client(okHttpClient).build();
        return retrofit.create(serviceClass);
    }




    private static SSLSocketFactory getSSLSocketFactory(X509TrustManager trustManager) {
        try {
            SSLContext sslContext = SSLContext.getInstance(App.getAppContext().getString(R.string.SSL_context_protocol));
            sslContext.init(null, new TrustManager[]{trustManager}, null);
            return sslContext.getSocketFactory();
        } catch (Exception e) {
            Log.e(Constants.TAG, "" + (R.string.error_creating_sslsocketfactory), e);
            throw new RuntimeException("Failed to create SSLSocketFactory", e);
        }
    }

    private static List<ConnectionSpec> getConnectionSpec() {
        List<ConnectionSpec> specs = new ArrayList<>();
        specs.add(new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                .tlsVersions(TlsVersion.TLS_1_2) // Specify TLS 1.2
                .build());
        specs.add(ConnectionSpec.COMPATIBLE_TLS);
        specs.add(ConnectionSpec.CLEARTEXT);
        return specs;
    }
}
