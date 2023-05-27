package entity;

import entity.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {
    public static int orderNum = 1;
    private Map<Product, Integer> products;

    public Order() {
        this.products = new HashMap<>();
    }

    public void addProduct(Product product) {
        if(!products.containsKey(product)){
            products.put(product, 1);
        }else {
            products.put(product, products.get(product) + 1);
        }
    }

    public Integer getTotalPrice() {
        Integer total = 0;
        for (Map.Entry<Product, Integer> product : products.entrySet()) {
            int price = product.getKey().getPrice();
            int quantity = product.getValue();
            total += price*quantity;
        }
        return total;
    }

    public void showOrder(){
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            String line = String.format("%-15s | W %5d | %3d | %s", product.getName(), product.getPrice(), quantity,product.getDetail());
            System.out.println(line);
        }
        System.out.println();
        System.out.println("[ Total ]");
        System.out.println("W " + getTotalPrice() + "원");
    }

    public void removeOrder(){
        this.products = new HashMap<>();
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }
}
