package com.marcos.android1_project2;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;


public class UploadActivity extends AppCompatActivity {

    private  ContatoDAO contatoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        contatoDAO = new ContatoDAO(this);

        Contato contato = new Contato(1,"teste","mail@mail.com", "123");

        Contato contato1 = new Contato(3,"Kess", "mail@mail.com", "123");
        int rowsAffected = contatoDAO.updateContato(contato1);
        System.out.println("Linha afetadas: " + rowsAffected);

        int lines = contatoDAO.deleteContato(1);
        System.out.println("Linhas afetadas " + lines);

        List<Contato> contatoList = contatoDAO.getAllContato();

        for (Contato c: contatoList) {
            System.out.println("ID: "+ c.getId());
            System.out.println("Nome: "+ c.getName());
            System.out.println("Email: "+ c.getEmail());
            System.out.println("Telefone: "+ c.getPhone());
        }
    }
}