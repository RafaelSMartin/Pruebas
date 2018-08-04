package org.rafaels.tdconsulting.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.rafaels.tdconsulting.R;
import org.rafaels.tdconsulting.model.remote.APIService;
import org.rafaels.tdconsulting.model.remote.ApiUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertFragment extends Fragment {

    public static final String TAG = "InsertFragment";

    @BindView(R.id.nombre)
    EditText nombre;

    @BindView(R.id.apellidos)
    EditText apellidos;

    @BindView(R.id.telefono)
    EditText telefono;

    @BindView(R.id.insert)
    Button insert;

    private String name, surname, phone;
    private APIService mAPIService;


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.insert_fragment, container, false);
        ButterKnife.bind(this, view);

        nombre.setText("Rafael");
        apellidos.setText("Martin Gonzalez");
        telefono.setText("666864748");

        mAPIService = ApiUtils.getApiService();


        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = nombre.getText().toString();
                surname = apellidos.getText().toString();
                phone = telefono.getText().toString();

                sendInsertDatos();
            }
        });

        return view;
    }

    private void sendInsertDatos() {
        mAPIService.login(name, surname, phone).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("RetroFit1",  call.toString()+"");
                Log.d("RetroFit2",  response.toString()+"");
                Log.d("RetroFit3",  response.message()+"");
                Log.d("RetroFit4",  response.isSuccessful()+"");
                Log.d("RetroFit5",  response.code()+"");
                switch (response.code()) {
                    case 200:
                        Toast.makeText(getActivity(), "insreccion ok", Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        break;
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("RetroFitError", t.getCause().getMessage());
            }
        });


    }





}
