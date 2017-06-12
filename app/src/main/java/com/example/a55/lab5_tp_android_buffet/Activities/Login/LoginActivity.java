package com.example.a55.lab5_tp_android_buffet.Activities.Login;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.a55.lab5_tp_android_buffet.Activities.Login.Controller.LoginCtrl;
import com.example.a55.lab5_tp_android_buffet.Activities.Login.Model.LoginModel;
import com.example.a55.lab5_tp_android_buffet.Activities.Login.View.LoginView;
import com.example.a55.lab5_tp_android_buffet.POJOS.Pedido;
import com.example.a55.lab5_tp_android_buffet.POJOS.Producto;
import com.example.a55.lab5_tp_android_buffet.POJOS.Usuario;
import com.example.a55.lab5_tp_android_buffet.R;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Ocultar ActionBar
        getSupportActionBar().hide();

        // Usuarios
        Usuario.listaUsuarios = new ArrayList<Usuario>();

        Usuario u1 = new Usuario("Juan", "Torrellas", 20555444, "a@a.com", "123");
        Usuario.registrarUsuario(u1);

        // Productos
        Producto.listaProductos = new ArrayList<Producto>();

        Producto.listaProductos.add( new Producto("Tostado"                      , 22.00) ) ;
        Producto.listaProductos.add( new Producto("Empanada carne"               , 18.00) ) ;
        Producto.listaProductos.add( new Producto("Empanada jamón y queso"       , 18.00) ) ;
        Producto.listaProductos.add( new Producto("Porcion de pizza"             , 16.00) ) ;

        Producto.listaProductos.add( new Producto("CocaCola 357 Cc. (lata)"      , 15.00) ) ;
        Producto.listaProductos.add( new Producto("CocaCola Zero 357 Cc. (lata)" , 15.00) ) ;
        Producto.listaProductos.add( new Producto("Fanta 357 Cc. (lata) "        , 15.00) ) ;
        Producto.listaProductos.add( new Producto("Café con leche"               , 13.00) ) ;

        Producto.listaProductos.add( new Producto("Saladix 80 / 100 Gr. (caja)"   , 20.00) ) ;
        Producto.listaProductos.add( new Producto("Saladix 25 / 30 Gr. (sobre)"   ,  9.00) ) ;
        Producto.listaProductos.add( new Producto("Toblerone 50 Gr."              , 18.00) ) ;
        Producto.listaProductos.add( new Producto("Cerealitas Pocket 105 Gr."     , 19.00) ) ;

        // Pedido
        Pedido.listaPedidos = new ArrayList<Producto>();
        Pedido.precioTotalPedido= 0.00;
        Pedido.cantidadItemsPedido = 0;

        // MVC
        LoginModel loginModel = new LoginModel();
        LoginView loginView = new LoginView(this, loginModel);
        LoginCtrl loginCtrl = new LoginCtrl(loginView);


    }
}
