package entity;

public class Account {
    private int totalSale;

    public Account(){
        this.totalSale = 0;
    }

    public void accountIncrease(int sale){
        this.totalSale += sale;
    }

    public int getTotalSale() {
        return totalSale;
    }
}
