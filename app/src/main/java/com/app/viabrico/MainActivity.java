package com.app.viabrico;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private static final String LIEN = "https://srm-viabrico.herokuapp.com/api/users";

    private List<User> listUser = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getAllUsers();
    }

    // Boutons de directions vers les différentes pages :
    public void goToHome(View view) {
        Intent intent = new Intent(this, ProviderActivity.class);
        startActivity(intent);
    }

    public void getAllUsers() {
        listUser.clear();
        // client HTTP :
        AsyncHttpClient client = new AsyncHttpClient();
        client.setConnectTimeout(60000);
        client.setResponseTimeout(60000);

        // paramètres :
        RequestParams requestParams = new RequestParams();

        client.get(LIEN, requestParams, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                Gson gson = new Gson();

                String retour = new String(response);
                try {
                    JSONArray jsonArray = new JSONArray(retour);


                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        User user = gson.fromJson(jsonObject.toString(), User.class);
                        Log.e("user", user.mail);
                        listUser.add(user);
                    }

                    Log.i("azertyuiop", listUser.toString());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }


    public void submit(View view) {

        Boolean redirect = false;
        String mail_value;
        String password_value;
        EditText email  = (EditText) findViewById(R.id.email);
        EditText password  = (EditText) findViewById(R.id.password);

        mail_value = email.getText().toString();
        password_value = password.getText().toString();

        Log.e("pass", password_value);
        for (int i = 0; i < listUser.size(); i++) {
            if (mail_value.equals(listUser.get(i).mail) && password_value.equals(listUser.get(i).password)) {
                redirect = true;
                Intent intent = new Intent(this, ProviderActivity.class);
                startActivity(intent);
            }
        }

        if (!redirect) {
            Toast.makeText(this, "Wrong Login",
                    Toast.LENGTH_LONG).show();
        }
    }
}
