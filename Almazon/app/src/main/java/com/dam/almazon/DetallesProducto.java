package com.dam.almazon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DetallesProducto extends AppCompatActivity {

    private TextView tvNombre, tvUnidades, tvDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detalles_producto);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar las vistas
        tvNombre = findViewById(R.id.tvNombreProducto);
        tvUnidades = findViewById(R.id.tvUnidadesProducto);
        tvDescripcion = findViewById(R.id.tvDescripcionProducto);

        // Obtener los datos del producto desde el intent
        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre");
        int unidades = intent.getIntExtra("unidades", 0);
        String descripcion = intent.getStringExtra("descripcion");

        tvNombre.setText(nombre);
        tvUnidades.setText(String.valueOf(unidades));
        tvDescripcion.setText(descripcion);
    }
    // Método para volver a la actividad anterior
    public void volver(View view) {
        finish();
    }
}