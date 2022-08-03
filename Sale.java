
import java.util.*;

/**Sale class contains methods for individual sale*/
public class Sale {

    //Array list to hold sale line items
    private List <SalesLineItem> saleLineItems= new ArrayList<>();

    private boolean isComplete = false;

    /**Method to add item to array list and display specs to user */
    public void makeLineItem(ProductSpecification spec, int quantity){
        SalesLineItem curItem = new SalesLineItem(spec, quantity);
        System.out.println("Item:                                       " + curItem.getName());
        saleLineItems.add(curItem);
        System.out.println("Subtotal:                                   $" + curItem.getSubtotal());
    }

    /**Method for user to make a payment */
    public void makePayment(){

        Scanner input = new Scanner(System.in);

        System.out.println("---------------------------------------------");

        displaySalesLineItems();

        Double initialTotal = 0.0;

        for(int i = 0; i < saleLineItems.size(); i++){
                initialTotal += (saleLineItems.get(i).getSubtotal());
        }

        //Subtotal
        System.out.println("Subtotal:                                    $" + initialTotal);


        System.out.println("Total with tax (6%):                         $" + getTotal());

        System.out.print("Tendered amount:                             $");

        //ensure numeric input
        try {
            Double tenderedAmount = input.nextDouble();

            //ensure input is greater than total
        while(tenderedAmount < getTotal() ){

            System.out.println("Please enter decimal amount greater than total.");

            System.out.print("Tendered amount:                             $");

            tenderedAmount =input.nextDouble();
        }

        System.out.println("Change:                                      $" + ((int)((tenderedAmount - getTotal()) * 100.0 )) / 100.0);

        System.out.println("-------------------------------------------");

        } catch(InputMismatchException x){
            System.out.println("Please enter a valid decimal amount.");
        }

    }

    /** Return method to indicate sale is complete */
    public boolean becomeComplete(){
       return true;
    }

    /** Method to get the total of the sales line items */
    public double getTotal() {

        double total = 0;

        Iterator it = saleLineItems.iterator();
        while (it.hasNext()) {
            SalesLineItem sli = (SalesLineItem) it.next();
            total += (sli.getSubtotal());
        }

        double  tax = 0;

        for(int i = 0; i < saleLineItems.size(); i++){
            if(saleLineItems.get(i).determineSalesTax()){
                tax += (saleLineItems.get(i).getSubtotal() * 0.06);
            }
        }
        total += tax;
        total = (int)(total*1000)/ 1000.0;

        return total;
    }

    /** Method to display sales line items */
    public void displaySalesLineItems(){

        bubbleSort(saleLineItems);

        for(int i = 0; i < saleLineItems.size(); i++){
            System.out.printf("%s %20s %10s", saleLineItems.get(i).getQuantity(),saleLineItems.get(i).getName(), "$" + saleLineItems.get(i).getSubtotal());
            System.out.println();
        }
    }

    /** bubble sort method to display in alphabetical order */
    public static List<SalesLineItem> bubbleSort(List<SalesLineItem> saleLineItems){
        boolean needNextPass = true;

        for(int k = 1; k < saleLineItems.size() && needNextPass; k++){

            for(int i = 0; i < saleLineItems.size() - k; i++ ){

                if(saleLineItems.get(i).getName().compareTo(saleLineItems.get(i + 1).getName()) == 0){
                    saleLineItems.get(i).setQuantity(saleLineItems.get(i).getQuantity() + saleLineItems.get(i + 1).getQuantity());
                    saleLineItems.remove(i + 1);
                    needNextPass=true;
                    break;
                }

                //swap
                if(saleLineItems.get(i).getName().compareTo(saleLineItems.get(i + 1).getName()) > 0){
                    SalesLineItem temp = saleLineItems.get(i);
                    saleLineItems.set(i, saleLineItems.get(i + 1));
                    saleLineItems.set(i + 1, temp);
                    needNextPass = true;
                }

            }
        }
        return saleLineItems;
    }
}

