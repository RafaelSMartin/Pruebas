package com.rafaels.testvocces.database;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import java.util.Vector;

/**
 * Created by Rafael S Martin on 28/02/2018.
 */

public class saveProvider implements IDatabase {

    private Activity activity;


    public saveProvider(Activity activity){
        this.activity = activity;
    }

    @Override
    public void guardarJson(String imagen, String nombre, String primerApellido,
                            String segundoApellido, String genero,
                            String telefono, String fecha) {
        Uri uri = Uri.parse( "content://com.rafaels.testvocces/db");
        ContentValues valores = new ContentValues();
        valores.put("imagen", imagen);
        valores.put("nombre", nombre);
        valores.put("primerApellido", primerApellido);
        valores.put("segundoApellido", segundoApellido);
        valores.put("genero", genero);
        valores.put("telefono", telefono);
        valores.put("fecha", fecha);
        try {
            activity.getContentResolver().insert(uri, valores);
        } catch (Exception e) {
//            Toast.makeText(activity, "Verificar que está instalado ”+ “com.rafaels.testvocces",Toast.LENGTH_LONG).show();
//            Log.e("TestVocces", "Error: " + e.toString(), e);
        }
    }

    @Override
    public Vector<String> listaJson(int cantidad) {
        Vector<String> result = new Vector<String>();
        Uri uri = Uri.parse( "content://com.rafaels.testvocces/db");
        Cursor cursor = activity. getContentResolver().query (uri, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String imagen = cursor.getString( cursor.getColumnIndex("imagen"));
                String nombre = cursor.getString( cursor.getColumnIndex("nombre"));
                String primerApellido = cursor.getString( cursor.getColumnIndex("primerApellido"));
                String segundoApellido = cursor.getString( cursor.getColumnIndex("segundoApellido"));
                String genero = cursor.getString( cursor.getColumnIndex("genero"));
                String telefono = cursor.getString( cursor.getColumnIndex("telefono"));
                String fecha = cursor.getString( cursor.getColumnIndex("fecha"));
                result.add(imagen + " " + nombre + " " + primerApellido + " " + segundoApellido +
                " " + genero + " " + telefono + " " + fecha);
            }
        }
        return result;
    }
}
