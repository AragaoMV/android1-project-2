package com.marcos.android1_project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText update_name,update_email,update_phone;
    Button btnUpdate;
    String id, name, email, phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        update_name = findViewById(R.id.update_name);
        update_email = findViewById(R.id.update_email);
        update_phone = findViewById(R.id.update_phone);
        btnUpdate = findViewById(R.id.btnUpdate);
        getAndSetIntentData();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtenha os dados atualizados do usuário a partir dos campos de texto
                String updatedName = update_name.getText().toString();
                String updatedEmail = update_email.getText().toString();
                String updatedPhone = update_phone.getText().toString();

                DatabaseHelper databaseHelper = new DatabaseHelper(UpdateActivity.this);
                databaseHelper.updateData(id, updatedName, updatedEmail, updatedPhone);
                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
    void getAndSetIntentData(){
        if(getIntent().hasExtra("id")&&
                getIntent().hasExtra("name")&&
                getIntent().hasExtra("email")&&
                getIntent().hasExtra("phone")){
            //Pega os dados do Intent
            id=getIntent().getStringExtra("id");
            name=getIntent().getStringExtra("name");
            email=getIntent().getStringExtra("email");
            phone=getIntent().getStringExtra("phone");

            //Seta os dados do Intent
            update_name.setText(name);
            update_email.setText(email);
            update_phone.setText(phone);

        }else {
            Toast.makeText(this, "Contato não encontrado", Toast.LENGTH_SHORT).show();
        }
    }
}