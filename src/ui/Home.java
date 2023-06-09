package ui;

import entity.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Home {
    private SoldAmount soldAmount;
    private SoldList soldList;
    private Order order;
    private ArrayList<Menu> menuList;
    private Map<String, ArrayList<Product>> productMap;
    private Scanner scanner;

    public Home(ArrayList<Menu> menuList, Map<String, ArrayList<Product>> productMap){
        this.scanner = new Scanner(System.in);
        this.menuList = menuList;
        this.productMap = productMap;
        soldAmount = new SoldAmount();
        soldList = new SoldList();
        order = new Order();
    }

    public void show(){
        int inputNumber = 0;

        while(true){
            displayMenu(); // 초기 메뉴화면
            inputNumber = selectMenu(6);
            switch(inputNumber){
                case 0:
                    salesRecord(); // 판매기록 조회
                    break;
                case 1: case 2: case 3: case 4:
                    displayProduct(inputNumber); // 해당 카테고리의 product 조회
                    break;
                case 5:
                    displayOrder(); // 현재 주문(장바구니) 조회
                    break;
                case 6:
                    cancelOrder(); // 현재 진행중인 주문 취소
                    break;
            }
        }
    }

    private int selectMenu(int maxNum) { // minNum과 maxNum사이의 정수입력을 받을때까지 입력을 받음
        while(true) {
            System.out.print("Select : ");
            String line = scanner.nextLine();
            Integer menuNumber = isValid(line);
            if (menuNumber > maxNum || menuNumber < 0) {
                System.out.println("숫자를 확인해주세요");
                continue;
            }
            return menuNumber;
        }
    }

    private int isValid(String line){
        try{
            return Integer.parseInt(line);
        } catch(NumberFormatException e){
            return Integer.MAX_VALUE;
        }
    }

    private void displayMenu() {
        System.out.println("------------------------------------------------");
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
        System.out.println("------------------------------------------------");
    }

    private void displayProduct(int id){
        String categoryName = menuList.get(id-1).getName(); // 선택한 카테고리의 이름을 가져옴
        ArrayList<Product> products = productMap.get(categoryName); // productMap에서 해당 카테고리에 해당되는 product list를 가져옴

        System.out.println("------------------------------------------------");
        System.out.println("\"SHAKESHACK BURGER 에 오신걸 환영합니다.\"");
        System.out.println("아래 상품판을 보시고 상품을 골라 입력해주세요.");
        System.out.println();

        System.out.println("[ " + categoryName + " MENU ]");
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            String line = String.format("%d. %-15s | W %5d | %s", i+1, product.getName(), product.getPrice(), product.getDetail());
            System.out.println(line);
        }
        System.out.println();
        System.out.println("0. 뒤로가기");
        System.out.println("------------------------------------------------");

        int inputNumber = selectMenu(products.size());
        if (inputNumber == 0) return;
        displayAdd(inputNumber, products);
    }

    private void displayAdd(int inputNumber, ArrayList<Product> products){
        Product selectedProduct = products.get(inputNumber-1); // product list중 선택한 product
        String line = String.format("\"%-15s | W %5d | %s\"", selectedProduct.getName(), selectedProduct.getPrice(), selectedProduct.getDetail());
        System.out.println(line);
        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인        0. 취소");
        System.out.println("------------------------------------------------");

        inputNumber = selectMenu(1);
        if (inputNumber == 1){
            order.addProduct(selectedProduct); // 선택한 product를 장바구니(order)에 추가
            System.out.println(selectedProduct.getName()+" 가 장바구니에 추가되었습니다.");
        }
    };

    private void displayOrder(){
        System.out.println("------------------------------------------------");
        System.out.println("아래와 같이 주문 하시겠습니까?");
        System.out.println();
        System.out.println("[ Orders ]");
        Map<Product, Integer> orderedProducts = order.getProducts(); // 장바구니(order)에 담겨진 product들을 가져옴
        for (Map.Entry<Product, Integer> entry : orderedProducts.entrySet()) {
            Product product = entry.getKey(); // 상품정보
            int quantity = entry.getValue(); // 개수
            String line = String.format("%-15s | W %5d | %3d | %s", product.getName(), product.getPrice(), quantity,product.getDetail());
            System.out.println(line);
        }
        System.out.println();
        System.out.println("[ Total ]");
        System.out.println("W " + order.getTotalPrice() + "원");
        System.out.println();

        System.out.println("1. 주문      0. 메뉴판");
        System.out.println("------------------------------------------------");

        int inputNumber = selectMenu(1);
        if (inputNumber == 0) return;

        if (inputNumber == 1) {
            soldAmount.increaseTotalSale(order.getTotalPrice()); // 총 판매금액에 현재 장바구니에 담겨진 상품들의 총 금액을 더해줌
            soldList.addSoldList(order.getProducts()); // 판매목록에 현재 장바구니에 담겨진 상품들을 추가
            order.removeOrder(); // 장바구니 비우기

            System.out.println("주문이 완료되었습니다!");
            System.out.println();
            String line = String.format("대기번호는 [ %d ] 번 입니다.", order.getOrderNum());
            order.setOrderNum(order.getOrderNum()+1);
            System.out.println(line);
            System.out.println("3초후 메뉴판으로 돌아갑니다.");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e){}
        }
    }

    private void cancelOrder() {
        System.out.println("------------------------------------------------");

        System.out.println("진행하던 주문을 취소하시겠습니까?");
        System.out.println("1. 확인        0. 취소");
        System.out.println("------------------------------------------------");

        int inputNumber = selectMenu(1);
        if (inputNumber == 1){
            System.out.println("진행하던 주문이 취소되었습니다.");
            order.removeOrder(); // 주문취소(장바구니 비우기)
        }
    }

    private void salesRecord() {
        System.out.println("------------------------------------------------");
        System.out.println("[ 총 판매금액 현황 ]");
        String line = String.format("현재까지 총 판매된 금액은 [ W %d ] 입니다.", soldAmount.getTotalSale());
        System.out.println(line);
        System.out.println("------------------------------------------------");
        System.out.println("[ 총 판매상품 목록 현황 ]");
        System.out.println("현재까지 총 판매된 상품 목록은 아래와 같습니다.");
        System.out.println();
        soldList.getProducts().forEach(((product, quantity) -> {
            System.out.println(String.format("- %-15s | W %5d | %d", product.getName(), product.getPrice(), quantity));
        }));
        System.out.println("------------------------------------------------");
        System.out.println("0. 돌아가기");
        selectMenu(0);
    }
}
