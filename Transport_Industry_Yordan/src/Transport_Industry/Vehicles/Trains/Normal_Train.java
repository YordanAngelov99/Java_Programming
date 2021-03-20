package Transport_Industry.Vehicles.Trains;

import java.util.Random;

public class Normal_Train extends Train{
    public Normal_Train(String name, String model, int yearProduced) {
        super(name, model, yearProduced);
    }

    @Override
    protected distanceType getDistanceType() {
        return distanceType.SHORT_DISTANCE;
    }

    @Override
    public int getSpeed() {
        return new Random().nextInt(30) + 100;
    }
}
