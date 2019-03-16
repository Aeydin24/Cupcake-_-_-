package shopping;
import entity.Cupcake;
import java.util.Objects;

/**
 *
 * @author Benjamin og Christian
 *
 */
public class LineItem {

    /**
     * When a cupcake is added, a new Line Item is created and added to the
     * cart.
     */
    
    private int quantity;
    private final Cupcake cupcake;

    
    public LineItem(Cupcake cupcake) {
        this.cupcake = cupcake;
    }


    public int getQuantity() {
        return quantity;
    }


    public Cupcake getCupcake() {
        return cupcake;
    }


    public void addQuantity(int quantity) {
        this.quantity = this.quantity + quantity;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return cupcake.toString() + " Quantity: " + this.quantity + " Total Price: " + (cupcake.getPrice() * quantity) + " $";
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
