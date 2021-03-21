package TransportIndustry.Vehicles.Trains;

import TransportIndustry.Vehicles.Vehicle;

public abstract class Train extends Vehicle {
    protected enum distanceType {SHORT_DISTANCE, LONG_DISTANCE};
    private distanceType type;


    public Train(String name, String model, int yearProduced) {
        super(name, model, yearProduced);
        this.type = getDistanceType();
        this.speed = getSpeed();
        this.vehicleType = vehicleType.TRAIN;
    }

    protected abstract distanceType getDistanceType();
    public abstract int getSpeed();
}
