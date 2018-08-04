package comeatest.rafaels.org.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import butterknife.BindView;
import butterknife.ButterKnife;
import comeatest.rafaels.org.R;
import comeatest.rafaels.org.util.Utils;

public class DetailFragment extends Fragment {

    public static final String TAG = "DETAIL_FRAGMENT";

    public static String NAME = "name_bundle";
    public static String LOGO = "logo_bundle";
    public static String OPEN_HOUR = "open_hour_bundle";
    public static String CLOSING_HOUR = "closing_hour_bundle";
    public static String LOCATION_LNG = "location_lng_bundle";
    public static String LOCATION_LAT = "location_lat_bundle";
    public static String CATEGORY = "category_bundle";
    public static String ID = "id_bundle";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.name)
    TextView name;

    @BindView(R.id.logo)
    SimpleDraweeView logo;

    @BindView(R.id.hour)
    TextView hour;

    @BindView(R.id.category)
    TextView category;

    @BindView(R.id.id)
    TextView id;

    private String nameTitle;
    private double lat, lng;
    MapView mMapView;
    private GoogleMap googleMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);

        Bundle args = getArguments();
        if(args != null){
            nameTitle = args.getString(NAME);
            name.setText(args.getString(NAME));
            logo.setImageURI(args.getString(LOGO));
            hour.setText("TimeTable: "+ args.getString(OPEN_HOUR)+
                    " - " + args.getString(CLOSING_HOUR));
            lng = Double.parseDouble(args.getString(LOCATION_LNG));
            lat = Double.parseDouble(args.getString(LOCATION_LAT));
            category.setText(args.getString(CATEGORY));
//            id.setText((args.getString(ID)));
        }

        initToolbar();
        initTypeface();
        initGoogleMaps(view, savedInstanceState);

        return view;
    }

    private void initToolbar(){
        toolbar.setTitle(nameTitle);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                getActivity().onBackPressed();
            }
        });
    }

    private void initTypeface(){
        Utils.setTypefaceAvenirMedium(getActivity(), name);
        Utils.setTypefaceAvenirMedium(getActivity(), hour);
        Utils.setTypefaceAvenirMedium(getActivity(), category);
    }

    private void initGoogleMaps(View view, Bundle savedInstanceState){
        mMapView  = view.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume();

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;
                LatLng location = new LatLng(lat, lng); //Nos ubicamos
                mMap.addMarker(new MarkerOptions().position(location).title(nameTitle));

                CameraPosition cameraPosition = new CameraPosition.Builder().target(location).zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}
