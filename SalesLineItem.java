
/** Class for individual item in each sale*/

public class SalesLineItem extends Sale {

    private ProductSpecification spec;
    private int quantity;
    private ProductCatalog catalog;

    //Constructor
    SalesLineItem(ProductSpecification spec, int quantity) {
        this.spec = spec;
        this.quantity = quantity;
    }

    //Getter for name
    public String getName(){
        return spec.getName();
    }

    //Getter for quantity
    public int getQuantity(){
        return quantity;
    }

    //Getter for code
    public String getCode(){
        return spec.getCode();
    }
    public double getSubtotal(){
        return (spec.getUnitPrice() * quantity);
    }

    //Setter for quantity
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    //Determine if sales tax
    public boolean determineSalesTax(){
        return spec.getSalesTax();
    }
}
