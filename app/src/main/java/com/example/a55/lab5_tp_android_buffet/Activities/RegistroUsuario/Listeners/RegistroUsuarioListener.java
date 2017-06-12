package com.example.a55.lab5_tp_android_buffet.Activities.RegistroUsuario.Listeners;

import android.view.View;

import com.example.a55.lab5_tp_android_buffet.Activities.RegistroUsuario.Interfaces.IRegistroUsuario;

/**
 * Created by A55 on 14/05/2017.
 */

public class RegistroUsuarioListener implements View.OnClickListener {

    public IRegistroUsuario iRegistroUsuario;

    public RegistroUsuarioListener(IRegistroUsuario iRegistroUsuario) {

        this.iRegistroUsuario = iRegistroUsuario;
    }

    @Override
    public void onClick(View v) {
        this.iRegistroUsuario.operar(v);
    }
}
