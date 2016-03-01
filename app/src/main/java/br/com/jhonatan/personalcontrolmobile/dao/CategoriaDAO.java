package br.com.jhonatan.personalcontrolmobile.dao;

import java.io.IOException;
import java.util.List;

import br.com.jhonatan.personalcontrolmobile.dto.Categoria;

/**
 * Created by jhonatan.rodrigues on 24/02/2016.
 */
public class CategoriaDAO extends GenericDAO<Categoria> {

    public CategoriaDAO() {
        super(Categoria.class);
    }

    public List<Categoria> listar() throws IOException {
        return chamadaGET("http://personalcontrol-rdgs.rhcloud.com/cadastrosGeraisApi/getCategorias", null);
    }


}
