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

public class DaftarPegawai extends AppCompatActivity {
    Button bbarang;
    ListView listView;
    Toolbar toolbar;
    SqliteHelper sqliteHelper;
    ArrayList<String> listData;
    AlertDialog.Builder dialog;
    public static String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_pegawai);
        listView = findViewById(R.id.listView);
        bbarang =findViewById(R.id.addpegawai);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        bbarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DaftarPegawai.this, Add_Pegawai.class);
                startActivity(intent);
            }
        });
        listData = new ArrayList<>();
        sqliteHelper = new SqliteHelper(getBaseContext());
        getData();
        listView.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listData));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, View view, final int position, long l) {final CharSequence[] dialogitem = {"Edit", "Delete"};
                final CharSequence[] dialogitempegawai = {"Edit", "Delete"};
                dialog = new AlertDialog.Builder(DaftarPegawai.this);
                dialog.setCancelable(true);
                dialog.setItems(dialogitempegawai, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0){
                            Toast.makeText(DaftarPegawai.this, "edit", Toast.LENGTH_SHORT).show();
//                            Toast.makeText(DaftarPegawai.this, adapterView.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
                            name = adapterView.getItemAtPosition(position).toString();
//
                            Intent intent = new Intent(DaftarPegawai.this,EditPegawai.class);
                            startActivity(intent);
                        }else if (i == 1){
                            SQLiteDatabase db = sqliteHelper.getWritableDatabase();
                            db.execSQL("delete from barangs where username = '" + name + "'");
                            Toast.makeText(DaftarPegawai.this, "data telah dihapus", Toast.LENGTH_SHORT).show();

                        }
                    }
                }).show();

//                Toast.makeText(DaftarPegawai.this,adapterView.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(DaftarPegawai.this,FromUpdatePegawai.class);
//                startActivity(intent);
            }
        });

    }
    private void getData(){
        //Mengambil Repository dengan Mode Membaca
        SQLiteDatabase ReadData = sqliteHelper.getReadableDatabase();
        Cursor cursor = ReadData.rawQuery("SELECT * FROM users",null);

        cursor.moveToFirst();//Memulai Cursor pada Posisi Awal

        //Melooping Sesuai Dengan Jumlan Data (Count) pada cursor
        for(int count=0; count < cursor.getCount(); count++){

            cursor.moveToPosition(count);//Berpindah Posisi dari no index 0 hingga no index terakhir

            listData.add(cursor.getString(1));//Menambil Data Dari Kolom 1 (Nama)
            //Lalu Memasukan Semua Datanya kedalam ArrayList
        }
    }
    
}
