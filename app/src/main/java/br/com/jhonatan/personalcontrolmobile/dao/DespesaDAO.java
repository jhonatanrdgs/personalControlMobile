package br.com.jhonatan.personalcontrolmobile.dao;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

import br.com.jhonatan.personalcontrolmobile.dto.Despesa;

/**
 * Created by jhonatan.rodrigues on 01/03/2016.
 */
public class DespesaDAO extends GenericDAO<Despesa> {

    public DespesaDAO() {
        super(Despesa.class);
    }

    public List<Despesa> listar(String autenticacao) throws IOException {
        return chamadaGET("http://personalcontrol-rdgs.rhcloud.com/despesaApi/listarUltimasDespesas", autenticacao);//TODO url
    }


    public void salvarDespesa(Despesa despesa, String autenticacao) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(despesa);
        chamadaPOST("http://192.168.100.5:8080/personalcontrol/despesaApi/salvarDespesa", autenticacao, jsonInString);//TODO possivel retorno
    }
}
