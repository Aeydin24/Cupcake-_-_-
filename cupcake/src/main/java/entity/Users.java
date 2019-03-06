/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;

public class Users implements Serializable {
    
    private String userName;
    private String password;
    private int balance;
    private String email;
    
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

    public double getBalance() {
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

    @Override
    public String toString() {
        return "Users{" + "userName=" + userName + ", password=" + password + ", balance=" + balance + ", email=" + email + '}';
    }
    
    

    
}
