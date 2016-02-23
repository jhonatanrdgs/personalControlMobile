package br.com.jhonatan.personalcontrolmobile.util;

import br.com.jhonatan.personalcontrolmobile.dto.Usuario;

/**
 * Created by Jhonatan on 22/02/2016.
 */
public final class SessaoUtil {

    private static SessaoUtil sessaoUtil;
    private Usuario dadosUsuario;

    private SessaoUtil() {}

    public static SessaoUtil getInstance() {
        if (sessaoUtil == null) {
            sessaoUtil = new SessaoUtil();
        }
        return sessaoUtil;
    }

    public void setDadosUsuario(Usuario usuario) {
        this.dadosUsuario = usuario;
    }

    public Usuario getDadosUsuario() {
        return dadosUsuario;
    }
}
