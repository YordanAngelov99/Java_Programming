package transportation.transportindustry;

import transportation.person.Driver;
import transportation.person.Person;
import transportation.person.sellers.DestinationTicketsSeller;
import transportation.transportindustry.destinations.Destination;
import transportation.transportindustry.vehicles.Bus;
import transportation.transportindustry.vehicles.cars.Car;
import transportation.transportindustry.vehicles.cars.ElectricCar;
import transportation.transportindustry.vehicles.cars.InternalCombustionEngineCar;
import transportation.transportindustry.vehicles.Plane;
import transportation.transportindustry.vehicles.Ship;
import transportation.transportindustry.vehicles.trains.FastTrain;
import transportation.transportindustry.vehicles.trains.NormalTrain;
import transportation.transportindustry.vehicles.trains.Train;
import transportation.transportindustry.vehicles.Vehicle;

import java.util.*;

public class TransportIndustry {
    public static final int DRIVERS_TOTAL = 20;
    public static final int NUMBER_FOR_CARS_CASE = 0;
    public static final int NUMBER_FOR_TRAINS_CASE = 1;
    public static final int NUMBER_FOR_BUSES_CASE = 2;
    public static final int NUMBER_FOR_SHIPS_CASE = 3;
    public static final int NUMBER_FOR_PALNES_CASE = 4;
    public static final int NUMBER_OF_ALL_VEHICLES = 5;
    private List<Driver> drivers;
    private HashSet<Vehicle> vehicles;
    private HashMap<Vehicle.VehicleType,Integer> allVehiclesByType;
    private List<Car> cars;
    private List<Train> trains;
    private List<DestinationTicketsSeller> destinationTicketsSellers;
    private List<String> normalDestinationNames;
    private List<String> exoticDestinationNames;

    public TransportIndustry(){
        this.drivers = new ArrayList<>();
        this.vehicles = new HashSet<>();
        this.cars = new ArrayList<>();
        this.trains = new ArrayList<>();
        this.destinationTicketsSellers = new ArrayList<>();
        this.allVehiclesByType = new HashMap<>();
        this.normalDestinationNames = new ArrayList<>();
        this.exoticDestinationNames = new ArrayList<>();
    }

    public Driver assignDriver(){
        if(this.drivers == null){
            System.out.println("There are no drivers!");
            return null;
        }
        Driver driver = this.drivers.get(new Random().nextInt(this.drivers.size()));
        if(driver != null && driver.getVehicle() != null){
            this.drivers.remove(driver);
            this.vehicles.remove(driver.getVehicle());  // the driver and his vehicle are taken now
        }
        return driver;
    }

    public void addTheAvailableDriverAndVehicleAgain(Driver driver){
        this.drivers.add(driver);
        this.vehicles.add(driver.getVehicle());    // the driver and his vehicle are now available again
    }

    public void reachDestination(Driver driver,Destination destination){
        Vehicle v = driver.getVehicle();
        System.out.println("You have reached your destination! " + destination.getName() + "! We hope you enjoy it.");
        System.out.println("The destination is rated with " + destination.getRating() + " !");
        System.out.println("You have been going with our driver - " + driver.getName());
        System.out.println("The vehicle that drove you is " + v.getVehicleType() + " (model : " + v.getName() + ")");
        System.out.println("It drives with the incredible speed of " + v.getSpeed() + " horse powers!");
    }

    public void addSellers(List<DestinationTicketsSeller> destinationTicketsSellers){
        for (DestinationTicketsSeller s : destinationTicketsSellers){
            this.destinationTicketsSellers.add(s);
        }
    }

    public void addDrivers(){
        if(this.destinationTicketsSellers == null){
            System.out.println("There are no sellers!Please assign sellers first!");
            return;
        }
        DestinationTicketsSeller destinationTicketsSeller = this.destinationTicketsSellers.get(new Random().nextInt(this.destinationTicketsSellers.size()));
        String[] driverNames = {"Joe","John","Mike","Jeffrey","Gary"};
        for(int i = 0; i < DRIVERS_TOTAL;i++) {
            Destination destination = destinationTicketsSeller.getRandomDestination();
            if(destination == null){
                System.out.println("No destinations! Please enter new destinations!");
                continue;
            }
            Driver driver = new Driver(driverNames[(int)(Math.random() * driverNames.length)],
                    ( (int)(Math.random() * 100) ) + 200, Person.Gender.MALE,
                    ( (int)(Math.random() * 30) ) + 25, destination , this);
            this.drivers.add(driver);

        }
    }

    public boolean checkIfVehicleAvailable( Vehicle.VehicleType VehicleType){

        if(VehicleType == null){
            return false;
        }

        for (Vehicle v : this.vehicles){
            if(v != null) {
                if (v.getVehicleType() == VehicleType) {
                    return true;
                }
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

    public void printVehiclesStatistics(){
        printVehiclesByName();
        printVehiclesByModel();
        printVehiclesByYearProduced();
        printVehiclesBySpeed();
        printAllVehiclesAvailableByTypes(); // These statistics are for all Vehicles

        printTrainsByDistance(); // These statistic is for all Trains
        printCarsByRegistrationNumber(); // These statistic is for all Cars
    }

    private void printVehicleInformation(List<Vehicle> vehicles){
        int i = 1;

        for(Vehicle v : vehicles){
            if(v != null) {
                System.out.println("Vehicle " + i++ + ":");
                System.out.println(v);
                System.out.println();
            }
        }
    }

    private void printOnlyTrainsInformation(List<Train> trains){
        int i = 1;

        for(Train t : trains) {
            if (t != null){
                System.out.println("Train " + i++ + ":");
                System.out.println(t);
                System.out.println();
            }
        }
    }

    private void printOnlyCarsInformation(List<Car> cars){
        int i = 1;

        for(Car c : cars){
            if(c != null) {
                System.out.println("Car " + i++ + ":");
                System.out.println(c);
                System.out.println();
            }
        }
    }

    private void printVehiclesByName(){
        System.out.println();
        System.out.println("---------- First Statistic - Vehicles printed by Name ----------");
        System.out.println();


        if(this.vehicles == null){
            System.out.println("There are no vehicles!");
            return;
        }
        List<Vehicle> vehiclesByName = new ArrayList<>(this.vehicles);
        Collections.sort(vehiclesByName,(Vehicle o1, Vehicle o2) -> o1.getName().compareTo(o2.getName()));
        printVehicleInformation(vehiclesByName);
    }

    private void printVehiclesByModel(){
        System.out.println();
        System.out.println("---------- Second Statistic - Vehicles printed by Model ----------");
        System.out.println();

        if(this.vehicles == null){
            System.out.println("There are no vehicles!");
            return;
        }
        List<Vehicle> vehiclesByModel = new ArrayList<>(this.vehicles);
        Collections.sort(vehiclesByModel, (Vehicle o1, Vehicle o2) -> o1.getModel().compareTo(o2.getModel()));
        printVehicleInformation(vehiclesByModel);

    }
    private void printVehiclesByYearProduced(){
        System.out.println();
        System.out.println("---------- Third Statistic - Vehicles printed by Year Produced ----------");
        System.out.println();

        if(this.vehicles == null){
            System.out.println("There are no vehicles!");
            return;
        }
        List<Vehicle> vehiclesByYearProduced = new ArrayList<>(this.vehicles);
        Collections.sort(vehiclesByYearProduced,(Vehicle o1, Vehicle o2) -> o1.getYearProduced() - o2.getYearProduced());
        printVehicleInformation(vehiclesByYearProduced);
    }
    private void printVehiclesBySpeed(){
        System.out.println();
        System.out.println("---------- Fourth Statistic - Vehicles printed by Speed ----------");
        System.out.println();
        if(this.vehicles == null){
            System.out.println("There are no vehicles!");
            return;
        }
        List<Vehicle> vehiclesBySpeed = new ArrayList<>(this.vehicles);
        Collections.sort(vehiclesBySpeed,(Vehicle o1, Vehicle o2) -> o1.getSpeed() - o2.getSpeed());
        printVehicleInformation(vehiclesBySpeed);
    }

    private void printAllVehiclesAvailableByTypes(){
        System.out.println();
        System.out.println("---------- Fifth Statistic - Vehicles by availability ----------");
        System.out.println();

        if(this.allVehiclesByType == null){
            System.out.println("There are no vehicles!");
            return;
        }

        for (Map.Entry<Vehicle.VehicleType,Integer> e : this.allVehiclesByType.entrySet()){
            System.out.println("Available vehicles of type " + e.getKey() + " are " + e.getValue() + " !");
        }
        System.out.println();
    }

    private void printTrainsByDistance(){
        System.out.println();
        System.out.println("---------- Sixth Statistic - Trains printed by Distance ----------");
        System.out.println();

        if(this.trains == null){
            System.out.println("There are no trains!");
            return;
        }

        List<Train> trainsByDistance = new ArrayList<>(this.trains);
        Collections.sort(trainsByDistance,(Train o1,Train o2) -> o1.getDistanceType().compareTo(o2.getDistanceType()));
        printOnlyTrainsInformation(this.trains);
    }

    private void printCarsByRegistrationNumber(){
        System.out.println();
        System.out.println("---------- Seventh Statistic - Cars printed by Registration Number ----------");
        System.out.println();

        if(this.cars == null){
            System.out.println("There are no cars!");
            return;
        }

        List<Car> carsByRegistrationNumber = new ArrayList<>(this.cars);
        Collections.sort(carsByRegistrationNumber,(Car o1,Car o2) -> o1.getRegistrationNumber().compareTo(o2.getRegistrationNumber()));
        printOnlyCarsInformation(carsByRegistrationNumber);
    }


    private Vehicle getVehicle() {

        Vehicle v = null;
        switch ((int)(Math.random() * NUMBER_OF_ALL_VEHICLES)) {
            case NUMBER_FOR_CARS_CASE: {
                if (new Random().nextBoolean()) {
                    v = new ElectricCar("Tesla", "15-12", 2018, "21234512");
                } else {
                    v = new InternalCombustionEngineCar("Honda", "civic", 2008, "01245474");
                }
                this.cars.add((Car) v);
            }
            break;
            case NUMBER_FOR_TRAINS_CASE: {
                if (new Random().nextBoolean()) {
                    v = new NormalTrain("Subarna Express", "Genesis", 2008);
                } else {
                    v = new FastTrain("Ekota Express", "Genesis", 2010);
                }
                this.trains.add((Train) v);
            }
            break;
            case NUMBER_FOR_BUSES_CASE: {
                v = new Bus("Mercedes", "2000", 1999, "12097213");
                break;
            }
            case NUMBER_FOR_SHIPS_CASE: {
                v = new Ship("Master Baiter", "ship model", 2002);
                break;
            }
            case NUMBER_FOR_PALNES_CASE: {
                v = new Plane("Wright Flyer", "Revell 1:48 B25J Mitchell", 2020, 200);
                break;
            }
            default: v = new Bus("Mercedes", "2000", 2000, "12097213");
                break; // for default we put a bus
        }
        this.vehicles.add(v);
        if(!this.allVehiclesByType.containsKey(v.getVehicleType())){
            this.allVehiclesByType.put(v.getVehicleType(),1);
        }else{
            Integer temp = this.allVehiclesByType.get(v.getVehicleType()) + 1;
            this.allVehiclesByType.put(v.getVehicleType(),temp);
        }


        return v;
    }

}

