package model;

import db.DbConnection;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;
import java.util.*;

public class Mahasiswa {

    private int id;
    private String nama;
    private String nim;
    private int tahunMasuk;
    private int sks;
    private int jenisId;
    private int biaya;


    public Mahasiswa(String nama, String nim, int tahunMasuk, int sks, int jenisId, int biaya) {
    this.nama = nama;
    this.nim = nim;
    this.tahunMasuk = tahunMasuk;
    this.sks = sks;
    this.jenisId = jenisId;
    this.biaya = biaya;
}
public Mahasiswa(int id, String nama, String nim, int tahunMasuk, int sks, int jenisId, int biaya) {
    this.id = id;
    this.nama = nama;
    this.nim = nim;
    this.tahunMasuk = tahunMasuk;
    this.sks = sks;
    this.jenisId = jenisId;
    this.biaya = biaya;
}



    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getNim() {
        return nim;
    }

    public int getSks() {
        return sks;
    }

    public int getJenisId() {
        return jenisId;
    }

    public int getBiaya() {
        return biaya;
    }
    public int getTahunMasuk() {
        return tahunMasuk;
}
    public void setTahunMasuk(int tahunMasuk) {
        this.tahunMasuk = tahunMasuk;
}
}
