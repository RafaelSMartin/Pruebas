package com.rafaels.testvocces.database;

import android.net.Uri;

import java.util.List;
import java.util.Vector;

/**
 * Created by Rafael S Martin on 27/02/2018.
 */

public interface IDatabase {

    void guardarJson(String imagen, String nombre,
                            String primerApellido, String segundoApellido,
                            String genero, String telefono, String fecha);

    Vector<String> listaJson(int cantidad);

}
