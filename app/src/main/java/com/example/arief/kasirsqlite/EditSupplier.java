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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.arief.kasirsqlite.helper.SqliteHelper;

import java.util.ArrayList;

public class EditSupplier extends AppCompatActivity {
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
        setContentView(R.layout.activity_edit_supplier);

        simpan = (Button) findViewById(R.id.simpan);
        kodelayoat = (TextInputLayout) findViewById(R.id.kode_textInputlayout);
        kode = (AppCompatEditText) findViewById(R.id.kodesupplier);
        namaLayout = (TextInputLayout) findViewById(R.id.namatextInputlayout);
        nama = (AppCompatEditText) findViewById(R.id.namasupplier);
        alamatLayout = (TextInputLayout) findViewById(R.id.alamattextInputlayout);
        alamat = (AppCompatEditText) findViewById(R.id.alamat);
        noLayout = (TextInputLayout) findViewById(R.id.notextInputlayout);
        notlp = (AppCompatEditText) findViewById(R.id.notlp);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sqliteHelper = new SqliteHelper(this);
        linearLayout=(LinearLayout)findViewById(R.id.linierLayout);
        linearLayout.setOnClickListener(null);

        //        listData = new ArrayList<>();
//        sqliteHelper = new SqliteHelper(getBaseContext());
        nama.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (nama.getText().toString().isEmpty()) {
                    namaLayout.setErrorEnabled(true);
                    namaLayout.setError("Please enter Nama");
                } else {
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
                if (nama.getText().toString().isEmpty()) {
                    namaLayout.setErrorEnabled(true);
                    namaLayout.setError("Please enter Nama");
                } else {
                    namaLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        alamat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (alamat.getText().toString().isEmpty()) {
                    alamatLayout.setErrorEnabled(true);
                    alamatLayout.setError("Please enter harga");
                } else {
                    alamatLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        notlp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (notlp.getText().toString().isEmpty()) {
                    noLayout.setErrorEnabled(true);
                    noLayout.setError("Please enter harga");
                } else {
                    noLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        getData();
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = sqliteHelper.getWritableDatabase();
                db.execSQL("update barangs set namasupplier='"+
                        nama.getText().toString() +"', alamatsupplier='" +
                        alamat.getText().toString()+"', notelp='"+
                        notlp.getText().toString() +"'"
                        + " WHERE namasupplier =" + "'" + DaftarSupplier.id + "'");
                Toast.makeText(EditSupplier.this, "data supplier berhasil di simpan", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getData(){
        //Mengambil Repository dengan Mode Membaca
        SQLiteDatabase ReadData = sqliteHelper.getReadableDatabase();
        Toast.makeText(this, DaftarBarang.id, Toast.LENGTH_SHORT).show();
        Cursor cursor = ReadData.rawQuery("SELECT * FROM suppliers where namasupplier = '"+ DaftarSupplier.id+"'",null);

        cursor.moveToFirst();//Memulai Cursor pada Posisi Awal

        //Melooping Sesuai Dengan Jumlan Data (Count) pada cursor
        for(int count=0; count < cursor.getCount(); count++) {

            cursor.moveToPosition(count);//Berpindah Posisi dari no index 0 hingga no index terakhir

            nama.setText(cursor.getString(1).toString());
            alamat.setText(cursor.getString(2).toString());
            notlp.setText(cursor.getString(3).toString());
            //listData.add(cursor.getString(4).toString());//Menambil Data Dari Kolom 1 (Nama)
            //Lalu Memasukan Semua Datanya kedalam ArrayList
        }
    }

}
