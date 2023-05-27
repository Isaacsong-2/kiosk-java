package entity;


import java.util.HashMap;
import java.util.Map;

public class Order {
    public static int orderNum = 1;
    private Map<Product, Integer> products;

    public Order() {
        this.products = new HashMap<>();
    }

    public void addProduct(Product product) {
        products.compute(product, (p, quantity) -> quantity == null ? 1 : quantity + 1);
    }

    public Integer getTotalPrice() {
        return products.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
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
        products.clear();
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }
}
