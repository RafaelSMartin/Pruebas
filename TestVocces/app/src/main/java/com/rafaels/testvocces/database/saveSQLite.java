package com.rafaels.testvocces.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import java.util.List;
import java.util.Vector;

/**
 * Created by Rafael S Martin on 27/02/2018.
 */

public class saveSQLite extends SQLiteOpenHelper implements IDatabase{

    public saveSQLite(Context context) {
        super(context, "resultadoJson", null, 1);    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE resultadoJson ("+
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "imagen TEXT, nombre TEXT, primerApellido TEXT, segundoApellido TEXT, genero TEXT, telefono INTEGER, fecha TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // En caso de una nueva versión habría que actualizar las tablas
    }

    @Override
    public void guardarJson(String imagen, String nombre,
                            String primerApellido, String segundoApellido,
                            String genero, String telefono, String fecha) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO resultadoJson VALUES ( null, '"+
                imagen+"',  '"+
                nombre+"',  '"+primerApellido+"',  '"+segundoApellido+"',  '"+
                genero+"',  '"+telefono+"',  '"+fecha+"')");
        db.close();
    }

    @Override
    public Vector<String> listaJson(int cantidad) {
        Vector<String> result = new Vector<String>();
        SQLiteDatabase db = getReadableDatabase();

        //Consulta query
        String[] CAMPOS = {"imagen", "nombre", "primerApellido", "segundoApellido",
        "genero", "telefono", "fecha"};
        Cursor cursor=db.query(
                "resultadoJson", //tabla a consultar (FROM)
                CAMPOS, //columnas a devolver (SELECT)
                null, //consulta (WHERE)
                null, //reemplaza "?" de seleccion
                null, //agrupado por (GROUPBY)
                null, //condicion de f. aritmetica
                null, //ordenado por
                Integer.toString(cantidad)); //cantidad max. de registros

        while (cursor.moveToNext()){
            result.add(cursor.getInt(0)+" " +cursor.getString(1));
        }
        cursor.close();
        db.close();
        return result;

    }
}
