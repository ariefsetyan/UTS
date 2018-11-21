package com.example.arief.kasirsqlite.transaksi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.arief.kasirsqlite.Login;
import com.example.arief.kasirsqlite.R;

public class Penjualan extends AppCompatActivity {
    Button adddata;
    TextView Ttotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.arief.kasirsqlite.R.layout.activity_penjualan);
        Ttotal = findViewById(R.id.text);
        adddata = (Button) findViewById(R.id.barang);

        adddata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Penjualan.this, TBarang.class);
//
                startActivity(intent);
            }
        });
    }
    @Override
    public void onResume(){
        super.onResume();
//        Ttotal.setText(Transaksi.total);
    }
}
