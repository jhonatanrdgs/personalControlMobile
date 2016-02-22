package br.com.jhonatan.personalcontrolmobile.controller;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import br.com.jhonatan.personalcontrolmobile.R;
import br.com.jhonatan.personalcontrolmobile.assincrono.AtividadeAssincrona;
import br.com.jhonatan.personalcontrolmobile.dto.Categoria;
import br.com.jhonatan.personalcontrolmobile.dto.Despesa;
import br.com.jhonatan.personalcontrolmobile.dto.MetodoPagamento;
import br.com.jhonatan.personalcontrolmobile.service.CategoriaService;
import br.com.jhonatan.personalcontrolmobile.service.DespesaService;
import br.com.jhonatan.personalcontrolmobile.service.MetodoPagamentoService;

/**
 * Created by Jhonatan on 15/12/2015.
 */
public class DespesaActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_list_despesa);
        new AtividadeAssincrona(findViewById(R.id.listaDespesas), this).execute(new DespesaService());//TODO
    }


    public void novo(View v) {
        setContentView(R.layout.content_despesa);
        new AtividadeAssincrona(findViewById(R.id.categoria), this).execute(new CategoriaService());//TODO melhorar
        new AtividadeAssincrona(findViewById(R.id.metodoPg), this).execute(new MetodoPagamentoService());
    }


    public void salvar(View v) {
        try {
            EditText descricao = (EditText) findViewById(R.id.descricao);
            Spinner categoria = (Spinner) findViewById(R.id.categoria);
            Spinner metodoPg = (Spinner) findViewById(R.id.metodoPg);
            EditText valor = (EditText) findViewById(R.id.valor);
            EditText data = (EditText) findViewById(R.id.data);
            CheckBox fixa = (CheckBox) findViewById(R.id.fixa);
            EditText parcelas = (EditText) findViewById(R.id.parcelas);

            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt-BR"));

            Despesa despesa = new Despesa(descricao.getText().toString(), Double.valueOf(valor.getText().toString()), (Categoria) categoria.getSelectedItem(),
                    (MetodoPagamento) metodoPg.getSelectedItem(), df.parse(data.getText().toString()), fixa.isChecked(), Integer.valueOf(parcelas.getText().toString()));

            new SalvarDespesa(despesa).execute("http://192.168.100.5:8080/personalcontrol/despesaApi/salvarDespesa");
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    class SalvarDespesa extends AsyncTask<String, Void, Despesa> {//TODO fazer mesmo esquema do AtividadeAssincrona

        private Despesa despesa;

        public SalvarDespesa(Despesa despesa) {
            this.despesa = despesa;
        }


        protected Despesa doInBackground(String... serviceUrl) {
            return new DespesaService().salvarDespesa(this.despesa, serviceUrl[0]);//TODO
        }

        protected void onPostExecute(Despesa lista) {
            //TODO
        }

    }

}