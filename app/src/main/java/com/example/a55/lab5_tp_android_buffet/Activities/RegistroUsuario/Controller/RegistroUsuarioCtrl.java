package com.example.a55.lab5_tp_android_buffet.Activities.RegistroUsuario.Controller;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.example.a55.lab5_tp_android_buffet.Activities.Login.Interfaces.Ilogin;
import com.example.a55.lab5_tp_android_buffet.Activities.Menu.MenuActivity;
import com.example.a55.lab5_tp_android_buffet.Activities.RegistroUsuario.Interfaces.IRegistroUsuario;
import com.example.a55.lab5_tp_android_buffet.Activities.RegistroUsuario.Listeners.RegistroUsuarioListener;
import com.example.a55.lab5_tp_android_buffet.Activities.RegistroUsuario.View.RegistroUsuarioView;
import com.example.a55.lab5_tp_android_buffet.Http.JsonParser;
import com.example.a55.lab5_tp_android_buffet.Http.ThreadConnection;
import com.example.a55.lab5_tp_android_buffet.POJOS.Usuario;
import com.example.a55.lab5_tp_android_buffet.R;
import com.example.a55.lab5_tp_android_buffet.ValidacionesForms.ValidacionesForms;

import org.json.JSONObject;

/**
 * Created by A55 on 14/05/2017.
 */

public class RegistroUsuarioCtrl implements IRegistroUsuario, Handler.Callback {

    /**
     * Atributos
     */
    public RegistroUsuarioView registroUsuarioView;
    public RegistroUsuarioListener registroUsuarioListener;

    public Handler handler;
    public Usuario usuarioARegistrar;


    /**
     * Constructor
     * @param registroUsuarioView
     */
    public RegistroUsuarioCtrl(RegistroUsuarioView registroUsuarioView) {

        this.registroUsuarioView = registroUsuarioView;
        this.registroUsuarioListener = new RegistroUsuarioListener(this);

        this.registroUsuarioView.btnRegistrarme.setOnClickListener(registroUsuarioListener);

        // Handler para conexiones
        this.handler = new Handler(this);
    }

    @Override
    public void operar(View v) {

        String nombre   = this.registroUsuarioView.etNombre.getText().toString();
        String apellido = this.registroUsuarioView.etApellido.getText().toString();
        String dni      = this.registroUsuarioView.etDni.getText().toString();
        String email    = this.registroUsuarioView.etEmail.getText().toString();
        String clave1   = this.registroUsuarioView.etClave1.getText().toString();
        String clave2   = this.registroUsuarioView.etClave2.getText().toString();

        switch (v.getId()) {

            case R.id.btnRegistrarme:

                //Validaciones Form
                if(!ValidacionesForms.validarInputVacio(nombre)) {
                    this.registroUsuarioView.etNombre.setError( this.registroUsuarioView.registroUsuarioActivity.getResources().getString(R.string.campoVacio) );
                    break;
                }

                if(!ValidacionesForms.validarInputVacio(apellido)) {
                    this.registroUsuarioView.etApellido.setError( this.registroUsuarioView.registroUsuarioActivity.getResources().getString(R.string.campoVacio) );
                    break;
                }

                if(!ValidacionesForms.validarInputVacio(dni)) {
                    this.registroUsuarioView.etDni.setError( this.registroUsuarioView.registroUsuarioActivity.getResources().getString(R.string.campoVacio) );
                    break;
                }

                if(!ValidacionesForms.validarDniLenght(dni)) {
                    this.registroUsuarioView.etDni.setError( this.registroUsuarioView.registroUsuarioActivity.getResources().getString(R.string.dniInvalido) );
                    break;
                }

                if(!ValidacionesForms.validarInputVacio(email)) {
                    this.registroUsuarioView.etEmail.setError( this.registroUsuarioView.registroUsuarioActivity.getResources().getString(R.string.campoVacio) );
                    break;
                }

                if(!ValidacionesForms.validarInputEmail(email)) {
                    this.registroUsuarioView.etEmail.setError( this.registroUsuarioView.registroUsuarioActivity.getResources().getString(R.string.emailInvalido) );
                    break;
                }

                if(!ValidacionesForms.validarInputVacio(clave1)) {
                    this.registroUsuarioView.etClave1.setError( this.registroUsuarioView.registroUsuarioActivity.getResources().getString(R.string.campoVacio) );
                    break;
                }

                if(!ValidacionesForms.validarInputVacio(clave2)) {
                    this.registroUsuarioView.etClave2.setError( this.registroUsuarioView.registroUsuarioActivity.getResources().getString(R.string.campoVacio) );
                    break;
                }

                if(!ValidacionesForms.validarClavesRegistro(clave1, clave2)) {
                    this.registroUsuarioView.etClave1.setError( this.registroUsuarioView.registroUsuarioActivity.getResources().getString(R.string.campoVacio) );
                    this.registroUsuarioView.etClave2.setError( this.registroUsuarioView.registroUsuarioActivity.getResources().getString(R.string.campoVacio) );
                    break;
                }

                this.usuarioARegistrar = new Usuario(nombre, apellido, Integer.parseInt(dni), email, clave1);
                this.validarUsuarioNoExista( this.usuarioARegistrar );
                break;

            default:
                Toast.makeText(this.registroUsuarioView.registroUsuarioActivity, "SWITCH DEFAULT", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void validarUsuarioNoExista(Usuario usuario) {

        Thread threadValidarUsuarioNoExista = new Thread(new ThreadConnection(handler, "usuarios/" + usuario.mail, "getString"));
        threadValidarUsuarioNoExista.start();
    }

    @Override
    public void registrar(Usuario usuario) {

        try {

            String stringJsonPost = "{"+
                "\"nombre\":"   + "\"" + usuario.nombre   + "\"," +
                "\"apellido\":" + "\"" + usuario.apellido + "\"," +
                "\"dni\":"             + usuario.dni      + "," +
                "\"mail\":"     + "\"" + usuario.mail     + "\"," +
                "\"clave\":"    + "\"" + usuario.clave    + "\""  + "}";


            Thread threadRegistrarUsuario = new Thread(new ThreadConnection(handler, "usuarios/nuevo", stringJsonPost, "postString"));
            threadRegistrarUsuario.start();
        } catch (Exception e) {
            Log.d("ERROR: ", e.getMessage());
        }
    }


    @Override
    public boolean handleMessage(Message msg) {

        try {
            // validarUsuarioNoExista
            if (msg.arg1 == 3) {

                Boolean rtaValidarUsuarioNoExista = JsonParser.getValidacionUsuarioNoExista((String) msg.obj);

                if (rtaValidarUsuarioNoExista) {

                    this.registrar(this.usuarioARegistrar);
                    //Log.d("NO EXISTE :)", ""+rtaValidarUsuarioNoExista);
                } else {
                    Toast toast = Toast.makeText(this.registroUsuarioView.registroUsuarioActivity, this.registroUsuarioView.registroUsuarioActivity.getResources().getString(R.string.registroUsuarioError), Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
            // registrarUsuario
            if (msg.arg1 == 4) {
                String mensaje = (String) msg.obj;

                if (mensaje.equals("Se inserto correctamente")) {

                    Toast toast = Toast.makeText(this.registroUsuarioView.registroUsuarioActivity, this.registroUsuarioView.registroUsuarioActivity.getResources().getString(R.string.registroUsuarioOk), Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    this.registroUsuarioView.registroUsuarioActivity.finish();
                } else {
                    Toast toast = Toast.makeText(this.registroUsuarioView.registroUsuarioActivity, this.registroUsuarioView.registroUsuarioActivity.getResources().getString(R.string.registroUsuarioError), Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }

            }
            //Error de conexión httpManager -> ThreadConnection
            if (msg.arg1 == 1000) {
                Toast.makeText(this.registroUsuarioView.registroUsuarioActivity, this.registroUsuarioView.registroUsuarioActivity.getResources().getString(R.string.problemaConexion), Toast.LENGTH_LONG).show();
            }
        } catch(Exception e) {
            Log.d("ERROR CATCH", e.getMessage());
        }

        return true;
    }
}
