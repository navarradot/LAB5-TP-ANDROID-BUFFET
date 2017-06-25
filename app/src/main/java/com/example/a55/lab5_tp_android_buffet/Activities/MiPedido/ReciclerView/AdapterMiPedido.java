package com.example.a55.lab5_tp_android_buffet.Activities.MiPedido.ReciclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a55.lab5_tp_android_buffet.Activities.Menu.RecyclerView.AdapterMenu;
import com.example.a55.lab5_tp_android_buffet.Activities.Menu.RecyclerView.ViewHolderMenu;
import com.example.a55.lab5_tp_android_buffet.Activities.Menu.View.MenuView;
import com.example.a55.lab5_tp_android_buffet.Activities.MiPedido.View.MiPedidoView;
import com.example.a55.lab5_tp_android_buffet.Http.JsonParser;
import com.example.a55.lab5_tp_android_buffet.Http.ThreadConnection;
import com.example.a55.lab5_tp_android_buffet.POJOS.Pedido;
import com.example.a55.lab5_tp_android_buffet.POJOS.Producto;
import com.example.a55.lab5_tp_android_buffet.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by A55 on 18/05/2017.
 */

public class AdapterMiPedido extends RecyclerView.Adapter<ViewHolderMiPedido> {

    private MiPedidoView miPedidoView;

    public AdapterMiPedido(MiPedidoView miPedidoView)
    {
        this.miPedidoView = miPedidoView;
    }

    @Override
    public ViewHolderMiPedido onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_mi_pedido, parent, false);
        ViewHolderMiPedido myViewHolder = new ViewHolderMiPedido(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolderMiPedido holder, int position) {

        holder.miPedidoView = this.miPedidoView;

        // Trae el productoPedido de la lista de pedidos en la posicion en la que esta el holder
        Producto p = Pedido.listaPedidos.get(position);

        holder.tvNombreProducto.setText(p.nombre);
        holder.tvPrecioProductoNumero.setText(p.precio.toString());

        if (p.imagenBytes == null) {

            //this.descargarImagen(position);

            try {
                holder.ivProducto.setImageResource(R.mipmap.utnfra);
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("EOnBindViewH", "" + e.getMessage());
            }
        }
        else {
            // Bindea la imagen del producto (imagenBytes) con el respectivo ImageView del RecivlerView
            try {
                Bitmap bitmap = BitmapFactory.decodeByteArray(p.imagenBytes, 0, p.imagenBytes.length);
                holder.ivProducto.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("EOnBindViewH", "" + e.getMessage());
            }
        }

        //Guarda la posicion en el holder
        holder.posicion = position;
    }

    @Override
    public int getItemCount() {
        //Log.d("ATENCION: ", "ENTRO AL getItemCount() "+ count++);
        return Pedido.obtenerCantidadItemsPedido();
    }
}
