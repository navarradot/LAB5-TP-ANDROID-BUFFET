package com.example.a55.lab5_tp_android_buffet.Activities.Login.View;

import android.app.Activity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.a55.lab5_tp_android_buffet.Activities.Login.Model.LoginModel;
import com.example.a55.lab5_tp_android_buffet.R;

/**
 * Created by A55 on 14/05/2017.
 */

public class LoginView {

    /**
     * Atributos
     */

    public LoginModel loginModel;
    public Activity loginActivity;

    public EditText etEmail;
    public EditText etClave;
    public CheckBox chkRecordarme;
    public Button btnIngresar;
    public Button btnRegistrarme;

    /**
     * Contructor
     * @param loginActivity
     * @param loginModel
     */
    public LoginView(Activity loginActivity, LoginModel loginModel) {

        this.loginActivity = loginActivity;
        this.loginModel = loginModel;

        this.etEmail = (EditText)loginActivity.findViewById(R.id.etEmail);
        this.etClave = (EditText)loginActivity.findViewById(R.id.etClave);
        this.chkRecordarme = (CheckBox) loginActivity.findViewById(R.id.chkRecordarme);
        this.btnIngresar = (Button)loginActivity.findViewById(R.id.btnIngresar);
        this.btnRegistrarme = (Button)loginActivity.findViewById(R.id.btnRegistrarme);
    }
}
