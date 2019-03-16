/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author ndupo
 */

/** Entity class. */
public class Cupcake 
{

    private final Top top;
    private final Bottom bottom;
    private final int totalPrice;

    public Cupcake(Top top, Bottom bottom) {
        this.top = top;
        this.bottom = bottom;
        this.totalPrice = top.getPrice() + bottom.getPrice();
    }

    public Top getTop() {
        return top;
    }

    public Bottom getBottom() {
        return bottom;
    }
    
    public int getPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return bottom.getName() + " bottom with " + top.getName() + 
                " topping " + totalPrice +  " $";
    }
    
}
