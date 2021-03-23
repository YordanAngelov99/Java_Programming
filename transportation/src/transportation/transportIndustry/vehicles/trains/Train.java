package transportation.transportIndustry.vehicles.trains;

import transportation.transportIndustry.vehicles.Vehicle;

public abstract class Train extends Vehicle {
    public enum distanceType {SHORT_DISTANCE, LONG_DISTANCE};
    private distanceType type;


    public Train(String name, String model, int yearProduced,int speed) {
        super(name, model, yearProduced,speed);
        this.type = getDistanceType();
        this.vehicleType = vehicleType.TRAIN;
    }

    public abstract distanceType getDistanceType();

    @Override
    public String toString() {
        return "Name of the vehicle: " + this.getName() +
                "\nModel of the vehicle: " + this.getModel() +
                "\nYear produced: " + this.getYearProduced() +
                "\nSpeed of the vehicle: " + this.getSpeed() +
                "\nDistance type of the vehicle: " + this.getDistanceType();
    }
}
