package transportation.transportIndustry.vehicles.cars;

import java.util.Random;

public class InternalCombustionEngineCar extends Car{


    public InternalCombustionEngineCar(String name, String model, int yearProduced, String registrationNumber) {
        super(name, model, yearProduced, registrationNumber,new Random().nextInt(100) + 70);
    }

}
