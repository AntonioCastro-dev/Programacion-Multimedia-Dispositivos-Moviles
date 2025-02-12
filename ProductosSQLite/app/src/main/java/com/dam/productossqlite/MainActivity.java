package com.dam.productossqlite;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText et_codigo;
    private EditText et_descripcion;
    private EditText et_precio;
    private Button btnVerLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        et_codigo = findViewById(R.id.txt_codigo);
        et_descripcion = findViewById(R.id.txt_descripcion);
        et_precio = findViewById(R.id.txt_precio);
        btnVerLista = findViewById(R.id.btn_lista);
    }

    public void registrar (View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase baseDeDatos = admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();
        String descripcion = et_descripcion.getText().toString();
        String precio = et_precio.getText().toString();

        if(!codigo.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("codigo", codigo);
            registro.put("descripcion", descripcion);
            registro.put("precio", precio);

            baseDeDatos.insert("articulos", null, registro);

            et_codigo.setText("");
            et_descripcion.setText("");
            et_precio.setText("");

            Toast.makeText(this, "Producto registrado", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Todos los campos deben estar rellenos", Toast.LENGTH_SHORT).show();
        }
        baseDeDatos.close();
    }

    public void buscar (View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase baseDeDatos = admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();

        if(!codigo.isEmpty()){
            Cursor fila = baseDeDatos.rawQuery("select descripcion, precio from articulos where codigo =" + codigo, null);

            if(fila.moveToFirst()){
                et_descripcion.setText(fila.getString(0));
                et_precio.setText(fila.getString(1));
            }else{
                Toast.makeText(this, "No existe ningun producto con ese codigo", Toast.LENGTH_SHORT).show();
            }
            fila.close();
        }else{
            Toast.makeText(this, "Tienes que ingresar un codigo", Toast.LENGTH_SHORT).show();
        }
        baseDeDatos.close();
    }

    public void eliminar (View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase baseDeDatos = admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();

        if(!codigo.isEmpty()){
            int cantidad = baseDeDatos.delete("articulos","codigo="+codigo,null);

            et_codigo.setText("");
            et_descripcion.setText("");
            et_precio.setText("");

            if(cantidad==1){
                Toast.makeText(this, "Articulo eliminado",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Articulo no existe", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Introduzca codigo del articulo", Toast.LENGTH_SHORT).show();
        }
        baseDeDatos.close();
    }

    public void modificar(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion",null,1);
        SQLiteDatabase baseDeDatos = admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();
        String descripcion = et_descripcion.getText().toString();
        String precio = et_precio.getText().toString();

        if(!codigo.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("codigo", codigo);
            registro.put("descripcion", descripcion);
            registro.put("precio", precio);

            int cantidad = baseDeDatos.update("articulos",registro,"codigo ="+codigo, null);

            if(cantidad==1){
                Toast.makeText(this, "Articulo modificado",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "No se ha podido modificar",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this,"Todos los campos deben estar rellenos",Toast.LENGTH_SHORT).show();
        }
        baseDeDatos.close();
    }

    public void verLista(View view){
        Intent intent = new Intent(this, ListaProductosActivity.class);
        startActivity(intent);
    }
}