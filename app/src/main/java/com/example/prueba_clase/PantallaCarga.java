package com.example.prueba_clase;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class PantallaCarga extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_carga);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Después del tiempo de espera, iniciar la actividad principal
                Intent intent = new Intent(PantallaCarga.this, MainActivity.class);
                startActivity(intent);

                // Cierra la actividad actual para que no se pueda volver a la pantalla de espera
                finish();
            }
        }, 5000);





    }

}
