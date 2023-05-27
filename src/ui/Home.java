package ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Home {
    ArrayList<Menu> menuList = new ArrayList<Menu>();
    private Scanner scanner;

    public Home(ArrayList<Menu> menuList){
        this.scanner = new Scanner(System.in);
        this.menuList = menuList;
    }

    public void show(){
        int inputNumber = 0;

        while(true){
            displayMenu();
            inputNumber = selectMenu();

        }
    }

    private int selectMenu() {
        System.out.print("Select : ");
        int menuNumber = scanner.nextInt();
        if (menuNumber > 0 && menuNumber <= 6) {
            scanner.nextLine(); // 버프를 비우기 위함.
            return menuNumber;
        } else {
            System.out.println("IT's a invalid number -->" + menuNumber);
            return -1;
        }
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
}
