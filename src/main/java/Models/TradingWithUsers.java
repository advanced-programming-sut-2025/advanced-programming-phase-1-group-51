package Models;

import Models.Items.Item;
import java.util.ArrayList;

public class TradingWithUsers {

    private User sellerUser;
    private User buyerUser;
    private ArrayList<Item> soldItems;
    private int TradedBalance;

    public TradingWithUsers(User sellerUser, User buyerUser, ArrayList<Item> soldItems, int tradedBalance) {
        this.sellerUser = sellerUser;
        this.buyerUser = buyerUser;
        this.soldItems = soldItems;
        this.TradedBalance = tradedBalance;
    }

    public User getSellerUser() {
        return sellerUser;
    }

    public void setSellerUser(User sellerUser) {
        this.sellerUser = sellerUser;
    }

    public User getBuyerUser() {
        return buyerUser;
    }

    public void setBuyerUser(User buyerUser) {
        this.buyerUser = buyerUser;
    }

    public ArrayList<Item> getSoldItems() {
        return soldItems;
    }

    public void setSoldItems(ArrayList<Item> soldItems) {
        this.soldItems = soldItems;
    }

    public int getTradedBalance() {
        return TradedBalance;
    }

    public void setTradedBalance(int tradedBalance) {
        TradedBalance = tradedBalance;
    }
}
