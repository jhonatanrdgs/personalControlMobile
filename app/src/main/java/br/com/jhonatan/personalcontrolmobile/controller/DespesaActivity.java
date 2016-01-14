package br.com.jhonatan.personalcontrolmobile.controller;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;


import br.com.jhonatan.personalcontrolmobile.R;
import br.com.jhonatan.personalcontrolmobile.dto.Categoria;
import br.com.jhonatan.personalcontrolmobile.dto.MetodoPagamento;
import br.com.jhonatan.personalcontrolmobile.service.CadastrosGeraisService;

/**
 * Created by Jhonatan on 15/12/2015.
 */
public class DespesaActivity extends Activity {

    private CadastrosGeraisService service = new CadastrosGeraisService();//TODO injeção de dependencias

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_list_despesa);
    }

    public void novo(View v) {
        setContentView(R.layout.content_despesa);

        new CarregarComboCategoria((Spinner)findViewById(R.id.categoria)).execute("http://personalcontrol-rdgs.rhcloud.com/cadastrosGeraisApi/getCategorias");
        new CarregarComboMetodoPagamento((Spinner)findViewById(R.id.metodoPg)).execute("http://personalcontrol-rdgs.rhcloud.com/cadastrosGeraisApi/getCategorias");

    }

    public void preencherCombo(Spinner combo, List<Categoria> itens) {
        ArrayAdapter<Categoria> adapter2 = new ArrayAdapter<Categoria>(this, android.R.layout.simple_spinner_dropdown_item, itens);
        combo.setAdapter(adapter2);
    }

    public void preencherCombo2(Spinner combo, List<MetodoPagamento> itens) {//TODO
        ArrayAdapter<MetodoPagamento> adapter2 = new ArrayAdapter<MetodoPagamento>(this, android.R.layout.simple_spinner_dropdown_item, itens);
        combo.setAdapter(adapter2);
    }


    public void salvar(View v) {
        EditText descricao = (EditText) findViewById(R.id.descricao);
        Spinner categoria = (Spinner) findViewById(R.id.categoria);
        Spinner metodoPg = (Spinner) findViewById(R.id.metodoPg);
        EditText valor = (EditText) findViewById(R.id.valor);
        EditText data = (EditText) findViewById(R.id.data);
        System.out.println(descricao.getText().toString());
        System.out.println(categoria.getSelectedItem().toString());
        System.out.println(metodoPg.getSelectedItem().toString());
        System.out.println(valor.getText().toString());
        System.out.println(data.getText().toString());
    }

    class CarregarComboCategoria extends AsyncTask<String, Void, List<Categoria>> {

        private Spinner combo;

        public CarregarComboCategoria(Spinner combo) {
            this.combo = combo;
        }

        protected List<Categoria> doInBackground(String... serviceUrl) {
            return service.listarCategorias(serviceUrl[0]);
        }

        protected void onPostExecute(List<Categoria> lista) {
            preencherCombo(combo, lista);
        }
    }

    class CarregarComboMetodoPagamento extends AsyncTask<String, Void, List<MetodoPagamento>> {

        private Spinner combo;

        public CarregarComboMetodoPagamento(Spinner combo) {
            this.combo = combo;
        }

        protected List<MetodoPagamento> doInBackground(String... serviceUrl) {
            return service.listarMetodosPagamento(serviceUrl[0]);
        }

        protected void onPostExecute(List<MetodoPagamento> lista) {
            preencherCombo2(combo, lista);
        }

    }
}