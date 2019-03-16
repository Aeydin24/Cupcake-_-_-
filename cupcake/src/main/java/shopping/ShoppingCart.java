package shopping;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Benjamin og Christian
 */
public class ShoppingCart {

    /**
     * The shopping cart holds Line Items which has information of which
     * bottom and topping the cupcake has, and the quantity of cupcakes.
     */
    private List<LineItem> lineItems;
    private String date;

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public ShoppingCart() {
        this.lineItems = new ArrayList<>();
    }

    public boolean isEmpty() {
        return lineItems == null || lineItems.isEmpty();
    }

    /** Adds a lineItem to the List of lineItems
     * @param lineItem */
    public void addLineItem(LineItem lineItem) {
        this.lineItems.add(lineItem);
    }

    /** returns a list of all lineItems in the List of lineItems
     * @return  */
    public List<LineItem> getLineItems() {
        return lineItems;
    }

    @Override
    public String toString() {
        String print = "";
        for (LineItem item: this.lineItems){
            print += item.toString();
        }
        return "Shopping Cart: " + print;
    }

    public void setLineItems(List<LineItem> items) {
        this.lineItems = items;
    }

}
