package com.app.viabrico;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PutActivity extends AppCompatActivity {

    private Provider provider;

    private EditText provider_name;
    private EditText provider_description;
    private EditText provider_address;
    private EditText provider_phone;
    private EditText provider_mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put);
        provider = (Provider) getIntent().getSerializableExtra("provider");

        provider_name  = findViewById(R.id.provider_name);
        provider_description  = findViewById(R.id.provider_description);
        provider_address  = findViewById(R.id.provider_address);
        provider_phone  = findViewById(R.id.provider_phone);
        provider_mail  = findViewById(R.id.provider_mail);

        Log.i("bigeard", provider.name);

        provider_name.setText(provider.name);
        provider_description.setText(provider.description);
        provider_address.setText(provider.address);
        provider_phone.setText(provider.phone);
        provider_mail.setText(provider.mail);
    }

    public void submit(View view) {

        if( provider_name.getText().toString().trim().equals("")
                || provider_name.getText().toString().trim().equals("")
                || provider_description.getText().toString().trim().equals("")
                || provider_address.getText().toString().trim().equals("")
                || provider_phone.getText().toString().trim().equals("")
                || provider_mail.getText().toString().trim().equals("")
        )  {
            Toast.makeText(this, "Error: enter all fields",  Toast.LENGTH_LONG).show();
        } else {
            String[] values = new String[6];
            values[1] = provider_name.getText().toString();
            values[2] = provider_description.getText().toString();
            values[3] = provider_address.getText().toString();
            values[4] = provider_phone.getText().toString();
            values[5] = provider_mail.getText().toString();

            AsyncT asyncT = new AsyncT("PUT", "https://srm-viabrico.herokuapp.com/api/providers/" + provider.id, values);
            asyncT.execute();

            Intent intent = new Intent(this, ProviderActivity.class);
            startActivity(intent);

        }
    }

}
