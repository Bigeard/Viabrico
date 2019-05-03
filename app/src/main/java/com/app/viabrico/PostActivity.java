package com.app.viabrico;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class PostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
    }

    public void submit(View view) {

        String[] values = new String[6];
        EditText provider_name  = (EditText) findViewById(R.id.provider_name);
        EditText provider_description  = (EditText) findViewById(R.id.provider_description);
        EditText provider_address  = (EditText) findViewById(R.id.provider_address);
        EditText provider_phone  = (EditText) findViewById(R.id.provider_phone);
        EditText provider_mail  = (EditText) findViewById(R.id.provider_mail);

        values[1] = provider_name.getText().toString();
        values[2] = provider_description.getText().toString();
        values[3] = provider_address.getText().toString();
        values[4] = provider_phone.getText().toString();
        values[5] = provider_mail.getText().toString();

        AsyncT asyncT = new AsyncT("POST", "https://srm-viabrico.herokuapp.com/api/providers", values);
        asyncT.execute();

        Intent intent = new Intent(this, ProviderActivity.class);
        startActivity(intent);
    }

}
