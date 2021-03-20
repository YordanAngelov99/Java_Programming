import Person.Customer;
import Person.Seller;
import Transport_Industry.Transport_Industry;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File f = new File("c://Users/User/Desktop/InputFile.txt"); // Enter for your text file with the same information
        int customersCount = 0;
        int sellersCount = 0;
        Scanner sc = null;
        try {
            sc = new Scanner(f);
            System.out.println("Enter the number of customers in the file: ");
            customersCount = sc.nextInt();
            System.out.println("Enter the number of sellers in the file: ");
            sellersCount = sc.nextInt();
        } catch (FileNotFoundException e) {
            System.out.println("Your file not found !Please enter the exact file location !");
            return;
        }

        final int NUMBER_OF_CUSTOMERS = customersCount; // Number of Customers
        final int NUMBER_OF_SELLERS = sellersCount; // Number of Sellers
        final int SELLER_NUMBER = 1; // index of the Seller to which you will pay
        Transport_Industry transport_industry = Transport_Industry.createTransportIndustry();

        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<Seller> sellers = new ArrayList<>();

        for(int i = 0; i < NUMBER_OF_CUSTOMERS;i++){
            Customer c = new Customer("Dany" + " " + i,new BigDecimal("500"),true,19,true);
            customers.add(c);
        }

        for(int i = 0; i < NUMBER_OF_SELLERS;i++){
            Seller s = new Seller("Matt" + " " + i,new BigDecimal("1000"),false,20,transport_industry);
            sellers.add(s);
        }


        transport_industry.addSellers(sellers);
        transport_industry.addDrivers();
        transport_industry.assignVehicleToDrivers();

        sc.nextLine();
        System.out.println();
        System.out.println("Enter a type of transport among the following for all customers in the file: \"Bus\", \"Plane\", " +
                "\"Car\", \"Train\" and \"Ship\"");
        System.out.println("Enter a destination among the following for all customers in the file: \"Moscow\",\"Kairo\",\"Boston\"," +
                " \"Miami\",\"Sofia\",\"Plovdiv\",\"London\" ");


        for(Customer c : customers){

            System.out.println();
            System.out.println("========================= CUSTOMER =========================");
            System.out.println();

            try {
                String vehicleType = sc.nextLine();
                String destination = sc.nextLine();

                try {
                    c.pay(sellers.get(SELLER_NUMBER - 1), vehicleType, destination, transport_industry); // for the sellers you can choose which one you want
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Please enter a valid index for the sellers!");
                }

                if (c.hasTicket()) {
                    c.transport(transport_industry);
                }
            } catch (NoSuchElementException e){
                System.out.println("You haven't entered the write amount of input data in the file!");
            }
        }
    }
}
