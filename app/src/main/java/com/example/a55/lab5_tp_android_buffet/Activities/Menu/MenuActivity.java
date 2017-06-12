package com.example.a55.lab5_tp_android_buffet.Activities.Menu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.a55.lab5_tp_android_buffet.Activities.Login.LoginActivity;
import com.example.a55.lab5_tp_android_buffet.Activities.Menu.Controller.MenuCtrl;
import com.example.a55.lab5_tp_android_buffet.Activities.Menu.Model.MenuModel;
import com.example.a55.lab5_tp_android_buffet.Activities.Menu.RecyclerView.AdapterMenu;
import com.example.a55.lab5_tp_android_buffet.Activities.Menu.View.MenuView;
import com.example.a55.lab5_tp_android_buffet.POJOS.Pedido;
import com.example.a55.lab5_tp_android_buffet.POJOS.Producto;
import com.example.a55.lab5_tp_android_buffet.R;

public class MenuActivity extends AppCompatActivity {

    Menu miMenu;
    MenuModel menuModel;
    MenuView menuView;
    MenuCtrl menuCtrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //Cambia titulo a  ActionBar
        getSupportActionBar().setTitle("MENU");

        //MVC
        menuModel = new MenuModel();
        menuView = new MenuView(this, menuModel);
        menuCtrl = new MenuCtrl(menuView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.menuCtrl.actualizarDatos();
    }

    //Para poner el menu que creamos por xml
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menusuperior, menu);
        this.miMenu = menu;

        return true;
    }

    //Para capturar la accion del boton del menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.btnMenuDesloguearse) {
            menuCtrl.desloguear();
        }

        if (item.getItemId() == R.id.btnVerPedidoActual) {
            menuCtrl.verPedido();
        }
        return super.onOptionsItemSelected(item);
    }
}
