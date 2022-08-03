import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/** Store class, creates product catalog and register, runs main method*/
class Store {

    //create catalog instance
    static ProductCatalog catalog;

    //create catalog object
    static {
        try {
            catalog = new ProductCatalog();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //use catalog object to create register object
    static Register register = new Register(catalog);

    //constructor for store class
    Store() throws FileNotFoundException {}

    /** Main method */
    public static void main(String [] args) throws IOException{

        //determine if user wants to start a sale
        getRegister().determineSale();
    }

    /** Getter for register */
    public static Register getRegister() {
        return register;
}
}
