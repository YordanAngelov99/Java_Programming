package Person;

import Transport_Industry.Destinations.Destination;
import Transport_Industry.Transport_Industry;
import Transport_Industry.Vehicles.Vehicle;

import java.math.BigDecimal;

public class Driver extends Person{
    private Vehicle vehicle;
    private Destination destination;
    private static Transport_Industry transport_industry;

    public Driver(String name, BigDecimal money, boolean isMale, int age,Destination destination,Transport_Industry new_transport_industry) {
        super(name, money, isMale, age);
        this.destination = destination;
        transport_industry = new_transport_industry;
    }

    public void getInfoFromDriver(Destination destination){

        String type = transport_industry.getDestinationType(destination);
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
