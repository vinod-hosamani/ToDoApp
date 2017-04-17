package bridgelabz.app.com.listviewswipetest.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bridgelabz.app.com.listviewswipetest.R;
import bridgelabz.app.com.listviewswipetest.base.BaseActivity;
import bridgelabz.app.com.listviewswipetest.utils.Constants;

public class RegisterationActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "RegistrationActivity";
    AppCompatEditText editTextRegName;
    AppCompatEditText editTextRegEm;
    AppCompatEditText editTextRegPass;
    AppCompatEditText editTextRegMob;
    AppCompatEditText editTextRegAddr;
    AppCompatButton saveButton;
    AppCompatButton AlreadyAccountButton;
    Matcher mMatcher;
    Pattern Pattern;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initView();
    }

    @Override
    public void initView() {
        editTextRegName = (AppCompatEditText) findViewById(R.id.editTextName_Reg);
        editTextRegEm = (AppCompatEditText) findViewById(R.id.editTextEmail_Reg);
        editTextRegPass = (AppCompatEditText) findViewById(R.id.editTextPass_Regi);
        editTextRegMob = (AppCompatEditText) findViewById(R.id.editTextMobi_Reg);
        editTextRegAddr = (AppCompatEditText) findViewById(R.id.editTextAddr_Reg);
        saveButton = (AppCompatButton) findViewById(R.id.SaveBtn);
        AlreadyAccountButton = (AppCompatButton) findViewById(R.id.AlreadyAccbtn);
        setListeners();
    }

    @Override
    public void setListeners() {

        saveButton.setOnClickListener(this);
        AlreadyAccountButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.SaveBtn:
                validate();
                break;
            case R.id.AlreadyAccbtn:
                Intent intent = new Intent(RegisterationActivity.this, LoginActivity.class);
                startActivity(intent);
                break;

        }
    }

    public void validate() {
        boolean checkemail = false, checkname = false, checkpassword = false, checkMobile = false, checkAddress = false;

        Pattern mPattern = Pattern.compile(Constants.Password_Pattern);
        mMatcher = mPattern.matcher(editTextRegPass.getText().toString());

        SharedPreferences sh = getSharedPreferences(Constants.keys, Context.MODE_PRIVATE);
        String name = editTextRegName.getText().toString();
        String email = editTextRegEm.getText().toString();
        String password = editTextRegPass.getText().toString();
        String Mobile = editTextRegMob.getText().toString();
        String Adress = editTextRegAddr.getText().toString();

        if (name.isEmpty()) {
            editTextRegName.setError(getString(R.string.name_should_not_empty));

        } else {
            checkname = true;
        }


        if (email.isEmpty()) {
            editTextRegEm.setError(getString(R.string.Emailid_should_not_empty));

        } else if (!isValidEmail(email)) {
            editTextRegEm.setError(getString(R.string.incorrect_Email));
        } else {
            checkemail = true;
        }


        if (password.isEmpty()) {
            editTextRegPass.setError(getString(R.string.password_should_not_empty));

        } else if (password.length() < 5) {
            editTextRegPass.setError(getString(R.string.password_should_be_minimum_5_character));

        } else if (mMatcher.matches()) {
            checkpassword = true;
        } else {
            editTextRegPass.setError(getString(R.string.wrong_format_of_password));
            editTextRegPass.requestFocus();

        }

        if (Mobile.isEmpty()) {
            editTextRegMob.setError(getString(R.string.mobile_no_should_not_be_empty));
        } else if (Mobile.length() < 10) {
            editTextRegMob.setError(getString(R.string.mobile_no_should_be_10_digit));
        } else {
            checkMobile = true;
        }

        if (Adress.isEmpty()) {
            editTextRegAddr.setError(getString(R.string.Address_should_not_be_empty));

        } else {
            checkAddress = true;
        }
        if (checkname && checkMobile && checkAddress && checkemail && checkpassword) {
            SharedPreferences.Editor editor = sh.edit();
            editor.putString("Name", name);
            editor.putString("email", email);
            editor.putString("password", password);
            editor.putString("Mobile", Mobile);
            editor.putString("Adress", Adress);
            editor.putBoolean("islogin", false);
            editor.commit();
            Toast.makeText(getApplicationContext(), R.string.registration_successful, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();

        }

    }

    private boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}

