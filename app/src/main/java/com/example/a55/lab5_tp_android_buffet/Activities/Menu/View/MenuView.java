package com.example.a55.lab5_tp_android_buffet.Activities.Menu.View;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import com.example.a55.lab5_tp_android_buffet.Activities.Menu.Model.MenuModel;
import com.example.a55.lab5_tp_android_buffet.Activities.Menu.RecyclerView.AdapterMenu;
import com.example.a55.lab5_tp_android_buffet.POJOS.Pedido;
import com.example.a55.lab5_tp_android_buffet.POJOS.Producto;
import com.example.a55.lab5_tp_android_buffet.R;

/**
 * Created by A55 on 18/05/2017.
 */

public class MenuView {

    /**
     * Atributos
     */

    public MenuModel menuModel;
    public Activity menuActivity;

    public RecyclerView recyclerListaProductos;
    public Button btnVerPedido;
    public TextView tvImporteTotalNumero;
    public TextView tvCantidadElementosNumero;

    /**
     * Contructor
     */
    public MenuView(Activity menuActivity, MenuModel menuModel) {

        this.menuActivity = menuActivity;
        this.menuModel = menuModel;

        this.btnVerPedido = (Button)menuActivity.findViewById(R.id.btnVerPedido);
        this.tvImporteTotalNumero = (TextView) menuActivity.findViewById(R.id.tvImporteTotalNumero);
        this.tvImporteTotalNumero.setText(Pedido.precioTotalPedido.toString());
        this.tvCantidadElementosNumero = (TextView) menuActivity.findViewById(R.id.tvCantidadElementosNumero);
        this.tvCantidadElementosNumero.setText(Pedido.cantidadItemsPedido.toString());

        // RecyclerView
        this.recyclerListaProductos = (RecyclerView)menuActivity.findViewById(R.id.RecyclerListaProductos);

        //Le decimos como presenta la informacion, puede ser grilla, columnas etc.
        LinearLayoutManager layoutManager = new LinearLayoutManager(menuActivity);

        this.recyclerListaProductos.setLayoutManager(layoutManager);

        AdapterMenu adapter = new AdapterMenu(Producto.listaProductos, this);
        this.recyclerListaProductos.setAdapter(adapter);
    }
}
