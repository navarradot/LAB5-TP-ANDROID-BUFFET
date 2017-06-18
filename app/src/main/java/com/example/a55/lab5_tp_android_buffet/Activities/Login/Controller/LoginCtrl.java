package com.example.a55.lab5_tp_android_buffet.Activities.Login.Controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
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
import com.example.a55.lab5_tp_android_buffet.Http.JsonParser;
import com.example.a55.lab5_tp_android_buffet.Http.ThreadConnection;
import com.example.a55.lab5_tp_android_buffet.POJOS.Usuario;
import com.example.a55.lab5_tp_android_buffet.R;
import com.example.a55.lab5_tp_android_buffet.ValidacionesForms.ValidacionesForms;

import java.util.List;

/**
 * Created by A55 on 14/05/2017.
 */

public class LoginCtrl implements Ilogin, Handler.Callback {

    /**
     * Atributos
     */

    public LoginView loginView;
    public LoginListener loginListener;

    public SharedPreferences sharSetea;
    public SharedPreferences shar;

    Handler handler;

    /**
     * Constructor
     * @param loginView
     */
    public LoginCtrl(LoginView loginView) {

        this.loginView = loginView;
        this.loginListener = new LoginListener(this);

        this.loginView.btnIngresar.setOnClickListener(loginListener);
        this.loginView.btnRegistrarme.setOnClickListener(loginListener);

        // Handler para conexiones
        this.handler = new Handler(this);

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
        String mail = this.shar.getString("email", "");
        String clave = this.shar.getString("clave", "");
        if (recordarme) {
            Usuario.usuarioActual = new Usuario(mail, clave);
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

        // Guarda el usuarioActual, si no es validado se borra.
        Usuario.usuarioActual = usuario;

        Thread threadValidarUsuario = new Thread(new ThreadConnection(handler, "http://192.168.0.2:3000/usuarios/" + usuario.mail + "/" + usuario.clave, "getString"));
        threadValidarUsuario.start();
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

    @Override
    public boolean handleMessage(Message msg) {

        // validarLogin
        if (msg.arg1 == 2) {

            Boolean rtaLogin =  JsonParser.getValidacionLogin((String)msg.obj);

            if (rtaLogin) {

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
                // Borra el usuarioActual guardado antes de validar.ç
                Usuario.usuarioActual = null;

                Toast toast = Toast.makeText(this.loginView.loginActivity, this.loginView.loginActivity.getResources().getString(R.string.emailClaveIncorrecto), Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        }

        return true;
    }
}
