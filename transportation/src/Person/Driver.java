package Person;

import TransportIndustry.Destinations.Destination;
import TransportIndustry.TransportIndustry;
import TransportIndustry.Vehicles.Vehicle;

import java.math.BigDecimal;

public class Driver extends Person{
    private Vehicle vehicle;
    private Destination destination;
    private static TransportIndustry TransportIndustry;

    public Driver(String name, BigDecimal money, Person.Gender gender, int age,Destination destination,TransportIndustry new_TransportIndustry) {
        super(name, money, gender, age);
        this.destination = destination;
        TransportIndustry = new_TransportIndustry;
    }

    public void getInfoFromDriver(Destination destination){

        String type = TransportIndustry.getDestinationType(destination);
        if(type.equals("exotic"))
            System.out.println("Driver: This is one of the best destinations ever!");
        else{
            System.out.println("Driver: This is a good destination,although there are even more pretty epic ones!");
        }
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        if(vehicle != null){
            this.vehicle = vehicle;
        }
    }
}
