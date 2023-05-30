package entity;

public class SoldAmount {
    private int totalSale;

    public SoldAmount(){
        this.totalSale = 0;
    }

    public void increaseTotalSale(int sale){
        this.totalSale += sale;
    }

    public int getTotalSale() {
        return totalSale;
    }
}
