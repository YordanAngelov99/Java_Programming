package TransportIndustry.Vehicles.Trains;

import java.util.Random;

public class FastTrain extends Train {
    public FastTrain(String name, String model, int yearProduced) {
        super(name, model, yearProduced);
    }

    @Override
    public int getSpeed() {
        return new Random().nextInt(100) + 150;
    }

    @Override
    protected distanceType getDistanceType() {
        return distanceType.LONG_DISTANCE;
    }
}
