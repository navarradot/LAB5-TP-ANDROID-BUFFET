package com.example.a55.lab5_tp_android_buffet.Activities.MiPedido.Listeners;

import android.view.View;

import com.example.a55.lab5_tp_android_buffet.Activities.Menu.Interfaces.IMenu;
import com.example.a55.lab5_tp_android_buffet.Activities.MiPedido.Interfaces.IMiPedido;

/**
 * Created by A55 on 18/05/2017.
 */

public class MiPedidoListener implements View.OnClickListener {

    /**
     * Atributos
     */
    public IMiPedido iMiPedido;

    /**
     * Constructor
     */
    public MiPedidoListener(IMiPedido iMiPedido) {

        this.iMiPedido = iMiPedido;
    }


    @Override
    public void onClick(View v) {

        this.iMiPedido.operar(v);
    }
}
