package com.example.a55.lab5_tp_android_buffet.Activities.Login;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.a55.lab5_tp_android_buffet.Activities.Login.Controller.LoginCtrl;
import com.example.a55.lab5_tp_android_buffet.Activities.Login.Model.LoginModel;
import com.example.a55.lab5_tp_android_buffet.Activities.Login.View.LoginView;
import com.example.a55.lab5_tp_android_buffet.POJOS.Pedido;
import com.example.a55.lab5_tp_android_buffet.POJOS.Producto;
import com.example.a55.lab5_tp_android_buffet.POJOS.Usuario;
import com.example.a55.lab5_tp_android_buffet.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    LoginModel loginModel;
    LoginView loginView;
    LoginCtrl loginCtrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Ocultar ActionBar
        getSupportActionBar().hide();

        // MVC
        this.loginModel = new LoginModel();
        this.loginView = new LoginView(this, loginModel);
        this.loginCtrl = new LoginCtrl(loginView);

    }

}
