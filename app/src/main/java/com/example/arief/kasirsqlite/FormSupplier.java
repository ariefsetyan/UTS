package com.example.arief.kasirsqlite;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.arief.kasirsqlite.helper.SqliteHelper;
import com.example.arief.kasirsqlite.helper.Supplier;

import java.util.ArrayList;

public class FormSupplier extends AppCompatActivity {
    LinearLayout linearLayout;
    Toolbar toolbar;
    Button simpan;
    TextInputLayout kodelayoat,namaLayout,alamatLayout,noLayout;
    AppCompatEditText kode,nama,alamat,notlp;
    SqliteHelper sqliteHelper;
    ArrayList<String> listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_supplier);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        simpan = (Button) findViewById(R.id.simpan);
        kodelayoat = (TextInputLayout) findViewById(R.id.kode_textInputlayout);
        kode = (AppCompatEditText)findViewById(R.id.kodesupplier);
        namaLayout = (TextInputLayout)findViewById(R.id.namatextInputlayout);
        nama = (AppCompatEditText)findViewById(R.id.namasupplier);
        alamatLayout = (TextInputLayout)findViewById(R.id.alamattextInputlayout);
        alamat = (AppCompatEditText)findViewById(R.id.alamat);
        noLayout = (TextInputLayout)findViewById(R.id.notextInputlayout);
        notlp = (AppCompatEditText) findViewById(R.id.notlp);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sqliteHelper = new SqliteHelper(this);
//        listData = new ArrayList<>();
//        sqliteHelper = new SqliteHelper(getBaseContext());
        nama.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (nama.getText().toString().isEmpty()){
                    namaLayout.setErrorEnabled(true);
                    namaLayout.setError("Please enter Nama");
                }else {
                    namaLayout.setErrorEnabled(false);
                }
            }
        });
        nama.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (nama.getText().toString().isEmpty()){
                    namaLayout.setErrorEnabled(true);
                    namaLayout.setError("Please enter Nama");
                }else {
                    namaLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        alamat.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (alamat.getText().toString().isEmpty()){
                    alamatLayout.setErrorEnabled(true);
                    alamatLayout.setError("Please enter harga");
                }else {
                    alamatLayout.setErrorEnabled(false);
                }
            }
        });
        alamat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (alamat.getText().toString().isEmpty()){
                    alamatLayout.setErrorEnabled(true);
                    alamatLayout.setError("Please enter harga");
                }else {
                    alamatLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        notlp.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (notlp.getText().toString().isEmpty()){
                    noLayout.setErrorEnabled(true);
                    noLayout.setError("Please enter stok");
                }else {
                    noLayout.setErrorEnabled(false);
                }
            }
        });
        notlp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (notlp.getText().toString().isEmpty()){
                    noLayout.setErrorEnabled(true);
                    noLayout.setError("Please enter stok");
                }else {
                    noLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    String KodeSupplier = kode.getText().toString();
                    String NamaSupplier = nama.getText().toString();
                    String AlamatSupplier = alamat.getText().toString();
                    String NoSupplier = notlp.getText().toString();

                    //Check in the database is there any user associated with  this email
                    if (!sqliteHelper.isEmailExists(KodeSupplier)) {

                        //Email does not exist now add new user to database
                        sqliteHelper.addsupplier(new Supplier(KodeSupplier,NamaSupplier,AlamatSupplier,NoSupplier));
                        Snackbar.make(simpan, "Data Berhasi Disimpan ", Snackbar.LENGTH_LONG).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
//                                finish();
                                Intent intent = new Intent(FormSupplier.this,DaftarSupplier.class);
                                startActivity(intent);
                            }
                        }, Snackbar.LENGTH_LONG);
                    }else {

                        //Email exists with email input provided so show error user already exist
                        Snackbar.make(simpan, "Data already exists with same id ", Snackbar.LENGTH_LONG).show();
                    }


                }
            }
        });
    }
    //This method is used to validate input given by user
    public boolean validate() {
        boolean valid = false;

        //Get values from EditText fields
        String kodeSupplier = kode.getText().toString();
        String namaSupplier = nama.getText().toString();
        String alamatSuppplier = alamat.getText().toString();
        String notpl = notlp.getText().toString();

        //Handling validation for Email field
        if (kodeSupplier.isEmpty()) {
            valid = false;
            kode.setError("Please enter valid kode!");
        } else {
            valid = true;
            kode.setError(null);
        }

        //Handling validation for nama barang field
        if (namaSupplier.isEmpty()) {
            valid = false;
            nama.setError("Please enter valid kode!");
        } else {
            valid = true;
            nama.setError(null);
        }
        //Handling validation for harga barang field
        if (alamatSuppplier.isEmpty()) {
            valid = false;
            alamat.setError("Please enter valid kode!");
        } else {
            valid = true;
            alamat.setError(null);
        }
        //Handling validation for stok barang field
        if (notpl.isEmpty()) {
            valid = false;
            notlp.setError("Please enter valid kode!");
        } else {
            valid = true;
            notlp.setError(null);
        }

        return valid;
    }

}
