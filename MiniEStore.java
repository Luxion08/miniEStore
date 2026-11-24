import java.util.Scanner;

public class MiniEStore {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Store store = new Store();

        System.out.println("==========================================");
        System.out.println("       SELAMAT DATANG DI MINI STORE       ");
        System.out.println("==========================================");

        System.out.print("Masukkan Nama Anda: ");
        String namaBuyer = scanner.nextLine();
        
        System.out.print("Masukkan Email Anda: ");
        String email = scanner.nextLine();
        
        System.out.print("Masukkan Nomor Telepon Anda: ");
        String noTelp = scanner.nextLine();
        
        System.out.print("Masukkan Alamat Lengkap Anda: ");
        String alamat = scanner.nextLine();

        // Tampilkan daftar provinsi agar user tahu wilayah yang tersedia
        Provinsi.displayProvinsi(Provinsi.getDaftarProvinsi());
        System.out.print("Masukkan Provinsi Tujuan Pengiriman: ");
        String provinsiInput = scanner.nextLine();

        // Jika provinsi tidak ditemukan, langsung akhiri program
        if (Provinsi.findByName(Provinsi.getDaftarProvinsi(), provinsiInput) == null) {
            System.out.println("\nMohon maaf pengiriman ke provinsi Anda masih belum tersedia.....");
            scanner.close();
            return;
        }

        double saldoAwal;
        do {
            System.out.print("Masukkan Saldo Awal Anda (Rp): ");
            saldoAwal = scanner.nextDouble();
        } while (saldoAwal < 0);

        Buyer buyer = new Buyer(namaBuyer, alamat, provinsiInput, saldoAwal);

        int orderCounter = 0;
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n==========================================");
            System.out.printf("Halo, %s! | Tujuan: %s (Ongkir Rp %d) | Saldo: Rp.%.2f-\n",
                    buyer.getnama(), buyer.getProvinsiTujuan().nama, buyer.getOngkir(), buyer.getsaldo());
            System.out.println("==========================================");
            System.out.println("1. Lihat Semua Produk");
            System.out.println("2. Cari Produk");
            System.out.println("3. Tambah Produk ke Keranjang");
            System.out.println("4. Lihat Keranjang Belanja");
            System.out.println("5. Hapus / Kurangi Item di Keranjang");
            System.out.println("6. Checkout & Bayar");
            System.out.println("7. Status Pesanan & Cetak Struk");
            System.out.println("8. Top Up Saldo");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu (0-8): ");

            int pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    store.displayCatalog();
                    break;

                case 2:
                    System.out.println("\nCari berdasarkan: [1] Nama Produk  [2] ID Produk  [3] Kategori");
                    System.out.print("Pilih metode pencarian: ");
                    int metodeCari = scanner.nextInt();
                    scanner.nextLine(); // bersihkan buffer

                    if (metodeCari == 1) {
                        System.out.print("Masukkan kata kunci nama produk: ");
                        String keyword = scanner.nextLine();
                        store.searchProduct(keyword);
                    } else if (metodeCari == 2) {
                        System.out.print("Masukkan ID produk: ");
                        int searchId = scanner.nextInt();
                        store.searchIdProduct(searchId);
                    } else if (metodeCari == 3) {
                        System.out.print("Masukkan kategori produk: ");
                        String kategori = scanner.nextLine();
                        store.searchProductCategory(kategori);
                    } else {
                        System.out.println("Metode pencarian tidak valid.");
                    }
                    break;

                case 3:
                    char tambahLagi;
                    do {
                        store.displayCatalog();
                        System.out.print("Masukkan ID Produk yang ingin dibeli: ");
                        int prodId = scanner.nextInt();
                        Product selectedProduct = store.findById(prodId);

                        if (selectedProduct == null) {
                            System.out.println("Produk dengan ID tersebut tidak ditemukan.");
                        } else {
                            System.out.print("Masukkan jumlah (Qty): ");
                            int qty = scanner.nextInt();
                            buyer.getcart().addtocart(selectedProduct, qty);
                        }

                        System.out.print("\nApakah Anda ingin menambah produk lagi ke keranjang? (y/t): ");
                        tambahLagi = scanner.next().charAt(0);
                    } while (tambahLagi == 'y' || tambahLagi == 'Y');
                    break;

                case 4:
                    buyer.getcart().viewcart(buyer.getProvinsiTujuan());
                    break;

                case 5:
                    if (buyer.getcart().isempty()) {
                        System.out.println("Keranjang belanja masih kosong.");
                    } else {
                        buyer.getcart().viewcart(buyer.getProvinsiTujuan());
                        System.out.print("Masukkan ID Produk yang ingin dikurangi/dihapus: ");
                        int removeId = scanner.nextInt();
                        System.out.print("Masukkan jumlah yang ingin dihapus: ");
                        int removeQty = scanner.nextInt();
                        buyer.getcart().removeItem(removeId, removeQty);
                    }
                    break;

                case 6:
                    if (buyer.getcart().isempty()) {
                        System.out.println("Keranjang belanja masih kosong, tidak dapat melakukan checkout.");
                        break;
                    }

                    buyer.getcart().viewcart(buyer.getProvinsiTujuan());

                    System.out.print("\nApakah Anda ingin mengubah provinsi pengiriman? (y/t): ");
                    char ubahProv = scanner.next().charAt(0);
                    if (ubahProv == 'y' || ubahProv == 'Y') {
                        scanner.nextLine(); // bersihkan buffer
                        buyer.displayProvinsi();
                        System.out.print("Masukkan Provinsi Tujuan baru: ");
                        String provBaru = scanner.nextLine();
                        if (!buyer.setProvinsiByName(provBaru)) {
                            System.out.println("Provinsi tidak tersedia, tetap menggunakan " + buyer.getProvinsiTujuan().nama + ".");
                        }
                        buyer.getcart().viewcart(buyer.getProvinsiTujuan());
                    }

                    System.out.print("\nApakah Anda ingin menggunakan voucher? (y/t): ");
                    char pakaiVoucher = scanner.next().charAt(0);
                    if (pakaiVoucher == 'y' || pakaiVoucher == 'Y') {
                        buyer.getcart().displayVouchers();
                        System.out.print("Masukkan ID Voucher: ");
                        int voucherId = scanner.nextInt();
                        buyer.getcart().applyVoucher(voucherId);
                        buyer.getcart().viewcart(buyer.getProvinsiTujuan());
                    }

                    System.out.print("\nLanjutkan pembayaran? (y/t): ");
                    char konfirmasi = scanner.next().charAt(0);
                    if (konfirmasi == 'y' || konfirmasi == 'Y') {
                        double saldoSebelum = buyer.getsaldo();
                        buyer.checkout();
                        if (buyer.getsaldo() < saldoSebelum) {
                            orderCounter++;
                            buyer.printOrderReceipt(orderCounter);
                        }
                    } else {
                        System.out.println("Checkout dibatalkan.");
                    }
                    break;

                case 7:
                    buyer.viewOrderHistory();
                    if (orderCounter > 0) {
                        System.out.print("Masukkan Order ID untuk mencetak struk detail (0 untuk kembali): ");
                        int receiptId = scanner.nextInt();
                        if (receiptId != 0) {
                            buyer.printOrderReceipt(receiptId);
                        }
                    }
                    break;

                case 8:
                    System.out.print("Masukkan nominal Top Up (Rp): ");
                    double nominalTopUp = scanner.nextDouble();
                    buyer.topUp(nominalTopUp);
                    break;

                case 0:
                    System.out.println("Terima kasih telah menggunakan Mini Store. Sampai jumpa!");
                    isRunning = false;
                    break;

                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih angka 0-8.");
            }
        }

        scanner.close();
    }
}
