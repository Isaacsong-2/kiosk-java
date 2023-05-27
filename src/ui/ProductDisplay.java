package ui;

import java.util.ArrayList;
import java.util.Scanner;

public class ProductDisplay {

    private String categoryName;
    private ArrayList<Product> products;
    private Scanner scanner;
    public ProductDisplay(String categoryName, ArrayList<Product> products) {
        this.scanner = new Scanner(System.in);
        this.categoryName = categoryName;
        this.products = products;
    }

    public void show(){
        System.out.println("\"SHAKESHACK BURGER 에 오신걸 환영합니다.\"");
        System.out.println("아래 상품판을 보시고 상품을 골라 입력해주세요.");
        System.out.println();

        System.out.println("[ " + categoryName + " MENU ]");
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            String line = String.format("%d. %-15s | W %5d | %s", (i + 1), product.getName(), product.getPrice(), product.getDetail());
            System.out.println(line);
        }
        System.out.println();
        int inputNumber = selectMenu();
    }

    private int selectMenu() {
        System.out.print("Select : ");
        int menuNumber = scanner.nextInt();
        if (menuNumber > 0 && menuNumber <= products.size()) {
            scanner.nextLine(); // 버프를 비우기 위함.
            return menuNumber;
        } else {
            System.out.println("It's a invalid number -->" + menuNumber);
            System.out.println();
            return -1;
        }
    }

}
