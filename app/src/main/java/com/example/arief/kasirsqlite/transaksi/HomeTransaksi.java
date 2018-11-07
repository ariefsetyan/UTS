package com.example.arief.kasirsqlite.transaksi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.arief.kasirsqlite.custom.CustomAdapter;

public class HomeTransaksi extends AppCompatActivity {
    ListView jisi;
    String isinya[] = {"Penjualan", "Pembelian"};
    int flags[] = {com.example.arief.kasirsqlite.R.drawable.ic_shopping_cart_black_24dp,
            com.example.arief.kasirsqlite.R.drawable.ic_add_shopping_cart_black_24dp};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.arief.kasirsqlite.R.layout.activity_home_transaksi);
        jisi=(ListView)findViewById(com.example.arief.kasirsqlite.R.id.listView);
        CustomAdapter adapter = new CustomAdapter(getApplicationContext(), isinya, flags);
        jisi.setAdapter(adapter);
        jisi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (isinya[i] == "Penjualan"){
                    Toast.makeText(HomeTransaksi.this, "penjulan", Toast.LENGTH_SHORT).show();
                }else if (isinya[i]=="Pembelian"){
                    Toast.makeText(HomeTransaksi.this, "pemebelian", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
