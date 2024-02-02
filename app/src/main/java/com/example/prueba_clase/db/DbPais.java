package com.example.prueba_clase.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.prueba_clase.ListElement;


import androidx.annotation.Nullable;

import com.example.prueba_clase.ListElement;
import com.example.prueba_clase.Pais;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DbPais extends DBHelper {
    Context context;
    public DbPais(@Nullable Context context) {
        super(context);
        this.context=context;
    }

/*public String obtenerInfo(int position) {
    DBHelper dbHelper = new DBHelper(context);
    SQLiteDatabase db = dbHelper.getWritableDatabase();
    Cursor cursorPaises = null;
    String info=null;
    cursorPaises = db.rawQuery("SELECT info FROM " + TABLE_NAME, null);
    try{
        if (cursorPaises.moveToFirst()) {
            do {
                info = cursorPaises.getString(position);
                return info;
            } while (cursorPaises.moveToNext());
    }
    }catch(SQLException e){
        e.printStackTrace();

    }finally{
            cursorPaises.close();
            db.close();
        }

    return info;
}*/
public String obtenerInfo(int itemId) {
    DBHelper dbHelper = new DBHelper(context);
    SQLiteDatabase db = dbHelper.getWritableDatabase();
    Cursor cursorPaises = null;
    String info = null;

    try {
        // Ajusta la consulta para seleccionar el elemento específico según su identificador único
        cursorPaises = db.rawQuery("SELECT info FROM " + TABLE_NAME + " WHERE id = ?", new String[]{String.valueOf(itemId)});

        if (cursorPaises.moveToFirst()) {
            // Verifica si la columna "info" existe en el cursor
            int columnIndex = cursorPaises.getColumnIndex("info");
            if (columnIndex != -1) {
                // La columna "info" existe, obtén el valor
                info = cursorPaises.getString(columnIndex);
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

    return info;
}



    public List<ListElement> obtenerPaises(){

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        List<ListElement> listaPaises = new ArrayList<>();

        Cursor cursorPaises = null;
        cursorPaises=db.rawQuery("SELECT * FROM "+ TABLE_NAME,null);
        if (cursorPaises.moveToFirst()){
            do {
                ListElement listElement = new ListElement(cursorPaises.getString(1),cursorPaises.getString(2),cursorPaises.getString(3));


                listaPaises.add(listElement);

            }while(cursorPaises.moveToNext());
        }//if
        cursorPaises.close();


        return listaPaises ;
    }
}