package TransportIndustry.Vehicles;

import TransportIndustry.TransportIndustry;

public class Vehicle {

    public enum vehicleType {CAR,TRAIN,PLANE,SHIP,BUS};
    private String name;
    private String model;
    protected int speed; // in horse power
    private int yearProduced;
    protected vehicleType vehicleType;

    public String getName() {
        return name;
    }


    public Vehicle(String name, String model, int yearProduced) {
        this.name = name;
        this.model = model;
        this.speed = 0;
        this.yearProduced = yearProduced;
    }

    public Vehicle.vehicleType getVehicleType() {
        return vehicleType;
    }

    public int getSpeed() {
        return speed;
    }
}
