/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.List;
import shopping.LineItem;
import shopping.ShoppingCart;

/** Entity class. */
public class Users implements Serializable {
    
    private String userName;
    private String password;
    private int balance;
    private String email;
    private ShoppingCart cart;
    
        public Users() {
    }

    public Users(String userName, String password, int balance, String email) {
        this.userName = userName;
        this.password = password;
        this.balance = balance;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void addBalance(int amount) {
        this.balance = this.balance + amount;
    }
    public int getTotalPrice() {
        int totalprice = 0;
        cart = getCart();

        if (cart != null && !cart.isEmpty()) {
            List<LineItem> items = cart.getLineItems();

            for (LineItem item : items) {
                totalprice = totalprice
                        + item.getQuantity() * item.getCupcake().getPrice();
            }
        }
        return totalprice;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    @Override
    public String toString() {
        return "Users{" + "userName=" + userName + ", password=" + password + ", balance=" + balance + ", email=" + email + '}';
    }

    
}
