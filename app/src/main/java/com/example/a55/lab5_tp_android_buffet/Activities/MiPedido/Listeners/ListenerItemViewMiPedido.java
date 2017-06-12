package com.example.a55.lab5_tp_android_buffet.Activities.MiPedido.Listeners;

import android.view.View;

import com.example.a55.lab5_tp_android_buffet.Activities.Menu.Interfaces.IItemMenu;
import com.example.a55.lab5_tp_android_buffet.Activities.MiPedido.Interfaces.IItemMiPedido;

/**
 * Created by A55 on 18/05/2017.
 */

public class ListenerItemViewMiPedido implements View.OnClickListener {

    private IItemMiPedido iItem;

    public ListenerItemViewMiPedido(IItemMiPedido iItem) {

        this.iItem = iItem;
    }

    public void onClick(View v) {

        this.iItem.quitarProductoPedido(v);
    }
}
