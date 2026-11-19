package com.mycompany.miniestore;
public class CartItem {
    public Product product;
    public int quantity;
    
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    public Product getProduct() { 
        return product; 
    }
    public void setProduct(Product product) { 
        this.product = product; 
    }
    public int getQuantity() { 
        return quantity; 
    }
    public void setQuantity(int quantity) { 
        this.quantity = quantity; 
    }
    public void addquantity(int quantity){
        this.quantity+=quantity;
    }
    public double getSubtotal(){
        return product.harga*quantity;
    }
    @Override
    public String toString() {
        return String.format("%-4d | %-18s x %-2d = Rp.%.2f-", product.id,product.nama, quantity, getSubtotal());
    }
}
