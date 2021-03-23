package transportation.transportIndustry.vehicles.trains;

import java.util.Random;

public class NormalTrain extends Train{
    public NormalTrain(String name, String model, int yearProduced) {
        super(name, model, yearProduced,new Random().nextInt(30) + 100);
    }

    @Override
    public distanceType getDistanceType() {
        return distanceType.SHORT_DISTANCE;
    }

}
