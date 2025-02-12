package com.dam.almazon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ProductoAdapter extends ArrayAdapter<Producto> {
    private Context context;
    private List<Producto> productos;
    // Constructor
    public ProductoAdapter(Context context, List<Producto> productos) {
        super(context, 0, productos);
        this.context = context;
        this.productos = productos;
    }
    // Método para actualizar la lista de productos
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_producto, parent, false);
        }

        Producto producto = productos.get(position);

        TextView nombreProducto = convertView.findViewById(R.id.nombreProducto);
        TextView unidadesProducto = convertView.findViewById(R.id.unidadesProducto);

        nombreProducto.setText(producto.getNombre());
        unidadesProducto.setText(String.valueOf(producto.getUnidades()));

        return convertView;
    }
}