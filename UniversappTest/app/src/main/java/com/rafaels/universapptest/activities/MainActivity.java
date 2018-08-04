package com.rafaels.universapptest.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.rafaels.universapptest.R;
import com.rafaels.universapptest.fragments.MainFragment;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        launchMainFragment();

    }

    private void launchMainFragment(){
        MainFragment mainFragment = (MainFragment)getFragmentManager()
                .findFragmentByTag(MainFragment.TAG);

        if(mainFragment == null){
            mainFragment = new MainFragment();
            getFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.fragment_root, mainFragment, MainFragment.TAG)
                    .commit();
        }
    }

}
