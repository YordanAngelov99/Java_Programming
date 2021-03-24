package transportation.transportindustry.vehicles;

import java.util.Objects;

public abstract class Vehicle  {

    public enum vehicleType {CAR,TRAIN,PLANE,SHIP,BUS};
    private String name;
    private String model;
    protected int speed; // in horse power
    private int yearProduced;
    protected vehicleType vehicleType;
    private double hourlyRate;

    public Vehicle(String name, String model, int yearProduced, int speed) {
        this.name = name;
        this.model = model;
        this.speed = speed;
        this.yearProduced = yearProduced;
        this.hourlyRate = getHourlyRate();
    }

    public Vehicle.vehicleType getVehicleType() {
        return vehicleType;
    }

    public int getSpeed() {
        return speed;
    }

    public String getModel() {
        return model;
    }

    public int getYearProduced() {
        return yearProduced;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return yearProduced == vehicle.yearProduced &&
                name.equals(vehicle.name) &&
                model.equals(vehicle.model) &&
                vehicleType == vehicle.vehicleType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, model, yearProduced, vehicleType);
    }

    @Override
    public String toString() {
        return "Name of the vehicle: " + this.getName() +
                "\nModel of the vehicle: " + this.getModel() +
                "\nYear produced: " + this.getYearProduced() +
                "\nSpeed of the vehicle: " + this.getSpeed();
    }

    public abstract double getHourlyRate();
}
