package com.example.a55.lab5_tp_android_buffet.Activities.Menu.Controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a55.lab5_tp_android_buffet.Activities.Login.LoginActivity;
import com.example.a55.lab5_tp_android_buffet.Activities.Menu.Interfaces.IMenu;
import com.example.a55.lab5_tp_android_buffet.Activities.Menu.Listeners.MenuListener;
import com.example.a55.lab5_tp_android_buffet.Activities.Menu.MenuActivity;
import com.example.a55.lab5_tp_android_buffet.Activities.Menu.View.MenuView;

import com.example.a55.lab5_tp_android_buffet.Activities.MiPedido.MiPedidoActivity;
import com.example.a55.lab5_tp_android_buffet.Http.JsonParser;
import com.example.a55.lab5_tp_android_buffet.Http.ThreadConnection;
import com.example.a55.lab5_tp_android_buffet.POJOS.Pedido;
import com.example.a55.lab5_tp_android_buffet.POJOS.Producto;
import com.example.a55.lab5_tp_android_buffet.POJOS.Usuario;
import com.example.a55.lab5_tp_android_buffet.R;

/**
 * Created by A55 on 18/05/2017.
 */

public class MenuCtrl implements IMenu {

    /**
     * Atributos
     */
    public MenuView menuView;
    public MenuListener menuListener;

    public SharedPreferences shar;

    /**
     * Constructor
     */
    public MenuCtrl(MenuView menuView) {

        this.menuView = menuView;
        this.menuListener = new MenuListener(this);

        this.menuView.fabVerPedido.setOnClickListener(menuListener);

        // Levanta SharedPreferences
        this.shar = PreferenceManager.getDefaultSharedPreferences(this.menuView.menuActivity);

    }

    public void actualizarDatos() {

        ( (TextView)this.menuView.menuActivity.findViewById(R.id.tvImporteTotalNumero) ).setText(Pedido.precioTotalPedido.toString());

        ( (TextView)this.menuView.menuActivity.findViewById(R.id.tvCantidadElementosNumero) ).setText(Pedido.cantidadItemsPedido.toString());

    }

    @Override
    public void operar(View v) {

        switch (v.getId()) {

            case R.id.fabVerPedido:

                this.verPedido();
                break;

            default:
                Toast.makeText(this.menuView.menuActivity, "SWITCH DEFAULT", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void verPedido() {

        //Toast.makeText(this.menuView.menuActivity, "UE A VER PEDIDO", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent (this.menuView.menuActivity, MiPedidoActivity.class);
        this.menuView.menuActivity.startActivity(intent);

    }

    public void desloguear() {
        this.borrarSharedPreferences();

        Pedido.limpiarMiPedido();

        Intent intent = new Intent (this.menuView.menuActivity, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        this.menuView.menuActivity.startActivity(intent);

        this.menuView.menuActivity.finish();
    }

    public void borrarSharedPreferences() {
        SharedPreferences.Editor editor = this.shar.edit();

        editor.putBoolean("recordarme", false);
        editor.putString("email", "");
        editor.putString("clave", "");

        editor.commit();
    }
}
