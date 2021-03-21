package TransportIndustry;

import Person.Driver;
import Person.Person;
import Person.Seller;
import TransportIndustry.Destinations.Destination;
import TransportIndustry.Vehicles.Bus;
import TransportIndustry.Vehicles.Cars.ElectricCar;
import TransportIndustry.Vehicles.Cars.InternalCombustionEngineCar;
import TransportIndustry.Vehicles.Plane;
import TransportIndustry.Vehicles.Ship;
import TransportIndustry.Vehicles.Trains.FastTrain;
import TransportIndustry.Vehicles.Trains.NormalTrain;
import TransportIndustry.Vehicles.Vehicle;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TransportIndustry {
    public static final int DRIVERS_TOTAL = 10;
    public static final int NUMBER_FOR_CARS_CASE = 0;
    public static final int NUMBER_FOR_TRAINS_CASE = 1;
    public static final int NUMBER_FOR_BUSES_CASE = 2;
    public static final int NUMBER_FOR_SHIPS_CASE = 3;
    public static final int NUMBER_FOR_PALNES_CASE = 4;
    private List<Driver> drivers;
    private List<Vehicle> vehicles;
    private List<Seller> sellers;
    private List<String> normalDestinationNames;
    private List<String> exoticDestinationNames;
    private static TransportIndustry TransportIndustry = null;

    private TransportIndustry(){
        this.drivers = new ArrayList<>();
        this.vehicles = new ArrayList<>();
        this.sellers = new ArrayList<>();
        this.normalDestinationNames = new ArrayList<>();
        this.exoticDestinationNames = new ArrayList<>();
    }

    public Driver assignDriver(){
        Driver driver = this.drivers.get(new Random().nextInt(this.drivers.size()));
        if(driver != null && driver.getVehicle() != null){
            this.vehicles.remove(driver);
            this.vehicles.remove(driver.getVehicle());  // the driver and his vehicle are taken now
        }
        return driver;
    }

    public void addTheAvailableDriverAndVehicleAgain(Driver driver){
        this.drivers.add(driver);
        this.vehicles.add(driver.getVehicle());    // the driver and his vehicle are now available again
    }
    public static TransportIndustry createTransportIndustry(){
        if(TransportIndustry == null){
            return new TransportIndustry();
        }
        return TransportIndustry;
    }

    public void reachDestination(Driver driver,Destination destination){
        Vehicle v = driver.getVehicle();
        System.out.println("You have reached your destination! " + destination.getName() + "! We hope you enjoy it.");
        System.out.println("The destination is rated with " + destination.getRating() + " !");
        System.out.println("You have been going with our driver - " + driver.getName());
        System.out.println("The vehicle that drove you is " + v.getVehicleType() + " (model : " + v.getName() + ")");
        System.out.println("It drives with the incredible speed of " + v.getSpeed() + " horse powers!");
    }

    public void addSellers(List<Seller> sellers){
        for (Seller s : sellers){
            this.sellers.add(s);
        }
    }

    public void addDrivers(){
        List<Driver> newDrivers = new ArrayList<>();
        if(this.sellers == null){
            System.out.println("There are no sellers!Please assign sellers first!");
            return;
        }
        Seller seller = this.sellers.get(new Random().nextInt(this.sellers.size()));
        String[] driverNames = {"Joe","John","Mike","Jeffrey","Gary"};
        for(int i = 0; i < DRIVERS_TOTAL;i++) {
            Destination destination = seller.getRandomDestination();
            if(destination == null){
                System.out.println("No destinations! Please enter new destinations!");
                continue;
            }
            Driver driver = new Driver(driverNames[new Random().nextInt(driverNames.length)],
                    new BigDecimal(new Random().nextInt(100) + 200), Person.Gender.MALE,
                    new Random().nextInt(30) + 25, destination , this);
            this.drivers.add(driver);

        }

    }

    public boolean checkIfVehicleAvailable( Vehicle.vehicleType vehicleType){

        if(vehicleType == null){
            return false;
        }

        for (Vehicle v : this.vehicles){
            if(v.getVehicleType() == vehicleType){
                return true;
            }
        }
        return false;
    }

    public void assignVehicleToDrivers(){
        for(Driver driver : drivers){
            Vehicle v = getVehicle();
            driver.setVehicle(v);
        }
    }

    public String getDestinationType(Destination destination) {
        if(exoticDestinationNames.contains(destination.getName())){
            return "exotic";
        }else{
            return "normal";
        }

    }

    public void addExoticDestinationNames(String[] exoticDestinationNames) {
        for(String s : exoticDestinationNames){
            this.exoticDestinationNames.add(s);
        }
    }

    public void addNormalDestinationNames(String[] normalDestinationNames) {
        for(String s : normalDestinationNames){
            this.normalDestinationNames.add(s);
        }
    }

    private Vehicle getVehicle() {

        Vehicle v = null;
        switch (new Random().nextInt(5)) {
            case NUMBER_FOR_CARS_CASE: {
                if (new Random().nextBoolean()) {
                    v = new ElectricCar("Tesla", "15-12", 2018, "21234512");
                } else {
                    v = new InternalCombustionEngineCar("Honda", "civic", 2008, "01245474");
                }
            }
            break;
            case NUMBER_FOR_TRAINS_CASE: {
                if (new Random().nextBoolean()) {
                    v = new NormalTrain("Subarna Express", "Genesis", 2008);
                } else {
                    v = new FastTrain("Ekota Express", "Genesis", 2010);
                }
            }
            break;
            case NUMBER_FOR_BUSES_CASE:
                v = new Bus("Mercedes", "2000", 2010, "12097213");
                break;
            case NUMBER_FOR_SHIPS_CASE:
                v = new Ship("Master Baiter", "ship model", 2002);
                break;
            case NUMBER_FOR_PALNES_CASE:
                v = new Plane("Wright Flyer", "Revell 1:48 B25J Mitchell", 2010, 200);
                break;
            default: v = new Bus("Mercedes", "2000", 2010, "12097213");
                break; // for default we put a bus
        }
        this.vehicles.add(v);

        return v;
    }

}

