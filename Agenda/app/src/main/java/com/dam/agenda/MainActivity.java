package com.dam.agenda;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // Declarar los campos de entrada
    private EditText etNombre, etDatos;

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

        // Inicializar los campos de entrada
        etNombre = findViewById(R.id.txtNombre);
        etDatos = findViewById(R.id.txtDatos);
    }

    public void guardar(View view) {
        // Cargar las variables nombre y datos con los datos de los campos de entrada
        String nombre = etNombre.getText().toString();
        String datos = etDatos.getText().toString();

        // Guardar los datos en SharedPreferences
        SharedPreferences preferencias = getSharedPreferences("agenda", MODE_PRIVATE);
        SharedPreferences.Editor obj_editor = preferencias.edit();
        obj_editor.putString(nombre,datos);
        obj_editor.commit();

        // Mostrar un mensaje de confirmación
        Toast.makeText(this, "El contacto ha sido guardado", Toast.LENGTH_SHORT).show();

        // Limpiar los campos de entrada
        etNombre.setText("");
        etDatos.setText("");
    }

    public void buscar(View view) {
        // Recuperar el nombre del contacto
        String nombre = etNombre.getText().toString();

        // Recuperar los datos de SharedPreferences
        SharedPreferences preferencias = getSharedPreferences("agenda", MODE_PRIVATE);
        String datos = preferencias.getString(nombre, "");

        // Mostrar los datos en el campo de entrada
        if(datos.length() == 0){
            // Mostrar un mensaje de error
            Toast.makeText(this, "No se encontró ningún registro", Toast.LENGTH_SHORT).show();
        }else{
            etDatos.setText(datos);
        }
    }
}