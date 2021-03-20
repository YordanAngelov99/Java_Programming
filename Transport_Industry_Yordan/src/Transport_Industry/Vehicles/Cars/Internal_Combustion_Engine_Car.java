package Transport_Industry.Vehicles.Cars;

import java.util.Random;

public class Internal_Combustion_Engine_Car extends Car{


    public Internal_Combustion_Engine_Car(String name, String model, int yearProduced, String registrationNumber) {
        super(name, model, yearProduced, registrationNumber);
    }

    @Override
    public int getSpeed() {
        return new Random().nextInt(100) + 70;
    }
}
