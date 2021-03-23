package TransportIndustry.Vehicles.Trains;

import java.util.Random;

public class NormalTrain extends Train{
    public NormalTrain(String name, String model, int yearProduced) {
        super(name, model, yearProduced);
    }

    @Override
    public int getSpeed() {
        return new Random().nextInt(30) + 100;
    }

    @Override
    protected distanceType getDistanceType() {
        return distanceType.SHORT_DISTANCE;
    }

}
