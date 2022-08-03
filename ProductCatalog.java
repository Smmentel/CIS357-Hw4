import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

/** Class to handle file input, and hold item data */
public class ProductCatalog {

    //Map to hold item data
    private static Map<String, ProductSpecification> productSpecs = new HashMap<String, ProductSpecification>();

    //Create file instance
    private static File file = new File("src/Items");



    /** Constructor that reads from input file and loads file data into hash map */
    public ProductCatalog() throws FileNotFoundException {

        //Create scanner for the file
        Scanner fileInput = new Scanner(file);

        Scanner delimiter = fileInput.useDelimiter(",");

        //Read file data into Product Specification object
        while (delimiter.hasNext()) {

            String code = delimiter.next().trim();

            boolean salesTax = false;

            if(code.charAt(0) == 'A'){
                salesTax = true;
            }

            if (delimiter.hasNext()) {
                String name = delimiter.next().trim();

                String price = delimiter.next().trim();

                double unitPrice = Double.parseDouble(price);

                //add item to hash map
                ProductSpecification spec = new ProductSpecification(code, name, unitPrice, salesTax);
                productSpecs.put(code, spec);
            }
        }
    }

    /** Return method */
        public ProductSpecification getSpecification (String itemID){

            return productSpecs.get(itemID);
        }

    /** Method to print out items */
    public static void displayItems (){

        System.out.println("Item Code     Item Name          Unit Price");


            for(Map.Entry<String, ProductSpecification> entry: productSpecs.entrySet()){
                System.out.printf("%s %20s %10s", entry.getKey(), entry.getValue().getName(), "$"+entry.getValue().getUnitPrice());
            System.out.println();}

            System.out.println();
        }


        /** Method for user navigation through updating item data */
        public static void updateItemData() throws IOException {

        System.out.println();

        System.out.print("Do you want to update item data? (A/D/M/Q) ");

        Scanner input = new Scanner(System.in);

        char userInput = input.next().charAt(0);

        switch(userInput){
            case'A': addItem(); break;
            case'D': deleteItem(); break;
            case'M': modifyItem(); break;
            case'Q': quit(); break;
        }
        }

        /** Method to add item */
        public static void addItem() throws IOException {

            //Create object to write in the file
            FileWriter output = new FileWriter(file, true);

            Scanner input = new Scanner(System.in);

            System.out.print("Please enter code: ");

            String code = input.next();

            boolean salesTax = false;

            if(code.charAt(0) == 'A'){
                salesTax = true;
            }

            System.out.print("Please enter name: ");

            String name = input.next().trim();

            System.out.print("Please print price: ");

            String price = input.next().trim();

            double unitPrice = Double.parseDouble(price);


            ProductSpecification spec = new ProductSpecification(code, name, unitPrice, salesTax);
            productSpecs.put(code, spec);

            output.write("");

            output.write(code + ", " + name + ", " + unitPrice + ", ");

            output.close();

            System.out.println("Update successful! Item: " + name + " has been added.");

            updateItemData();
        }

        /** Method to delete item*/
        public static void deleteItem() throws IOException {

            System.out.print("Enter code of item to be deleted: ");

            Scanner input = new Scanner(System.in);

            String code = null;

            String userInput;

            userInput = input.next();

            try{
                code = determineCorrectItemID(userInput);

            }
            catch(InputMismatchException x){
                System.out.print("Please enter a valid item code. Try again");

                System.out.print("Enter item code: ");

                userInput = input.next();
            }

            //Determine if the user entered the correct item code
            while(code.equals("No Match")){

                System.out.println("Invalid item code try again.");

                System.out.print("Please enter item code: ");

                userInput = input.next();

                try{
                    code = determineCorrectItemID(userInput);
                }
                catch(InputMismatchException x){
                    System.out.print("Please enter a valid item code. Try again");

                    System.out.print("Enter item code: ");

                    userInput = input.next();
                }
            }

            productSpecs.remove(code);

            System.out.println("Item code: " + code + " has been deleted successfully.");

            updateItemData();
        }

        /** Method to modify item */
        public static void modifyItem() throws IOException {
            System.out.println("Enter code of item to be modified: ");

            FileWriter output = new FileWriter(file, true);

            Scanner input = new Scanner(System.in);

            String userInput = input.next();

            String initialCode = null;

            //Determine initial item index
            try{
                initialCode = determineCorrectItemID(userInput);

            }
            catch(InputMismatchException x){
                System.out.print("Please enter a valid item code. Try again");

                System.out.print("Enter item code: ");

                userInput = input.next();
            }

            //Determine if the user entered the correct item code
            while(initialCode.equals("No Match")){

                System.out.println("Invalid item code try again.");

                System.out.print("Please enter item code: ");

                userInput = input.next();

                try{
                    initialCode = determineCorrectItemID(userInput);
                }
                catch(InputMismatchException x){
                    System.out.print("Please enter a valid item code. Try again");

                    System.out.print("Enter item code: ");

                    userInput = input.next();
                }
            }
            //User modifying item data
            System.out.println("Item code: " + productSpecs.get(initialCode).getCode());
            System.out.print("Modification: ");
            String newCode = input.next();

            System.out.println("Item name: " + productSpecs.get(initialCode).getName());
            System.out.print("Modification: ");
            String newName = input.next();

            System.out.println("Item name: " + productSpecs.get(initialCode).getUnitPrice());
            System.out.print("Modification: ");
            Double newUnitPrice = input.nextDouble();

            //Set modifications
            productSpecs.get(initialCode).setName(newName);
            productSpecs.get(initialCode).setUnitPrice(newUnitPrice);
            productSpecs.get(initialCode).setCode(newCode);


            System.out.println("Item: " + productSpecs.get(newCode).getName() + " successfully modified");
            updateItemData();
        }

        /** Method to quit */
        public static void quit(){
            System.out.println();
            displayItems();
            System.out.print("Thank you for using POST System. Bye.");
            System.exit(1);

         }

    /** Method to determine if the user entered an existing item */
    public static String determineCorrectItemID(String itemID){

        switch(itemID){
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

