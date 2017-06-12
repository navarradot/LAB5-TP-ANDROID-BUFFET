package com.example.a55.lab5_tp_android_buffet.Activities.Login.Controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.example.a55.lab5_tp_android_buffet.Activities.Login.Interfaces.Ilogin;
import com.example.a55.lab5_tp_android_buffet.Activities.Login.Listeners.LoginListener;
import com.example.a55.lab5_tp_android_buffet.Activities.Login.View.LoginView;
import com.example.a55.lab5_tp_android_buffet.Activities.Menu.MenuActivity;
import com.example.a55.lab5_tp_android_buffet.Activities.RegistroUsuario.RegistroUsuarioActivity;
import com.example.a55.lab5_tp_android_buffet.POJOS.Usuario;
import com.example.a55.lab5_tp_android_buffet.R;
import com.example.a55.lab5_tp_android_buffet.ValidacionesForms.ValidacionesForms;

/**
 * Created by A55 on 14/05/2017.
 */

public class LoginCtrl implements Ilogin {

    /**
     * Atributos
     */

    public LoginView loginView;
    public LoginListener loginListener;

    public SharedPreferences sharSetea;
    public SharedPreferences shar;

    /**
     * Constructor
     * @param loginView
     */
    public LoginCtrl(LoginView loginView) {

        this.loginView = loginView;
        this.loginListener = new LoginListener(this);

        this.loginView.btnIngresar.setOnClickListener(loginListener);
        this.loginView.btnRegistrarme.setOnClickListener(loginListener);

        // Setea SharedPreferences
        this.sharSetea = loginView.loginActivity.getSharedPreferences("miConfig", Context.MODE_PRIVATE);

        // Para editar el Shar
        if (!(sharSetea.contains("recordarme")) || !(sharSetea.contains("email")) || !(sharSetea.contains("clave")) ) {

            SharedPreferences.Editor editor = sharSetea.edit();

            editor.putBoolean("recordarme", true);
            editor.putString("email", "");
            editor.putString("clave", "");
            editor.commit();
        }

        // Levanta SharedPreferences (reemplazado por inicio automatico - mas abajo)
        /*
        this.shar = PreferenceManager.getDefaultSharedPreferences(this.loginView.loginActivity);
        this.loginView.chkRecordarme.setChecked( this.shar.getBoolean("recordarme", false) );
        this.loginView.etEmail.setText( this.shar.getString("email", "") );
        this.loginView.etClave.setText( this.shar.getString("clave", "") );
        */
        this.shar = PreferenceManager.getDefaultSharedPreferences(this.loginView.loginActivity);
        Boolean recordarme = this.shar.getBoolean("recordarme", false);
        if (recordarme) {
            Intent intent = new Intent (this.loginView.loginActivity, MenuActivity.class);
            this.loginView.loginActivity.startActivity(intent);
        }


    }

    @Override
    public void operar(View v) {

        String email = (this.loginView.etEmail.getText()).toString();
        String clave = (this.loginView.etClave.getText()).toString();

        switch (v.getId()) {

            case R.id.btnIngresar:

                //Validaciones Form
                if(!ValidacionesForms.validarInputVacio(email)) {
                    this.loginView.etEmail.setError( this.loginView.loginActivity.getResources().getString(R.string.campoVacio) );
                    break;
                }

                if(!ValidacionesForms.validarInputEmail(email)) {
                    this.loginView.etEmail.setError( this.loginView.loginActivity.getResources().getString(R.string.emailInvalido) );
                    break;
                }

                if(!ValidacionesForms.validarInputVacio(clave)) {
                    this.loginView.etClave.setError( this.loginView.loginActivity.getResources().getString(R.string.campoVacio) );
                    break;
                }

                this.ingresar( new Usuario(email, clave) );
                break;

            case R.id.btnRegistrarme:
                this.registrar();
                break;

            default:
                Toast.makeText(this.loginView.loginActivity, "SWITCH DEFAULT", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void ingresar(Usuario usuario) {

        if (Usuario.validarUsuarioLogin(usuario)) {
            Toast toast = Toast.makeText(this.loginView.loginActivity, "ENTRO!!!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();

            if(this.loginView.chkRecordarme.isChecked()) {
                this.guardarSharedPreferences();
            }
            else {
                this.borrarSharedPreferences();
            }
            Intent intent = new Intent (this.loginView.loginActivity, MenuActivity.class);
            this.loginView.loginActivity.startActivity(intent);
        }
        else {
            Toast toast = Toast.makeText(this.loginView.loginActivity, this.loginView.loginActivity.getResources().getString(R.string.emailClaveIncorrecto), Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    @Override
    public void registrar() {
        Intent intent = new Intent (this.loginView.loginActivity, RegistroUsuarioActivity.class);
        this.loginView.loginActivity.startActivity(intent);
    }

    public void borrarSharedPreferences() {
        SharedPreferences.Editor editor = this.shar.edit();

        editor.putBoolean("recordarme", false);
        editor.putString("email", "");
        editor.putString("clave", "");

        editor.commit();
    }

    public void guardarSharedPreferences() {
        SharedPreferences.Editor editor = this.shar.edit();

        editor.putBoolean("recordarme", true);
        editor.putString("email", this.loginView.etEmail.getText().toString());
        editor.putString("clave", this.loginView.etClave.getText().toString());

        editor.commit();
    }
}
