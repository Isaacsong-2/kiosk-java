import ui.Home;
import ui.Menu;

import java.util.ArrayList;

public class Kiosk {
    private void startKiosk(){
        ArrayList<Menu> menuList = new ArrayList<>();
        menuList.add(new Menu("Burgers", "앵거스 비프 통살을 다져만든 버거"));
        menuList.add(new Menu("Frozen Custard", "매장에서 신선하게 만드는 아이스크림"));
        menuList.add(new Menu("Drinks", "매장에서 직접 만드는 음료"));
        menuList.add(new Menu("Beer", "뉴욕 브루클린 브루어리에서 양조한 맥주"));
        Home home = new Home(menuList);
        home.show();
    }

    public static void main(String[] args) {
        Kiosk kiosk = new Kiosk();
        kiosk.startKiosk();
    }
}
