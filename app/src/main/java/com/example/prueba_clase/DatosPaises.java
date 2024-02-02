package com.example.prueba_clase;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.prueba_clase.db.DBHelper;

public class DatosPaises extends AppCompatActivity {

    TextView tvD;
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datospaises);
        Toolbar tb = findViewById(R.id.toolbar_2);
        setSupportActionBar(tb);
        tb.inflateMenu(R.menu.menu_main);

        tvD=findViewById(R.id.textViewD);
        Intent intent1 = getIntent();
        String info =intent1.getStringExtra("miInfo");

        // Mostrar la descripción en el TextView
        tvD.setText(info);


        ImageButton btV=findViewById(R.id.inicioD);
        btV.setOnClickListener(v -> {
            Intent intent = new Intent(DatosPaises.this, MainActivity.class);
            startActivity(intent);
        });



        }
    @Override
    public boolean onCreateOptionsMenu(Menu m1) {
        getMenuInflater().inflate(R.menu.menu_main, m1);
        return super.onCreateOptionsMenu(m1);
        }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Identifica el ítem del menú seleccionado por su ID

        if (item.getItemId() == R.id.action_CrearDb) {

            DBHelper dbHelper = new DBHelper(DatosPaises.this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            if (db != null) {
                dbHelper.onUpgrade(db, 1, 2);
                Toast.makeText(DatosPaises.this, "Base de datos creada", Toast.LENGTH_LONG).show();
            }
            return true;
        } else if(item.getItemId() == R.id.action_eliminarPais ) {
            Intent intent = new Intent(DatosPaises.this,EliminarPais.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    }



