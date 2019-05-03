package com.app.viabrico;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

class AsyncT extends AsyncTask<Void,Void,Void> {
    public String method;
    public String url;
    public String[] values;

    public AsyncT(String method, String url, String[] values) {
        this.method = method;
        this.values = values;
        this.url = url;
    }

    @Override
    protected Void doInBackground(Void... params) {

        try {
            Log.i("Bigeard", "AAA");
            URL url = new URL(this.url);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod(this.method);
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("Accept","application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // conn.connect();
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());

            if(this.method == "POST" || this.method == "PUT") {
                JSONObject jsonObject = new JSONObject();

                jsonObject.put("name", values[1]);
                jsonObject.put("description", values[2]);
                jsonObject.put("address", values[3]);
                jsonObject.put("phone", values[4]);
                jsonObject.put("mail", values[5]);

                Log.i("JSON", String.valueOf(this.values));
                wr.writeBytes(jsonObject.toString());
            }

            wr.flush();
            wr.close();

            Log.i("STATUS", String.valueOf(conn.getResponseCode()));
            Log.i("MSG", conn.getResponseMessage());

            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}