package com.example.arief.kasirsqlite;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import android.content.Intent;

import android.support.design.widget.Snackbar;
import android.widget.TextView;

import com.example.arief.kasirsqlite.helper.SqliteHelper;
import com.example.arief.kasirsqlite.helper.User;

public class Login extends AppCompatActivity {
    AppCompatEditText user, pass;
    RelativeLayout relativeLayout;
    TextInputLayout userLayout, passLayout;
    Button login;
    SqliteHelper sqliteHelper;
    TextView buatakun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        buatakun = findViewById(R.id.buatakun);
        buatakun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,Add_Pegawai.class);
                startActivity(intent);
            }
        });
        login=(Button)findViewById(R.id.login);
        user = (AppCompatEditText)findViewById(R.id.username);
        userLayout = (TextInputLayout)findViewById(R.id.username_TextInputlayout);
        pass = (AppCompatEditText)findViewById(R.id.password);
        passLayout = (TextInputLayout)findViewById(R.id.password_TextInputlayout);
        relativeLayout = (RelativeLayout)findViewById(R.id.relativlayout);
        relativeLayout.setOnClickListener(null);

        sqliteHelper = new SqliteHelper(this);

        user.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (user.getText().toString().isEmpty()){
                    userLayout.setErrorEnabled(true);
                    userLayout.setError("Please enter username!");
                }else{
                    userLayout.setErrorEnabled(false);
                }
            }
        });
        user.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (user.getText().toString().isEmpty()){
                    userLayout.setErrorEnabled(true);
                    userLayout.setError("Please enter username!");
                }else{
                    userLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        passLayout.setCounterEnabled(true);
        passLayout.setCounterMaxLength(8);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check user input is correct or not
                if (validate()) {

                    //Get values from EditText fields
                    String Email = user.getText().toString();
                    String Password = pass.getText().toString();

                    //Authenticate user
                    User currentUser = sqliteHelper.Authenticate(new User(null, null, Email, Password));

                    //Check Authentication is successful or not
                    if (currentUser != null) {
                        Snackbar.make(login, "Successfully Logged in!", Snackbar.LENGTH_LONG).show();

                        //User Logged in Successfully Launch You home screen activity
                        Intent intent=new Intent(Login.this,Home.class);
                        startActivity(intent);
                        finish();

                    } else {

                        //User Logged in Failed
                        Snackbar.make(login, "Failed to log in , please try again", Snackbar.LENGTH_LONG).show();

                    }
                }
            }
        });
    }
    //This method is used to validate input given by user
    public boolean validate() {
        boolean valid = false;

        //Get values from EditText fields
        String Email = user.getText().toString();
        String Password = pass.getText().toString();

        //Handling validation for Email field
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            valid = false;
            user.setError("Please enter valid email!");
        } else {
            valid = true;
            user.setError(null);
        }

        //Handling validation for Password field
        if (Password.isEmpty()) {
            valid = false;
            pass.setError("Please enter valid password!");
        } else {
            if (Password.length() > 5) {
                valid = true;
                pass.setError(null);
            } else {
                valid = false;
                pass.setError("Password is to short!");
            }
        }

        return valid;
    }
}
