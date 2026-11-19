package com.mycompany.miniestore;

import java.util.ArrayList;
import java.util.List;

public class shoppingCart {
    private List<CartItem> items;
    private final double pajak = 0.11;
    private final List<Voucher> vouchers;
    private Voucher activeVoucher = null;

    public shoppingCart() {
        this.items = new ArrayList<>();
        this.vouchers = Voucher.getDaftarVoucher();
    }

    public void addtocart(Product product, int quantity) {
        if (quantity <= 0) {
            System.out.println("Jumlah pembelian harus lebih dari 0.");
            return;
        }
        if (quantity > product.stock) {
            System.out.println("Stok tidak mencukupi! Sisa stok: " + product.stock);
            return;
        }

        CartItem existingItem = null;
        for (CartItem item : items) {
            if (item.getProduct().id == product.id) {
                existingItem = item;
                break;
            }
        }

        if (existingItem != null) {
            if (existingItem.getQuantity() + quantity > product.stock) {
                System.out.println("Total di keranjang melebihi stok tersedia! Sisa stok: " + product.stock);
                return;
            }
            existingItem.addquantity(quantity);
            System.out.println("Updated quantity for " + product.nama + " in cart.");
        } else {
            items.add(new CartItem(product, quantity));
            System.out.println("Added " + product.nama + " x" + quantity + " to cart.");
        }
    }

    public double getCartSubtotal() {
        double subtotal = 0;
        for (CartItem item : items) {
            subtotal += item.getSubtotal();
        }
        return subtotal;
    }

    public void viewcart(Provinsi prov) {
        if (items.isEmpty()) {
            System.out.println("Keranjang belanja Anda masih kosong.");
            return;
        }

        System.out.println("\n--- YOUR SHOPPING CART ---");
        for (CartItem item : items) {
            System.out.println(item.toString());
        }
        System.out.println("--------------------------");
        System.out.printf("Subtotal      : Rp %.2f\n", getCartSubtotal());
        if (activeVoucher != null) {
            System.out.printf("Diskon Voucher: -Rp %.2f (%s)\n", getNominalDiskon(), activeVoucher.nama);
        }
        System.out.printf("Pajak (11%%)   : Rp %.2f\n", getPajak());
        System.out.printf("Ongkir (%s) : Rp %d.00\n", prov.nama, prov.ongkir);
        System.out.println("--------------------------");
        System.out.printf("Total Belanja : Rp.%.2f-\n", total(prov.ongkir));
    }

    public double getPajak() {
        return getCartSubtotal() * pajak;
    }

    public double total(int ongkir) {
        if (items.isEmpty()) {
            return 0;
        }
        return getCartSubtotal() - getNominalDiskon() + getPajak() + ongkir;
    }

    public void clearcart() {
        items.clear();
        this.activeVoucher = null;
    }

    public boolean isempty() {
        return items.isEmpty();
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void removeItem(int productId, int quantityToRemove) {
        CartItem targetItem = null;

        for (CartItem item : items) {
            if (item.getProduct().id == productId) {
                targetItem = item;
                break;
            }
        }

        if (targetItem == null) {
            System.out.println("Produk dengan ID tersebut tidak ada di dalam keranjang.");
            return;
        }

        if (quantityToRemove >= targetItem.getQuantity()) {
            items.remove(targetItem);
            System.out.println(targetItem.getProduct().nama + " berhasil dihapus dari keranjang.");
        } else {
            targetItem.addquantity(-quantityToRemove);
            System.out.println("Jumlah " + targetItem.getProduct().nama + " di keranjang dikurangi.");
        }
    }

    public void displayVouchers() {
        Voucher.displayVouchers(vouchers);
    }

    public void applyVoucher(int voucherId) {
        double subtotal = getCartSubtotal();
        Voucher v = Voucher.findById(vouchers, voucherId);
        if (v != null) {
            if (subtotal >= v.minBelanja) {
                this.activeVoucher = v;
                System.out.println("Voucher \"" + v.nama + "\" berhasil digunakan!");
            } else {
                System.out.printf("Gagal! Minimal belanja untuk voucher ini adalah Rp %.0f\n", v.minBelanja);
            }
        } else {
            System.out.println("ID Voucher tidak ditemukan.");
        }
    }

    public double getNominalDiskon() {
        if (activeVoucher == null) {
            return 0;
        }
        return activeVoucher.hitungDiskon(getCartSubtotal());
    }

    public String getActiveVoucherName() {
        return (activeVoucher != null) ? activeVoucher.nama : "Tidak ada";
    }
}