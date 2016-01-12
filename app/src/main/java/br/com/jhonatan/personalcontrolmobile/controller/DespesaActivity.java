package br.com.jhonatan.personalcontrolmobile.controller;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

import br.com.jhonatan.personalcontrolmobile.R;

/**
 * Created by Jhonatan on 15/12/2015.
 */
public class DespesaActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_list_despesa);
    }

    public void novo(View v) {
        setContentView(R.layout.content_despesa);
        Spinner dropdown = (Spinner)findViewById(R.id.categoria);
        String[] items = new String[]{"1", "2", "three"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        new RetrieveFeedTask().execute("http://www.cheesejedi.com/rest_services/get_big_cheese?level=1");

    }

    public void preencherCombo() {
        Spinner metodoPg = (Spinner)findViewById(R.id.metodoPg);
        String[] items2 = new String[]{"1", "2", "three"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        metodoPg.setAdapter(adapter2);
    }


    public void salvar(View v) {
        EditText descricao = (EditText) findViewById(R.id.descricao);
        Spinner categoria = (Spinner) findViewById(R.id.categoria);
        Spinner metodoPg = (Spinner) findViewById(R.id.metodoPg);
        EditText valor = (EditText) findViewById(R.id.valor);
        EditText data = (EditText) findViewById(R.id.data);
        System.out.println(descricao.getText().toString());
        System.out.println(categoria.getSelectedItem().toString());
        System.out.println(metodoPg.getSelectedItem().toString());
        System.out.println(valor.getText().toString());
        System.out.println(data.getText().toString());
    }

    class RetrieveFeedTask extends AsyncTask<String, Void, JSONArray> {

        private Exception exception;

        protected JSONArray doInBackground(String... serviceUrl) {
            disableConnectionReuseIfNecessary();

            HttpURLConnection urlConnection = null;
            try {
                // create connection
                URL urlToRequest = new URL(serviceUrl[0]);
                urlConnection = (HttpURLConnection)
                        urlToRequest.openConnection();
                urlConnection.setConnectTimeout(15000);
                urlConnection.setReadTimeout(15000);

                // handle issues
                int statusCode = urlConnection.getResponseCode();
                if (statusCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
                    // handle unauthorized (if service requires user login)
                } else if (statusCode != HttpURLConnection.HTTP_OK) {
                    // handle any other errors, like 404, 500,..
                }

                // create JSON object from content
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                return new JSONArray(getResponseText(in));

            } catch (MalformedURLException e) {
                e.printStackTrace();
                // URL is invalid
            } catch (SocketTimeoutException e) {
                e.printStackTrace();
                // data retrieval or connection timed out
            } catch (IOException e) {
                e.printStackTrace();
                // could not read response body
                // (could not create input stream)
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }

            return null;
        }

        /**
         * required in order to prevent issues in earlier Android version.
         */
        private void disableConnectionReuseIfNecessary() {
            // see HttpURLConnection API doc
            if (Integer.parseInt(Build.VERSION.SDK)
                    < Build.VERSION_CODES.FROYO) {
                System.setProperty("http.keepAlive", "false");
            }
        }

        private String getResponseText(BufferedReader inStream) {
            try {
                String feed_str = null;
                String entireFeed = "";
                while ((feed_str = inStream.readLine()) != null) {
                    entireFeed += feed_str;
                }
                return entireFeed;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        protected void onPostExecute(JSONArray feed) {
            //http://stackoverflow.com/questions/9909765/how-to-pass-the-result-of-asynctask-onpostexecute-method-into-the-parent-activit
            preencherCombo();
            System.out.println(feed);
        }


    }


}