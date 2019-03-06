/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;

public class Users implements Serializable {
    private int id;
    private String userName;
    private String password;
    private double balance;
    private boolean admin;
    private String email;

    public Users(String userName, String password, double balance, boolean admin, String email) {
        this.userName = userName;
        this.password = password;
        this.balance = balance;
        this.admin = admin;
        this.email = email;
    }

    public int getId() {
        return id;
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

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Users{" + "id=" + id + ", userName=" + userName + ", password=" + password + ", balance=" + balance + ", admin=" + admin + ", email=" + email + '}';
    }
    
    

    
}
