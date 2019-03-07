package shopping;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author
 */
public class ShoppingCart {

    /**
     * The shopping cart holds Line Items which has information of which cupcake
     * (bottom and topping) and the quantity of cupcakes. The Line Item also has
     * an invoice_id to prepare it for assignment 6. Create a ShoppingCart class
     * that has a list of LineItems (create this class too) The shopping cart
     * should be stored in the session (Why do you think?).
     */
    
    private List<LineItem> lineItems;
    private int invoiceid;
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

    /**
     * The addLineItem-method adds a lineItem to the List of lineItems
     */
    public void addLineItem(LineItem lineItem) {
        this.lineItems.add(lineItem);
    }

    /**
     * The getLineItems-method returns a list of all lineItems in the List of lineItems
     */
    public List<LineItem> getLineItems() {
        return lineItems;
    }

    /**
     * Get-method
     */
    public int getInvoiceid() {
        return invoiceid;
    }

    /**
     * Set-method
     */
    public void setInvoiceid(int invoiceid) {
        this.invoiceid = invoiceid;
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