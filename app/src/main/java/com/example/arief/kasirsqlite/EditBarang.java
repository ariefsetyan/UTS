package com.example.arief.kasirsqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.Toast;

import com.example.arief.kasirsqlite.helper.SqliteHelper;

import java.util.ArrayList;

public class EditBarang extends AppCompatActivity {
    String Sid,Snama,Sharga,Sstok;
    LinearLayout linearLayout;
    Toolbar toolbar;
    Button simpan;
    TextInputLayout kodelayoat,namaLayout,harLayout,stokLayout;
    AppCompatEditText kode,nama,harga,stok;
    SqliteHelper sqliteHelper;
    ArrayList<String> listData;
    Spinner supp;
    Cursor cursor;

//    public static String id, nama, harga,;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_barang);
        toolbar=(Toolbar)findViewById(R.id.toolbar);

        supp = findViewById(R.id.spinner);
        listData = new ArrayList<>();
        sqliteHelper = new SqliteHelper(getBaseContext());
//        getData();
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, listData);

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

        //        toolbar
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
                SQLiteDatabase db = sqliteHelper.getWritableDatabase();
                db.execSQL("update barangs set namabarang='"+
                        nama.getText().toString() +"', harga='" +
                        harga.getText().toString()+"', stok='"+
                        stok.getText().toString() +"'"
                        + " WHERE namabarang =" + "'" + DaftarBarang.id + "'");
            }
        });

        getData();
    }

    private void getData(){
        //Mengambil Repository dengan Mode Membaca
        SQLiteDatabase ReadData = sqliteHelper.getReadableDatabase();
                Toast.makeText(this, DaftarBarang.id, Toast.LENGTH_SHORT).show();
        Cursor cursor = ReadData.rawQuery("SELECT * FROM barangs where namabarang = '"+ DaftarBarang.id+"'",null);

        cursor.moveToFirst();//Memulai Cursor pada Posisi Awal

        //Melooping Sesuai Dengan Jumlan Data (Count) pada cursor
        for(int count=0; count < cursor.getCount(); count++){

            cursor.moveToPosition(count);//Berpindah Posisi dari no index 0 hingga no index terakhir

            Sid = cursor.getString(0);
            kode.setText(Sid);
            nama.setText(cursor.getString(1).toString());
            harga.setText(cursor.getString(2).toString());
            stok.setText(cursor.getString(3).toString());
            listData.add(cursor.getString(4).toString());//Menambil Data Dari Kolom 1 (Nama)
            //Lalu Memasukan Semua Datanya kedalam ArrayList


        }
    }
}
