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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.arief.kasirsqlite.helper.Barang;
import com.example.arief.kasirsqlite.helper.SqliteHelper;
import com.example.arief.kasirsqlite.helper.User;


public class FormBarang extends AppCompatActivity {
    LinearLayout linearLayout;
    Toolbar toolbar;
    Button simpan;
    TextInputLayout kodelayoat,namaLayout,harLayout,stokLayout;
    AppCompatEditText kode,nama,harga,stok;
    SqliteHelper sqliteHelper;

    private String[] Item = {"Bakso","Ayam Goreng","Mie Rebus","Nasi Padang",
            "Ikan Bakar","Seblak","Gorengan","Mie Ayam"};
    Spinner supp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_barang);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        supp = findViewById(R.id.spinner);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, Item);

// mengeset Array Adapter tersebut ke Spinner
        supp.setAdapter(adapter);

        sqliteHelper = new SqliteHelper(this);

        kodelayoat=(TextInputLayout)findViewById(R.id.kode_textInputlayout);
        kode = (AppCompatEditText) findViewById(R.id.kodebarang);

        namaLayout=(TextInputLayout)findViewById(R.id.namatextInputlayout);
        nama = (AppCompatEditText) findViewById(R.id.namabarang);

        harLayout=(TextInputLayout)findViewById(R.id.hargatextInputlayout);
        harga=(AppCompatEditText)findViewById(R.id.harga);

        stokLayout = (TextInputLayout)findViewById(R.id.stok_extInputlayout);
        stok=(AppCompatEditText)findViewById(R.id.stok);

        linearLayout=(LinearLayout)findViewById(R.id.linierLayout);
        linearLayout.setOnClickListener(null);

        simpan = findViewById(R.id.simpan);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

        harga.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (harga.getText().toString().isEmpty()){
                    harLayout.setErrorEnabled(true);
                    harLayout.setError("Please enter harga");
                }else {
                    harLayout.setErrorEnabled(false);
                }
            }
        });
        harga.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (harga.getText().toString().isEmpty()){
                    harLayout.setErrorEnabled(true);
                    harLayout.setError("Please enter harga");
                }else {
                    harLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        stok.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (stok.getText().toString().isEmpty()){
                    stokLayout.setErrorEnabled(true);
                    stokLayout.setError("Please enter stok");
                }else {
                    stokLayout.setErrorEnabled(false);
                }
            }
        });
        stok.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (stok.getText().toString().isEmpty()){
                    stokLayout.setErrorEnabled(true);
                    stokLayout.setError("Please enter stok");
                }else {
                    stokLayout.setErrorEnabled(false);
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
                    String KodeBarang = kode.getText().toString();
                    String NamaBarang = nama.getText().toString();
                    String HargaBarang = harga.getText().toString();
                    String StokBarang = stok.getText().toString();
                    String IdSupper = stok.getText().toString();

                    //Check in the database is there any user associated with  this email
                    if (!sqliteHelper.isEmailExists(KodeBarang)) {

                        //Email does not exist now add new user to database
                        sqliteHelper.addbarang(new Barang(KodeBarang,NamaBarang,HargaBarang,StokBarang,IdSupper));
                        Snackbar.make(simpan, "Data Berhasi Disimpan ", Snackbar.LENGTH_LONG).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
//                                finish();
                                Intent intent = new Intent(FormBarang.this,DaftarBarang.class);
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
        String kodeBarang = kode.getText().toString();
        String namaBarang = nama.getText().toString();
        String hargaBarang = harga.getText().toString();
        String stokBarang = stok.getText().toString();

        //Handling validation for Email field
        if (kodeBarang.isEmpty()) {
            valid = false;
            kode.setError("Please enter valid kode!");
        } else {
            valid = true;
            kode.setError(null);
        }

        //Handling validation for nama barang field
        if (namaBarang.isEmpty()) {
            valid = false;
            nama.setError("Please enter valid kode!");
        } else {
            valid = true;
            nama.setError(null);
        }
        //Handling validation for harga barang field
        if (hargaBarang.isEmpty()) {
            valid = false;
            harga.setError("Please enter valid kode!");
        } else {
            valid = true;
            harga.setError(null);
        }
        //Handling validation for stok barang field
        if (stokBarang.isEmpty()) {
            valid = false;
            stok.setError("Please enter valid kode!");
        } else {
            valid = true;
            stok.setError(null);
        }

        return valid;
    }
}
