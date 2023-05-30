package entity;


import java.util.HashMap;
import java.util.Map;

public class Order {
    private int orderNum;
    private Map<Product, Integer> products;

    public Order() {
        this.orderNum = 1;
        this.products = new HashMap<>();
    }

    public void addProduct(Product product) {
        // products map에서 product key값에 해당하는 value가 null이면 1을, 아니면 기존 value에 +1을 해준다.
        products.compute(product, (p, quantity) -> quantity == null ? 1 : quantity + 1);
    }

    public Integer getTotalPrice() {
        return products.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public void removeOrder(){
        products.clear();
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }
}
