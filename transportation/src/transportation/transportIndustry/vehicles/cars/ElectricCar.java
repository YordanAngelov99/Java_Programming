package transportation.transportIndustry.vehicles.cars;

import java.util.Random;

public class ElectricCar extends Car{


    public ElectricCar(String name, String model, int yearProduced, String registrationNumber) {
        super(name, model, yearProduced, registrationNumber,new Random().nextInt(150) + 100);
    }

}
