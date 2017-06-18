package com.example.a55.lab5_tp_android_buffet.Activities.MiPedido.Controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

import com.example.a55.lab5_tp_android_buffet.Activities.Login.LoginActivity;
import com.example.a55.lab5_tp_android_buffet.Activities.Menu.Listeners.MenuListener;
import com.example.a55.lab5_tp_android_buffet.Activities.Menu.View.MenuView;
import com.example.a55.lab5_tp_android_buffet.Activities.MiPedido.Interfaces.IMiPedido;
import com.example.a55.lab5_tp_android_buffet.Activities.MiPedido.Listeners.MiPedidoListener;
import com.example.a55.lab5_tp_android_buffet.Activities.MiPedido.View.MiPedidoView;
import com.example.a55.lab5_tp_android_buffet.POJOS.Pedido;
import com.example.a55.lab5_tp_android_buffet.R;

/**
 * Created by A55 on 18/05/2017.
 */

public class MiPedidoCtrl implements IMiPedido {
    /**
     * Atributos
     */

    public MiPedidoView miPedidoView;
    public MiPedidoListener miPedidoListener;

    SharedPreferences shar;

    /**
     * Constructor
     */
    public MiPedidoCtrl(MiPedidoView miPedidoView) {

        this.miPedidoView = miPedidoView;
        this.miPedidoListener = new MiPedidoListener(this);

        this.miPedidoView.btnConfirmarPedido.setOnClickListener(miPedidoListener);

        // Levanta SharedPreferences
        this.shar = PreferenceManager.getDefaultSharedPreferences(this.miPedidoView.miPedidoActivity);

    }

    @Override
    public void operar(View v) {

        switch (v.getId()) {

            case R.id.btnConfirmarPedido:

                this.confirmarPedido();
                break;

            default:
                Toast.makeText(this.miPedidoView.miPedidoActivity, "SWITCH DEFAULT", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void confirmarPedido() {
        if (Pedido.cantidadItemsPedido == 0) {
            Toast.makeText(this.miPedidoView.miPedidoActivity, R.string.pedidoVacio, Toast.LENGTH_SHORT).show();
        }
        else {
            this.limpiarPedido();
            Toast.makeText(this.miPedidoView.miPedidoActivity, R.string.pedidoEnviado, Toast.LENGTH_SHORT).show();
        }

    }

    public void limpiarPedido() {
        Pedido.limpiarMiPedido();

        Pedido.limpiarMiPedido();
        this.miPedidoView.miPedidoActivity.finish();
    }

    public void desloguear() {
        this.borrarSharedPreferences();

        Pedido.limpiarMiPedido();

        Intent intent = new Intent (this.miPedidoView.miPedidoActivity, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        this.miPedidoView.miPedidoActivity.startActivity(intent);

        this.miPedidoView.miPedidoActivity.finish();
    }

    public void borrarSharedPreferences() {
        SharedPreferences.Editor editor = this.shar.edit();

        editor.putBoolean("recordarme", false);
        editor.putString("email", "");
        editor.putString("clave", "");

        editor.commit();
    }
}
