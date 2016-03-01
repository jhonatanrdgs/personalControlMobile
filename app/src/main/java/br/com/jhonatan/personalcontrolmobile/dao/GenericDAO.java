package br.com.jhonatan.personalcontrolmobile.dao;

import android.os.Build;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jhonatan.rodrigues on 22/02/2016.
 */
public abstract class GenericDAO<T> {

    private Class<T> classe;

    public GenericDAO(Class<T> classe) {
        this.classe = classe;
    }

    protected List<T> chamadaGET(String serviceUrl, String autenticacaoEncoded) throws IOException {
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
            //TODO handle unauthorized (if service requires user login)
        } else if (statusCode != HttpURLConnection.HTTP_OK) {
            //TODO handle any other errors, like 404, 500,..
        }
        return convertResponse(new BufferedReader(new InputStreamReader(urlConnection.getInputStream())));
    }

    protected void chamadaPOST(String serviceUrl, String autenticacaoEncoded, String json) throws IOException {
        URL u = new URL(serviceUrl);
        HttpURLConnection conn = (HttpURLConnection) u.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Basic " + autenticacaoEncoded);
        conn.setRequestProperty("Content-type", "application/json");

        OutputStream os = conn.getOutputStream();
        os.write(json.getBytes("UTF-8"));
        os.close();

        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
            //TODO
        } else if (responseCode != HttpURLConnection.HTTP_OK) {
            //TODO handle any other errors, like 404, 500,..
        }
        System.out.println(responseCode);

    }

    private void disableConnectionReuseIfNecessary() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.FROYO) {
            System.setProperty("http.keepAlive", "false");
        }
    }

    private List<T> convertResponse(BufferedReader inStream) {
        try {
            String feed_str = null;
            String entireFeed = "";
            while ((feed_str = inStream.readLine()) != null) {
                entireFeed += feed_str;
            }

            List<T> lista = new ArrayList<T>();
            ObjectMapper mapper = new ObjectMapper();

            JSONArray array = new JSONArray(entireFeed);
            for (int i = 0; i < array.length(); i++) {
                lista.add(mapper.readValue(array.getString(i), classe));
            }

            return lista;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
