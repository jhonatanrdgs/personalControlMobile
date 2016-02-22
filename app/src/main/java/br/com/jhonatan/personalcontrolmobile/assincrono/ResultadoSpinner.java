package br.com.jhonatan.personalcontrolmobile.assincrono;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

import br.com.jhonatan.personalcontrolmobile.dto.Categoria;

/**
 * Created by Jhonatan on 21/02/2016.
 */
public class ResultadoSpinner<T> implements Resultado<T> {

    private List<T> itens;

    public ResultadoSpinner(List<T> itens) {
        this.itens = itens;
    }

    @Override
    public void executar(View componente, Activity activity) {
        Spinner spinner = (Spinner) componente;
        ArrayAdapter<T> adapter2 = new ArrayAdapter<T>(activity, android.R.layout.simple_spinner_dropdown_item, itens);
        spinner.setAdapter(adapter2);
    }

}
