package br.com.jhonatan.personalcontrolmobile.dto;

import android.widget.Spinner;

/**
 * Created by jhonatan.rodrigues on 13/01/2016.
 */
public class Categoria {

    private Long id;
    private String descricao;

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }

}
