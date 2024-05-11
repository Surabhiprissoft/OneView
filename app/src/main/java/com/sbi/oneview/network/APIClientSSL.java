package com.sbi.oneview.network;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import com.sbi.oneview.R;
import com.sbi.oneview.base.App;
import com.sbi.oneview.utils.Constants;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
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


    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create());

    public static <S> S create(Context context, Class<S> serviceClass) throws Exception {
        // Load your custom certificates from resources
        Resources resources = context.getResources();
        InputStream certificate1InputStream = resources.openRawResource(R.raw.certnew); // UAT
        InputStream certificate2InputStream = resources.openRawResource(R.raw.root);



        InputStream[] caInputStreams = {certificate1InputStream, certificate2InputStream};
        CustomTrustManager2 customTrustManager2 = new CustomTrustManager2(caInputStreams);

        List<X509Certificate> customCertificates = new ArrayList<>();

        SSLSocketFactory sslSocketFactory = getSSLSocketFactory(customTrustManager2);


        OkHttpClient okHttpClient = httpClient
                .sslSocketFactory(sslSocketFactory, customTrustManager2)
                .retryOnConnectionFailure(true)
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

    private static X509Certificate loadCertificate(InputStream certificateInputStream) {
        try {
            CertificateFactory cf = CertificateFactory.getInstance(App.getAppContext().getString(R.string.certificate_factory_type));
            Certificate certificate = cf.generateCertificate(certificateInputStream);
            if (certificate instanceof X509Certificate) {
                return (X509Certificate) certificate;
            } else {
                throw new IllegalArgumentException(App.getAppContext().getString(R.string.the_certificate_is_not_an_x_509_certificate));
            }
        } catch (Exception e) {
            Log.e("" + Constants.TAG, "" + App.getAppContext().getString(R.string.error_loading_certificate), e);
            throw new RuntimeException("Failed to load certificate", e);
        } finally {
            try {
                certificateInputStream.close();
            } catch (IOException e) {
                // Handle the exception
            }
        }
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





