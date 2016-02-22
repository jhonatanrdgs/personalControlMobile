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
public class ResultadoListView<T> implements Resultado<T> {

    private List<T> itens;

    public ResultadoListView(List<T> itens) {
        this.itens = itens;
    }

    @Override
    public void executar(View componente, Activity activity) {
        ListView listView = (ListView) componente;
        final StableArrayAdapter adapter = new StableArrayAdapter(activity, android.R.layout.simple_list_item_1, (List<Despesa>) itens);//TODO
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final Despesa item = (Despesa) parent.getItemAtPosition(position);
                Toast.makeText(view.getContext(), item.getDescricao() + " selected", Toast.LENGTH_LONG).show();
            }

        });
    }
}
