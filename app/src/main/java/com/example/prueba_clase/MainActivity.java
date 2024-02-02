package com.example.prueba_clase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.prueba_clase.db.DBHelper;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button botonCambio = findViewById(R.id.botonCambio);
        botonCambio.setOnClickListener(v -> {
            Intent intent = new Intent(this,MainActivity2.class);
            startActivity(intent);
        });

        Button botonAdd = findViewById(R.id.BotonAdd);
        botonAdd.setOnClickListener(v -> {
            Intent intent = new Intent(this,Formulario.class);
            startActivity(intent);
        });

    Toolbar tb = findViewById(R.id.toolbar_1);
        setSupportActionBar(tb);
        tb.inflateMenu(R.menu.menu_main);

    }//onCreate

    @Override
    public boolean onCreateOptionsMenu(Menu m1) {
        getMenuInflater().inflate(R.menu.menu_main, m1);
        return super.onCreateOptionsMenu(m1);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Identifica el ítem del menú seleccionado por su ID

        if (item.getItemId() == R.id.action_CrearDb) {

            DBHelper dbHelper = new DBHelper(MainActivity.this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            if (db != null) {
                dbHelper.onUpgrade(db, 1, 2);
                Toast.makeText(MainActivity.this, "Base de datos creada", Toast.LENGTH_LONG).show();
            }
            return true;
        } else if(item.getItemId() == R.id.action_eliminarPais ) {
            Intent intent = new Intent(MainActivity.this,EliminarPais.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}