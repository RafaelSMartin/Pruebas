package com.rafaels.universapptest.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rafaels.universapptest.R;
import com.rafaels.universapptest.utils.Util;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailFragment extends Fragment
{
    public static final String TAG = "DETAIL_FRAGMENT";

    public static String IMAGE = "image_bundle";
    public static String NAME = "name_bundle";
    public static String SURNAME = "surname_bundle";
    public static String PHONE = "phone_bundle";
    public static String DATE = "date_bundle";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.image_custom)
    ImageView imageDetail;

    @BindView(R.id.nombre_custom)
    TextView nameDetail;

    @BindView(R.id.apellido_custom)
    TextView surnameDetail;

    @BindView(R.id.telefono_custom)
    TextView phoneDetail;

    @BindView(R.id.fecha_custom)
    TextView dateDetail;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);

        Bundle args = getArguments();
        if(args != null){
            String image = args.getString(IMAGE);
            Glide.with(getActivity())
                    .load(image)
                    .apply(RequestOptions.circleCropTransform())
                    .into(imageDetail);
            nameDetail.setText(Util.ucFirst(args.getString(NAME)));
            surnameDetail.setText(Util.ucFirst(args.getString(SURNAME)));
            phoneDetail.setText(args.getString(PHONE));
            dateDetail.setText((args.getString(DATE)));

        }

        initToolbar();

        return view;
    }

    private void initToolbar(){
        toolbar.setTitle(nameDetail.getText());
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(v ->
                getActivity().onBackPressed()
        );
    }



}
