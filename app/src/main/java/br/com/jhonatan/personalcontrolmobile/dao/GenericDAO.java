package br.com.jhonatan.personalcontrolmobile.dao;

import android.os.Build;
import android.util.Base64;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

/**
 * Created by jhonatan.rodrigues on 22/02/2016.
 */
public abstract class GenericDAO {
    // URL is invalid,  // data retrieval or connection timed out, // could not read response body (could not create input stream)
    public BufferedReader chamadaGET(String serviceUrl, String autenticacaoEncoded) throws IOException {
        disableConnectionReuseIfNecessary();

        HttpURLConnection urlConnection = null;
        URL urlToRequest = new URL(serviceUrl);
        urlConnection = (HttpURLConnection) urlToRequest.openConnection();
        if (autenticacaoEncoded != null) {
            urlConnection.setRequestProperty("Authorization", "Basic " + autenticacaoEncoded);
        }
        urlConnection.setConnectTimeout(15000);
        urlConnection.setReadTimeout(15000);

        int statusCode = urlConnection.getResponseCode();
        if (statusCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
            // handle unauthorized (if service requires user login)
        } else if (statusCode != HttpURLConnection.HTTP_OK) {
            // handle any other errors, like 404, 500,..
        }
        return new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
    }

    private void disableConnectionReuseIfNecessary() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.FROYO) {
            System.setProperty("http.keepAlive", "false");
        }
    }

}
