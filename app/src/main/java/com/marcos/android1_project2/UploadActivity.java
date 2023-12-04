package com.marcos.android1_project2;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class UploadActivity extends AppCompatActivity {

    private EditText input_name, input_email, input_phone;
    private Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        input_name = findViewById(R.id.input_name);
        input_email = findViewById(R.id.input_email);
        input_phone = findViewById(R.id.input_phone);
        btnSalvar = findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            DatabaseHelper databaseHelper = new DatabaseHelper(UploadActivity.this);
            databaseHelper.addContact(input_name.getText().toString().trim(),
                                      input_email.getText().toString().trim(),
                                      Integer.valueOf(input_phone.getText().toString().trim()));
                Intent intent = new Intent(UploadActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
