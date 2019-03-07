/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping;
import entity.Cupcake;
import java.util.Objects;

/**
 *
 * @author
 */
public class LineItem {

    /**
     * When a cupcake is added, a new Line Item is created and added to the
     * cart. If the user order the same cake twice we can add yet another
     * LineItem (or the quantity of an already existing Line Item can be
     * incremented).
     */
    private int quantity;
    private final Cupcake cupcake;

    /**
     * Constructor Object
     *
     * @param cupcake
     */
    public LineItem(Cupcake cupcake) {
        this.cupcake = cupcake;
    }

    /**
     * Get Quantity
     *
     * @return int
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Get Cupcake object
     *
     * @return Cupcake object
     */
    public Cupcake getCupcake() {
        return cupcake;
    }

    /**
     * Add Quantity
     *
     * @param quantity Quantity you want to add
     */
    public void addQuantity(int quantity) {
        this.quantity = this.quantity + quantity;
    }

    /**
     * Set Quantity
     *
     * @param quantity Sets quantity to this amount
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * To String
     *
     * @return String
     */
    @Override
    public String toString() {
        return cupcake.toString() + " Quantity: " + this.quantity + " Total Price: " + (cupcake.getPrice() * quantity);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LineItem other = (LineItem) obj;
        if (!Objects.equals(this.cupcake, other.cupcake)) {
            return false;
        }
        return true;
    }

}
