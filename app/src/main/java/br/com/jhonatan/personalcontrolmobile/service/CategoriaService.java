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
import br.com.jhonatan.personalcontrolmobile.dao.CategoriaDAO;
import br.com.jhonatan.personalcontrolmobile.dto.Categoria;

/**
 * Created by Jhonatan on 21/02/2016.
 */
public class CategoriaService implements Service<Categoria> {

    public Resultado<Categoria> listar() {
        try {
            Resultado<Categoria> resultado = new ResultadoSpinner<>(new CategoriaDAO().listar());
            return resultado;
        } catch (IOException e) {
            e.printStackTrace();//TODO
            return null;
        }
    }

}
