package br.com.jhonatan.personalcontrolmobile.service;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import br.com.jhonatan.personalcontrolmobile.dto.Credenciais;
import br.com.jhonatan.personalcontrolmobile.dto.Usuario;

/**
 * Created by Jhonatan on 22/02/2016.
 */
public class LoginService {

    private String SERVICE_URL = "http://personalcontrol-rdgs.rhcloud.com/loginApi/logar";



    public Usuario logar(Credenciais credenciais) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(credenciais);
            URL u = new URL(SERVICE_URL);
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-type", "application/json");

            OutputStream os = conn.getOutputStream();
            os.write(jsonInString.getBytes("UTF-8"));
            os.close();

            int responseCode = conn.getResponseCode();
            System.out.println(responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            return convertResponseDespesa(in);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Usuario convertResponseDespesa(BufferedReader inStream) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(inStream, Usuario.class);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
