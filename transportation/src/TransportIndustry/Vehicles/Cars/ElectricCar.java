package TransportIndustry.Vehicles.Cars;

import java.util.Random;

public class ElectricCar extends Car{


    public ElectricCar(String name, String model, int yearProduced, String registrationNumber) {
        super(name, model, yearProduced, registrationNumber);
    }

    @Override
    public int getSpeed() {
        return new Random().nextInt(150) + 100; // horse power
    }
}
