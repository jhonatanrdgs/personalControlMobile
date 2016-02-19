package br.com.jhonatan.personalcontrolmobile.controller;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import br.com.jhonatan.personalcontrolmobile.R;
import br.com.jhonatan.personalcontrolmobile.dto.Categoria;
import br.com.jhonatan.personalcontrolmobile.dto.Despesa;
import br.com.jhonatan.personalcontrolmobile.dto.MetodoPagamento;
import br.com.jhonatan.personalcontrolmobile.service.CadastrosGeraisService;

/**
 * Created by Jhonatan on 15/12/2015.
 */
public class DespesaActivity extends Activity {

    private CadastrosGeraisService service = new CadastrosGeraisService();//TODO injeção de dependencias
    Despesa d = new Despesa();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_list_despesa);
        new ListarDespesas((ListView) findViewById(R.id.listaDespesas)).execute("http://personalcontrol-rdgs.rhcloud.com/despesaApi/listarDespesas");
    }


    public void novo(View v) {
        setContentView(R.layout.content_despesa);
        new CarregarComboCategoria((Spinner)findViewById(R.id.categoria)).execute("http://personalcontrol-rdgs.rhcloud.com/cadastrosGeraisApi/getCategorias");
        new CarregarComboMetodoPagamento((Spinner)findViewById(R.id.metodoPg)).execute("http://personalcontrol-rdgs.rhcloud.com/cadastrosGeraisApi/getMetodosPagamento");

    }

    public void preencherCombo(Spinner combo, List<Categoria> itens) {
        ArrayAdapter<Categoria> adapter2 = new ArrayAdapter<Categoria>(this, android.R.layout.simple_spinner_dropdown_item, itens);
        combo.setAdapter(adapter2);
    }

    public void preencherCombo2(Spinner combo, List<MetodoPagamento> itens) {//TODO
        ArrayAdapter<MetodoPagamento> adapter2 = new ArrayAdapter<MetodoPagamento>(this, android.R.layout.simple_spinner_dropdown_item, itens);
        combo.setAdapter(adapter2);
    }

    public void preencherLista(ListView listview, List<Despesa> itens) {//TODO
//        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
//                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
//                "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
//                "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
//                "Android", "iPhone", "WindowsMobile" };

        final StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, itens);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                Toast.makeText(view.getContext(), item + " selected", Toast.LENGTH_LONG).show();
            }

        });
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

            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            d.setCategoria((Categoria) categoria.getSelectedItem());
            d.setData(df.parse(data.getText().toString()));
            d.setDescricao(descricao.getText().toString());
            d.setFixa(fixa.isChecked());
            d.setId(null);
            d.setMetodoPagamento((MetodoPagamento) metodoPg.getSelectedItem());
            d.setTotalParcelas(Integer.valueOf(parcelas.getText().toString()));
            d.setValorTotal(new Double(valor.getText().toString()));
            new SalvarDespesa().execute("http://192.168.100.5:8080/personalcontrol/despesaApi/salvarDespesa");
        } catch (ParseException e) {
            e.printStackTrace();
        }

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

    class ListarDespesas extends AsyncTask<String, Void, List<Despesa>> {

        private ListView listView;

        public ListarDespesas(ListView listView) {
            this.listView = listView;
        }

        protected List<Despesa> doInBackground(String... serviceUrl) {
            return service.listarDespesas(serviceUrl[0]);
        }

        protected void onPostExecute(List<Despesa> lista) {
            preencherLista(listView, lista);
        }
    }

    class SalvarDespesa extends AsyncTask<String, Void, Despesa> {


        protected Despesa doInBackground(String... serviceUrl) {
            return service.salvarDespesa(d, serviceUrl[0]);
        }

        protected void onPostExecute(Despesa lista) {
            //TODO
        }

    }

    class StableArrayAdapter extends ArrayAdapter<Despesa> {

        HashMap<Despesa, Integer> mIdMap = new HashMap<Despesa, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId, List<Despesa> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            Despesa item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }
}