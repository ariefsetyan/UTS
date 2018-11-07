package com.example.arief.kasirsqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.arief.kasirsqlite.custom.CustomGridViewActivity;

public class Home extends AppCompatActivity {
    Toolbar toolbar;

    GridView androidGridView;

    String CustomGridViewActivity[] = {"Mater Barang",
            "Transaksi",
            "Laporan",

    };
    int gridViewImageId[] = {
            R.drawable.ic_view_agenda_black_24dp,
            R.drawable.ic_shopping_cart_black_24dp,
            R.drawable.ic_assignment_black_24dp
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        androidGridView = (GridView)findViewById(R.id.gridView);
        com.example.arief.kasirsqlite.custom.CustomGridViewActivity customAdapter = new CustomGridViewActivity(Home.this,CustomGridViewActivity,gridViewImageId);
        androidGridView.setAdapter(customAdapter);
        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (CustomGridViewActivity[i] == "Mater Barang"){
                    Intent dbarang = new Intent(Home.this,DataMaster.class);
                    startActivity(dbarang);
                }else if (CustomGridViewActivity[i] == "Transaksi"){
//                    Intent Itransaksi=new Intent(Home.this,Transaksi.class);
//                    startActivity(Itransaksi);
                }else if (CustomGridViewActivity[i] == "Laporan"){
                    Toast.makeText(Home.this, "kode3", Toast.LENGTH_SHORT).show();
                }

//                Intent intent = new Intent(Home.this,AddBarang.class);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if (id==R.id.logout){
            Toast.makeText(this, "logout", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
