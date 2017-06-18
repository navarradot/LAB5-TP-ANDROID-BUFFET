package com.example.a55.lab5_tp_android_buffet.Http;

import android.app.Activity;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.example.a55.lab5_tp_android_buffet.R;

/**
 * Created by A55 on 17/06/2017.
 */

public class ThreadConnection implements Runnable {

    private Handler handler;
    private String tipo;
    private String strUrl;
    Integer listPosition;
    private String stringJsonPost;


    public ThreadConnection (Handler handler, String strUrl, String tipo)
    {
        this.handler    = handler;
        this.tipo       = tipo;

        this.strUrl     = strUrl;
    }

    public ThreadConnection (Handler handler, String strUrl, String stringJsonPost, String tipo)
    {
        this.handler    = handler;
        this.tipo       = tipo;
        this.strUrl     = strUrl;
        this.stringJsonPost   = stringJsonPost;
    }

    public ThreadConnection (Handler handler, String strUrl, Integer listPosition, String tipo)
    {
        this.handler        = handler;
        this.tipo           = tipo;
        this.listPosition   = listPosition;
        this.strUrl         = strUrl;
    }


    @Override
    public void run() {

        try {

            Message message = new Message();
            HttpManager httpManager = new HttpManager(this.strUrl);

            if (httpManager.isReady()) {

                if (this.tipo.equals("getImagen"))
                {
                    message.arg1 = 1;
                    message.arg2 = this.listPosition;
                    message.obj = httpManager.getBytesDataByGET();
                }

                if (this.tipo.equals("getString"))
                {
                    message.arg1 = 2;
                    message.obj = httpManager.getStrDataByGET();
                }

                if (this.tipo.equals("postString"))
                {
                    message.arg1 = 3;
                    message.obj = httpManager.getStrDataByPOST(this.stringJsonPost);
                }
            }

            this.handler.sendMessage(message);

        } catch (Exception e)
        {
            Log.d("PROBLEMA CONEXION: ", "" + e.getMessage());
            e.printStackTrace();
        }
    }
}
