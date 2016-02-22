package br.com.jhonatan.personalcontrolmobile.assincrono;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import java.util.List;

/**
 * Created by Jhonatan on 21/02/2016.
 */
public interface Resultado<T> {

    void executar(View componente, Activity activity);

}
