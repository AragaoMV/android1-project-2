package com.marcos.android1_project2;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class UploadActivity extends AppCompatActivity {

    Button btnSaveData;
    EditText addName, addPhone, addMail;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        btnSaveData = findViewById(R.id.btnSaveData);
        addName = findViewById(R.id.addName);
        addPhone = findViewById(R.id.addPhone);
        addMail = findViewById(R.id.addMail);

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                        }
                    }
                }
        );


        btnSaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveData();
               // Intent intent = new Intent(UploadActivity.this, MainActivity.class);
             //   startActivity(intent);
            }
        });

    }

    public void SaveData(){
        String name = addName.getText().toString();
        String mail = addMail.getText().toString();
        String phone = addPhone.getText().toString();

        DataClass dataClass = new DataClass(name,mail,phone);

        FirebaseDatabase.getInstance().getReference("Contatos").child(name).setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(UploadActivity.this,"Salvo com Sucesso",Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UploadActivity.this,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}