package com.example.a55.lab5_tp_android_buffet.Activities.MiPedido.View;

import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.a55.lab5_tp_android_buffet.Activities.Menu.Model.MenuModel;
import com.example.a55.lab5_tp_android_buffet.Activities.Menu.RecyclerView.AdapterMenu;
import com.example.a55.lab5_tp_android_buffet.Activities.MiPedido.Model.MiPedidoModel;
import com.example.a55.lab5_tp_android_buffet.Activities.MiPedido.ReciclerView.AdapterMiPedido;
import com.example.a55.lab5_tp_android_buffet.POJOS.Pedido;
import com.example.a55.lab5_tp_android_buffet.POJOS.Producto;
import com.example.a55.lab5_tp_android_buffet.R;

/**
 * Created by A55 on 18/05/2017.
 */

public class MiPedidoView {

    /**
     * Atributos
     */

    public MiPedidoModel miPedidoModel;
    public Activity miPedidoActivity;

    public RecyclerView recyclerListaProductosPedidos;
    public FloatingActionButton fabConfirmarPedido;
    public TextView tvImporteTotalNumero;
    public TextView tvCantidadElementosNumero;

    /**
     * Contructor
     */
    public MiPedidoView(Activity miPedidoActivity, MiPedidoModel miPedidoModel) {

        this.miPedidoActivity = miPedidoActivity;
        this.miPedidoModel = miPedidoModel;

        this.fabConfirmarPedido = (FloatingActionButton) miPedidoActivity.findViewById(R.id.fabConfirmarPedido);
        //this.btnDesloguearse = (Button)menuActivity.findViewById(R.id.btnDesloguearse);
        this.tvImporteTotalNumero = (TextView) miPedidoActivity.findViewById(R.id.tvImporteTotalNumero);
        this.tvImporteTotalNumero.setText(Pedido.precioTotalPedido.toString());
        this.tvCantidadElementosNumero = (TextView) miPedidoActivity.findViewById(R.id.tvCantidadElementosNumero);
        this.tvCantidadElementosNumero.setText(Pedido.cantidadItemsPedido.toString());

        // RecyclerView
        this.recyclerListaProductosPedidos = (RecyclerView)miPedidoActivity.findViewById(R.id.RecyclerListaProductosPedidos);

        //Le decimos como presenta la informacion, puede ser grilla, columnas etc.
        LinearLayoutManager layoutManager = new LinearLayoutManager(miPedidoActivity);

        this.recyclerListaProductosPedidos.setLayoutManager(layoutManager);

        AdapterMiPedido adapter = new AdapterMiPedido(this);
        this.recyclerListaProductosPedidos.setAdapter(adapter);
    }
}
