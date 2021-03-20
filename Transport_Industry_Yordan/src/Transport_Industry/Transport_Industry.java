package Transport_Industry;

import Person.Driver;
import Person.Seller;
import Transport_Industry.Destinations.Destination;
import Transport_Industry.Vehicles.Bus;
import Transport_Industry.Vehicles.Cars.Electric_Car;
import Transport_Industry.Vehicles.Cars.Internal_Combustion_Engine_Car;
import Transport_Industry.Vehicles.Plane;
import Transport_Industry.Vehicles.Ship;
import Transport_Industry.Vehicles.Trains.Fast_Train;
import Transport_Industry.Vehicles.Vehicle;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

public class Transport_Industry {
    public static final int DRIVERS_TOTAL = 10;
    private ArrayList<Driver> drivers;
    private ArrayList<Vehicle> vehicles;
    private ArrayList<Seller> sellers;
    private ArrayList<String> normalDestinationNames;
    private ArrayList<String> exoticDestinationNames;
    private static Transport_Industry transport_industry = null;

    private Transport_Industry(){
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
    public static Transport_Industry createTransportIndustry(){
        if(transport_industry == null){
            return new Transport_Industry();
        }
        return transport_industry;
    }

    public void reachDestination(Driver driver,Destination destination){
        Vehicle v = driver.getVehicle();
        System.out.println("You have reached your destination! " + destination.getName() + "! We hope you enjoy it.");
        System.out.println("The destination is rated with " + destination.getRating() + " !");
        System.out.println("You have been going with our driver - " + driver.getName());
        System.out.println("The vehicle that drove you is " + v.getVehicleType() + " (model : " + v.getName() + ")");
        System.out.println("It drives with the incredible speed of " + v.getSpeed() + " horse powers!");
    }

    public void addSellers(ArrayList<Seller> sellers){
        for (Seller s : sellers){
            this.sellers.add(s);
        }
    }

    public void addDrivers(){
        ArrayList<Driver> newDrivers = new ArrayList<>();
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
                    new BigDecimal(new Random().nextInt(100) + 200), true,
                    new Random().nextInt(30) + 25, destination , this);
            this.drivers.add(driver);

        }

    }

    private Vehicle getVehicle() {

        Vehicle v = null;
        switch (new Random().nextInt(5)) {
            case 0: {
                if (new Random().nextBoolean()) {
                    v = new Electric_Car("Tesla", "15-12", 2018, "21234512");
                } else {
                    v = new Internal_Combustion_Engine_Car("Honda", "civic", 2008, "01245474");
                }
            }
            break;
            case 1: {
                if (new Random().nextBoolean()) {
                    v = new Fast_Train("Subarna Express", "Genesis", 2008);
                } else {
                    v = new Fast_Train("Ekota Express", "Genesis", 2010);
                }
            }
            break;
            case 2:
                v = new Bus("Mercedes", "2000", 2010, "12097213");
                break;
            case 3:
                v = new Ship("Master Baiter", "ship model", 2002);
                break;
            case 4:
                v = new Plane("Wright Flyer", "Revell 1:48 B25J Mitchell", 2010, 200);
                break;
                default: v = new Bus("Mercedes", "2000", 2010, "12097213");
                break;
        }
        this.vehicles.add(v);

        return v;
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
}

