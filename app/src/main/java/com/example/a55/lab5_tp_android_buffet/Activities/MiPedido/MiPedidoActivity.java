package com.example.a55.lab5_tp_android_buffet.Activities.MiPedido;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import com.example.a55.lab5_tp_android_buffet.Activities.MiPedido.Controller.MiPedidoCtrl;
import com.example.a55.lab5_tp_android_buffet.Activities.MiPedido.Model.MiPedidoModel;
import com.example.a55.lab5_tp_android_buffet.Activities.MiPedido.View.MiPedidoView;
import com.example.a55.lab5_tp_android_buffet.POJOS.Pedido;
import com.example.a55.lab5_tp_android_buffet.R;

public class MiPedidoActivity extends AppCompatActivity {

    Menu miMenu;
    MiPedidoModel miPedidoModel;
    MiPedidoView miPedidoView;
    MiPedidoCtrl miPedidoCtrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_pedido);

        //Cambia titulo a  ActionBar
        getSupportActionBar().setTitle("MI PEDIDO");

        //MVC
        miPedidoModel = new MiPedidoModel();
        miPedidoView  = new MiPedidoView(this, miPedidoModel);
        miPedidoCtrl  = new MiPedidoCtrl(miPedidoView);
    }


    //Para poner el menu que creamos por xml
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mipedidosuperior, menu);
        this.miMenu = menu;

        return true;
    }

    //Para capturar la accion del boton del menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.btnMenuDesloguearse) {
            miPedidoCtrl.desloguear();
        }

        if (item.getItemId() == R.id.btnConfirmarPedidoActual) {
            miPedidoCtrl.confirmarPedido();
        }

        if (item.getItemId() == R.id.btnLimpiarPedido) {
            miPedidoCtrl.limpiarPedido();
        }

        return super.onOptionsItemSelected(item);
    }


}
