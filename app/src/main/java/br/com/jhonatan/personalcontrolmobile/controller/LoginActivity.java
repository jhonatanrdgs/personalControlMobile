package br.com.jhonatan.personalcontrolmobile.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import br.com.jhonatan.personalcontrolmobile.R;

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
        //TODO realizar login
        Intent homeActivity = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(homeActivity);
    }
}
