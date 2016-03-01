package br.com.jhonatan.personalcontrolmobile.assincrono;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.jhonatan.personalcontrolmobile.adapter.StableArrayAdapter;
import br.com.jhonatan.personalcontrolmobile.dto.Despesa;

/**
 * Created by Jhonatan on 21/02/2016.
 */
public class ResultadoListView implements Resultado {

    private List<? extends LinhaListView> itens;
    private int lista;
    private int listView;

    public ResultadoListView(int lista, int listView, List<? extends LinhaListView> itens) {
        this.itens = itens;
        this.lista = lista;
        this.listView = listView;
    }

    @Override
    public void executar(View componente, Activity activity) {
        ListView listView = (ListView) componente;
        final StableArrayAdapter adapter = new StableArrayAdapter(activity, lista, this.listView, itens);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final LinhaListView item = (LinhaListView) parent.getItemAtPosition(position);
                Toast.makeText(view.getContext(), item.getPrimeiraLinha() + " selecionada.", Toast.LENGTH_LONG).show();
            }
        });
    }
}
