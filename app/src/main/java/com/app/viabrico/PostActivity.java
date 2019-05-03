package com.app.viabrico;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
    }

    public void submit(View view) {

        EditText provider_name = findViewById(R.id.provider_name);
        EditText provider_description = findViewById(R.id.provider_description);
        EditText provider_address = findViewById(R.id.provider_address);
        EditText provider_phone = findViewById(R.id.provider_phone);
        EditText provider_mail = findViewById(R.id.provider_mail);

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

            AsyncT asyncT = new AsyncT("POST", "https://srm-viabrico.herokuapp.com/api/providers", values);
            asyncT.execute();

            Intent intent = new Intent(this, ProviderActivity.class);
            startActivity(intent);
        }
    }

}
