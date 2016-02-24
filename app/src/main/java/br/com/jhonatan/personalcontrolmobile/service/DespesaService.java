package br.com.jhonatan.personalcontrolmobile.service;

import android.os.Build;
import android.util.Base64;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.com.jhonatan.personalcontrolmobile.R;
import br.com.jhonatan.personalcontrolmobile.assincrono.LinhaListView;
import br.com.jhonatan.personalcontrolmobile.assincrono.Resultado;
import br.com.jhonatan.personalcontrolmobile.assincrono.ResultadoListView;
import br.com.jhonatan.personalcontrolmobile.dto.Despesa;
import br.com.jhonatan.personalcontrolmobile.util.SessaoUtil;

/**
 * Created by Jhonatan on 21/02/2016.
 */
public class DespesaService implements Service<Despesa> {

    private String SERVICE_URL = "http://personalcontrol-rdgs.rhcloud.com/despesaApi/listarUltimasDespesas";

    @Override
    public Resultado<Despesa> listar() {
        disableConnectionReuseIfNecessary();

        String encoding = Base64.encodeToString((SessaoUtil.getInstance().getDadosUsuario().getNomeUsuario() + ":"
                + SessaoUtil.getInstance().getDadosUsuario().getSenhaDescriptografada()).getBytes(), Base64.NO_WRAP);

        HttpURLConnection urlConnection = null;
        try {
            // create connection
            URL urlToRequest = new URL(SERVICE_URL);
            urlConnection = (HttpURLConnection) urlToRequest.openConnection();
            urlConnection.setRequestProperty("Authorization", "Basic " + encoding);
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
            return convertResponseDespesa(in);

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
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.FROYO) {
            System.setProperty("http.keepAlive", "false");
        }
    }

    private Resultado<Despesa> convertResponseDespesa(BufferedReader inStream) {
        try {
            String feed_str = null;
            String entireFeed = "";
            while ((feed_str = inStream.readLine()) != null) {
                entireFeed += feed_str;
            }

            List<LinhaListView> lista = new ArrayList<LinhaListView>();
            ObjectMapper mapper = new ObjectMapper();

            JSONArray array = new JSONArray(entireFeed);
            for (int i = 0; i < array.length(); i++) {
                lista.add(mapper.readValue(array.getString(i), Despesa.class));
            }

            Resultado<Despesa> resultado = new ResultadoListView(android.R.layout.simple_list_item_1, R.layout.listview_despesa, lista);
            return resultado;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Despesa salvarDespesa(Despesa despesa, String urlService) {
        ObjectMapper mapper = new ObjectMapper();

        String encoding = Base64.encodeToString((SessaoUtil.getInstance().getDadosUsuario().getNomeUsuario() + ":"
                + SessaoUtil.getInstance().getDadosUsuario().getSenhaDescriptografada()).getBytes(), Base64.NO_WRAP);

        try {
            String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(despesa);
            URL u = new URL(urlService);
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Basic " + encoding);
            conn.setRequestProperty("Content-type", "application/json");

            OutputStream os = conn.getOutputStream();
            os.write(jsonInString.getBytes("UTF-8"));
            os.close();

            int responseCode = conn.getResponseCode();
            System.out.println(responseCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return despesa;
    }

}
