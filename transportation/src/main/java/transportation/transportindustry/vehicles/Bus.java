package transportation.transportindustry.vehicles;

import java.util.Random;

public class Bus extends Vehicle{

    private String registrationNumber;

    public Bus(String name, String model, int yearProduced,String registrationNumber) {
        super(name, model, yearProduced,new Random().nextInt(40) + 80);
        this.registrationNumber = registrationNumber;
        this.VehicleType = VehicleType.BUS;
        if(registrationNumber == null || registrationNumber.equals("")){
            this.registrationNumber = "default registration number";
        }else {
            this.registrationNumber = registrationNumber;
        }
    }

    @Override
    public double getHourlyRate() {
        return 25;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }
}
