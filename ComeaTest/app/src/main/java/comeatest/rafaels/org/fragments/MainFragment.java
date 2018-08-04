package comeatest.rafaels.org.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import comeatest.rafaels.org.R;
import comeatest.rafaels.org.adapter.RestaurantAdapter;
import comeatest.rafaels.org.model.Model;
import comeatest.rafaels.org.model.remote.APIService;
import comeatest.rafaels.org.model.remote.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment{
    public static final String TAG = "MainFragment";

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private RecyclerView.LayoutManager layoutManager;
    private RestaurantAdapter adapter;

    private APIService mAPIService;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
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
                      for(int i = 0; i < response.body().size(); i++) {
                        Model data = response.body().get(i);
                        Log.d("response0: ", data.toString());
                        Log.d("response1: ", data.getName());
                      }
                        loadApdapter(response.body());
                        break;

                    default:
                        break;
                }
            }
            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
                Log.d("RetroFitError", t.getCause().getMessage());
            }
        });
    }


    private void loadApdapter(final List<Model> modelList){
        adapter = new RestaurantAdapter(getActivity(), modelList);
        recyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = recyclerView.getChildAdapterPosition(view);
                launchDetailFragment(modelList.get(pos));
//                Toast.makeText(getActivity(), pos+"", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void launchDetailFragment(Model model){
        DetailFragment detailFragment = (DetailFragment) getActivity()
                .getFragmentManager()
                .findFragmentByTag(DetailFragment.TAG);

        if(detailFragment == null){
            detailFragment = new DetailFragment();
            createBundle(model, detailFragment);
            getActivity().getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_root, detailFragment, DetailFragment.TAG)
                    .addToBackStack(DetailFragment.TAG)
                    .commit();
        }

    }

    private void createBundle(Model model, DetailFragment detailFragment){
        Bundle args = new Bundle();

        args.putString(DetailFragment.NAME, model.getName());
        args.putString(DetailFragment.LOGO, model.getLogoUrl());
        args.putString(DetailFragment.OPEN_HOUR, String.valueOf(model.getOpeningHour()));
        args.putString(DetailFragment.CLOSING_HOUR, String.valueOf(model.getClosingHour()));
        args.putString(DetailFragment.LOCATION_LNG, String.valueOf(
                Objects.requireNonNull(model.getLocation()).getLng()));
        args.putString(DetailFragment.LOCATION_LAT, String.valueOf(model.getLocation().getLat()));
        args.putString(DetailFragment.CATEGORY, model.getCategory());
        args.putString(DetailFragment.ID, String.valueOf(model.getId()));

        detailFragment.setArguments(args);
    }

}
