package com.marcos.android1_project2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME= "Contatos.db";
    private static final int DATABASE_VERSION= 1;
    private static final String TABLE_NAME= "lista_contato";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "mail";
    private static final String COLUMN_PHONE = "phone";

     DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_EMAIL + " TEXT, "+
                COLUMN_PHONE + " INTEGER);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    void addContact(String name, String email, int phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME,name);
        cv.put(COLUMN_EMAIL,email);
        cv.put(COLUMN_PHONE,phone);

       long result = db.insert(TABLE_NAME, null, cv);
       if (result == -1){
           Toast.makeText(context, "Falha ao Criar", Toast.LENGTH_SHORT).show();
       }else {
           Toast.makeText(context, "Contato Criado", Toast.LENGTH_SHORT).show();
       }
    }
    Cursor readAllData(){
        String query = "SELECT * FROM "+ TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null){
           cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    void updateData(String row_id, String name,String email,String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_EMAIL, email);
        cv.put(COLUMN_PHONE, phone);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Falha ao atualizar", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Atualizado com Sucesso!", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteData(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Falha ao Deletar.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Deletado com Sucesso.", Toast.LENGTH_SHORT).show();
        }
    }
}
