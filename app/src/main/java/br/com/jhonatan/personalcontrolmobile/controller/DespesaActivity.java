package br.com.jhonatan.personalcontrolmobile.controller;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_list_despesa);
        new ListarDespesas((ListView) findViewById(R.id.listaDespesas)).execute("http://personalcontrol-rdgs.rhcloud.com/despesaApi/listarUltimasDespesas");//TODO
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
        final StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, itens);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final Despesa item = (Despesa) parent.getItemAtPosition(position);
                Toast.makeText(view.getContext(), item.getDescricao() + " selected", Toast.LENGTH_LONG).show();
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

            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt-BR"));

            Despesa despesa = new Despesa(descricao.getText().toString(), Double.valueOf(valor.getText().toString()), (Categoria) categoria.getSelectedItem(),
                    (MetodoPagamento) metodoPg.getSelectedItem(), df.parse(data.getText().toString()), fixa.isChecked(), Integer.valueOf(parcelas.getText().toString()));

            new SalvarDespesa(despesa).execute("http://192.168.100.5:8080/personalcontrol/despesaApi/salvarDespesa");
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

        private Despesa despesa;

        public SalvarDespesa(Despesa despesa) {
            this.despesa = despesa;
        }


        protected Despesa doInBackground(String... serviceUrl) {
            return service.salvarDespesa(this.despesa, serviceUrl[0]);
        }

        protected void onPostExecute(Despesa lista) {
            //TODO
        }

    }

    class StableArrayAdapter extends ArrayAdapter<Despesa> {

        List<Despesa> despesas;

        public StableArrayAdapter(Context context, int textViewResourceId, List<Despesa> objects) {
            super(context, textViewResourceId, objects);
            despesas = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.simple_list_item_1,parent,false);
                // inflate custom layout called row
                holder = new ViewHolder();
                holder.tv =(TextView) convertView.findViewById(R.id.firstLine);
                holder.tv2 =(TextView) convertView.findViewById(R.id.secondLine);
                // initialize textview
                convertView.setTag(holder);
            }
            else
            {
                holder = (ViewHolder)convertView.getTag();
            }
            Despesa despesa = despesas.get(position);
            holder.tv.setText(despesa.getDescricaoFormatada());
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt-BR"));
            holder.tv2.setText(df.format(despesa.getData()));
            // set the name to the text;

            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }

    static class ViewHolder {
        TextView tv;
        TextView tv2;
    }

}