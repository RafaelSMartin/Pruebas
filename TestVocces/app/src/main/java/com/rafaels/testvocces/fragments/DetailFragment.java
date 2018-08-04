package com.rafaels.testvocces.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rafaels.testvocces.R;
import com.rafaels.testvocces.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rafael S Martin on 27/02/2018.
 */

public class DetailFragment extends Fragment
{
    public static final String TAG = "DETAIL_FRAGMENT";

    public static String IMAGEN = "imagen_bundle";
    public static String NOMBRE = "nombre_bundle";
    public static String APELLIDO = "apellido_bundle";
    public static String TELEFONO = "telefono_bundle";
    public static String FECHA = "fecha_bundle";

    @BindView(R.id.image_custom)
    ImageView imagenDetail;

    @BindView(R.id.nombre_custom)
    TextView nombreDetail;

    @BindView(R.id.apellido_custom)
    TextView apellidoDetail;

    @BindView(R.id.telefono_custom)
    TextView telefonoDetail;

    @BindView(R.id.fecha_custom)
    TextView fechaDetail;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);

        Bundle args = getArguments();
        if(args != null){
            String image = args.getString(IMAGEN);
            Glide.with(getActivity())
                    .load(image)
                    .into(imagenDetail);
            nombreDetail.setText(Util.ucFirst(args.getString(NOMBRE)));
            apellidoDetail.setText(Util.ucFirst(args.getString(APELLIDO)));
            telefonoDetail.setText(args.getString(TELEFONO));

            fechaDetail.setText((args.getString(FECHA)));

        }

        return view;
    }




}
