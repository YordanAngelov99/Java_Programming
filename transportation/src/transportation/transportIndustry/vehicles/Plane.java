package transportation.transportIndustry.vehicles;

import java.util.Random;

public class Plane extends Vehicle{

    private int fansLength;

    public Plane(String name, String model, int yearProduced,int fansLength) {
        super(name, model, yearProduced,new Random().nextInt(2000) + 2000);
        this.fansLength = fansLength;
        this.vehicleType = vehicleType.PLANE;
    }
}
