package br.com.jhonatan.personalcontrolmobile.assincrono;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;

import br.com.jhonatan.personalcontrolmobile.service.Service;


/**
 * Created by Jhonatan on 21/02/2016.
 */
public class AtividadeAssincrona extends AsyncTask<Service, Void, Resultado> {

    private View componente;
    private Activity activity;

    public AtividadeAssincrona(View componente, Activity activity) {
        this.componente = componente;
        this.activity = activity;
    }

    protected Resultado doInBackground(Service... services) {
        return services[0].listar();
    }

    protected void onPostExecute(Resultado resultado) {
        resultado.executar(componente, activity);
    }

}
