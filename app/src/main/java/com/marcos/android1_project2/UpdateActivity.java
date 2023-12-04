package com.marcos.android1_project2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText update_name,update_email,update_phone;
    Button btnUpdate;
    Button btnDelete;
    String id, name, email, phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        update_name = findViewById(R.id.update_name);
        update_email = findViewById(R.id.update_email);
        update_phone = findViewById(R.id.update_phone);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
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

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmaDelete();
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
    void confirmaDelete(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Deletar " + name + " ?");
        builder.setMessage("Deseja realmente deletar " + name + " ?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseHelper databaseHelper = new DatabaseHelper(UpdateActivity.this);
                databaseHelper.deleteData(id);
                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}