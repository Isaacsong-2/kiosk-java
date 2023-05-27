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

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%-10s | W %5d | %s", getName(), getPrice(), getDetail());
    }
}
