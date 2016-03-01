package br.com.jhonatan.personalcontrolmobile.dao;

import java.io.IOException;
import java.util.List;

import br.com.jhonatan.personalcontrolmobile.dto.MetodoPagamento;

/**
 * Created by jhonatan.rodrigues on 01/03/2016.
 */
public class MetodoPagamentoDAO extends GenericDAO<MetodoPagamento> {

    public MetodoPagamentoDAO() {
        super(MetodoPagamento.class);
    }

    public List<MetodoPagamento> listar() throws IOException {
        return chamadaGET("http://personalcontrol-rdgs.rhcloud.com/cadastrosGeraisApi/getMetodosPagamento", null);//TODO url
    }
}
