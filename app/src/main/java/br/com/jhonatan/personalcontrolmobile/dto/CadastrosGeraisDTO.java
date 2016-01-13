package br.com.jhonatan.personalcontrolmobile.dto;

import android.widget.Spinner;

/**
 * Created by jhonatan.rodrigues on 13/01/2016.
 */
public interface CadastrosGeraisDTO {

    public Long getId();
    public String getDescricao();
    public void preencherCombo(Spinner spinner);
}
