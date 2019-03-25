package com.example.ecommerce;

import android.os.AsyncTask;
import android.util.Log;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpGetRequest extends AsyncTask<String, String, String> {
    public static final String REQUEST_METHOD = "GET";
    public static final int READ_TIMEOUT = 1500000;
    public static final int CONNECTION_TIMEOUT = 1500000;

    @Override
    protected String doInBackground(String... strings) {
        String stringUrl = strings[0];
        String result;
        String inputLine;

        try {

            URL myUrl = new URL(stringUrl);

            HttpsURLConnection connection = (HttpsURLConnection) myUrl.openConnection();

            connection.setRequestMethod(REQUEST_METHOD);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);

            connection.connect();

            InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();

            while((inputLine = reader.readLine()) != null){
                stringBuilder.append(inputLine);
            }
            reader.close();
            streamReader.close();
            connection.disconnect();

            result = stringBuilder.toString();
            Log.d("HttpOutput", result);
        } catch (IOException e) {
            e.printStackTrace();
            result = null;
        }
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);


    }
}
