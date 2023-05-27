package ui;

import entity.Menu;
import entity.Product;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import entity.Order;

public class Home {
    private Order order = new Order();
    private ArrayList<Menu> menuList;
    private Map<String, ArrayList<Product>> productMap;
    private Scanner scanner;

    public Home(ArrayList<Menu> menuList, Map<String, ArrayList<Product>> productMap){
        this.scanner = new Scanner(System.in);
        this.menuList = menuList;
        this.productMap = productMap;
    }

    public void show(){
        int inputNumber = 0;

        while(true){
            displayMenu();
            inputNumber = selectMenu();
            switch(inputNumber){
                case 1: case 2: case 3: case 4:
                    displayProduct(inputNumber);
                    break;
                case 5:
                    displayOrder();
                    break;
                case 6:
//                    OrderDisplay orderDisplay = new OrderDisplay();
                    break;

                default:
                    System.out.println("Choose again!");
            }
        }
    }

    private int selectMenu() {
        System.out.print("Select : ");
        int menuNumber = scanner.nextInt();
        scanner.nextLine(); // 버프를 비우기 위함.
        return menuNumber;
    }

    private void displayMenu() {
        System.out.println("\"SHAKESHACK BURGER 에 오신걸 환영합니다.\"");
        System.out.println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.");
        System.out.println();

        System.out.println("[ SHAKESHACK MENU ]");
        for (int i = 0; i < menuList.size(); i++) {
            Menu menu = menuList.get(i);
            String line = String.format("%d. %-15s | %s", (i + 1), menu.getName(), menu.getDetail());
            System.out.println(line);
        }
        System.out.println();
        System.out.println("Additional Options:");
        System.out.println("5. Order       | 장바구니를 확인 후 주문합니다.");
        System.out.println("6. Cancel      | 진행중인 주문을 취소합니다.");
    }

    private void displayProduct(int id){
        String categoryName = menuList.get(id-1).getName();
        ArrayList<Product> products = productMap.get(categoryName);

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
        Product selectedProduct = products.get(inputNumber);
        String line = String.format("\"%-15s | W %5d | %s\"", selectedProduct.getName(), selectedProduct.getPrice(), selectedProduct.getDetail());
        System.out.println(line);
        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인        2. 취소");
        inputNumber = selectMenu();
        if (inputNumber == 1){
            order.addProduct(selectedProduct);
            System.out.println(selectedProduct.getName()+" 가 장바구니에 추가되었습니다.");
        }
    }

    private void displayOrder(){
        System.out.println("아래와 같이 주문 하시겠습니까?");
        System.out.println();
        System.out.println("[ Orders ]");
        order.showOrder();
        System.out.println();

        System.out.println("1. 주문      2. 메뉴판");
        int inputNumber = selectMenu();
        if (inputNumber == 1) {
            order.removeOrder();
            System.out.println("주문이 완료되었습니다!");
            System.out.println();
            String line = String.format("대기번호는 [ %d ] 번 입니다.", order.orderNum++);
            System.out.println(line);
            System.out.println("3초후 메뉴판으로 돌아갑니다.");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
