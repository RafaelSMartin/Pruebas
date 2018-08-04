package org.rafaels.tdconsulting.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.rafaels.tdconsulting.R;
import org.rafaels.tdconsulting.fragments.ConsultaFragment;
import org.rafaels.tdconsulting.fragments.InsertFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.consulta)
    Button consulta;

    @BindView(R.id.insert)
    Button insert;

    @BindView(R.id.logo)
    ImageView logo;

    private boolean flagMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        showMenu();

        initButton();
    }

    private void initButton() {
        consulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchConsultaFragment();
            }
        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchInsertFragment();
            }
        });
    }


    private void launchConsultaFragment(){
        hideMenu();
        ConsultaFragment consultaFragment = (ConsultaFragment)getFragmentManager()
                .findFragmentByTag(ConsultaFragment.TAG);
        if(consultaFragment == null) {
            consultaFragment = new ConsultaFragment();
            getFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.fragment_root, consultaFragment, ConsultaFragment.TAG)
                    .addToBackStack(ConsultaFragment.TAG)
                    .commit();
        }
    }

    private void launchInsertFragment(){
        hideMenu();
        InsertFragment insertFragment = (InsertFragment)getFragmentManager()
                .findFragmentByTag(InsertFragment.TAG);
        if(insertFragment == null) {
            insertFragment = new InsertFragment();
            getFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.fragment_root, insertFragment, InsertFragment.TAG)
                    .addToBackStack(ConsultaFragment.TAG)
                    .commit();
        }
    }

    private void hideMenu(){
        consulta.setVisibility(View.GONE);
        insert.setVisibility(View.GONE);
        logo.setVisibility(View.GONE);
        flagMenu = false;
    }

    private void showMenu(){
        consulta.setVisibility(View.VISIBLE);
        insert.setVisibility(View.VISIBLE);
        logo.setVisibility(View.VISIBLE);
        flagMenu = true;
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        if(!flagMenu){
            showMenu();
        }else{
            hideMenu();
        }
    }


}
