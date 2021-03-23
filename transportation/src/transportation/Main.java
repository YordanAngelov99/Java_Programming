package transportation;

import transportation.person.Customer;
import transportation.person.Person;
import transportation.person.Seller;
import transportation.transportIndustry.TransportIndustry;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        File f = new File("InputFile.txt");
        int customersCount = 0;
        int sellersCount = 0;
        try(Scanner sc = new Scanner(f)) {
            System.out.println("Enter the number of customers in the file: ");
            customersCount = sc.nextInt();
            System.out.println("Enter the number of sellers in the file: ");
            sellersCount = sc.nextInt();

            final int numberOfCustomers = customersCount; // Number of Customers // camel case
            final int numberOfSellers = sellersCount; // Number of Sellers
            final int sellerNumber = 1; // index of the Seller to which you will pay
            TransportIndustry transportIndustry = new TransportIndustry();

            List<Customer> customers = new ArrayList<>();
            List<Seller> sellers = new ArrayList<>();

            for (int i = 0; i < numberOfCustomers; i++) {
                Customer c = new Customer("Dany" + " " + i, ((int) (Math.random() * 500)) + 500, Person.Gender.FEMALE, 19, true);
                customers.add(c);
            }

            for (int i = 0; i < numberOfSellers; i++) {
                Seller s = new Seller("Matty" + " " + i, 1000, Person.Gender.MALE, 20, transportIndustry);
                sellers.add(s);
            }


            transportIndustry.addSellers(sellers);
            transportIndustry.addDrivers();
            transportIndustry.assignVehicleToDrivers();


            sc.nextLine();
            int a = 1 + 2;
            System.out.println("Enter a type of transport among the following for all customers in the file: \"Bus\", \"Plane\", " +
                    "\"Car\", \"Train\" and \"Ship\"");
            System.out.println("Enter a destination among the following for all customers in the file: \"Moscow\",\"Kairo\",\"Boston\"," +
                    " \"Miami\",\"Sofia\",\"Plovdiv\",\"London\" ");


            for (Customer c : customers) {

                System.out.println();
                System.out.println("========================= CUSTOMER =========================");
                System.out.println();

                try {
                    String vehicleType = sc.nextLine();
                    String destination = sc.nextLine();

                    try {
                        c.pay(sellers.get(sellerNumber - 1), vehicleType, destination, transportIndustry); // for the sellers you can choose which one you want
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Please enter a valid index for the sellers!");
                    }

                    if (c.hasTicket()) {
                        c.transport(transportIndustry);
                    }
                } catch (NoSuchElementException e) {
                    System.out.println("You haven't entered the right amount of input data in the file!");
                }
            }
            transportIndustry.printVehiclesStatistics();
        } catch (FileNotFoundException e) {
        System.out.println("Your file not found !Please enter the exact file location !");
        return;
    }


    }
}

