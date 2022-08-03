import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;

/**Register class where the sale is communicated with user*/
public class Register {

    //working variables
    private Sale sale;
    private ProductCatalog catalog;
    public Scanner input = new Scanner(System.in);

    //register constructor
    public Register(ProductCatalog catalog) {
        this.catalog = catalog;
    }

    //determines and navigates user through sale
    public void determineSale() throws IOException {

        Boolean saleComplete = false;

        //loop until sale is complete
       do  {
            System.out.print("Would you like to begin a new sale(Y/N)?    ");

            String userInput = input.next().trim();

            //determine what to do based on user input
            if (userInput.equalsIgnoreCase("y"))
                makeNewSale(); //create a new sale
            else if (userInput.equalsIgnoreCase("n"))
                saleComplete = endSale(); //end sale
            else
                System.out.println("Try again. Please enter valid input."); // user entered input that has no match

        } while (saleComplete == false);
        }

    public void makeNewSale() throws FileNotFoundException {

        //create sale object
        sale = new Sale();

        String itemID = "";

        //loop until user indicates they want to make a payment
        while (!itemID.equalsIgnoreCase("makePayment")) {

            System.out.print("Please enter item id:                       ");

            itemID = input.next().trim();

            //method to determine meaning of user input
            itemID = determineCorrectItemID(itemID);

            //loop until user enters valid input for itemID
            while (itemID.equals("noMatch")) {
                System.out.print("Please enter a valid item code:             ");

                itemID = input.next().trim();

                itemID = determineCorrectItemID(itemID);
            }
            //if statement based upon the users input for itemID
            if (itemID.equals("makePayment")) //user entered -1
                sale.makePayment();
            else if (itemID.equals("displayItems")) {//user entered 0000
                catalog.displayItems();
            } else {
                int quantity = 0;
                while (quantity < 1) {
                    System.out.print("Enter quantity:                             ");
                    String tryQuantity = input.next();
                    try {
                        quantity = Integer.parseInt(tryQuantity);
                    } catch (NumberFormatException x) {
                        System.out.println("Please enter a valid quantity.");
                    }
                }
                enterItem(itemID, quantity);
            }
        }

    }

    /**Method to enter in sale line items*/
    public void enterItem(String itemID, int quantity) {

        ProductSpecification spec = catalog.getSpecification(itemID);

        sale.makeLineItem(spec, quantity);

        System.out.println();
    }

    public boolean endSale() throws IOException {
        sale.becomeComplete();

        System.out.println("The total sale for the day is $ " + sale.getTotal());

        ProductCatalog.updateItemData();

        return true;
    }

    /**Method to determine itemID */
    public String determineCorrectItemID(String itemID){

        switch(itemID){
            case"-1":   return "makePayment";
            case"0000": return "displayItems";
            case"A001": return itemID;
            case"A002": return itemID;
            case"A003": return itemID;
            case"A004": return itemID;
            case"A005": return itemID;
            case"A006": return itemID;
            case"A007": return itemID;
            case"B001": return itemID;
            case"B002": return itemID;
            case"B003": return itemID;
        }
        return "noMatch";
    }
}

