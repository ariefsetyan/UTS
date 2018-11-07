package com.example.arief.kasirsqlite.helper;

public class DetilTransaksi {
    public String idTransaksi;
    public String idBarang;
    public String idUser;

    public DetilTransaksi(String idTransaksi, String idBarang, String idUser) {
        this.idTransaksi = idTransaksi;
        this.idBarang = idBarang;
        this.idUser = idUser;
    }
}