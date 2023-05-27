package entity;

import java.util.HashMap;
import java.util.Map;

public class SoldList {
    private Map<Product, Integer> products;

    public SoldList(){
        this.products = new HashMap<>();
    }

    public void addSoldList(Map<Product, Integer> addedProducts) {
        addedProducts.forEach((product, quantity) ->
                products.merge(product, quantity, Integer::sum));
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }
}