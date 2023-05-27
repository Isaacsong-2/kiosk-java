package entity;

import java.util.HashMap;
import java.util.Map;

public class SoldList {
    private Map<Product, Integer> products;

    public SoldList(){
        this.products = new HashMap<>();
    }

    public void addSoldList(Map<Product, Integer> addedProducts){
        for (Map.Entry<Product, Integer> entry : addedProducts.entrySet()){
            Product product = entry.getKey();
            int quantity = entry.getValue();
            if(!products.containsKey(product)){
                products.put(product, quantity);
            }else {
                products.put(product, products.get(product) + quantity);
            }
        }
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }
}