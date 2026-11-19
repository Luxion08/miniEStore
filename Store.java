package com.mycompany.miniestore;
import java.util.ArrayList;
import java.util.List;
public class Store {
    private final List<Product> products;
    public Store() {
        this.products = new ArrayList<>();
        techProducts(); 
        schoolProducts();
    }
    private void techProducts() {
        products.add(new Product(101, "Mechanical Keyboard", 150000.00, 10, "Tech Product"));
        products.add(new Product(102, "Wireless Mouse", 90000.00, 15, "Tech Product"));
        products.add(new Product(103, "Gaming Headset", 125000.00, 8, "Tech Product"));
        products.add(new Product(104, "USB-C Hub", 25000.00, 20, "Tech Product"));
        products.add(new Product(105, "Mousepad XXL", 15000.00, 25, "Tech Product"));
        products.add(new Product(106, "Deskmat L", 30000.00, 25, "Tech Product"));
        products.add(new Product(107, "Monitor", 500000.00, 5, "Tech Product"));
    }
    private void schoolProducts(){
        products.add(new Product(201, "Pack of book", 25000.00,50, "School Product"));
        products.add(new Product(202, "Pack of pencil", 15000.00,50, "School Product"));
        products.add(new Product(203, "Pack of pen", 20000.00,50, "School Product"));
        products.add(new Product(204, "Ruler", 5000.00,50, "School Product"));
        products.add(new Product(205, "Bag", 250000.00,10, "School Product"));
    }
    
    public void displayCatalog() {
        System.out.println("\n==========================================");
        System.out.println("              STORE CATALOG               ");
        System.out.println("==========================================");
        System.out.printf("%-4s | %-22s | %-8s | %-5s\n", "ID", "Nama Produk", "Harga", "Stok", "Kategori");
        System.out.println("------------------------------------------");
        for (Product p : products) {
            // Mencetak tiap produk dengan format rapi (rata kiri/kanan)
            System.out.printf("%-4d | %-22s | Rp.%-10.2f- | %-5d | %-15s\n", p.id, p.nama, p.harga, p.stock,p.kategori);
        }
        System.out.println("==========================================");
    }
    
    public Product findById(int id) {
        for (Product p : products) {
            if (p.id == id) {
                return p; // Mengembalikan objek produk jika ID-nya cocok
            }
        }
        return null; // Jika tidak ada produk yang cocok dengan ID tersebut
    }


    public void searchIdProduct(int id) {
        boolean found = false;
        System.out.println("\n--- Search Results for \"" + id + "\" ---");
        for (Product p : products) {
            if (p.id == id) {
                System.out.printf("ID: %-4d | %-22s | Rp.%-10.2f- | Stock: %-5d | kategori: %-15s\n", p.id, p.nama, p.harga, p.stock, p.kategori);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Produk tidak ditemukan.");
        }
        System.out.println("---------------------------------------------");
    }
    
    public void searchProduct(String keyword) {
        boolean found = false;
        System.out.println("\n--- Search Results for ID \"" + keyword + "\" ---");
        for (Product p : products) {
            // Mengubah ke huruf kecil (.toLowerCase()) agar pencarian tidak sensitif huruf besar/kecil
            if (p.nama.toLowerCase().contains(keyword.toLowerCase())) {
                System.out.printf("ID: %-4d | %-22s | Rp.%-10.2f- | Stock: %-5d | kategori: %-15s\n", p.id, p.nama, p.harga, p.stock, p.kategori);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Produk tidak ditemukan.");
        }
        System.out.println("---------------------------------------------");
    }

    public void searchProductCategory(String Category) {
        boolean found = false;
        System.out.println("\n--- Search Results for ID \"" + Category + "\" ---");
        for (Product p : products) {
            // Mengubah ke huruf kecil (.toLowerCase()) agar pencarian tidak sensitif huruf besar/kecil
            if (p.kategori.toLowerCase().contains(Category.toLowerCase())) {
                System.out.printf("ID: %-4d | %-22s | Rp.%-10.2f- | Stock: %-5d | kategori: %-15s\n", p.id, p.nama, p.harga, p.stock, p.kategori);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Produk tidak ditemukan.");
        }
        System.out.println("---------------------------------------------");
    }
    
    // Getter untuk mengambil data list produk
    public List<Product> getProducts() {
        return products;
    }
}
