package entity;

public class Menu {
    
    private String name;
    private String detail;

    public Menu(String name, String detail) {
        this.name = name;
        this.detail = detail;
    }


    public String getName() {
        return name;
    }

    public String getDetail() {
        return detail;
    }
}
