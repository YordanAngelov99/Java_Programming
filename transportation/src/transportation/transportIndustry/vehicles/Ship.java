package transportation.transportIndustry.vehicles;

import java.util.Random;

public class Ship extends Vehicle{

    public Ship(String name, String model, int yearProduced) {
        super(name, model, yearProduced,new Random().nextInt(50) + 100);
        this.vehicleType = vehicleType.SHIP;
    }

}
