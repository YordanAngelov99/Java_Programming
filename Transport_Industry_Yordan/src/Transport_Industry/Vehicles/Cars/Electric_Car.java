package Transport_Industry.Vehicles.Cars;

import java.util.Random;

public class Electric_Car extends Car{


    public Electric_Car(String name, String model, int yearProduced, String registrationNumber) {
        super(name, model, yearProduced, registrationNumber);
    }

    @Override
    public int getSpeed() {
        return new Random().nextInt(150) + 100; // horse power
    }
}
