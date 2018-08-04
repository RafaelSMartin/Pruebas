package org.rafaels.tdconsulting.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.rafaels.tdconsulting.R;
import org.rafaels.tdconsulting.preferences.PreferencesIsLogin;
import org.rafaels.tdconsulting.utils.Constant;
import org.rafaels.tdconsulting.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    @BindView(R.id.input_email)
    EditText emailText;

    @BindView(R.id.input_password)
    EditText passwordText;

    @BindView(R.id.btn_login)
    Button loginButton;

    private String password, email;
    private String token;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(PreferencesIsLogin.getPreferencesIsLogin(this,
                Constant.PREFERENCES_IS_LOGIN_KEY)){
            Intent i = new Intent(this, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
            finish();
        }

        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        emailText.setText(Constant.email);
        passwordText.setText(Constant.password);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            login();
            }
        });

    }


    public void login() {

        if (!validate()) {
            onLoginFailed();
            return;
        }

        loginButton.setEnabled(false);

        email = emailText.getText().toString();
        password = passwordText.getText().toString();

        sendText();
    }

    @Override
    public void onResume(){
        super.onResume();
        if(PreferencesIsLogin.getPreferencesIsLogin(this, Constant.PREFERENCES_IS_LOGIN_KEY)){
            Utils.launchActivity(LoginActivity.this, MainActivity.class);
        }
    }

    public void onLoginSuccess() {
        Toast.makeText(getBaseContext(), R.string.login_success, Toast.LENGTH_LONG).show();
        loginButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), R.string.login_failed, Toast.LENGTH_LONG).show();
        loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        if (email.isEmpty() || !email.equals("tdconsulting")) {
            emailText.setError(getString(R.string.email_error));
            valid = false;
        } else {
            emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10 ||
                !password.equals("test")) {
            passwordText.setError(getString(R.string.pass_error));
            valid = false;
        } else {
            passwordText.setError(null);
        }

        return valid;
    }

    private void sendText(){
        Utils.launchActivity(this, MainActivity.class);
        onLoginSuccess();
        PreferencesIsLogin.savePreferencesIsLoggin(this,
                Constant.PREFERENCES_IS_LOGIN_KEY, true);
    }


}

