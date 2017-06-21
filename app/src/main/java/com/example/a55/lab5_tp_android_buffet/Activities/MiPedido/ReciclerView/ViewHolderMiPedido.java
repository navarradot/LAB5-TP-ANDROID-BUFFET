package com.example.a55.lab5_tp_android_buffet.Activities.MiPedido.ReciclerView;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a55.lab5_tp_android_buffet.Activities.Menu.Interfaces.IItemMenu;
import com.example.a55.lab5_tp_android_buffet.Activities.Menu.Listeners.ListenerItemViewMenu;
import com.example.a55.lab5_tp_android_buffet.Activities.Menu.View.MenuView;
import com.example.a55.lab5_tp_android_buffet.Activities.MiPedido.Interfaces.IItemMiPedido;
import com.example.a55.lab5_tp_android_buffet.Activities.MiPedido.Listeners.ListenerItemViewMiPedido;
import com.example.a55.lab5_tp_android_buffet.Activities.MiPedido.View.MiPedidoView;
import com.example.a55.lab5_tp_android_buffet.POJOS.Pedido;
import com.example.a55.lab5_tp_android_buffet.POJOS.Producto;
import com.example.a55.lab5_tp_android_buffet.R;

/**
 * Created by A55 on 18/05/2017.
 */

public class ViewHolderMiPedido extends RecyclerView.ViewHolder implements IItemMiPedido {

    public ImageView ivProducto;
    public TextView tvNombreProducto;
    public TextView tvPrecioProductoNumero;
    public Button btnQuitarProductoPedido;

    public Integer posicion;
    public MiPedidoView miPedidoView;

    public ListenerItemViewMiPedido listener;


    public ViewHolderMiPedido(View itemView) {

        super(itemView);

        //De esta manera solamente voy a hacer tantos findViewById como objetos cree y no cada vez que lo llame. Asi se llaman una sola vez y no siempre.
        this.ivProducto                 = (ImageView)itemView.findViewById(R.id.ivProducto);
        this.tvNombreProducto           = (TextView) itemView.findViewById(R.id.tvNombreProducto);
        this.tvPrecioProductoNumero     = (TextView) itemView.findViewById(R.id.tvPrecioProductoNumero);
        this.btnQuitarProductoPedido    = (Button)   itemView.findViewById(R.id.btnQuitarProductoPedido);

        this.listener = new ListenerItemViewMiPedido(this);

        this.btnQuitarProductoPedido.setOnClickListener(listener);
    }

    //Mi método de mi interface
    @Override
    public void quitarProductoPedido(View v) {
        Log.d("", "POS holder: " + posicion);
        Pedido.quitarProductoPedido(posicion);

        ( (TextView)this.miPedidoView.miPedidoActivity.findViewById(R.id.tvImporteTotalNumero) ).setText(Pedido.precioTotalPedido.toString());
        ( (TextView)this.miPedidoView.miPedidoActivity.findViewById(R.id.tvCantidadElementosNumero) ).setText(Pedido.cantidadItemsPedido.toString());

        RecyclerView recyclerView       = this.miPedidoView.recyclerListaProductosPedidos;
        AdapterMiPedido adapterMiPedido = (AdapterMiPedido)recyclerView.getAdapter();

        recyclerView.removeViewAt(posicion);
        adapterMiPedido.notifyItemRemoved(posicion);

        adapterMiPedido.notifyItemRangeChanged(posicion, Pedido.obtenerCantidadItemsPedido());

        for (int i=0; i<Pedido.listaPedidos.size(); i++) {
            Producto p = Pedido.listaPedidos.get(i);
            Log.d("index nuevo: " + i, "nom: " + p.nombre);
        }
    }
}
