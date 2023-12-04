package com.marcos.android1_project2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private FloatingActionButton btnAddContato;
    DatabaseHelper databaseHelper;
    ArrayList<String> contact_id, contact_name, contact_email, contact_phone;
    ContactAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        btnAddContato = findViewById(R.id.btnAddContato);
        btnAddContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UploadActivity.class);
                startActivity(intent);
            }
        });

        databaseHelper = new DatabaseHelper(MainActivity.this);
        contact_id = new ArrayList<>();
        contact_name = new ArrayList<>();
        contact_email = new ArrayList<>();
        contact_phone = new ArrayList<>();

        storeData();

        contactAdapter = new ContactAdapter(MainActivity.this, this,contact_id,contact_name,contact_email,contact_phone);
        recyclerView.setAdapter(contactAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            recreate();
        }
    }
    void storeData(){
        Cursor cursor = databaseHelper.readAllData();
        if (cursor.getCount()== 0){
            Toast.makeText(this, "Sem Dados", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                contact_id.add(cursor.getString(0));
                contact_name.add(cursor.getString(1));
                contact_email.add(cursor.getString(2));
                contact_phone.add(cursor.getString(3));
            }
        }
    }
}
