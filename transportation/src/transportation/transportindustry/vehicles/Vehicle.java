package transportation.transportindustry.vehicles;

import java.util.Objects;

public abstract class Vehicle  {

    private String name;
    private String model;
    protected int speed; // in horse power
    private int yearProduced;
    protected VehicleType VehicleType;
    private double hourlyRate;

    public Vehicle(String name, String model, int yearProduced, int speed) {
        if(name == null || name.equals("")){
            this.name = "Airport";
        }else {
            this.name = name;
        }
        if(model == null || model.equals("")){
            this.model = "default model";
        }else {
            this.model = model;
        }
        if(speed > 0) {
            this.speed = speed;
        }else{
            this.speed = 0;
        }
        if(yearProduced > 0) {
            this.yearProduced = yearProduced;
        }else{
            this.yearProduced = 0;
        }
        this.hourlyRate = getHourlyRate();
    }

    public Vehicle.VehicleType getVehicleType() {
        return VehicleType;
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
                VehicleType == vehicle.VehicleType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, model, yearProduced, VehicleType);
    }

    @Override
    public String toString() {
        return "Name of the vehicle: " + this.getName() +
                "\nModel of the vehicle: " + this.getModel() +
                "\nYear produced: " + this.getYearProduced() +
                "\nSpeed of the vehicle: " + this.getSpeed();
    }

    public abstract double getHourlyRate();

    public enum VehicleType
    {
        CAR, TRAIN, PLANE, SHIP, BUS;
    }
}
