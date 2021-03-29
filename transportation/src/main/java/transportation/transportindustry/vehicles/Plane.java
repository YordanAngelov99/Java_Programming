package transportation.transportindustry.vehicles;

import java.util.Random;

public class Plane extends Vehicle{

    private int fansLength;

    public Plane(String name, String model, int yearProduced,int fansLength) {
        super(name, model, yearProduced,new Random().nextInt(2000) + 2000);
        if(fansLength > 0) {
            this.fansLength = fansLength;
        }else{
            this.fansLength = 0;
        }
        this.VehicleType = VehicleType.PLANE;
    }

    @Override
    public double getHourlyRate() {
        return 50;
    }

    public int getFansLength() {
        return fansLength;
    }
}
