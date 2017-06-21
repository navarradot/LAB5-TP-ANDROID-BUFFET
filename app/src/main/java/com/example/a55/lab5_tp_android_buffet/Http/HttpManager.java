package com.example.a55.lab5_tp_android_buffet.Http;

import android.net.Uri;
import android.preference.PreferenceActivity;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by A55 on 16/06/2017.
 */

public class HttpManager {

    private String url;
    private HttpURLConnection conn;

    public HttpManager(String url) {

        // Valida que la ruta no sea de una imagen: Ejemplo: http://google.com/imagen.jpg
        if (!url.contains("http")) {
            url = "http://192.168.0.2:3000/" + url;
        }

        conn = crearHttpUrlConn(url);
    }

    private HttpURLConnection crearHttpUrlConn(String strUrl)
    {
        URL url = null;

        try {

            url = new URL(strUrl);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setReadTimeout(15000 /* milliseconds */);
            urlConnection.setConnectTimeout(20000 /* milliseconds */);
            return urlConnection;
        } catch (MalformedURLException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return null;
    }

    public boolean isReady(){

        return conn!=null;
    }

    public String getStrDataByGET() throws IOException {

        byte[] bytes = getBytesDataByGET();
        return new String(bytes, "UTF-8");
    }

    public byte[] getBytesDataByGET() throws IOException {

        conn.setRequestMethod("GET");
        conn.connect();
        int response = conn.getResponseCode();

        if(response==200) {
            InputStream is = conn.getInputStream();
            return readFully(is);
        }
        else
            throw new IOException();
    }

    public String getStrDataByPOST(String stringJsonPost) throws IOException {

        byte[] bytes = getBytesDataByPOST(stringJsonPost);
        return new String(bytes, "UTF-8");
    }

    public byte[] getBytesDataByPOST(String stringJsonPost) throws IOException {

        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        conn.setRequestProperty("Content-Type", "application/json");
        conn.connect();

        OutputStream os = conn.getOutputStream();
        os.write(stringJsonPost.getBytes());
        os.flush();

        int response = conn.getResponseCode();

        if(response==200) {
            InputStream is = conn.getInputStream();
            return readFully(is);
        }
        else
            throw new IOException();
    }

    private byte[] readFully(InputStream inputStream) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length = 0;

        while ((length = inputStream.read(buffer)) != -1) {
            baos.write(buffer, 0, length);
        }
        inputStream.close();
        return baos.toByteArray();
    }
}
