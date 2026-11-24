import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderId;
    private List<CartItem> purchasedItems;
    private double subtotal;
    private String voucherName;
    private double discountAmount;
    private double taxAmount;
    private int shippingFee;
    private double totalAmount;
    private String orderDate;

    public Order(int orderId, List<CartItem> items, double subtotal, String voucherName,
                 double discountAmount, double taxAmount, int shippingFee, double totalAmount) {
        this.orderId = orderId;
        this.purchasedItems = new ArrayList<>(items);
        this.subtotal = subtotal;
        this.voucherName = voucherName;
        this.discountAmount = discountAmount;
        this.taxAmount = taxAmount;
        this.shippingFee = shippingFee;
        this.totalAmount = totalAmount;

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.orderDate = now.format(formatter);
    }

    public int getOrderId() {
        return orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    // Tampilan ringkas untuk daftar Order History (Menu 7)
    public void displayOrderDetails() {
        System.out.println("Order ID #" + orderId + " | Date: " + orderDate + " | Total: Rp " + String.format("%.2f", totalAmount));
        for (CartItem item : purchasedItems) {
            System.out.println("   - " + item.getProduct().nama + " (x" + item.getQuantity() + ") = Rp " + String.format("%.2f", item.getSubtotal()));
        }
        if (discountAmount > 0) {
            System.out.printf("   * Voucher Used : %s (-Rp %.2f)\n", voucherName, discountAmount);
        }
        System.out.println("------------------------------------------");
    }

    // Tampilan format struk detail untuk 1 transaksi spesifik
    public void printReceipt(String buyerName, String alamat, String provinsi) {
        System.out.println("\n==========================================");
        System.out.println("            MINI STORE RECEIPT            ");
        System.out.println("==========================================");
        System.out.println("Order ID           : #" + orderId);
        System.out.println("Customer           : " + buyerName);
        System.out.println("Alamat Tujuan      : " + alamat);
        System.out.println("Provinsi Tujuan    : " + provinsi);
        System.out.println("Tanggal Pembayaran : " + orderDate);
        System.out.println("Status Pesanan     : Dalam Proses Pengiriman");
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
        System.out.printf("Subtotal           : Rp %.2f\n", subtotal);
        System.out.printf("Voucher Used       : %s\n", voucherName);
        System.out.printf("Diskon Voucher     : -Rp %.2f\n", discountAmount);
        System.out.printf("Pajak (11%%)        : Rp %.2f\n", taxAmount);
        System.out.printf("Ongkir (%-10s): Rp %d.00\n", provinsi, shippingFee);
        System.out.println("------------------------------------------");
        System.out.printf("TOTAL PAID         : Rp %.2f\n", totalAmount);
        System.out.println("==========================================");
        System.out.println("      Terima Kasih Telah Berbelanja!      ");
        System.out.println("==========================================\n");
    }
}
