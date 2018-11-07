package com.example.arief.kasirsqlite;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.arief.kasirsqlite.custom.RecycleAdapter;
import com.example.arief.kasirsqlite.custom.User;
import com.example.arief.kasirsqlite.helper.SqliteHelper;

import java.util.ArrayList;
import java.util.List;

public class DaftarBarang extends AppCompatActivity {
//    Button addbarang;
//    Toolbar toolbar;
////    RecyclerView recyclerView;
//    ListView listView;
//    private SqliteHelper sqliteHelper;
////    String text [] = {"text","text","text"};
////
////    RecycleAdapter recycler;
////    List<User> datamodel;
//    Cursor cursor;
    Button bbarang;
    ListView listView;
    Toolbar toolbar;
    SqliteHelper sqliteHelper;
    ArrayList<String> listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_barang);
        listView = findViewById(R.id.listView);
        bbarang =findViewById(R.id.addBarang);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        bbarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DaftarBarang.this, FormBarang.class);
                startActivity(intent);
            }
        });
        listData = new ArrayList<>();
        sqliteHelper = new SqliteHelper(getBaseContext());
        getData();
        listView.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listData));

    }
    private void getData(){
        //Mengambil Repository dengan Mode Membaca
        SQLiteDatabase ReadData = sqliteHelper.getReadableDatabase();
        Cursor cursor = ReadData.rawQuery("SELECT * FROM barangs",null);

        cursor.moveToFirst();//Memulai Cursor pada Posisi Awal

        //Melooping Sesuai Dengan Jumlan Data (Count) pada cursor
        for(int count=0; count < cursor.getCount(); count++){

            cursor.moveToPosition(count);//Berpindah Posisi dari no index 0 hingga no index terakhir

            listData.add(cursor.getString(1));//Menambil Data Dari Kolom 1 (Nama)
            //Lalu Memasukan Semua Datanya kedalam ArrayList
        }
    }
}
