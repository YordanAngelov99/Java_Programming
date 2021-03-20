package Transport_Industry.Vehicles.Trains;

import java.util.Random;

public class Fast_Train extends Train {
    public Fast_Train(String name, String model, int yearProduced) {
        super(name, model, yearProduced);
    }

    @Override
    protected distanceType getDistanceType() {
        return distanceType.LONG_DISTANCE;
    }

    @Override
    public int getSpeed() {
        return new Random().nextInt(100) + 150;
    }
}
