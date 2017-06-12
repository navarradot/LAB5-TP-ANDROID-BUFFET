package com.example.a55.lab5_tp_android_buffet.Activities.Login.Listeners;

import android.view.View;

import com.example.a55.lab5_tp_android_buffet.Activities.Login.Interfaces.Ilogin;

/**
 * Created by A55 on 14/05/2017.
 */

public class LoginListener implements View.OnClickListener {

    /**
     * Atributos
     */
    public Ilogin iLogin;

    /**
     * Constructor
     */
    public LoginListener(Ilogin iLogin) {
        this.iLogin = iLogin;
    }


    @Override
    public void onClick(View v) {
        this.iLogin.operar(v);
    }
}
