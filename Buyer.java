import java.util.ArrayList;
import java.util.List;

public class Buyer {
    public String nama;
    public String alamat;
    public double saldo;
    public shoppingCart cart;
    private List<Order> orderHistory;
    private final List<Provinsi> daftarProvinsi;
    private Provinsi provinsiTujuan;

    public Buyer(String nama, String alamat, String namaProvinsi, double saldo) {
        this.nama = nama;
        this.alamat = alamat;
        this.saldo = saldo;
        this.cart = new shoppingCart();
        this.orderHistory = new ArrayList<>();
        this.daftarProvinsi = Provinsi.getDaftarProvinsi();
        setProvinsiByName(namaProvinsi);
    }

    public void displayProvinsi() {
        Provinsi.displayProvinsi(daftarProvinsi);
    }

    public boolean setProvinsiByName(String namaProvinsi) {
        Provinsi found = Provinsi.findByName(daftarProvinsi, namaProvinsi);
        if (found != null) {
            this.provinsiTujuan = found;
            System.out.println("Provinsi tujuan diatur ke: " + found.nama + " (Ongkir: Rp " + found.ongkir + ")");
            return true;
        }
        return false;
    }

    public Provinsi getProvinsiTujuan() {
        return provinsiTujuan;
    }

    public int getOngkir() {
        return (provinsiTujuan != null) ? provinsiTujuan.ongkir : 0;
    }

    public String getnama() {
        return nama;
    }

    public String getalamat() {
        return alamat;
    }

    public void setalamat(String alamat) {
        this.alamat = alamat;
    }

    public void setnama(String nama) {
        this.nama = nama;
    }

    public double getsaldo() {
        return saldo;
    }

    public void setsaldo(double saldo) {
        this.saldo = saldo;
    }

    public void topup(double amount) {
        saldo += amount;
    }

    public shoppingCart getcart() {
        return cart;
    }

    public void topUp(double amount) {
        if (amount > 0) {
            this.saldo += amount;
            System.out.println("Top-up berhasil! Saldo sekarang: Rp." + this.saldo + "-");
        } else {
            System.out.println("Jumlah top-up tidak valid.");
        }
    }

    public void checkout() {
        int ongkir = getOngkir();
        double totalBelanja = cart.total(ongkir);

        if (this.saldo < totalBelanja) {
            System.out.println("Transaksi Gagal! Saldo Anda tidak mencukupi untuk total belanja Rp." + totalBelanja + "-");
            return;
        }

        for (CartItem item : cart.getItems()) {
            Product prod = item.getProduct();
            int qtyBought = item.getQuantity();
            prod.reduceStock(qtyBought);
        }

        this.saldo -= totalBelanja;
        System.out.printf("Purchase successful! Remaining saldo: Rp.%.2f-\n", this.saldo);

        int newOrderId = orderHistory.size() + 1;
        Order completedOrder = new Order(
            newOrderId,
            cart.getItems(),
            cart.getCartSubtotal(),
            cart.getActiveVoucherName(),
            cart.getNominalDiskon(),
            cart.getPajak(),
            ongkir,
            totalBelanja
        );
        orderHistory.add(completedOrder);

        cart.clearcart();
    }

    public void printOrderReceipt(int searchId) {
        for (Order order : orderHistory) {
            if (order.getOrderId() == searchId) {
                order.printReceipt(this.nama, this.alamat, provinsiTujuan.nama);
                return;
            }
        }
        System.out.println("Struk dengan Order ID #" + searchId + " tidak ditemukan.");
    }

    public void viewOrderHistory() {
        System.out.println("\n==========================================");
        System.out.println("              ORDER HISTORY               ");
        System.out.println("==========================================");
        if (orderHistory.isEmpty()) {
            System.out.println("Belum ada riwayat transaksi.");
        } else {
            for (Order order : orderHistory) {
                order.displayOrderDetails();
            }
        }
        System.out.println("==========================================");
    }
}
