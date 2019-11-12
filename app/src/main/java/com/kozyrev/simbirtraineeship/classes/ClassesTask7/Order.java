package com.kozyrev.simbirtraineeship.classes.ClassesTask7;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private boolean isPaid = false;
    private boolean isSaled = false;
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product){
        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public boolean isPaid(){
        return isPaid;
    }

    public void setPaid(boolean isPaid){
        this.isPaid = isPaid;
    }

    public boolean isSaled(){
        return isPaid;
    }

    public void setSaled(boolean isSaled){
        this.isSaled = isSaled;
    }

    public double getTotalCost() {
        double totalCost = 0;
        for (Product product: products) {
            totalCost += product.getCost();
        }
        return totalCost;
    }
}
