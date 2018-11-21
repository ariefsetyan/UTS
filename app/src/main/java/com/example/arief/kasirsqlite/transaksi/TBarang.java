package com.example.arief.kasirsqlite.transaksi;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.arief.kasirsqlite.R;
import com.example.arief.kasirsqlite.helper.SqliteHelper;

import java.util.ArrayList;

public class TBarang extends AppCompatActivity {
    ListView listView;
    SqliteHelper sqliteHelper;
    public static ArrayList<String> listData;
    public static String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tbarang);
        listView = findViewById(R.id.listView);
        sqliteHelper = new SqliteHelper(getBaseContext());
        listData = new ArrayList<>();
        getData();
        listView.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listData));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                name = adapterView.getItemAtPosition(i).toString();
                Intent intent = new Intent(TBarang.this,Transaksi.class);
                startActivity(intent);
            }
        });
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
//            listData1.add(cursor.getString(0));

        }
    }
}
