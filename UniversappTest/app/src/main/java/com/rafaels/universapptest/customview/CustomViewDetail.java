package com.rafaels.universapptest.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rafaels.universapptest.R;

public class CustomViewDetail extends LinearLayout {

    private ImageView imageView;
    private TextView name;
    private TextView surname;
    private TextView phone;
    private TextView date;

    public CustomViewDetail(Context context, AttributeSet attrs){
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.detail, this, true);
        imageView = (ImageView) findViewById(R.id.image_custom);
        name = (TextView) findViewById(R.id.nombre_custom);
        surname = (TextView) findViewById(R.id.apellido_custom);

    }


}
