package com.example.a55.lab5_tp_android_buffet.Activities.MiPedido.Controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.example.a55.lab5_tp_android_buffet.Activities.Login.LoginActivity;
import com.example.a55.lab5_tp_android_buffet.Activities.Menu.Listeners.MenuListener;
import com.example.a55.lab5_tp_android_buffet.Activities.Menu.View.MenuView;
import com.example.a55.lab5_tp_android_buffet.Activities.MiPedido.Interfaces.IMiPedido;
import com.example.a55.lab5_tp_android_buffet.Activities.MiPedido.Listeners.MiPedidoListener;
import com.example.a55.lab5_tp_android_buffet.Activities.MiPedido.View.MiPedidoView;
import com.example.a55.lab5_tp_android_buffet.Http.JsonParser;
import com.example.a55.lab5_tp_android_buffet.Http.ThreadConnection;
import com.example.a55.lab5_tp_android_buffet.POJOS.Pedido;
import com.example.a55.lab5_tp_android_buffet.POJOS.Producto;
import com.example.a55.lab5_tp_android_buffet.POJOS.Usuario;
import com.example.a55.lab5_tp_android_buffet.R;

/**
 * Created by A55 on 18/05/2017.
 */

public class MiPedidoCtrl implements IMiPedido, Handler.Callback {
    /**
     * Atributos
     */

    public MiPedidoView miPedidoView;
    public MiPedidoListener miPedidoListener;

    public SharedPreferences shar;

    public Handler handler;

    /**
     * Constructor
     */
    public MiPedidoCtrl(MiPedidoView miPedidoView) {

        this.miPedidoView = miPedidoView;
        this.miPedidoListener = new MiPedidoListener(this);

        this.miPedidoView.fabConfirmarPedido.setOnClickListener(miPedidoListener);

        // Levanta SharedPreferences
        this.shar = PreferenceManager.getDefaultSharedPreferences(this.miPedidoView.miPedidoActivity);

        // Handler para conexiones
        this.handler = new Handler(this);

    }

    @Override
    public void operar(View v) {

        switch (v.getId()) {

            case R.id.fabConfirmarPedido:

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
            this.enviarPedido();
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

    public void enviarPedido() {

        String stringJsonPost = "{"+
                "\"usuario\":"   + "\"" + Usuario.usuarioActual.mail   + "\"," +
                "\"pedido\":[";


        for (int i=0; i < Pedido.listaPedidos.size(); i++) {

            Producto p = Pedido.listaPedidos.get(i);

            String producto = "{"+
                    "\"tipoMenu\":"   + "\""   + p.tipoMenu   + "\"," +
                    "\"nombre\":"     + "\""   + p.nombre     + "\"," +
                    "\"precio\":"              + p.precio     + ","   ;


            if (i == Pedido.listaPedidos.size()-1) {
                producto = producto.concat("\"imagen\":"     + "\""   + p.imagen     + "\""  + "}");
                stringJsonPost = stringJsonPost.concat(producto);
                break;
            }

            producto = producto.concat("\"imagen\":"     + "\""   + p.imagen     + "\""  + "},");
            stringJsonPost = stringJsonPost.concat(producto);
        }
        stringJsonPost = stringJsonPost.concat("]}");

        try {
            Log.e("MiPedido:", stringJsonPost);
            Thread threadEnviarPedido = new Thread(new ThreadConnection(handler, "pedidos/nuevo", stringJsonPost, "postString"));
            threadEnviarPedido.start();
        } catch (Exception e) {
            Log.d("ERROR: ", e.getMessage());
        }
    }

    @Override
    public boolean handleMessage(Message msg) {

        try {

            // enviarPedido
            if (msg.arg1 == 4) {
                String mensaje = (String) msg.obj;

                if (mensaje.equals("Se inserto correctamente")) {

                    this.limpiarPedido();
                    Toast.makeText(this.miPedidoView.miPedidoActivity, R.string.pedidoEnviado, Toast.LENGTH_SHORT).show();
                }
                else {

                    this.limpiarPedido();
                    Toast.makeText(this.miPedidoView.miPedidoActivity, R.string.pedidoEnviado, Toast.LENGTH_SHORT).show();
                }

            }
        } catch(Exception e) {
            Log.d("ERROR CATCH", e.getMessage());
        }

        return true;
    }
}
