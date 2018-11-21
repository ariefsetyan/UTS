package com.example.arief.kasirsqlite;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.arief.kasirsqlite.helper.SqliteHelper;

import java.util.ArrayList;

public class DaftarSupplier extends AppCompatActivity {
    Toolbar toolbar;
    ListView listView;
    Button addsuppler;
    SqliteHelper sqliteHelper;
    ArrayList<String> listData;
    public static String id;


    //    Adapter adapter;
//    List<Barang> itemList = new ArrayList<Barang>();
    AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_supplier);
        toolbar=findViewById(R.id.toolbar);
        listView = findViewById(R.id.listView);
        addsuppler = findViewById(R.id.addSupplier);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addsuppler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(DaftarSupplier.this,FormSupplier.class);
                startActivity(intent);

                addsuppler.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent1 =new Intent(DaftarSupplier.this,EditSupplier.class);
                        startActivity(intent);
                    }
                });

            }
        });
        listData = new ArrayList<>();
        sqliteHelper = new SqliteHelper(getBaseContext());
        getData();
        listView.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listData));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, View view, final int p, long l) {
                final CharSequence[] dialogitem = {"Edit", "Delete"};
                dialog = new AlertDialog.Builder(DaftarSupplier.this);
                dialog.setCancelable(true);
                dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0){
                            Toast.makeText(DaftarSupplier.this, "edit", Toast.LENGTH_SHORT).show();
//                            Toast.makeText(DaftarBarang.this, adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
                            id = adapterView.getItemAtPosition(p).toString();

                            Intent intent = new Intent(DaftarSupplier.this,EditSupplier.class);
                            startActivity(intent);
                        }else if (i == 1){
                            SQLiteDatabase db = sqliteHelper.getWritableDatabase();
                            db.execSQL("delete from suppliers where namasupplier = '" + id + "'");
                            Toast.makeText(DaftarSupplier.this, "data supplier telah dihapus", Toast.LENGTH_SHORT).show();

                        }
                    }
                }).show();
            }
        });


    }
    private void getData(){
        //Mengambil Repository dengan Mode Membaca
        SQLiteDatabase ReadData = sqliteHelper.getReadableDatabase();
        Cursor cursor = ReadData.rawQuery("SELECT * FROM suppliers",null);

        cursor.moveToFirst();//Memulai Cursor pada Posisi Awal

        //Melooping Sesuai Dengan Jumlan Data (Count) pada cursor
        for(int count=0; count < cursor.getCount(); count++){

            cursor.moveToPosition(count);//Berpindah Posisi dari no index 0 hingga no index terakhir

            listData.add(cursor.getString(1));//Menambil Data Dari Kolom 1 (Nama)
            //Lalu Memasukan Semua Datanya kedalam ArrayList
        }

    }

}
