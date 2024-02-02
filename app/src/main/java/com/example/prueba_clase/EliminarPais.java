package com.example.prueba_clase;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prueba_clase.db.DBHelper;


public class EliminarPais extends AppCompatActivity {
    EditText et = findViewById(R.id.editTextPaisEliminar);
    Button eliminarPais = findViewById(R.id.buttonEliminar);
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eliminarpais);

        eliminarPais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHelper dbHelper = new DBHelper(EliminarPais.this);
                dbHelper.eliminaPais(et.getText().toString(),EliminarPais.this);
            }
        });



    }
}
