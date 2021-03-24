package transportation;

import transportation.parkingindustry.Airport;
import transportation.person.Customer;
import transportation.person.Driver;
import transportation.person.Person;
import transportation.person.sellers.AirportTicketSeller;
import transportation.person.sellers.DestinationTicketsSeller;
import transportation.transportindustry.TransportIndustry;
import transportation.transportindustry.vehicles.Bus;
import transportation.transportindustry.vehicles.Plane;
import transportation.transportindustry.vehicles.Ship;
import transportation.transportindustry.vehicles.Vehicle;
import transportation.transportindustry.vehicles.cars.ElectricCar;
import transportation.transportindustry.vehicles.trains.FastTrain;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        File f = new File("InputFile.txt");
        int customersCount = 0;
        int sellersCount = 0;
        try(Scanner sc = new Scanner(f)) {

            System.out.println();
            System.out.println("========== FIRST PART ============");
            System.out.println();

            System.out.println("Enter the number of customers in the file: ");
            customersCount = sc.nextInt();
            System.out.println("Enter the number of sellers in the file: ");
            sellersCount = sc.nextInt();

            final int numberOfCustomers = customersCount; // Number of Customers // camel case
            final int numberOfDestinationTicketsSellers = sellersCount; // Number of Destination Tickets Sellers
            final int numberOfAirportTicketsSellers = sellersCount; // Number of Airport Tickets Sellers
            final int sellerNumber = 1; // index of the Seller to which you will pay
            TransportIndustry transportIndustry = new TransportIndustry();
            Airport airport = new Airport("Sofia Airport",4);

            List<Customer> customers = new ArrayList<>();
            List<DestinationTicketsSeller> destinationTicketsSellers = new ArrayList<>();
            List<AirportTicketSeller> airportTicketsSellers = new ArrayList<>();

            for (int i = 0; i < numberOfCustomers; i++) {
                Customer c = new Customer("Dany" + " " + i, ((int) (Math.random() * 500)) + 500, Person.Gender.FEMALE, 19, true);
                customers.add(c);
            }

            for (int i = 0; i < numberOfDestinationTicketsSellers; i++) {
                DestinationTicketsSeller s = new DestinationTicketsSeller("Matty" + " " + i, 1000, Person.Gender.MALE, 20, transportIndustry);
                destinationTicketsSellers.add(s);
            }

            for (int i = 0; i < numberOfAirportTicketsSellers; i++) {
                AirportTicketSeller s = new AirportTicketSeller("Matty" + " " + i, 1000, Person.Gender.MALE, 20, transportIndustry,airport);
                airportTicketsSellers.add(s);
            }


            transportIndustry.addSellers(destinationTicketsSellers);
            transportIndustry.addDrivers();
            transportIndustry.assignVehicleToDrivers();


//            sc.nextLine();
//            System.out.println();
//            System.out.println("Enter a type of transport among the following for all customers in the file: \"Bus\", \"Plane\", " +
//                    "\"Car\", \"Train\" and \"Ship\"");
//            System.out.println("Enter a destination among the following for all customers in the file: \"Moscow\",\"Kairo\",\"Boston\"," +
//                    " \"Miami\",\"Sofia\",\"Plovdiv\",\"London\" ");
//
//
//            for (Customer c : customers) {
//
//                System.out.println();
//                System.out.println("========================= CUSTOMER =========================");
//                System.out.println();
//
//                try {
//                    String numberOfDriversParking = sc.nextLine();
//                    String destination = sc.nextLine();
//
//                    try {
//                        c.pay(destinationTicketsSellers.get(sellerNumber - 1), numberOfDriversParking, destination, transportIndustry); // for the sellers you can choose which one you want
//                    } catch (IndexOutOfBoundsException e) {
//                        System.out.println("Please enter a valid index for the sellers!");
//                    }
//
//                    if (c.hasTicket()) {
//                        c.transport(transportIndustry);
//                    }
//                } catch (NoSuchElementException e) {
//                    System.out.println("You haven't entered the right amount of input data in the file!");
//                }
//            }
//
            System.out.println();
            System.out.println("========== SECOND PART ============");
            System.out.println();

            transportIndustry.printVehiclesStatistics();

            System.out.println();
            System.out.println("========== THIRD PART ============");
            System.out.println();


            final int numberOfDriversParking = 15;
            int countingVariableForDrivers = 1;
            final int numberForCaseCar = 0;
            final int numberForCaseTrain = 1;
            final int numberForCaseBus = 2;
            final int numberForCaseShip = 3;
            final int numberForCasePlane = 4;

            List<Driver> drivers = new ArrayList<>();
            for(int i = 1; i <= numberOfDriversParking; i++){
                Driver d = new Driver("Driver " + countingVariableForDrivers++ , (int)(Math.random() * 2500) + 2000, Person.Gender.MALE,
                        (int)(Math.random() * 20) + 20,null,transportIndustry);
                Vehicle v = null;
                switch ((int)(Math.random() * 5)){
                    case numberForCaseCar: v = new ElectricCar("Ferrari","12-43",
                            (int)(Math.random() * 40) + 1980,"898932112");break;
                    case numberForCaseTrain: v = new FastTrain("Fast train " + countingVariableForDrivers," 72-22",  (int)(Math.random() * 40) + 1980);break;
                    case numberForCaseBus: v = new Bus("Bus " + countingVariableForDrivers," 72-11", (int)(Math.random() * 40) + 1980, "898932112" );break;
                    case numberForCaseShip: v = new Ship("Ship " + countingVariableForDrivers, "12-11", (int)(Math.random() * 40) + 1980);break;
                    case numberForCasePlane: v = new Plane("Plane " + countingVariableForDrivers, "62-81",(int)(Math.random() * 40) + 1980,400);break;
                    default: v = new FastTrain("Fast train " + countingVariableForDrivers," 72-22",  (int)(Math.random() * 40) + 1980);break;
                }
                d.setVehicle(v);
                drivers.add(d);
            }
            int driverId = 1;
            countingVariableForDrivers = 0;

           for(Driver driver : drivers) {
               System.out.println();
               System.out.println("Driver " + driverId++ + " Money before parking: " + driver.getMoney());
               driver.park(airport, airportTicketsSellers.get(sellerNumber - 1));
               if(driver.getParkingTicket() == null){
                   System.out.println("You don't have a parking ticket!");
                   continue;
               }
               driver.driverWorkingWhileVehicleParked();
               if(driverId % 3 == 0 && drivers.get(countingVariableForDrivers).getParkingTicket() != null){      // a driver leaves when each third person parks
                   System.out.println("Driver " + (countingVariableForDrivers + 1) + " leaves the parking !");
                   System.out.println();
                   drivers.get(countingVariableForDrivers).leave(airport, airportTicketsSellers.get(sellerNumber - 1));
                   countingVariableForDrivers++;
                   System.out.println("His money after the parking fee -> " + drivers.get(countingVariableForDrivers - 1).getMoney());
                   System.out.println();
               }
               System.out.println();
           }
           // for the drivers left
           for(int k = countingVariableForDrivers;k < drivers.size();k++){
               if(drivers.get(k).getParkingTicket() != null) {
                   System.out.println("Driver " + k + " of vehicle " + drivers.get(k).getVehicle().getName() + " leaves the parking !");
                   drivers.get(k).leave(airport, airportTicketsSellers.get(sellerNumber - 1));
                   System.out.println("His money after the parking fee -> " + drivers.get(k).getMoney());
                   System.out.println();
               }
           }

        } catch (FileNotFoundException e) {
        System.out.println("Your file not found !Please enter the exact file location !");
        return;
    }


    }
}

