package com.example.arief.kasirsqlite.helper;

public class Barang {
    public String id;
    public String namBarang;
    public String harga;
    public String stok;
    public String idSupplier;

    public Barang(String id, String namBarang, String harga, String stok,String idSupplier) {
        this.id = id;
        this.namBarang = namBarang;
        this.harga = harga;
        this.stok = stok;
        this.idSupplier = idSupplier;
    }
    public String getId(){
        return id;
    }
    public String getnama(){
        return namBarang;
    }
    public String gethaga(){
        return harga;
    }
    public String getStok(){
        return stok;
    }
    public String getIdSupplier(){
        return idSupplier;
    }
}
