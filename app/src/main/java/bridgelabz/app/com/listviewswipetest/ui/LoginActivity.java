package bridgelabz.app.com.listviewswipetest.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bridgelabz.app.com.listviewswipetest.R;
import bridgelabz.app.com.listviewswipetest.base.BaseActivity;
import bridgelabz.app.com.listviewswipetest.utils.Constants;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "LoginActivity";
    AppCompatEditText editTextLogEm;
    AppCompatEditText editTextLogPass;
    AppCompatButton logBtn;
    AppCompatButton buttonCreateAcc;
    Pattern EmailPattern, Passwordpattern;
    Matcher mMatcher, matcher;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    @Override
    public void initView() {
        editTextLogEm = (AppCompatEditText) findViewById(R.id.editTextEmllog);
        editTextLogPass = (AppCompatEditText) findViewById(R.id.editTextPasslog);
        logBtn = (AppCompatButton) findViewById(R.id.buttonLog);
        buttonCreateAcc = (AppCompatButton) findViewById(R.id.buttonCreateAcc);
        setListeners();
    }

    @Override
    public void setListeners() {
        logBtn.setOnClickListener(this);
        buttonCreateAcc.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonLog:
                validate();
                break;

            case R.id.buttonCreateAcc:
                Intent intent = new Intent(LoginActivity.this, RegisterationActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void validate() {
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.keys, Context.MODE_PRIVATE);
        String email_login = sharedPreferences.getString("email", Constants.values);
        String password_login = sharedPreferences.getString("password", Constants.values);
        EmailPattern = Pattern.compile(Constants.EMAIL_PATTERN);
        mMatcher = EmailPattern.matcher(editTextLogEm.getText().toString());

        Passwordpattern = Pattern.compile(Constants.Password_Pattern);
        matcher = Passwordpattern.matcher(editTextLogPass.getText().toString());

        if (editTextLogEm.getText().toString().length() == 0) {
            editTextLogEm.setError(getString(R.string.Emailid_should_not_empty));
            editTextLogEm.requestFocus();
            //editTextPassword.setError("Valid pswrd");
        } else if (mMatcher.matches()) {

        } else {
            editTextLogEm.setError(getString(R.string.invalid_Emailid));
            editTextLogEm.requestFocus();
        }

        if (editTextLogPass.getText().toString().length() == 0) {
            editTextLogPass.setError(getString(R.string.Password_should_not_empty));
            editTextLogPass.requestFocus();
        } else if (matcher.matches()) {


        } else {
            editTextLogPass.setError("invalid Password");
            editTextLogPass.requestFocus();
        }
        if (editTextLogEm.getText().toString().equalsIgnoreCase(email_login) && editTextLogPass.getText().toString().equalsIgnoreCase(password_login)) {
            Toast.makeText(LoginActivity.this, getResources().getString(R.string.login_successfull), Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("islogin", true);
            editor.commit();
            Intent intent = new Intent(LoginActivity.this, TodoHomeActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(LoginActivity.this, R.string.details, Toast.LENGTH_LONG).show();
        }

    }
}

