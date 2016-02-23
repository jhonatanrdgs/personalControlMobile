package br.com.jhonatan.personalcontrolmobile.service;

import android.os.Build;
import android.util.Base64;

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
import br.com.jhonatan.personalcontrolmobile.assincrono.ResultadoListView;
import br.com.jhonatan.personalcontrolmobile.assincrono.ResultadoTextView;
import br.com.jhonatan.personalcontrolmobile.dto.RelatorioResumoDTO;
import br.com.jhonatan.personalcontrolmobile.util.SessaoUtil;

/**
 * Created by jhonatan.rodrigues on 23/02/2016.
 */
public class ResumoService implements Service<RelatorioResumoDTO> {

    private String SERVICE_URL = "http://personalcontrol-rdgs.rhcloud.com/relatorioApi/resumo";

    @Override
    public Resultado<RelatorioResumoDTO> listar() {
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
        if (Integer.parseInt(Build.VERSION.SDK)
                < Build.VERSION_CODES.FROYO) {
            System.setProperty("http.keepAlive", "false");
        }
    }

    private Resultado<RelatorioResumoDTO> convertResponseDespesa(BufferedReader inStream) {
        try {
            ObjectMapper mapper = new ObjectMapper();


            RelatorioResumoDTO dto = mapper.readValue(inStream, RelatorioResumoDTO.class);


            Resultado<RelatorioResumoDTO> resultado = new ResultadoTextView(dto);
            return resultado;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
