import java.io.FileNotFoundException;

/** Class for differentiating item data */
public class ProductSpecification {
    private String code;
    private String name;
    private double unitPrice;
    private boolean salesTax = true;

    /** Constructor */
    public ProductSpecification(String code, String name, double unitPrice, boolean salesTax) throws FileNotFoundException {
        this.code = code;
        this.name = name;
        this.unitPrice = unitPrice;
        this.salesTax = salesTax;
    }

    /** Setter for code */
    public void setCode(String code){
        this.code = code;
    }

    /** Setter for name */
    public void setName(String name){
        this.name = name;
    }

    /** Setter for unitPrice */
    public void setUnitPrice(double price){
        unitPrice = price;
    }

    /** Getter for name */
    public String getName(){
        return name;
    }

    /** Getter for unit price */
    public double getUnitPrice(){
        return unitPrice;
    }

    /** Getter for code */
    public String getCode(){
        return code;
    }

    /** Getter for sales tax */
    public boolean getSalesTax(){
        return salesTax;
    }
}

