package com.enes.notlar.db.rest;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.JsonReader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

class AsyncTaskRest extends AsyncTask<Void, Integer, String> {

    private String ENDPOINT = "http://104.206.242.118:8080/notlar";
    private  final String TAG = AsyncTaskRest.class.getName();
    private String service;
    private HashMap map;

    public AsyncTaskRest(String service, HashMap map) throws MalformedURLException {
        this.service = service;
        this.map =  map;

    }

    protected void onPreExecute(){

    }

    protected static String read(InputStream inputStream) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder total = new StringBuilder();
        for (String line; (line = r.readLine()) != null; ) {
            total.append(line).append('\n');
        }
        return total.toString();
    }

    protected String query() throws IOException {
        String response = "";

        Uri.Builder builder = new Uri.Builder();

        for(Object entry : map.entrySet()) {
            String key = (String) ((Map.Entry)entry).getKey();
            String value = (String) ((Map.Entry)entry).getValue();
            builder.appendQueryParameter(key, value);
        }

        String query = builder.build().getEncodedQuery();

        URL url = new URL(ENDPOINT + "/" + service + "?" + query);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(5000);
        conn.setConnectTimeout(10000);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.setDoOutput(true);

        conn.setRequestProperty("User-Agent", "my-rest-app-v0.1");
        conn.setRequestProperty("Accept", "application/vnd.github.v3+json");



        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        writer.write(query);
        writer.flush();
        writer.close();
        os.close();

        conn.connect();

        if (conn.getResponseCode() == 200) {
            InputStream inputStream = conn.getInputStream();
            response = read(inputStream);
        } else {
            // Error handling code goes here
            response = "error";
        }

        conn.disconnect();
        return response;
    }

    protected String callTest1(Void...arg0) {
        String response = "";

        try {
            HttpURLConnection connection = null;

            URL url = new URL(ENDPOINT + "/" + service);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "my-rest-app-v0.1");
            connection.setRequestProperty("Accept", "application/vnd.github.v3+json");

            //connection.setRequestMethod("POST");

            connection.getContent();

            if (connection.getResponseCode() == 200) {
                InputStream inputStream = connection.getInputStream();
                response = read(inputStream);
            } else {
                // Error handling code goes here
                response = "error";
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
            response = e.getMessage();
        }

        return response;
    }

    protected String doInBackground(Void...arg0) {
        String response = "";

        try {
            response = query();
        } catch (IOException e) {
            e.printStackTrace();
            response = e.getMessage();
        }

        return response;
    }

    protected void onProgressUpdate(Integer...a){

    }

    protected void onPostExecute(String result) {

    }
}
