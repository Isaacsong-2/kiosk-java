package entity;

public class Product extends Menu{
    private int price;

    public Product(String name, int price, String detail) {
        super(name, detail);
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
