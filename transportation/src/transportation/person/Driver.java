package transportation.person;

import transportation.parkingindustry.Airport;
import transportation.person.sellers.AirportTicketSeller;
import transportation.transportindustry.destinations.Destination;
import transportation.transportindustry.TransportIndustry;
import transportation.transportindustry.tickets.ParkingTicket;
import transportation.transportindustry.tickets.Ticket;
import transportation.transportindustry.vehicles.Vehicle;

public class Driver extends Person{
    public static final int HOURS_WORKED = 20;
    private Vehicle vehicle;
    private Destination destination;
    private static TransportIndustry TransportIndustry;
    private ParkingTicket parkingTicket;
    private int hoursParked;

    public Driver(String name, double money, Person.Gender gender, int age,Destination destination,TransportIndustry new_TransportIndustry) {
        super(name, money, gender, age);
        this.destination = destination;
        this.hoursParked = 0;
        TransportIndustry = new_TransportIndustry;
    }

    public void getInfoFromDriver(Destination destination){

        String type = TransportIndustry.getDestinationType(destination);
        if(type.equals("exotic"))
            System.out.println("Driver: This is one of the best destinations ever!");
        else{
            System.out.println("Driver: This is a good destination,although there are even more pretty epic ones!");
        }
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        if(vehicle != null){
            this.vehicle = vehicle;
        }
    }

    public void setHoursParked(int hoursParked) {
        this.hoursParked = hoursParked;
    }

    public int getHoursParked() {
        return hoursParked;
    }

    public void park(Airport airport,AirportTicketSeller airportTicketSeller){
        if(airport == null){
            System.out.println("There is no airport!!");
            return;
        }
        if(airportTicketSeller == null){
            System.out.println("Please specify a ticket Seller! You haven't chosen!");
            return;
        }
        Vehicle vehicle = this.getVehicle();
        if(vehicle == null){
            System.out.println("You don't have a vehicle! Buy one !");
            return;
        }
        if(!airport.checkIfParkSlotsAvailable(vehicle)){
            System.out.println("Sorry you either don't have a vehicle or");
            System.out.println(" there are no more parking slots for type " + vehicle.getVehicleType() + " at the moment! Come Later!");
            return;
        }
        System.out.println("You parked your " + vehicle.getName());
        Ticket ticket = airportTicketSeller.getRandomTicket();
        if(ticket == null){
            System.out.println("There are no more tickets left!");
            return;
        }
        this.parkingTicket = (ParkingTicket) ticket;
        airport.addVehicleToParking(vehicle);
    }

    public void driverWorkingWhileVehicleParked(){
        for(int i = 0 ;i < (Math.random() * HOURS_WORKED);i++){
            this.hoursParked++;
        }
        if(this.vehicle == null){
            System.out.println("You don't have a vehicle!");
            return;
        }
        System.out.println("Your " + this.getVehicle().getVehicleType() + " has been parked for " + this.hoursParked + " hours!");
    }

    public void leave(Airport airport,AirportTicketSeller airportTicketSeller){

        if(airport == null){
            System.out.println("There is no airport!!");
            return;
        }
        if(airportTicketSeller == null){
            System.out.println("Please specify a ticket Seller! You haven't chosen!");
            return;
        }
        Vehicle vehicle = this.getVehicle();
        if(vehicle == null){
            System.out.println("You don't have a vehicle! Buy one !");
            return;
        }
        double fee = vehicle.getHourlyRate() * this.getHoursParked();
        System.out.println();
        System.out.println("--------- Fee details ---------");
        System.out.println("You have parked for " + this.hoursParked + " hours ! The fee per hour is " + vehicle.getHourlyRate());
        System.out.println("So the total fee you need to pay is " + fee + "!");
        System.out.println("-------------------------------" );
        System.out.println();
        this.setMoney(this.getMoney() - fee);
        airportTicketSeller.setMoney(airportTicketSeller.getMoney() + fee);
        airport.removeVehicleFromParking(vehicle);
    }

    public ParkingTicket getParkingTicket() {
        return parkingTicket;
    }
}
