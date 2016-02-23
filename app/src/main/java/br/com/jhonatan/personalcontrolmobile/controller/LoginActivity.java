package br.com.jhonatan.personalcontrolmobile.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import br.com.jhonatan.personalcontrolmobile.R;
import br.com.jhonatan.personalcontrolmobile.dto.Credenciais;
import br.com.jhonatan.personalcontrolmobile.dto.Usuario;
import br.com.jhonatan.personalcontrolmobile.service.LoginService;
import br.com.jhonatan.personalcontrolmobile.util.CriptografiaUtil;
import br.com.jhonatan.personalcontrolmobile.util.SessaoUtil;

/**
 * Created by Jhonatan on 19/02/2016.
 */
public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login(View v) {
        try {
            EditText login = (EditText) findViewById(R.id.login);
            EditText senha = (EditText) findViewById(R.id.senha);
            new Logar(login.getText().toString(), senha.getText().toString()).execute("");

            Intent homeActivity = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(homeActivity);
        } catch (NoSuchAlgorithmException e) {
            //TODO
        }
    }
}

class Logar extends AsyncTask<String, Void, Usuario> {//TODO fazer mesmo esquema do AtividadeAssincrona

    private final String login;
    private final String senhaCriptografada;
    private final String senha;

    public Logar(String login, String senha) throws NoSuchAlgorithmException {
        this.login = login;
        this.senha = senha;
        this.senhaCriptografada = CriptografiaUtil.criptografarMD5(senha);
    }

    protected Usuario doInBackground(String... serviceUrl) {
        return new LoginService().logar(new Credenciais(login, senhaCriptografada));
    }

    protected void onPostExecute(Usuario usuario) {
        //TODO se não logou
        //TODO está redirecionando antes de fazer o login, o redirecionamento deve ser feito depois
        usuario.setSenhaDescriptografada(senha);
        SessaoUtil.getInstance().setDadosUsuario(usuario);
    }


}
