package br.com.jhonatan.personalcontrolmobile.service;

import android.os.Build;
import android.util.Base64;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;


import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.com.jhonatan.personalcontrolmobile.dto.Categoria;
import br.com.jhonatan.personalcontrolmobile.dto.Despesa;
import br.com.jhonatan.personalcontrolmobile.dto.MetodoPagamento;

/**
 * Created by jhonatan.rodrigues on 13/01/2016.
 */
public class CadastrosGeraisService {

    public List<Categoria> listarCategorias(String serviceUrl) {
        disableConnectionReuseIfNecessary();

        HttpURLConnection urlConnection = null;
        try {
            // create connection
            URL urlToRequest = new URL(serviceUrl);
            urlConnection = (HttpURLConnection)
                    urlToRequest.openConnection();
            urlConnection.setConnectTimeout(15000);
            urlConnection.setReadTimeout(15000);

            // handle issues
            int statusCode = urlConnection.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
                // handle unauthorized (if service requires user login)
            } else if (statusCode != HttpURLConnection.HTTP_OK) {
                // handle any other errors, like 404, 500,..
            }

            // create JSON object from content
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            return convertResponse(in);

        } catch (MalformedURLException e) {
            e.printStackTrace();
            // URL is invalid
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            // data retrieval or connection timed out
        } catch (IOException e) {
            e.printStackTrace();
            // could not read response body
            // (could not create input stream)
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return null;
    }

    public List<MetodoPagamento> listarMetodosPagamento(String serviceUrl) {
        disableConnectionReuseIfNecessary();

        HttpURLConnection urlConnection = null;
        try {
            // create connection
            URL urlToRequest = new URL(serviceUrl);
            urlConnection = (HttpURLConnection)
                    urlToRequest.openConnection();
            urlConnection.setConnectTimeout(15000);
            urlConnection.setReadTimeout(15000);

            // handle issues
            int statusCode = urlConnection.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
                // handle unauthorized (if service requires user login)
            } else if (statusCode != HttpURLConnection.HTTP_OK) {
                // handle any other errors, like 404, 500,..
            }

            // create JSON object from content
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            return convertResponseMetodoPagamento(in);

        } catch (MalformedURLException e) {
            e.printStackTrace();
            // URL is invalid
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            // data retrieval or connection timed out
        } catch (IOException e) {
            e.printStackTrace();
            // could not read response body
            // (could not create input stream)
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return null;
    }

    /**
     * required in order to prevent issues in earlier Android version.
     */
    private void disableConnectionReuseIfNecessary() {
        // see HttpURLConnection API doc
        if (Integer.parseInt(Build.VERSION.SDK)
                < Build.VERSION_CODES.FROYO) {
            System.setProperty("http.keepAlive", "false");
        }
    }

    private List<Categoria> convertResponse(BufferedReader inStream) {
        try {
            String feed_str = null;
            String entireFeed = "";
            while ((feed_str = inStream.readLine()) != null) {
                entireFeed += feed_str;
            }

            List<Categoria> lista = new ArrayList<Categoria>();
            ObjectMapper mapper = new ObjectMapper();

            JSONArray array = new JSONArray(entireFeed);
            for (int i = 0; i < array.length(); i++) {
                lista.add(mapper.readValue(array.getString(i), Categoria.class));
            }
            return lista;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<MetodoPagamento> convertResponseMetodoPagamento(BufferedReader inStream) {
        try {
            String feed_str = null;
            String entireFeed = "";
            while ((feed_str = inStream.readLine()) != null) {
                entireFeed += feed_str;
            }

            List<MetodoPagamento> lista = new ArrayList<MetodoPagamento>();
            ObjectMapper mapper = new ObjectMapper();

            JSONArray array = new JSONArray(entireFeed);
            for (int i = 0; i < array.length(); i++) {
                lista.add(mapper.readValue(array.getString(i), MetodoPagamento.class));
            }
            return lista;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Despesa salvarDespesa(Despesa despesa, String urlService) {
        //TODO usando url http://stackoverflow.com/questions/13911993/sending-a-json-http-post-request-from-android

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(urlService);

        try {
            httppost.setHeader( "Content-Type", "application/json");
            String authorizationString = "Basic " + Base64.encodeToString(
                    ("admin" + ":" + "rest").getBytes(),
                    Base64.NO_WRAP);
            httppost.setHeader("Authorization", authorizationString);


            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(despesa);

            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("req", jsonInString));

            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);
            System.out.println(response.getStatusLine().getStatusCode());
//            InputStream inputStream = response.getEntity().getContent();
//            InputStreamToStringExample str = new InputStreamToStringExample();
//            responseServer = str.getStringFromInputStream(inputStream);
//            Log.e("response", "response -----" + responseServer);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return despesa;
        //TODO colocar no service certo
    }
}
