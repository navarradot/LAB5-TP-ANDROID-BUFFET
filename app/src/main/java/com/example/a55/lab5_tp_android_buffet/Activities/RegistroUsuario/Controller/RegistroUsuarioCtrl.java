package com.example.a55.lab5_tp_android_buffet.Activities.RegistroUsuario.Controller;

import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.example.a55.lab5_tp_android_buffet.Activities.RegistroUsuario.Interfaces.IRegistroUsuario;
import com.example.a55.lab5_tp_android_buffet.Activities.RegistroUsuario.Listeners.RegistroUsuarioListener;
import com.example.a55.lab5_tp_android_buffet.Activities.RegistroUsuario.View.RegistroUsuarioView;
import com.example.a55.lab5_tp_android_buffet.POJOS.Usuario;
import com.example.a55.lab5_tp_android_buffet.R;
import com.example.a55.lab5_tp_android_buffet.ValidacionesForms.ValidacionesForms;

/**
 * Created by A55 on 14/05/2017.
 */

public class RegistroUsuarioCtrl implements IRegistroUsuario {

    /**
     * Atributos
     */
    RegistroUsuarioView registroUsuarioView;
    RegistroUsuarioListener registroUsuarioListener;


    /**
     * Constructor
     * @param registroUsuarioView
     */
    public RegistroUsuarioCtrl(RegistroUsuarioView registroUsuarioView) {

        this.registroUsuarioView = registroUsuarioView;
        this.registroUsuarioListener = new RegistroUsuarioListener(this);

        this.registroUsuarioView.btnRegistrarme.setOnClickListener(registroUsuarioListener);
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

                this.registrar( new Usuario(nombre, apellido, Integer.parseInt(dni), email, clave1) );
                break;

            default:
                Toast.makeText(this.registroUsuarioView.registroUsuarioActivity, "SWITCH DEFAULT", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void registrar(Usuario usuario) {

        if (Usuario.registrarUsuario(usuario)) {
            Toast toast = Toast.makeText(this.registroUsuarioView.registroUsuarioActivity, "Usuario Registrado! Ya puede acceder con sus credenciales.", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            this.registroUsuarioView.registroUsuarioActivity.finish();
        }
        else {
            Toast toast = Toast.makeText(this.registroUsuarioView.registroUsuarioActivity, this.registroUsuarioView.registroUsuarioActivity.getResources().getString(R.string.usuarioExiste), Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }



    }
}
