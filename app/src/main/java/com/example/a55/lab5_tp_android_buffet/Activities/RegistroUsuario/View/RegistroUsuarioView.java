package com.example.a55.lab5_tp_android_buffet.Activities.RegistroUsuario.View;

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;

import com.example.a55.lab5_tp_android_buffet.Activities.RegistroUsuario.Model.RegistroUsuarioModel;
import com.example.a55.lab5_tp_android_buffet.R;

/**
 * Created by A55 on 14/05/2017.
 */

public class RegistroUsuarioView {

    /**
     * Atributos
     */
    public Activity registroUsuarioActivity;
    public RegistroUsuarioModel registroUsuarioModel;

    public EditText etNombre;
    public EditText etApellido;
    public EditText etDni;
    public EditText etEmail;
    public EditText etClave1;
    public EditText etClave2;
    public Button   btnRegistrarme;

    /**
     * Constructor
     * @param registroUsuarioActivity
     * @param registroUsuarioModel
     */
    public RegistroUsuarioView(Activity registroUsuarioActivity, RegistroUsuarioModel registroUsuarioModel) {

        this.registroUsuarioActivity = registroUsuarioActivity;
        this.registroUsuarioModel = registroUsuarioModel;

        //Vinculo los elementos del activity con la vista
        this.etNombre       = (EditText)registroUsuarioActivity.findViewById(R.id.etNombre);
        this.etApellido     = (EditText)registroUsuarioActivity.findViewById(R.id.etApellido);
        this.etDni          = (EditText)registroUsuarioActivity.findViewById(R.id.etDni);
        this.etEmail        = (EditText)registroUsuarioActivity.findViewById(R.id.etEmail);
        this.etClave1       = (EditText)registroUsuarioActivity.findViewById(R.id.etClave1);
        this.etClave2       = (EditText)registroUsuarioActivity.findViewById(R.id.etClave2);
        this.btnRegistrarme = (Button)registroUsuarioActivity.findViewById(R.id.btnRegistrarme);

        //Impacto valores del model a la vista
        this.etNombre.setText(this.registroUsuarioModel.getNombre());
        this.etApellido.setText(this.registroUsuarioModel.getApellido());
        this.etDni.setText(this.registroUsuarioModel.getDni().toString());
        this.etEmail.setText(this.registroUsuarioModel.getEmail());
        this.etClave1.setText(this.registroUsuarioModel.getClave1());
        this.etClave2.setText(this.registroUsuarioModel.getClave2());



    }
}
