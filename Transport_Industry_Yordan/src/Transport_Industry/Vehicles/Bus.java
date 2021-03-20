package Transport_Industry.Vehicles;

import java.util.Random;

public class Bus extends Vehicle{

    private String registrationNumber;

    public Bus(String name, String model, int yearProduced,String registrationNumber) {
        super(name, model, yearProduced);
        this.speed = new Random().nextInt(40) + 80;
        this.registrationNumber = registrationNumber;
        this.vehicleType = vehicleType.BUS;
    }
}
