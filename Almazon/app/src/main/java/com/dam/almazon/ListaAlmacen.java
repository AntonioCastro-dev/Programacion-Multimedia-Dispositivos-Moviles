package com.dam.almazon;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListaAlmacen extends AppCompatActivity {

    private ListView listViewProductos;
    private Button btnAgregarProducto;
    private ArrayList<Producto> productos;
    private ProductoAdapter productoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_almacen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listViewProductos = findViewById(R.id.listViewProductos);
        btnAgregarProducto = findViewById(R.id.btnAgregarProducto);

        // Lista de productos (se puede inicializar con algunos productos)
        productos = new ArrayList<>();

        // Agregar productos a la lista
        productos.add(new Producto("Laptop", 5, "Laptop de 15.6 pulgadas, procesador Intel i7, 16GB RAM."));
        productos.add(new Producto("Teléfono Móvil", 10, "Teléfono Android con pantalla de 6.5 pulgadas y 128GB de almacenamiento."));
        productos.add(new Producto("Auriculares Inalámbricos", 8, "Auriculares Bluetooth con cancelación de ruido."));
        productos.add(new Producto("Smartwatch", 12, "Reloj inteligente con monitor de ritmo cardíaco y GPS."));
        productos.add(new Producto("Tablet", 6, "Tablet de 10 pulgadas con 64GB de almacenamiento y soporte para lápiz óptico."));
        productos.add(new Producto("Cámara Fotográfica", 4, "Cámara digital de 24MP con lente intercambiable."));
        productos.add(new Producto("Impresora", 7, "Impresora multifuncional con conexión WiFi y escáner."));
        productos.add(new Producto("Teclado Mecánico", 9, "Teclado mecánico con retroiluminación RGB."));
        productos.add(new Producto("Monitor", 3, "Monitor 4K de 27 pulgadas con tecnología HDR."));
        productos.add(new Producto("Altavoz Bluetooth", 11, "Altavoz inalámbrico portátil con sonido estéreo."));
        productos.add(new Producto("Router WiFi", 5, "Router inalámbrico con soporte para 5GHz y alta velocidad."));
        productos.add(new Producto("Disco Duro Externo", 14, "Disco duro externo de 2TB con USB 3.0."));
        productos.add(new Producto("Cargador Rápido", 20, "Cargador rápido de 18W compatible con varios dispositivos."));
        productos.add(new Producto("Memoria USB", 30, "Memoria USB de 64GB con protección contra agua y golpes."));
        productos.add(new Producto("Mouse Inalámbrico", 25, "Mouse inalámbrico ergonómico con alta precisión."));
        productos.add(new Producto("Silla Gamer", 5, "Silla ergonómica con soporte lumbar y reposabrazos ajustable."));
        productos.add(new Producto("Microondas", 7, "Microondas de 20L con función de descongelado y temporizador."));
        productos.add(new Producto("Lavadora", 3, "Lavadora de carga frontal con capacidad de 8kg y sistema de ahorro de agua."));
        productos.add(new Producto("Cafetera", 12, "Cafetera de cápsulas compatible con varias marcas."));
        productos.add(new Producto("Horno Eléctrico", 4, "Horno eléctrico de convección de 35L con control de temperatura."));
        productos.add(new Producto("Plancha a Vapor", 10, "Plancha de vapor con suela antiadherente y depósito de agua de 300ml."));
        productos.add(new Producto("Aspiradora", 6, "Aspiradora inalámbrica con autonomía de 40 minutos y filtro HEPA."));
        productos.add(new Producto("Smart TV", 8, "Televisor inteligente de 55 pulgadas con resolución 4K UHD y Android TV."));
        productos.add(new Producto("Ventilador", 15, "Ventilador de torre con mando a distancia y temporizador."));
        productos.add(new Producto("Termo Eléctrico", 4, "Termo eléctrico de 100L con control de temperatura digital."));
        productos.add(new Producto("Calefactor", 18, "Calefactor cerámico portátil con función de oscilación."));
        productos.add(new Producto("Afeitadora Eléctrica", 20, "Afeitadora eléctrica impermeable con 5 modos de velocidad."));
        productos.add(new Producto("Licuadora", 7, "Licuadora con vaso de cristal de 1.5L y 3 velocidades."));
        productos.add(new Producto("Parrilla Eléctrica", 5, "Parrilla eléctrica antiadherente con bandeja para la grasa."));
        productos.add(new Producto("Proyector", 9, "Proyector portátil con resolución Full HD y conectividad HDMI."));

        // Crear y configurar el adaptador
        productoAdapter = new ProductoAdapter(this, productos);
        listViewProductos.setAdapter(productoAdapter);

        // Click en un producto de la lista
        listViewProductos.setOnItemClickListener((parent, view, position, id) -> {
            Producto productoSeleccionado = productos.get(position);
            Intent intent = new Intent(ListaAlmacen.this, DetallesProducto.class);
            intent.putExtra("nombre", productoSeleccionado.getNombre());
            intent.putExtra("unidades", productoSeleccionado.getUnidades());
            intent.putExtra("descripcion", productoSeleccionado.getDescripcion());
            startActivity(intent);
        });

        // Botón para agregar un producto
        btnAgregarProducto.setOnClickListener(v -> {
            Intent intent = new Intent(ListaAlmacen.this, AgregarProducto.class);
            startActivityForResult(intent, 1); // 1 para identificar la solicitud
        });
    }

    // Método para recibir el nuevo producto desde la actividad de agregar producto
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String nombre = data.getStringExtra("nombre");
            int unidades = data.getIntExtra("unidades", 0);
            String descripcion = data.getStringExtra("descripcion");

            Producto nuevoProducto = new Producto(nombre, unidades, descripcion);
            productos.add(nuevoProducto);
            productoAdapter.notifyDataSetChanged();  // Actualiza la lista
        }
    }

}