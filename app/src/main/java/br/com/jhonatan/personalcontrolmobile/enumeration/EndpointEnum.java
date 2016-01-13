package br.com.jhonatan.personalcontrolmobile.enumeration;

/**
 * Created by jhonatan.rodrigues on 13/01/2016.
 */
public enum EndpointEnum {

    CADASTROS_GERAIS_ENDPOINT("http://personalcontrol-rdgs.rhcloud.com/cadastrosGeraisApi/"),
    RELATORIO_ENDPOINT("http://personalcontrol-rdgs.rhcloud.com/relatorioApi"),
    DESPESA_ENDPOINT("http://personalcontrol-rdgs.rhcloud.com/despesaApi/");

    private String  endpoint;

    private EndpointEnum(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
