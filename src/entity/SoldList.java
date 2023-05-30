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
                products.merge(product, quantity, Integer::sum)); // Product를 key값으로 quantity(개수)를 value값으로 하는 map데이터 2개를 merge하여 총 판매개수를 갱신
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }
}