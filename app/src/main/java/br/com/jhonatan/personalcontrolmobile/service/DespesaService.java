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
import br.com.jhonatan.personalcontrolmobile.dao.DespesaDAO;
import br.com.jhonatan.personalcontrolmobile.dto.Despesa;
import br.com.jhonatan.personalcontrolmobile.util.SessaoUtil;

/**
 * Created by Jhonatan on 21/02/2016.
 */
public class DespesaService implements Service<Despesa> {

    public Resultado<Despesa> listar() {
        try {
            String encoding = Base64.encodeToString((SessaoUtil.getInstance().getDadosUsuario().getNomeUsuario() + ":"
                    + SessaoUtil.getInstance().getDadosUsuario().getSenhaDescriptografada()).getBytes(), Base64.NO_WRAP);
            List<Despesa> lista = new DespesaDAO().listar(encoding);
            Resultado<Despesa> resultado = new ResultadoListView(android.R.layout.simple_list_item_1, R.layout.listview_despesa, lista);
            return resultado;
        } catch (IOException e) {
            e.printStackTrace();//TODO
            return null;
        }
    }


    public Despesa salvarDespesa(Despesa despesa, String urlService) {
        try {
            String encoding = Base64.encodeToString((SessaoUtil.getInstance().getDadosUsuario().getNomeUsuario() + ":"
                    + SessaoUtil.getInstance().getDadosUsuario().getSenhaDescriptografada()).getBytes(), Base64.NO_WRAP);
            new DespesaDAO().salvarDespesa(despesa, encoding);
        } catch (IOException e) {
            e.printStackTrace();//TODO
        }
        return despesa;//TODO
    }

}
