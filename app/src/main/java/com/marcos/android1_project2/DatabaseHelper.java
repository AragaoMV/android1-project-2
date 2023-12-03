package com.marcos.android1_project2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME= "ContatoDB";
    private static final String TABLE_NAME= "ListaContato";
    private final static   String KEY_ID = "id";
    private static final  String KEY_NAME = "name";
    private static final  String KEY_EMAIL = "email";
    private static final  String KEY_PHONE = "phone";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableSQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
                KEY_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                KEY_NAME + " TEXT, "+
                KEY_EMAIL + " TEXT, "+
                KEY_PHONE + " TEXT " + ")";

        db.execSQL(createTableSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long addContato(Contato contato){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contato.getName());
        values.put(KEY_EMAIL, contato.getEmail());
        values.put(KEY_PHONE, contato.getPhone());

        long id = database.insert(TABLE_NAME, null,values);
        database.close();
        return id;
    }
    public List<Contato> getAllContato(){
        List<Contato> contatoList = new ArrayList<>();
        String SQL = "SELECT * FROM " +  TABLE_NAME;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SQL, null);

        if(cursor.moveToFirst()){
            do {
                Contato contato = new Contato(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                );
                contatoList.add(contato);
            }while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();
        return contatoList;
    }
    public int updateContato(Contato contato){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME,contato.getName());
        contentValues.put(KEY_PHONE,contato.getPhone());
        contentValues.put(KEY_EMAIL,contato.getEmail());

        int rowsAffected = database.update(TABLE_NAME, contentValues, KEY_ID+ " = ? ",
                new String[] {String.valueOf(contato.getId())});

        database.close();
        return rowsAffected;
    }

    public int deleteContato(int contatoId){
        SQLiteDatabase database = this.getWritableDatabase();
        int rowsAffected = database.delete(TABLE_NAME, KEY_ID + " = ?", new String[]{String.valueOf(contatoId)});
        database.close();
        return rowsAffected;
    }
}
