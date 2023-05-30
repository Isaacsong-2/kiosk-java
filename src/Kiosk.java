import ui.Home;
import entity.Menu;
import entity.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Kiosk {

    public static void main(String[] args) {
        Kiosk kiosk = new Kiosk();
        kiosk.startKiosk();
    }
    private void startKiosk(){
        ArrayList<Menu> menuList = generateMenu(); // menu생성
        Map<String, ArrayList<Product>> productMap = generateProduct(menuList); // product생성
        Home home = new Home(menuList, productMap);
        home.show();
    }

    private ArrayList<Menu> generateMenu(){
        ArrayList<Menu> menuList = new ArrayList<>();
        menuList.add(new Menu("Burgers", "앵거스 비프 통살을 다져만든 버거"));
        menuList.add(new Menu("Frozen Custard", "매장에서 신선하게 만드는 아이스크림"));
        menuList.add(new Menu("Drinks", "매장에서 직접 만드는 음료"));
        menuList.add(new Menu("Beer", "맥주"));
        return menuList;
    }

    private Map<String,ArrayList<Product>> generateProduct(ArrayList<Menu> menuList){
        Map<String,ArrayList<Product>> productMap = new HashMap<>();
        // 첫번째 카테고리 product
        ArrayList<Product> products = new ArrayList<Product>();
        products.add(new Product("ShackBurger",8400,"토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        products.add(new Product("SmokeShack",10600,"애플 우드 칩으로 훈연한 짭짤한 베이컨, 매콤한 체리페퍼, 비프패티와 쉑소스가 토핑된 치즈버거"));
        products.add(new Product("ShroomBurger",10000,"치즈로 속을 채우고 바삭하게 튀겨낸 포토벨로 버섯 패티에 양상추, 토마토, 쉑소스를 올린 베지테리안 버거"));
        products.add(new Product("ShackStack",14800,"포토벨로 버섯패티, 비프패티와 함께 토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        products.add(new Product("Hamburger",6800,"고소한 포테이토 번과 비프패티의 풍미 그대로를 느낄 수 있는 기본에 충실한 버거"));
        productMap.put(menuList.get(0).getName(), products);
        // 두번째 카테고리 product
        products = new ArrayList<Product>();
        products.add(new Product("GreenTeaShake",7500,"진한 녹차맛이 매력적인 시즌 한정 쉐이크"));
        products.add(new Product("Shake",6500,"바닐라, 초콜릿, 솔티드 카라멜, 딸기, 피넛버터, 커피"));
        products.add(new Product("Floats",6500,"부드러운 바닐라 커스터드와 톡톡터지는 탄산이 만나 탄생한 색다른 음료"));
        products.add(new Product("Cup&Cones",5400,"매장에서 매일 신선하게 제조하는 부드럽고 쫀득한 바닐라, 초콜릿 커스터드"));
        productMap.put(menuList.get(1).getName(), products);
        // 세번째 카테고리 product
        products = new ArrayList<Product>();
        products.add(new Product("IcedTea",3500,"홍차를 직접 우려내어 달지 않고 향긋한 아이스티"));
        products.add(new Product("Lemonade",4300,"매일 매장에서 신선하게 제조하는 상큼한 레몬에이드"));
        products.add(new Product("FountainSoda",2900,"코카콜라, 코카콜라 제로, 스프라이트, 환타 오렌지, 환타 그레이프, 환타 파인애플"));
        products.add(new Product("Coffee",2500,"쉑 블렌드 원두를 사용한 밸런스 좋은 드립 커피"));
        productMap.put(menuList.get(2).getName(), products);
        // 네번째 카테고리 product
        products = new ArrayList<Product>();
        products.add(new Product("Cass",4000,"대표적인 대한민국 맥주"));
        products.add(new Product("Terra",4000,"청정라거"));
        products.add(new Product("Hoegaarden",7000,"벨기에 맥주"));
        products.add(new Product("Guinness",7000,"아일랜드산 흑맥주"));
        productMap.put(menuList.get(3).getName(), products);
        return productMap;
    }
}
