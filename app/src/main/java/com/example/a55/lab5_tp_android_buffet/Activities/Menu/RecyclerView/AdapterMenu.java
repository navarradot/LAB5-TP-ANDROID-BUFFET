package com.example.a55.lab5_tp_android_buffet.Activities.Menu.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a55.lab5_tp_android_buffet.Activities.Menu.View.MenuView;
import com.example.a55.lab5_tp_android_buffet.POJOS.Producto;
import com.example.a55.lab5_tp_android_buffet.R;

import java.util.List;

/**
 * Created by A55 on 18/05/2017.
 */

public class AdapterMenu extends RecyclerView.Adapter<ViewHolderMenu> {

    private List<Producto> listaProductos;
    private MenuView menuView;

    public AdapterMenu(List<Producto> lista, MenuView menuView)
    {
        this.listaProductos  = lista;
        this.menuView = menuView;

    }

    @Override
    public ViewHolderMenu onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_menu, parent, false);
        ViewHolderMenu myViewHolder = new ViewHolderMenu(v);
        //Log.d("ATENCION: ", "ENTRO AL onCreateViewHolder() " + create++);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolderMenu holder, int position) {
        Producto p = this.listaProductos.get(position);
        holder.menuView = this.menuView;
        holder.tvDescripcionProducto.setText(p.descripcion);
        holder.tvPrecioProductoNumero.setText(p.precio.toString());

        //Guarda la posicion en el holder
        holder.posicion = position;
        //Log.d("ATENCION: ", "ENTRO AL onBindViewHolder( )"+ bind++);
    }

    @Override
    public int getItemCount() {
        //Log.d("ATENCION: ", "ENTRO AL getItemCount() "+ count++);
        return this.listaProductos.size();
    }
}
