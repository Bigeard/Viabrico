package com.app.viabrico;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class ProviderActivity extends AppCompatActivity {

    // Constantes :
    private static final String LIEN = "http://s519716619.onlinehome.fr/exchange/madrental/get-vehicules.php";

    // Vues :
    private RecyclerView recyclerView = null;


    // Adapter :
    private ProviderAdapter ProviderAdapter = null;

    private List<Provider> listProvider = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);

        // client HTTP :
        AsyncHttpClient client = new AsyncHttpClient();

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
                        Provider provider = gson.fromJson(jsonObject.toString(), Provider.class);
                        listProvider.add(provider);
                    }

                    recyclerView = findViewById(R.id.list_providers);

                    // à ajouter pour de meilleures performances :
                    recyclerView.setHasFixedSize(true);

                    Log.i("Bigeard", listProvider.toString());


                    // layout manager, décrivant comment les items sont disposés :
                    LinearLayoutManager layoutManager = new LinearLayoutManager(ProviderActivity.this);
                    recyclerView.setLayoutManager(layoutManager);

                    // adapter :
                    ProviderAdapter = new ProviderAdapter(ProviderActivity.this, listProvider);
                    recyclerView.setAdapter(ProviderAdapter);

                    ProviderAdapter.actualiserProviders(listProvider);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
}