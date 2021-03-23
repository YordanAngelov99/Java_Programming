package TransportIndustry.Vehicles.Cars;

import java.util.Random;

public class InternalCombustionEngineCar extends Car{


    public InternalCombustionEngineCar(String name, String model, int yearProduced, String registrationNumber) {
        super(name, model, yearProduced, registrationNumber);
    }

    @Override
    public int getSpeed() {
        return new Random().nextInt(100) + 70;
    }
}
