package com.example.prueba_clase;

import android.content.ContentValues;
import android.content.Intent;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.prueba_clase.db.DBHelper;
import com.example.prueba_clase.db.DbPais;

public class Formulario extends AppCompatActivity {


    EditText ciudad,pais,anio,info;
    Button btnGuardar , btnCrear;

    DbPais db;
    ImageButton img;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario);

        db = new DbPais(this);
        ciudad=findViewById(R.id.editTextCiudad);
        pais=findViewById(R.id.editTextPais);
        anio=findViewById(R.id.editTextAño);
        info=findViewById(R.id.editTextDatos);
        img= findViewById(R.id.home_inicio3);
        btnCrear = findViewById(R.id.botonCrearBd);
        btnGuardar=findViewById(R.id.botonFormulario);

        img.setOnClickListener(view -> {
            Intent intent = new Intent(Formulario.this, MainActivity.class);
            startActivity(intent);
        });

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper  = new DBHelper(Formulario.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                if(db != null){
                    dbHelper.onUpgrade(db,1,2);
                    Toast.makeText(Formulario.this,"Base de datos creada",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(Formulario.this,"Error al crear la base de datos",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHelper paisdb = new DBHelper(Formulario.this);
                long id =paisdb.insertaPais(ciudad.getText().toString(),pais.getText().toString(),anio.getText().toString(),info.getText().toString());

                if(id>0){
                    Toast.makeText(Formulario.this,"Registro Guardado",Toast.LENGTH_LONG).show();
                    limpiar();
                }else{
                    Toast.makeText(Formulario.this,"Error al guardar Registro",Toast.LENGTH_LONG).show();
                }
            }
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
    private void limpiar(){
        ciudad.setText("");
        pais.setText("");
        anio.setText("");
        info.setText("");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Identifica el ítem del menú seleccionado por su ID

        if (item.getItemId() == R.id.action_CrearDb) {

            DBHelper dbHelper = new DBHelper(Formulario.this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            if (db != null) {
                dbHelper.onUpgrade(db, 1, 2);
                Toast.makeText(Formulario.this, "Base de datos creada", Toast.LENGTH_LONG).show();
            }
            return true;
        } else if(item.getItemId() == R.id.action_eliminarPais ) {
            Intent intent = new Intent(Formulario.this,EliminarPais.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

