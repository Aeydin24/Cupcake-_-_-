/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;

public class User implements Serializable
{
    private int id;
    private String userName;
    private String password;
    private double balance;
    private boolean admin;

    public User(String userName, String password, double balance, boolean admin)
    {
        this.userName = userName;
        this.password = password;
        this.balance = balance;
        this.admin = admin;
    }

    public User(int id, String userName, String password, double balance, boolean admin)
    {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.balance = balance;
        this.admin = admin;
    }
    
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public double getBalance()
    {
        return balance;
    }

    public void setBalance(double balance)
    {
        this.balance = balance;
    }

    public boolean isAdmin()
    {
        return admin;
    }

    public void setAdmin(boolean admin)
    {
        this.admin = admin;
    }

    @Override
    public String toString()
    {
        return "User{" + "id=" + id + ", userName=" + userName + ", password=" + password + ", balance=" + balance + ", admin=" + admin + '}';
    }
}
