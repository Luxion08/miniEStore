package com.mycompany.miniestore;

import java.util.ArrayList;
import java.util.List;

public class Voucher {
    public int id;
    public String nama;
    public double diskon;
    public double maxDiskon;
    public double minBelanja;

    public Voucher(int id, String nama, double diskon, double maxDiskon, double minBelanja) {
        this.id = id;
        this.nama = nama;
        this.diskon = diskon;
        this.maxDiskon = maxDiskon;
        this.minBelanja = minBelanja;
    }

    public static List<Voucher> getDaftarVoucher() {
        List<Voucher> daftar = new ArrayList<>();
        daftar.add(new Voucher(101, "Diskon 5% sd 150k", 0.05, 150000, 50000));
        daftar.add(new Voucher(102, "Diskon 5% sd 200k", 0.05, 200000, 50000));
        daftar.add(new Voucher(201, "Diskon 10% sd 250k", 0.10, 250000, 75000));
        daftar.add(new Voucher(301, "Diskon 25% sd 500k", 0.25, 500000, 300000));
        return daftar;
    }

    public static void displayVouchers(List<Voucher> daftar) {
        System.out.println("\n--- DAFTAR VOUCHER TERSEDIA ---");
        for (Voucher v : daftar) {
            System.out.printf("ID: %d | %s (Min. Belanja: Rp %.0f)\n", v.id, v.nama, v.minBelanja);
        }
        System.out.println("--------------------------------");
    }

    public static Voucher findById(List<Voucher> daftar, int id) {
        for (Voucher v : daftar) {
            if (v.id == id) {
                return v;
            }
        }
        return null;
    }

    public double hitungDiskon(double subtotal) {
        double potongan = subtotal * this.diskon;
        if (potongan > this.maxDiskon) {
            potongan = this.maxDiskon;
        }
        return potongan;
    }
}
