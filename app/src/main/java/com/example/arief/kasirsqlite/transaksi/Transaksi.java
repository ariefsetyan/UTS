package com.example.arief.kasirsqlite.transaksi;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arief.kasirsqlite.DaftarBarang;
import com.example.arief.kasirsqlite.R;
import com.example.arief.kasirsqlite.helper.SqliteHelper;

import java.util.ArrayList;
import java.util.Calendar;

public class Transaksi extends AppCompatActivity {
    TextView namabarang,harga,tanggal;
    EditText jumlah;
    Button add;
    SqliteHelper sqliteHelper;
    ArrayList<String> listData;
    ArrayList<Integer> listDataHarga;
    public static int har, total, kuanti;
    String k ;

    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);
        tanggal = findViewById(R.id.tgl);
        tanggal.setText(tanggal_sekarang);

        namabarang = findViewById(R.id.nama);
        harga = findViewById(R.id.harga);
        jumlah = findViewById(R.id.jmh);
        add = findViewById(R.id.add);
        listData = new ArrayList<>();
        listDataHarga = new ArrayList<>();
        sqliteHelper = new SqliteHelper(getBaseContext());
        getData();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kuanti = Integer.parseInt(jumlah.getText().toString());
                har = Integer.parseInt(k);
                total = har * kuanti;
                Toast.makeText(Transaksi.this, "hasil"+total, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Transaksi.this,Penjualan.class);
                startActivity(intent);
            }
        });
    }
    private void getData(){
        //Mengambil Repository dengan Mode Membaca
        SQLiteDatabase ReadData = sqliteHelper.getReadableDatabase();
        Toast.makeText(this, DaftarBarang.id, Toast.LENGTH_SHORT).show();
        Cursor cursor = ReadData.rawQuery("SELECT * FROM barangs where namabarang = '"+ TBarang.name+"'",null);

        cursor.moveToFirst();//Memulai Cursor pada Posisi Awal

        //Melooping Sesuai Dengan Jumlan Data (Count) pada cursor
        for(int count=0; count < cursor.getCount(); count++){

            cursor.moveToPosition(count);//Berpindah Posisi dari no index 0 hingga no index terakhir

//            id.setText(cursor.getString(0));
            namabarang.setText(cursor.getString(1).toString());
            harga.setText(cursor.getString(2).toString());
//            stok.setText(cursor.getString(3).toString());
//            listData.add(cursor.getString(4).toString());//Menambil Data Dari Kolom 1 (Nama)
            //Lalu Memasukan Semua Datanya kedalam ArrayList
            listData.add(cursor.getString(2).toString());

        }
        k= cursor.getString(2);
//        har =cursor.getInt(2);
    }
    public String getCurrentDate(){
        final Calendar c = Calendar.getInstance();
        int year, month, day;
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DATE);
        return day + "/" + (month+1) + "/" + year;
    }
    // cara pemanggilannya seperti ini
    String tanggal_sekarang = getCurrentDate();

    //This method is used to validate input given by user
    public boolean validate() {
        boolean valid = false;

        //Get values from EditText fields
        String jum = jumlah.getText().toString();

        //Handling validation for Email field
        if (jum.isEmpty()) {
            valid = false;
            jumlah.setError("Please enter valid kode!");
        } else {
            valid = true;
            jumlah.setError(null);
        }

        return valid;
    };

}
