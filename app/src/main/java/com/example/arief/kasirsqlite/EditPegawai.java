package com.example.arief.kasirsqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.arief.kasirsqlite.helper.SqliteHelper;

public class EditPegawai extends AppCompatActivity {Toolbar toolbar;
    AppCompatEditText Enama, Eemail, Epass;
    TextInputLayout nama_layout,email_layout,pas_layout;
    Button simpan;
    SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pegawai);
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

        getData();
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = sqliteHelper.getWritableDatabase();
                db.execSQL("update users set username='"+
                        Enama.getText().toString() +"', email='" +
                        Eemail.getText().toString()+"', password='"+
                        Epass.getText().toString() +"'"
                        + " WHERE username =" + "'" + DaftarPegawai.name + "'");
                Toast.makeText(EditPegawai.this, "data berhasil di simpan", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void getData(){
        //Mengambil Repository dengan Mode Membaca
        SQLiteDatabase ReadData = sqliteHelper.getReadableDatabase();
        Toast.makeText(this, DaftarBarang.id, Toast.LENGTH_SHORT).show();
        Cursor cursor = ReadData.rawQuery("SELECT * FROM users where username = '"+ DaftarPegawai.name+"'",null);

        cursor.moveToFirst();//Memulai Cursor pada Posisi Awal

        //Melooping Sesuai Dengan Jumlan Data (Count) pada cursor
        for(int count=0; count < cursor.getCount(); count++){

            cursor.moveToPosition(count);//Berpindah Posisi dari no index 0 hingga no index terakhir

            //Sid = cursor.getString(0);
           /// kode.setText(Sid);
            Enama.setText(cursor.getString(1).toString());
            Eemail.setText(cursor.getString(2).toString());
            Epass.setText(cursor.getString(3).toString());//Menambil Data Dari Kolom 1 (Nama)
            //Lalu Memasukan Semua Datanya kedalam ArrayList


        }
    }
}
