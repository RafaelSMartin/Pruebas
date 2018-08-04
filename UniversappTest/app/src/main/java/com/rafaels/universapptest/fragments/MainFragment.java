package com.rafaels.universapptest.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.FirebaseFirestore;
import com.rafaels.universapptest.R;
import com.rafaels.universapptest.adapter.CustomAdapter;
import com.rafaels.universapptest.model.Model;
import com.rafaels.universapptest.model.remote.APIService;
import com.rafaels.universapptest.model.remote.ApiUtils;


import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment{
    public static final String TAG = "MAIN_FRAGMENT";

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private APIService mAPIService;
    private FirebaseFirestore db;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        mAPIService = ApiUtils.getApiService();
        db = FirebaseFirestore.getInstance();

        sendRequest();

        return view;
    }

    private void sendRequest() {
        mAPIService.callModel().enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                Log.d("RetroFit1",  call.toString()+"");
                Log.d("RetroFit2",  response.toString()+"");
                Log.d("RetroFit3",  response.message()+"");
                Log.d("RetroFit4",  response.isSuccessful()+"");
                Log.d("RetroFit5",  response.code()+"");
                switch (response.code()) {
                    case 200:
                        db.collection("results").add(response.body());

//                        Query query = FirebaseFirestore.getInstance()
//                                .collection("results")
//                                .limit(8);

//                        FirestoreRecyclerOptions<Model> options = new FirestoreRecyclerOptions
//                                .Builder<Model>().setQuery(query, Model.class).build();

                        loadApdapter(response.body());
                        break;

                    default:
                        break;
                }
            }
            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Log.e("RetroFitError", t.getCause().getMessage());
            }
        });
    }


    private void loadApdapter(final Model model){
        CustomAdapter adapter = new CustomAdapter(getActivity(), model);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter.setOnItemClickListener(view -> {
            int pos= recyclerView.getChildAdapterPosition(view);

            DetailFragment detailFragment = (DetailFragment) getActivity().getFragmentManager()
                    .findFragmentByTag(DetailFragment.TAG);

            if(detailFragment == null){
                detailFragment = new DetailFragment();
                createBundle(model, detailFragment, pos);
                getFragmentManager().beginTransaction()
                        .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                        .replace(R.id.fragment_root, detailFragment, DetailFragment.TAG)
                        .addToBackStack(DetailFragment.TAG)
                        .commit();
            }
        });
    }

    private void createBundle(Model model, DetailFragment detailFragment, int pos){
        Bundle args = new Bundle();
        args.putString(DetailFragment.IMAGE, model.getResults().get(pos)
                .getPicture().getLarge());
        args.putString(DetailFragment.NAME, model.getResults().get(pos)
                .getName().getFirst());
        args.putString(DetailFragment.SURNAME, model.getResults().get(pos)
                .getName().getLast());
        args.putString(DetailFragment.PHONE, model.getResults().get(pos)
                .getPhone());
        args.putString(DetailFragment.DATE, model.getResults().get(pos)
                .getDob().getDate());
        detailFragment.setArguments(args);
    }


}
