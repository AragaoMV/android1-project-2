package com.marcos.android1_project2;

import android.content.Context;

import java.util.List;

public class ContatoDAO {
    private DatabaseHelper databaseHelper;
    public ContatoDAO(Context context){
        databaseHelper = new DatabaseHelper(context);
    }

    public long addContato(Contato contato){
        return databaseHelper.addContato(contato);
    }

    public List<Contato> getAllContato(){
        return databaseHelper.getAllContato();
    }

    public int updateContato(Contato contato){
        return databaseHelper.updateContato(contato);
    }

    public int deleteContato(int contatoId){
        return databaseHelper.deleteContato(contatoId);
    }
}
