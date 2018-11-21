package com.example.arief.kasirsqlite;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.arief.kasirsqlite.helper.SqliteHelper;
import com.example.arief.kasirsqlite.helper.User;

public class Add_Pegawai extends AppCompatActivity {
    Toolbar toolbar;
    AppCompatEditText Enama, Eemail, Epass;
    TextInputLayout nama_layout,email_layout,pas_layout;
    Button simpan;
    SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pegawai);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sqliteHelper = new SqliteHelper(this);
        nama_layout = findViewById(R.id.namapegawai_TextInpulLayout);
        Enama = (AppCompatEditText)findViewById(R.id.namapegawai);
        email_layout = findViewById(R.id.email_TextInpulLayout);
        Eemail = (AppCompatEditText)findViewById(R.id.email);
        pas_layout = findViewById(R.id.password_TextInputlayout);
        Epass = (AppCompatEditText) findViewById(R.id.pass);
        simpan = (Button) findViewById(R.id.simpan);
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    String UserName = Enama.getText().toString();
                    String Email = Eemail.getText().toString();
                    String Password = Epass.getText().toString();

                    //Check in the database is there any user associated with  this email
                    if (!sqliteHelper.isEmailExists(Email)) {

                        //Email does not exist now add new user to database
                        sqliteHelper.addUser(new User(null,UserName,Email,Password));
                        Snackbar.make(simpan, "User created successfully! Please Login ", Snackbar.LENGTH_LONG).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
//                                finish();
                                Intent intent = new Intent(Add_Pegawai.this,Login.class);
                                startActivity(intent);
                            }
                        }, Snackbar.LENGTH_LONG);
                    }else {

                        //Email exists with email input provided so show error user already exist
                        Snackbar.make(simpan, "User already exists with same email ", Snackbar.LENGTH_LONG).show();
                    }


                }

            }
        });
    }
    public boolean validate() {
        boolean valid = false;

        //Get values from EditText fields
        String UserName = Enama.getText().toString();
        String Email = Eemail.getText().toString();
        String Password = Epass.getText().toString();

        //Handling validation for UserName field
        if (UserName.isEmpty()) {
            valid = false;
            nama_layout.setError("Please enter valid username!");
        } else {
            if (UserName.length() > 5) {
                valid = true;
                nama_layout.setError(null);
            } else {
                valid = false;
                nama_layout.setError("Username is to short!");
            }
        }

        //Handling validation for Email field
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            valid = false;
            email_layout.setError("Please enter valid email!");
        } else {
            valid = true;
            email_layout.setError(null);
        }

        //Handling validation for Password field
        if (Password.isEmpty()) {
            valid = false;
            pas_layout.setError("Please enter valid password!");
        } else {
            if (Password.length() > 5) {
                valid = true;
                pas_layout.setError(null);
            } else {
                valid = false;
                pas_layout.setError("Password is to short!");
            }
        }

        return valid;
    }
}
