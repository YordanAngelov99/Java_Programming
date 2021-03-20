package Transport_Industry.Vehicles;

import java.util.Random;

public class Ship extends Vehicle{

    public Ship(String name, String model, int yearProduced) {
        super(name, model, yearProduced);
        this.speed = new Random().nextInt(50) + 100;
        this.vehicleType = vehicleType.SHIP;
    }

}
