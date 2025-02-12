package com.dam.proyectocalculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText et1;
    private EditText et2;
    private TextView tv1;
    /*private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;*/
    private Spinner sp1;

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

        et1 = (EditText) findViewById(R.id.txt_number1);
        et2 = (EditText) findViewById(R.id.txt_number2);
        tv1 = (TextView) findViewById(R.id.txt_resultado);

        /*rb1 = (RadioButton) findViewById(R.id.rb_sumar);
        rb2 = (RadioButton) findViewById(R.id.rb_restar);
        rb3 = (RadioButton) findViewById(R.id.rb_multiplicar);
        rb4 = (RadioButton) findViewById(R.id.rb_dividir);*/

        sp1 = findViewById(R.id.spinner);

        String[] opciones = {"sumar","restar","multiplicar","dividir"};

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, opciones);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item_estilo, opciones);

        sp1.setAdapter(adapter);
    }

    public void sumar(View view){
        String valor1 = et1.getText().toString();
        String valor2 = et2.getText().toString();

        int num1,num2;

        try {
            num1 = Integer.parseInt(valor1);
        }catch(NumberFormatException e){
            num1=0;
        }
        try {
            num2 = Integer.parseInt(valor2);
        }catch(NumberFormatException e){
            num2=0;
        }

        int suma = num1 + num2;

        String result = String.valueOf(suma);
        tv1.setText(result);
    }

    public void calcular(View view) {
        String valor1 = et1.getText().toString();
        String valor2 = et2.getText().toString();

        int valor1_int, valor2_int;

        try {
            valor1_int = Integer.parseInt(valor1);
        } catch (NumberFormatException e) {
            valor1_int = 0;
        }

        try {
            valor2_int = Integer.parseInt(valor2);
        } catch (NumberFormatException e) {
            valor2_int = 0;
        }

        String seleccion = sp1.getSelectedItem().toString();

        if (seleccion.equals("sumar")) {
            int suma = valor1_int + valor2_int;
            String resultado = String.valueOf(suma);
            tv1.setText(resultado);
        } else if (seleccion.equals("restar")) {
            int resta = valor1_int - valor2_int;
            String resultado = String.valueOf(resta);
            tv1.setText(resultado);
        } else if (seleccion.equals("multiplicar")) {
            int mult = valor1_int * valor2_int;
            String resultado = String.valueOf(mult);
            tv1.setText(resultado);
        } else if (seleccion.equals("dividir")) {
            int divis;
            String resultado;
            if (valor2_int != 0){
                divis = valor1_int/valor2_int;
                resultado = String.valueOf(divis);
                tv1.setText(resultado);
            }else{
                Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
            }

        }
    }

    /*public void calcular(View view) {
        String valor1 = et1.getText().toString();
        String valor2 = et2.getText().toString();

        int valor1_int, valor2_int;

        try {
            valor1_int = Integer.parseInt(valor1);
        }catch (NumberFormatException e){
            valor1_int = 0;
        }

        try {
            valor2_int = Integer.parseInt(valor2);
        }catch (NumberFormatException e){
            valor2_int = 0;
        }

        if(rb1.isChecked()){
            int suma = valor1_int + valor2_int;
            String resultado = String.valueOf(suma);
            tv1.setText(resultado);
        }else if(rb2.isChecked()){
            int resta = valor1_int - valor2_int;
            String resultado = String.valueOf(resta);
            tv1.setText(resultado);
        }else if(rb3.isChecked()){
            int mult = valor1_int * valor2_int;
            String resultado = String.valueOf(mult);
            tv1.setText(resultado);
        }else if(rb4.isChecked()){
            int divis;
            String resultado;
            if (valor1_int != 0 && valor2_int != 0){
                divis = valor1_int/valor2_int;
                resultado = String.valueOf(divis);
            }else{
                resultado = "ERROR";
            }
            tv1.setText(resultado);
        }

    }*/
}