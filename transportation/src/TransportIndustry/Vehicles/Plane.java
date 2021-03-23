package TransportIndustry.Vehicles;

import java.util.Random;

public class Plane extends Vehicle{

    private int fansLength;

    public Plane(String name, String model, int yearProduced,int fansLength) {
        super(name, model, yearProduced);
        this.speed = new Random().nextInt(2000) + 2000;
        this.fansLength = fansLength;
        this.vehicleType = vehicleType.PLANE;
    }
}
