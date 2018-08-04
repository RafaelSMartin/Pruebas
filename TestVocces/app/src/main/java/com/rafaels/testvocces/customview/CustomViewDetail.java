package com.rafaels.testvocces.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rafaels.testvocces.R;

import java.util.jar.Attributes;


public class CustomViewDetail extends LinearLayout{

    private ImageView imageView;
    private TextView nombre;
    private TextView apellido;
    private TextView telefono;
    private TextView fecha;

    public CustomViewDetail(Context context, AttributeSet attrs){
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.detail, this, true);
        imageView = (ImageView) findViewById(R.id.image_custom);
        nombre = (TextView) findViewById(R.id.nombre_custom);
        apellido = (TextView) findViewById(R.id.apellido_custom);

    }


}
