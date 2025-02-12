package com.dam.almazon;

import android.content.Intent;
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

public class AgregarProducto extends AppCompatActivity {

    private EditText etNombre;
    private EditText etUnidades;
    private EditText etDescripcion;
    private Button btnAgregar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_agregar_producto);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etNombre = findViewById(R.id.etNombre);
        etUnidades = findViewById(R.id.etUnidades);
        etDescripcion = findViewById(R.id.etDescripcion);
        btnAgregar = findViewById(R.id.btnAgregarProducto);

        // Configurar el botón de agregar producto para enviar los datos a la actividad anterior
        btnAgregar.setOnClickListener(v -> {
            String nombre = etNombre.getText().toString();
            String unidadesStr = etUnidades.getText().toString();
            String descripcion = etDescripcion.getText().toString();

            if (nombre.isEmpty() || unidadesStr.isEmpty() || descripcion.isEmpty()) {
                Toast.makeText(AgregarProducto.this, "Todos los campos deben estar completos", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    int unidades = Integer.parseInt(unidadesStr);
                    // Si es numérico, proceder con la lógica de agregar producto
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("nombre", nombre);
                    resultIntent.putExtra("unidades", unidades);
                    resultIntent.putExtra("descripcion", descripcion);
                    // Enviar los datos a la actividad anterior
                    setResult(RESULT_OK, resultIntent);
                    finish();
                } catch (NumberFormatException e) {
                    // Si no es un número válido, mostrar un mensaje
                    Toast.makeText(AgregarProducto.this, "El campo de unidades debe ser numérico", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    // Método para volver a la actividad anterior
    public void volver(View view) {
        finish();
    }
}