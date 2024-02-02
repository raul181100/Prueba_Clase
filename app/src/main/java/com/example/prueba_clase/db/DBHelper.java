package com.example.prueba_clase.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.prueba_clase.MainActivity2;
import com.example.prueba_clase.db.DbPais;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME ="mibase.db";
    public static final String TABLE_NAME ="t_paises";


    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "ciudad TEXT NOT NULL," +
                "pais TEXT NOT NULL," +
                "anio TEXT NOT NULL," +
                "info TEXT NOT NULL)");
    }
    public int obtenerId(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursorPaises = null;
        int id = 0;

        try {
            // Ajusta la consulta para seleccionar el elemento específico según su identificador único
            cursorPaises = db.rawQuery("SELECT id FROM " + TABLE_NAME + " WHERE id = ?", new String[]{String.valueOf(obtenerId())});

            if (cursorPaises.moveToFirst()) {
                // Verifica si la columna "info" existe en el cursor
                int columnIndex = cursorPaises.getColumnIndex("id");
                if (columnIndex != -1) {
                    // La columna "info" existe, obtén el valor
                    id = cursorPaises.getInt(columnIndex);
                } else {
                    // La columna "info" no existe en el cursor, manejar esto según tus necesidades
                    // Puedes imprimir un mensaje de error o realizar alguna acción apropiada
                    // Por ejemplo, lanzar una excepción o asignar un valor predeterminado a 'info'
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (cursorPaises != null) {
                cursorPaises.close();
            }
            db.close();
        }

        return id;
    }
    public long insertaPais(String ciudad, String pais,String anio,String info){


        SQLiteDatabase db =getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ciudad",ciudad);
        values.put("pais",pais);
        values.put("anio",anio);
        values.put("info",info);

        long id = db.insert(TABLE_NAME,null,values);
        db.close();

        return id;
    }
    public void eliminaPais(String Npais,Context context){

        SQLiteDatabase db = getWritableDatabase();
        String tableName = TABLE_NAME;
        String whereClause = "nombre = ?";  // Suponiendo que tienes una columna "_id" como clave primaria.
        String[] whereArgs = new String[] {Npais};

// Utiliza el método delete() para eliminar el registro
        int filasEliminadas = db.delete(tableName, whereClause, whereArgs);

        if (filasEliminadas > 0) {
            Toast.makeText(context,"Registro eliminado",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context,"Registro no eliminado",Toast.LENGTH_LONG).show();
        }
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

}
