package br.com.jhonatan.personalcontrolmobile.dto;

/**
 * Created by Jhonatan on 22/02/2016.
 */
public class Credenciais {

    private String user;
    private String pw;

    public Credenciais(String user, String pw) {
        this.user = user;
        this.pw = pw;
    }

    public String getUser() {
        return user;
    }

    public String getPw() {
        return pw;
    }
}
