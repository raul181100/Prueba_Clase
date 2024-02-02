package com.example.prueba_clase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Toast;
import com.example.prueba_clase.db.DBHelper;
import com.example.prueba_clase.db.DbPais;
import java.util.List;



public class MainActivity2 extends AppCompatActivity {

    List<ListElement> elements;
    private DbPais dbPais;
    private SQLiteDatabase database;

    ImageButton img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        img = findViewById(R.id.home_inicio2);
        img.setOnClickListener(view -> {

            Intent intent = new Intent(MainActivity2.this, MainActivity.class);
            startActivity(intent);
        });


        Toolbar tb = findViewById(R.id.toolbar_1);
        setSupportActionBar(tb);
        tb.inflateMenu(R.menu.menu_main);

        init();

    }//onCreate

    public void init() {
        dbPais = new DbPais(MainActivity2.this);
        elements = dbPais.obtenerPaises();

        ListAdapter adapter = new ListAdapter(elements, this);

        RecyclerView recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String info = dbPais.obtenerInfo(position + 1);

                Intent intent = new Intent(MainActivity2.this, DatosPaises.class);
                intent.putExtra("miInfo", info);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Identifica el ítem del menú seleccionado por su ID

        if (item.getItemId() == R.id.action_CrearDb) {

            DBHelper dbHelper = new DBHelper(MainActivity2.this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            if (db != null) {
                dbHelper.onUpgrade(db, 1, 2);
                Toast.makeText(MainActivity2.this, "Base de datos creada", Toast.LENGTH_LONG).show();
            }
            return true;
        } else if(item.getItemId() == R.id.action_eliminarPais ) {
            Intent intent = new Intent(MainActivity2.this,EliminarPais.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}//class
