package com.dam.almazon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void registrarUsuario(View view) {
        EditText usuarioEditText = findViewById(R.id.usuario2);
        EditText confUsuarioEditText = findViewById(R.id.usuario3);
        EditText passEditText = findViewById(R.id.pass2);
        EditText confPassEditText = findViewById(R.id.pass3);

        String usuario = usuarioEditText.getText().toString();
        String confUsuario = confUsuarioEditText.getText().toString();
        String password = passEditText.getText().toString();
        String confPassword = confPassEditText.getText().toString();

        if (!usuario.equals(confUsuario)) {
            Toast.makeText(this, "Los usuarios no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confPassword)) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }

        if (guardarUsuario(usuario, password)) {
            Toast.makeText(this, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, InicioSesion.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean guardarUsuario(String usuario, String password) {
        try {
            FileWriter fileWriter = new FileWriter(getFilesDir() + "/usuarios.txt", true);  // 'true' para agregar al archivo
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            String registro = usuario + "," + password;
            bufferedWriter.write(registro);
            bufferedWriter.newLine();  // Agregar una nueva línea
            bufferedWriter.close();  // Cerrar BufferedWriter
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void volver(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}