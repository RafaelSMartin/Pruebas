package org.rafaels.tdconsulting.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.rafaels.tdconsulting.R;
import org.rafaels.tdconsulting.adapter.ConsultaAdapter;
import org.rafaels.tdconsulting.model.Model;
import org.rafaels.tdconsulting.model.remote.APIService;
import org.rafaels.tdconsulting.model.remote.ApiUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConsultaFragment extends Fragment {

    public static final String TAG = "ConsultaFragment";

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private RecyclerView.LayoutManager layoutManager;

    private APIService mAPIService;
    private ConsultaAdapter adapter;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.consulta_fragment, container, false);
        ButterKnife.bind(this, view);

        mAPIService = ApiUtils.getApiService();

        sendConsultaDatos();

        return view;
    }

    public void sendConsultaDatos(){
        mAPIService.callModel().enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                Log.d("RetroFit1",  call.toString()+"");
                Log.d("RetroFit2",  response.toString()+"");
                Log.d("RetroFit3",  response.message()+"");
                Log.d("RetroFit4",  response.isSuccessful()+"");
                Log.d("RetroFit5",  response.code()+"");
                switch (response.code()) {
                    case 200:
//                      for(int i = 0; i<response.body().size();i++) {
//                        Model data = response.body().get(i);
//                      }
                        loadApdapter(response.body());
                        break;

                    default:
                        break;
                }
            }
            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
                Log.e("RetroFitError", t.getCause().getMessage());
            }
        });
    }

    private void loadApdapter(List<Model> model){
        adapter = new ConsultaAdapter(getActivity(), model);
        recyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
    }





}
