package com.example.a55.lab5_tp_android_buffet.Activities.Menu.Listeners;

import android.view.View;

import com.example.a55.lab5_tp_android_buffet.Activities.Menu.Interfaces.IMenu;

/**
 * Created by A55 on 18/05/2017.
 */

public class MenuListener implements View.OnClickListener {

    /**
     * Atributos
     */
    public IMenu iMenu;

    /**
     * Constructor
     */
    public MenuListener(IMenu iMenu) {

        this.iMenu = iMenu;
    }


    @Override
    public void onClick(View v) {

        this.iMenu.operar(v);
    }
}
