package com.mycompany.miniestore;

import java.util.ArrayList;
import java.util.List;

public class Provinsi {
    public String nama;
    public int ongkir;

    public Provinsi(String nama, int ongkir) {
        this.nama = nama;
        this.ongkir = ongkir;
    }

    public static List<Provinsi> getDaftarProvinsi() {
        List<Provinsi> daftar = new ArrayList<>();
        daftar.add(new Provinsi("Jawa Timur", 10000));
        daftar.add(new Provinsi("Jawa Tengah", 15000));
        daftar.add(new Provinsi("DI Yogyakarta", 15000));
        daftar.add(new Provinsi("Jawa Barat", 18000));
        daftar.add(new Provinsi("DKI Jakarta", 20000));
        daftar.add(new Provinsi("Banten", 20000));
        daftar.add(new Provinsi("Bali", 22000));
        daftar.add(new Provinsi("Sumatera", 30000));
        daftar.add(new Provinsi("Kalimantan", 35000));
        daftar.add(new Provinsi("Sulawesi", 40000));
        daftar.add(new Provinsi("Papua & Maluku", 55000));
        return daftar;
    }

    public static void displayProvinsi(List<Provinsi> daftar) {
        System.out.println("\n--- DAFTAR PROVINSI & ONGKIR ---");
        System.out.printf("%-20s | %-12s\n", "Provinsi", "Ongkir");
        System.out.println("-----------------------------------");
        for (Provinsi p : daftar) {
            System.out.printf("%-20s | Rp %d\n", p.nama, p.ongkir);
        }
        System.out.println("-----------------------------------");
    }

    public static Provinsi findByName(List<Provinsi> daftar, String keyword) {
        String key = keyword.trim().toLowerCase();
        for (Provinsi p : daftar) {
            if (p.nama.toLowerCase().contains(key)) {
                return p;
            }
        }
        return null;
    }
}