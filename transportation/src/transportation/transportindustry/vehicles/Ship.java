package transportation.transportindustry.vehicles;

import java.util.Random;

public class Ship extends Vehicle{

    public Ship(String name, String model, int yearProduced) {
        super(name, model, yearProduced,new Random().nextInt(50) + 100);
        this.VehicleType = VehicleType.SHIP;
    }

    @Override
    public double getHourlyRate() {
        return 60;
    }
}
