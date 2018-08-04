package com.rafaels.testvocces.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rafaels.testvocces.App;
import com.rafaels.testvocces.R;
import com.rafaels.testvocces.adapter.MiAdaptador;
import com.rafaels.testvocces.database.IDatabase;
import com.rafaels.testvocces.database.saveProvider;
import com.rafaels.testvocces.database.saveSQLite;
import com.rafaels.testvocces.model.Model;
import com.rafaels.testvocces.volley.DefaultExclusionStrategy;
import com.rafaels.testvocces.volley.SendRequest;

import java.sql.Array;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rafael S Martin on 27/02/2018.
 */

public class MainFragment extends Fragment implements
        Response.ErrorListener,
        Response.Listener<String>
{
    public static final String TAG = "MAIN_FRAGMENT";

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private RecyclerView.LayoutManager layoutManager;
    private MiAdaptador adaptador;

    public static IDatabase almacen;

    DetailFragment detailFragment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        sendSomething();

        return view;
    }

    private void sendSomething(){
        SendRequest enviarMensaje = new SendRequest("request");
        Request<?> request = enviarMensaje.getRequest(this, this);

        // Instancio el singleton pasandole la peticion y/o alguna etiqueta
        App.getInstance().addToRequestQueue(request, App.TAG);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        if (error instanceof AuthFailureError){
            Log.e("ErrorResponse", "Error credentials" + error.getMessage());
        } else if(error instanceof NetworkError){
            Log.e("ErrorResponse", "Error network" + error.getMessage());
        } else if (error instanceof NoConnectionError){
            Log.e("ErrorResponse", "Error no conection" + error.getMessage());
        } else if (error instanceof ParseError){
            Log.e("ErrorResponse", "Error no process the response" + error.getMessage());
        } else if (error instanceof ServerError){
            Log.e("ErrorResponse", "Error form server" + error.getMessage());
        } else if (error instanceof TimeoutError){
            Log.e( "ErrorResponse", "Error out of time" + error.getMessage());
        }
    }

    @Override
    public void onResponse(String response) {
        GsonBuilder builder = new GsonBuilder();
        builder.setExclusionStrategies(new DefaultExclusionStrategy());
        Gson gson = builder.create();

        Log.d("errores", response.toString()+"");


        final Model modelo = gson.fromJson(response, Model.class);
        //Guardar en la BBDD
//        almacen = new saveSQLite(getActivity());
        almacen = new saveProvider(getActivity());

        Log.d("errores", response.toString()+"");

//        tvResponse.setText(modelo.getResults().toString());
        String imagen;
        String nombre;
        String primerApellido;
        String segundoApellido;
        String genero;
        String telefono;
        String fecha;

        String s ="";
        for (int i = 0; i < modelo.getResults().size(); i++){
            imagen = modelo.getResults().get(i).getPicture().getMedium();
            nombre = modelo.getResults().get(i).getName().getTitle();
            primerApellido = modelo.getResults().get(i).getName().getFirst();
            segundoApellido = modelo.getResults().get(i).getName().getLast();
            genero = modelo.getResults().get(i).getGender();
            telefono = modelo.getResults().get(i).getPhone();
            fecha = modelo.getResults().get(i).getDob().getDate();

            almacen.guardarJson(imagen, nombre, primerApellido, segundoApellido,
                    genero, telefono, fecha);

            s += imagen +"\n"+ nombre +"\n"+ primerApellido +"\n"+
                    segundoApellido+"\n"+ genero +"\n"+ telefono +"\n"+ fecha + "\n"+"\n"+"\n"+"\n";

//            tvResponse.setText(s);

        }

        Log.d("SaveProvider", almacen.listaJson(100).toString());

//        adaptador = new MiAdaptador(getActivity(), almacen.listaJson(100));
        adaptador = new MiAdaptador(getActivity(), modelo);
        recyclerView.setAdapter(adaptador);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adaptador.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos= recyclerView.getChildAdapterPosition(v);

//                String s = almacen.listaJson(100).get(pos);
//                Toast.makeText(getActivity(), "Selección: " + pos + " - " + s, Toast.LENGTH_LONG).show();

                detailFragment = (DetailFragment) getActivity().getSupportFragmentManager().findFragmentByTag(DetailFragment.TAG);

                if(detailFragment == null){
                    detailFragment = new DetailFragment();
                    Bundle args = new Bundle();
                    args.putString(DetailFragment.IMAGEN, modelo.getResults().get(pos)
                            .getPicture().getLarge());
                    args.putString(DetailFragment.NOMBRE, modelo.getResults().get(pos)
                            .getName().getFirst());
                    args.putString(DetailFragment.APELLIDO, modelo.getResults().get(pos)
                            .getName().getLast());
                    args.putString(DetailFragment.TELEFONO, modelo.getResults().get(pos)
                            .getPhone());
                    args.putString(DetailFragment.FECHA, modelo.getResults().get(pos)
                            .getDob().getDate());
                    detailFragment.setArguments(args);
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_root, detailFragment, DetailFragment.TAG)
                            .addToBackStack(DetailFragment.TAG)
                            .commit();
                }

            }
        });
//        tvResponse.setText(almacen.listaJson(100).toString());

    }


}
