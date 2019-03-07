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
     * Constructor
     */
    public LineItem(Cupcake cupcake) {
        this.cupcake = cupcake;
    }

    /**
     * Get-method
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Get-method
     */
    public Cupcake getCupcake() {
        return cupcake;
    }

    /**
     * addQuantity adds more money to the quantity
     */
    public void addQuantity(int quantity) {
        this.quantity = this.quantity + quantity;
    }

    /**
     * setQuantity changes the quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

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
