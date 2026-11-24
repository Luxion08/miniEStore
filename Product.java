public class Product {
    public int id;
    public String nama;
    public double harga;
    public int stock;
    public String kategori;
    
    //constructor / inisialisasi nilai awal
    public Product(int id, String nama, double harga, int stock, String Kategori) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.stock = stock;
        this.kategori=Kategori;
    }
    
    //setter untuk apabila ada perubahan
    public void getid(int id){
        this.id=id;
    }
    public void getnama(String nama){
        this.nama=nama;
    }
    public void getharga(double harga){
        this.harga=harga;
    }
    public void getstock(int stock){
        this.stock=stock;
    }
    public void reduceStock(int stock){
        this.stock-=stock;
    }
    @Override
    public String toString() {
        return String.format("[%d] %-18s | Rp.%-7.2f- | Stock: %d", id, nama, harga, stock);
}
}
