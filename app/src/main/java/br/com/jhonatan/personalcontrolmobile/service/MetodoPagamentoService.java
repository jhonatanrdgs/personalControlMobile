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
import br.com.jhonatan.personalcontrolmobile.dao.MetodoPagamentoDAO;
import br.com.jhonatan.personalcontrolmobile.dto.MetodoPagamento;

/**
 * Created by Jhonatan on 21/02/2016.
 */
public class MetodoPagamentoService implements Service<MetodoPagamento> {


    public Resultado<MetodoPagamento> listar() {
        try {
            Resultado<MetodoPagamento> reultado = new ResultadoSpinner<MetodoPagamento>(new MetodoPagamentoDAO().listar());
            return reultado;
        } catch (IOException e) {
            e.printStackTrace();//TODO
            return null;
        }
    }

}
