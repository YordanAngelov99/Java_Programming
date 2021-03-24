package transportation.parkingindustry;

import transportation.person.sellers.AirportTicketSeller;
import transportation.transportindustry.vehicles.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Airport {
    public static final int NUMBER_OF_TRAINS_MAXIMUM_PLACES = 7;
    public static final int NUMBER_OF_CARS_MAXIMUM_PLACES = 30;
    public static final int NUMBER_OF_SHIPS_MAXIMUM_PLACES = 3;
    public static final int NUMBER_OF_BUSES_MAXIMUM_PLACES = 15;
    public static final int NUMBER_OF_PLANES_MAXIMUM_PLACES = 5;
    private String name;
    private int parkingLevel;
    private List<AirportTicketSeller> airportTicketSellers;
    private HashMap<Vehicle.VehicleType,List<Vehicle>> vehiclesParked;
    private HashMap<Vehicle.VehicleType,Integer> freeSpaces;

    public Airport(String name, int parkingLevel) {
        this.name = name;
        this.parkingLevel = parkingLevel;
        this.airportTicketSellers = new ArrayList<>();
        this.vehiclesParked = new HashMap<>();
        this.freeSpaces = addFreeSpaces();
    }

    public boolean checkIfParkSlotsAvailable(Vehicle vehicle) {
            if(vehicle == null){
                System.out.println("You don't have a vehicle! Buy one!");
                return false;
            }
            Vehicle.VehicleType type = vehicle.getVehicleType();
            if(this.freeSpaces.get(type) <= 0){
                return false;
            }

            int freeSpacesForThisVehicle = this.freeSpaces.get(type) - 1;
            this.freeSpaces.put(type,freeSpacesForThisVehicle);
            if( this.vehiclesParked.get(type) == null){
                System.out.println("There are no parked vehicles at the moment of this type!");
                return true;
            }
            System.out.println("Number of available slots for " + type + " are " + (this.freeSpaces.get(type) + 1));
            System.out.println("========================");
            return true;
    }


    public void addVehicleToParking(Vehicle vehicle){

        if(vehicle == null){
            System.out.println("You don't have a vehicle! Buy one!");
            return;
        }
        if(!this.vehiclesParked.containsKey(vehicle.getVehicleType())){
            this.vehiclesParked.put(vehicle.getVehicleType(),new ArrayList<>());
        }

        System.out.println(vehicle.getVehicleType() + " added to parking!");
        this.vehiclesParked.get(vehicle.getVehicleType()).add(vehicle);

    }

    public void removeVehicleFromParking(Vehicle vehicle){
        if(vehicle == null){
            System.out.println("You don't have a vehicle!");
            return;
        }
        if(this.vehiclesParked.containsKey(vehicle.getVehicleType())){
            this.vehiclesParked.get(vehicle.getVehicleType()).remove(vehicle);
        }

        int freeSpacesForThisVehicle = this.freeSpaces.get(vehicle.getVehicleType()) + 1;
        this.freeSpaces.put(vehicle.getVehicleType(),freeSpacesForThisVehicle);

        System.out.println("============ PARKED VEHICLES ============");
        for(Vehicle.VehicleType v : vehiclesParked.keySet()){
            System.out.println("Number of parked vehicles of type " + v + " are " + vehiclesParked.get(v).size());
            System.out.println("=========================================");
        }
        System.out.println();
    }
    private HashMap<Vehicle.VehicleType,Integer> addFreeSpaces(){
        HashMap<Vehicle.VehicleType,Integer> newSlots = new HashMap<>();
        newSlots.put(Vehicle.VehicleType.TRAIN, NUMBER_OF_TRAINS_MAXIMUM_PLACES);
        newSlots.put(Vehicle.VehicleType.CAR,NUMBER_OF_CARS_MAXIMUM_PLACES);
        newSlots.put(Vehicle.VehicleType.SHIP,NUMBER_OF_SHIPS_MAXIMUM_PLACES);
        newSlots.put(Vehicle.VehicleType.BUS,NUMBER_OF_BUSES_MAXIMUM_PLACES);
        newSlots.put(Vehicle.VehicleType.PLANE, NUMBER_OF_PLANES_MAXIMUM_PLACES);
        return  newSlots;
    }


}
