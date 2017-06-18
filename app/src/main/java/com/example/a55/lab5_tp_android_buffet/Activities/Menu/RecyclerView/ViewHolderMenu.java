package com.example.a55.lab5_tp_android_buffet.Activities.Menu.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a55.lab5_tp_android_buffet.Activities.Menu.Interfaces.IItemMenu;
import com.example.a55.lab5_tp_android_buffet.Activities.Menu.Listeners.ListenerItemViewMenu;
import com.example.a55.lab5_tp_android_buffet.Activities.Menu.MenuActivity;
import com.example.a55.lab5_tp_android_buffet.Activities.Menu.View.MenuView;
import com.example.a55.lab5_tp_android_buffet.POJOS.Pedido;
import com.example.a55.lab5_tp_android_buffet.POJOS.Producto;
import com.example.a55.lab5_tp_android_buffet.R;

import org.w3c.dom.Text;

/**
 * Created by A55 on 18/05/2017.
 */

public class ViewHolderMenu extends RecyclerView.ViewHolder implements IItemMenu {

    public ImageView ivProducto;
    public TextView tvNombreProducto;
    public TextView tvPrecioProductoNumero;
    public Button   btnAgregarProductoAPedido;

    public int posicion;
    public MenuView menuView;

    public ListenerItemViewMenu listener;
    public ViewGroup parent;


    public ViewHolderMenu(View itemView) {

        super(itemView);


        //De esta manera solamente voy a hacer tantos findViewById como objetos cree y no cada vez que lo llame. Asi se llaman una sola vez y no siempre.
        this.ivProducto                 = (ImageView)itemView.findViewById(R.id.ivProducto);
        this.tvNombreProducto           = (TextView) itemView.findViewById(R.id.tvNombreProducto);
        this.tvPrecioProductoNumero     = (TextView) itemView.findViewById(R.id.tvPrecioProductoNumero);
        this.btnAgregarProductoAPedido  = (Button)   itemView.findViewById(R.id.btnAgregarProductoAPedido);

        this.posicion = 0;
        this.parent = parent;

        this.listener = new ListenerItemViewMenu(this);
        //itemView.setOnClickListener(listener);
        this.btnAgregarProductoAPedido.setOnClickListener(listener);
    }

    //Mi método de mi interface
    @Override
    public void agregarProductoAPedido(View v) {

        //Log.d("POSICION DE ELEMENTO: ", "" + this.posicion);

        Pedido.agregarProductoAPedido(Producto.listaProductos.get(posicion));

        ( (TextView)this.menuView.menuActivity.findViewById(R.id.tvImporteTotalNumero) ).setText(Pedido.precioTotalPedido.toString());
        ( (TextView)this.menuView.menuActivity.findViewById(R.id.tvCantidadElementosNumero) ).setText(Pedido.cantidadItemsPedido.toString());


    }
}
