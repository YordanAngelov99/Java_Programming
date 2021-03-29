package transportation.transportindustry.vehicles.trains;

import java.util.Random;

public class FastTrain extends Train {
    public FastTrain(String name, String model, int yearProduced) {
        super(name, model, yearProduced,new Random().nextInt(100) + 150);
    }

    @Override
    public distanceType getDistanceType() {
        return distanceType.LONG_DISTANCE;
    }
}
