import Person.Customer;
import Person.Person;
import Person.Seller;
import TransportIndustry.TransportIndustry;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File f = new File("c://Users/User/Desktop/Java_Programming/TransportIndutryYordan/InputFile.txt"); // Enter for your text file with the same information
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
        TransportIndustry TransportIndustry = null;
        TransportIndustry = TransportIndustry.createTransportIndustry();

        List<Customer> customers = new ArrayList<>();
        List<Seller> sellers = new ArrayList<>();

        for(int i = 0; i < NUMBER_OF_CUSTOMERS;i++){
            Customer c = new Customer("Dany" + " " + i,new BigDecimal("500"), Person.Gender.FEMALE,19,true);
            customers.add(c);
        }

        for(int i = 0; i < NUMBER_OF_SELLERS;i++){
            Seller s = new Seller("Matty" + " " + i,new BigDecimal("1000"),Person.Gender.MALE,20,TransportIndustry);
            sellers.add(s);
        }


        TransportIndustry.addSellers(sellers);
        TransportIndustry.addDrivers();
        TransportIndustry.assignVehicleToDrivers();

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
                    c.pay(sellers.get(SELLER_NUMBER - 1), vehicleType, destination, TransportIndustry); // for the sellers you can choose which one you want
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Please enter a valid index for the sellers!");
                }

                if (c.hasTicket()) {
                    c.transport(TransportIndustry);
                }
            } catch (NoSuchElementException e){
                System.out.println("You haven't entered the write amount of input data in the file!");
            }
        }
    }
}

