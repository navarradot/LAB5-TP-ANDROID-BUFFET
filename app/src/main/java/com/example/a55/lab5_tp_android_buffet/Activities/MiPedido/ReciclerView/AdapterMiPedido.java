package com.example.a55.lab5_tp_android_buffet.Activities.MiPedido.ReciclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a55.lab5_tp_android_buffet.Activities.Menu.RecyclerView.ViewHolderMenu;
import com.example.a55.lab5_tp_android_buffet.Activities.Menu.View.MenuView;
import com.example.a55.lab5_tp_android_buffet.Activities.MiPedido.View.MiPedidoView;
import com.example.a55.lab5_tp_android_buffet.POJOS.Producto;
import com.example.a55.lab5_tp_android_buffet.R;

import java.util.List;

/**
 * Created by A55 on 18/05/2017.
 */

public class AdapterMiPedido extends RecyclerView.Adapter<ViewHolderMiPedido> {

    private List<Producto> listaProductosPedidos;
    private MiPedidoView miPedidoView;

    public AdapterMiPedido(List<Producto> lista, MiPedidoView miPedidoView)
    {
        this.listaProductosPedidos  = lista;
        this.miPedidoView = miPedidoView;

    }

    @Override
    public ViewHolderMiPedido onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_mi_pedido, parent, false);
        ViewHolderMiPedido myViewHolder = new ViewHolderMiPedido(v);
        //Log.d("ATENCION: ", "ENTRO AL onCreateViewHolder() " + create++);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolderMiPedido holder, int position) {

        holder.miPedidoView = this.miPedidoView;

        Producto p = this.listaProductosPedidos.get(position);

        // Carga la imagen desde la lista (ya descargada) al ImageView del itemView
        try {
            Bitmap bitmap = BitmapFactory.decodeByteArray(p.imagenBytes, 0, p.imagenBytes.length);
            holder.ivProducto.setImageBitmap(bitmap);
        } catch (Exception e) {

            e.printStackTrace();
            Log.d("EOnBindViewH", "" + e.getMessage());
        }
        holder.tvNombreProducto.setText(p.nombre);
        holder.tvPrecioProductoNumero.setText(p.precio.toString());

        //Guarda la posicion en el holder
        holder.posicion = position;
        //Log.d("ATENCION: ", "ENTRO AL onBindViewHolder( )"+ bind++);
    }

    @Override
    public int getItemCount() {
        //Log.d("ATENCION: ", "ENTRO AL getItemCount() "+ count++);
        return this.listaProductosPedidos.size();
    }
}
