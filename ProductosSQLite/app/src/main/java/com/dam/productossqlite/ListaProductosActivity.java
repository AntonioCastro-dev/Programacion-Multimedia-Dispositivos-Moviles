package com.dam.productossqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListaProductosActivity extends AppCompatActivity {

    private ArrayList<String> productos;
    private ListView listaProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_productos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //me faltaba esto xd
        productos = new ArrayList<>();

        listaProductos = findViewById(R.id.listaProds);

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase baseDeDatos = admin.getReadableDatabase();

        try {

            Cursor prodNom = baseDeDatos.rawQuery("select descripcion from articulos", null);

            if(prodNom.moveToFirst()){
                do {
                    productos.add(prodNom.getString(0));
                } while(prodNom.moveToNext()); //esto tampoco lo tenia
            } else {
                Toast.makeText(this, "No se encontraron productos", Toast.LENGTH_SHORT).show();
            }
            prodNom.close();

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.estilo_lista, productos);
            listaProductos.setAdapter(adapter);
        }catch (Exception e){
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
        baseDeDatos.close();
    }

    public void volver(View view) {
        finish();
    }
}