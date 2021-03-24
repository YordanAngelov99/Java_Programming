package transportation.transportindustry.vehicles.cars;

import transportation.transportindustry.vehicles.Vehicle;

public abstract  class Car extends Vehicle {

    private String registrationNumber;

    public Car(String name, String model, int yearProduced, String registrationNumber,int speed) {
        super(name, model, yearProduced,speed);
        this.speed = getSpeed();
        this.registrationNumber = registrationNumber;
        this.VehicleType = VehicleType.CAR;
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

    @Override
    public double getHourlyRate() {
        return 35;
    }
}
