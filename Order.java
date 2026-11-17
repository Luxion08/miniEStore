package com.mycompany.miniestore;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderId;
    private List<CartItem> purchasedItems;
    private double totalAmount;
    private String orderDate;
    
    public Order(int orderId, List<CartItem> items, double totalAmount) {
        this.orderId = orderId;
        this.purchasedItems = new ArrayList<>(items);
        this.totalAmount = totalAmount;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.orderDate = now.format(formatter);
    }

    // Getter untuk mencari berdasarkan Order ID
    public int getOrderId() {
        return orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    // Tampilan ringkas untuk daftar Order History
    public void displayOrderDetails() {
        System.out.println("Order ID #" + orderId + " | Date: " + orderDate + " | Total: Rp " + String.format("%.2f", totalAmount));
        for (CartItem item : purchasedItems) {
            System.out.println("   - " + item.getProduct().nama + " (x" + item.getQuantity() + ") = Rp " + String.format("%.2f", item.getSubtotal()));
        }
        System.out.println("------------------------------------------");
    }

    // Tampilan format struk detail untuk 1 transaksi spesifik
    public void printReceipt(String buyerName, String alamat) {
        System.out.println("\n==========================================");
        System.out.println("            MINI STORE RECEIPT            ");
        System.out.println("==========================================");
        System.out.println("Order ID           : #" + orderId);
        System.out.println("Customer           : " + buyerName);
        System.out.println("Alamat Tujuan      : " + alamat);
        System.out.println("Tanggal Pembayaran : " + orderDate);
        System.out.println("Status Pesanan : Dalam Proses Pengiriman" );
        System.out.println("------------------------------------------");
        System.out.printf("%-20s %-5s %-13s\n", "Item", "Qty", "Subtotal");
        System.out.println("------------------------------------------");
        for (CartItem item : purchasedItems) {
            System.out.printf("%-20s x%-4d Rp %-10.2f\n", 
                item.getProduct().nama, 
                item.getQuantity(), 
                item.getSubtotal());
        }
        System.out.println("------------------------------------------");
        System.out.printf("TOTAL PAID : Rp %.2f\n", totalAmount);
        System.out.println("==========================================");
        System.out.println("      Terima Kasih Telah Berbelanja!      ");
        System.out.println("==========================================\n");
    }
} 
//test
