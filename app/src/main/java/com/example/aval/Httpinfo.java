package com.example.aval;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Httpinfo extends AsyncTask <Void, Void, Cep> {
private final String cep;
StringBuilder resp = new StringBuilder();

    public Httpinfo(String cep) {
        this.cep = cep;
    }


    protected Cep doInBackground(Void... voids) {
    if(this.cep != null && this.cep.length() == 8){
        System.out.println(this.cep);
            try {
                URL url = new URL("http://www.viacep.com.br/ws/" + this.cep + "/json");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                connection.setDoOutput(true);
                connection.setConnectTimeout(5000);
                connection.connect();
                resp = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    resp.append(scanner.next());
                }

            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }  catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    return new Gson().fromJson(resp.toString(), Cep.class);
    }
}
