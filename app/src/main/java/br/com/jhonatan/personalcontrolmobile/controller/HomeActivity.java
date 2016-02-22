package br.com.jhonatan.personalcontrolmobile.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import br.com.jhonatan.personalcontrolmobile.R;


public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_home);

        TextView textView = (TextView) findViewById(R.id.descricaoLabel2);//TODO http://techlovejump.com/android-multicolumn-listview/
        textView.setText("2333");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent despesaActivity = new Intent(getApplicationContext(), DespesaActivity.class);
                startActivity(despesaActivity);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {//TODO colocar a certa, isso vem do menu
            Intent goToNextActivity = new Intent(getApplicationContext(), DespesaActivity.class);
            startActivity(goToNextActivity);
        }

        return super.onOptionsItemSelected(item);
    }
}
