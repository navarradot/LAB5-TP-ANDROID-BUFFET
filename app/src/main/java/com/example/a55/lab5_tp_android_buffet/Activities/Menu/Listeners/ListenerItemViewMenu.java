package com.example.a55.lab5_tp_android_buffet.Activities.Menu.Listeners;

import android.view.View;

import com.example.a55.lab5_tp_android_buffet.Activities.Menu.Interfaces.IItemMenu;

/**
 * Created by A55 on 18/05/2017.
 */

public class ListenerItemViewMenu implements View.OnClickListener {

    private IItemMenu iItem;

    public ListenerItemViewMenu(IItemMenu iItem) {

        this.iItem = iItem;
    }

    public void onClick(View v) {

        this.iItem.agregarProductoAPedido(v);
    }
}
