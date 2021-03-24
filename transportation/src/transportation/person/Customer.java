package transportation.person;

import transportation.person.sellers.DestinationTicketsSeller;
import transportation.transportindustry.destinations.Destination;
import transportation.transportindustry.tickets.Ticket;
import transportation.transportindustry.TransportIndustry;
import transportation.transportindustry.vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class Customer extends Person {

    private boolean hasDiscountCode;
    private int discountCodePercentage;
    private List<Ticket> ticketsBought;
    private Driver driver;

    public Customer(String name, double money, Person.Gender gender, int age, boolean hasDiscountCode) {
        super(name, money, gender, age);
        if(hasDiscountCode){
            this.discountCodePercentage = 15;
        }
        this.hasDiscountCode = hasDiscountCode;
        this.ticketsBought = new ArrayList<>();
    }

    public void transport(TransportIndustry TransportIndustry){
        if(TransportIndustry == null) {
            System.out.println("There is no Transport industry!");
            return;
        }
        this.driver = TransportIndustry.assignDriver();
        if(this.driver == null){
            System.out.println("There is no driver!");
            return;
        }
        if(this.ticketsBought == null){
            System.out.println("You haven't got any tickets!");
            return;
        }
        Ticket customerTicket = this.ticketsBought.get(this.ticketsBought.size() - 1);
        Destination destination = null;
        if(customerTicket.getDestination() != null) {
            destination = customerTicket.getDestination();
        }else{
            System.out.println("This ticket has no destination!");
            return;
        }
        if(this.driver != null) {
            this.driver.getInfoFromDriver(destination);
        }else{
            System.out.println("You don't have a driver!");
            return;
        }
        TransportIndustry.reachDestination(this.driver,destination);
        TransportIndustry.addTheAvailableDriverAndVehicleAgain(this.driver);
    }

    public boolean hasTicket(){

        return this.ticketsBought != null;
    }

    public Vehicle.vehicleType getVehicle(String vehicleName){
        Vehicle.vehicleType v = null;
        try {
            v = Vehicle.vehicleType.valueOf(vehicleName);

        }catch (NullPointerException e){
            System.out.println("You have entered an inappropriate name for the vehicle type! Please enter again!");
        }
        return v;
    }

    public void pay(DestinationTicketsSeller destinationTicketsSeller, String vehicleName, String cityName, TransportIndustry TransportIndustry){

        Ticket ticket = null;
        if(destinationTicketsSeller != null){
            if(destinationTicketsSeller.checkTicketAvailable(cityName) == null){
                System.out.println("There are no available tickets to this city");
                return;
            }else{
                ticket = destinationTicketsSeller.checkTicketAvailable(cityName);
            }
        }else
        {
            System.out.println("There is no Sellers!");
            return;
        }

        Vehicle.vehicleType v = null;

        v = getVehicle(vehicleName);

        if(TransportIndustry != null){
            if(!TransportIndustry.checkIfVehicleAvailable(v)) {
                if (v != null) {
                    System.out.println("There is no available vehicle of type " + v + " at the moment! You can " +
                            "try with another vehicle!");
                } else {
                    System.out.println("There is no such vehicle!");
                    return;
                }
            }
        }else{
                System.out.println("There is no Transport industry!");
                return;
            }
        System.out.println("Money before payment " + this.money);

        if(this.money < ticket.getPrice()){
            System.out.println("Not enough money to buy this ticket!");
            return;
        }else{

                double ticketNewPrice = destinationTicketsSeller.useDiscount(hasDiscountCode, discountCodePercentage, ticket.getPrice());
                this.money -= ticketNewPrice;
                destinationTicketsSeller.money += ticketNewPrice;
                System.out.println("Ticket bought!");
                this.ticketsBought.add(ticket);     // add the ticket to customer ticket collection
                destinationTicketsSeller.removeTicket(ticket);
        }
        System.out.println("Money after payment " + this.money);
        System.out.println();
    }
}
