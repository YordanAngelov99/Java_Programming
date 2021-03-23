package transportation.transportIndustry.vehicles.cars;

import transportation.transportIndustry.vehicles.Vehicle;

public abstract  class Car extends Vehicle {

    private String registrationNumber;

    public Car(String name, String model, int yearProduced, String registrationNumber,int speed) {
        super(name, model, yearProduced,speed);
        this.speed = getSpeed();
        this.registrationNumber = registrationNumber;
        this.vehicleType = vehicleType.CAR;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    @Override
    public String toString() {
        return "Name of the vehicle: " + this.getName() +
                "\nModel of the vehicle: " + this.getModel() +
                "\nYear produced: " + this.getYearProduced() +
                "\nSpeed of the vehicle: " + this.getSpeed() +
                "\nRegistration number of the vehicle: " + this.registrationNumber;
    }
}
