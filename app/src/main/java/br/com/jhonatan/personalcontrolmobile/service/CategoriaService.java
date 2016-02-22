package br.com.jhonatan.personalcontrolmobile.service;

import android.os.Build;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.com.jhonatan.personalcontrolmobile.assincrono.Resultado;
import br.com.jhonatan.personalcontrolmobile.assincrono.ResultadoSpinner;
import br.com.jhonatan.personalcontrolmobile.dto.Categoria;

/**
 * Created by Jhonatan on 21/02/2016.
 */
public class CategoriaService implements Service<Categoria> {

    private String SERVICE_URL = "http://personalcontrol-rdgs.rhcloud.com/cadastrosGeraisApi/getCategorias";

    @Override
    public Resultado<Categoria> listar() {
        disableConnectionReuseIfNecessary();

        HttpURLConnection urlConnection = null;
        try {
            // create connection
            URL urlToRequest = new URL(SERVICE_URL);
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

    private void disableConnectionReuseIfNecessary() {
        // see HttpURLConnection API doc
        if (Integer.parseInt(Build.VERSION.SDK)
                < Build.VERSION_CODES.FROYO) {
            System.setProperty("http.keepAlive", "false");
        }
    }

    private Resultado<Categoria> convertResponse(BufferedReader inStream) {
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
            Resultado<Categoria> resultado = new ResultadoSpinner<>(lista);
            return resultado;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
