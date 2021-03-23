package TransportIndustry.Vehicles.Cars;

import TransportIndustry.Vehicles.Vehicle;

public abstract  class Car extends Vehicle {

    private String registrationNumber;

    public Car(String name, String model, int yearProduced, String registrationNumber) {
        super(name, model, yearProduced);
        this.speed = getSpeed();
        this.registrationNumber = registrationNumber;
        this.vehicleType = vehicleType.CAR;
    }

    public abstract int getSpeed();
}
