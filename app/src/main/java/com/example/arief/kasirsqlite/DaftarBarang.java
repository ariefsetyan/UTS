package com.example.arief.kasirsqlite;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.arief.kasirsqlite.custom.RecycleAdapter;
import com.example.arief.kasirsqlite.custom.User;
import com.example.arief.kasirsqlite.helper.Barang;
import com.example.arief.kasirsqlite.helper.SqliteHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DaftarBarang extends AppCompatActivity {

    Button bbarang;
    ListView listView;
    Toolbar toolbar;
    SqliteHelper sqliteHelper;
    public static ArrayList<String> listData;
    public static ArrayList<String> listData1;
    public static String id;

//    Adapter adapter;
//    List<Barang> itemList = new ArrayList<Barang>();
    AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_barang);
        listView = findViewById(R.id.listView);
        bbarang =findViewById(R.id.addBarang);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        form barang
        bbarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DaftarBarang.this, FormBarang.class);
                startActivity(intent);
            }
        });

//        tampil list view
//        data array
        listData = new ArrayList<>();
        listData1 = new ArrayList<>();
        sqliteHelper = new SqliteHelper(getBaseContext());
        getData();

        listView.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listData));


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, View view, final int p, long l) {
                final CharSequence[] dialogitem = {"Edit", "Delete"};
                dialog = new AlertDialog.Builder(DaftarBarang.this);
                dialog.setCancelable(true);
                dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0){
                            Toast.makeText(DaftarBarang.this, "edit", Toast.LENGTH_SHORT).show();
//                            Toast.makeText(DaftarBarang.this, adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
                            id = adapterView.getItemAtPosition(p).toString();

                            Intent intent = new Intent(DaftarBarang.this,EditBarang.class);
                            startActivity(intent);
                        }else if (i == 1){
                            SQLiteDatabase db = sqliteHelper.getWritableDatabase();
                            db.execSQL("delete from barangs where namabarang = '" + id + "'");
                            Toast.makeText(DaftarBarang.this, "data telah dihapus", Toast.LENGTH_SHORT).show();

                        }
                    }
                }).show();
            }
        });

//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(final AdapterView<?> adapterView, final View view, final int i, long l) {
//
//                final CharSequence[] dialogitem = {"Edit", "Delete"};
//                dialog = new AlertDialog.Builder(DaftarBarang.this);
//                dialog.setCancelable(true);
//                dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int which) {
//                        switch(which){
//                            case 0:
//                                Toast.makeText(DaftarBarang.this, adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
////                                Intent intent = new Intent(DaftarBarang.this, EditBarang.class);
////                                startActivity(intent);
//                                break;
//                            case 1:
//                                SQLiteDatabase db = sqliteHelper.getWritableDatabase();
//                                db.execSQL("delete from barangs where id = '" + id + "'");
//                                Toast.makeText(DaftarBarang.this, "data telah dihapus", Toast.LENGTH_SHORT).show();
//
//                                break;
//                        }
//                    }
//                }).show();
//                return false;
//            }
//        });

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
//    private void hapus(){
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Konfirmasi");
//        builder.setMessage("Yakin untuk menghapus data?");
//        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                dialogInterface.dismiss();
//            }
//        });
//        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                dialogInterface.dismiss();
//            }
//        });
//    }
}
