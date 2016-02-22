package br.com.jhonatan.personalcontrolmobile.service;

import br.com.jhonatan.personalcontrolmobile.assincrono.Resultado;

/**
 * Created by Jhonatan on 21/02/2016.
 */
public interface Service<T> {

    Resultado<T> listar();
}
