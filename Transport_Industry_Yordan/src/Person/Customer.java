package Person;

import Transport_Industry.Destinations.Destination;
import Transport_Industry.Tickets.Ticket;
import Transport_Industry.Transport_Industry;
import Transport_Industry.Vehicles.Vehicle;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Customer extends Person {

    private boolean hasDiscountCode;
    private int discountCodePercentage;
    private ArrayList<Ticket> ticketsBought;
    private Driver driver;

    public Customer(String name, BigDecimal money, boolean isMale, int age, boolean hasDiscountCode) {
        super(name, money, isMale, age);
        if(hasDiscountCode){
            this.discountCodePercentage = 15;
        }
        this.hasDiscountCode = hasDiscountCode;
        this.ticketsBought = new ArrayList<>();
    }

    public void transport(Transport_Industry transport_industry){
        this.driver = transport_industry.assignDriver();
        Ticket customerTicket = this.ticketsBought.get(this.ticketsBought.size() - 1);;
        if(this.ticketsBought.size() <= 0) {
            System.out.println("You don't have a ticket!");
            return;
        }
        Destination destination = customerTicket.getDestination();
        this.driver.getInfoFromDriver(destination);
        transport_industry.reachDestination(this.driver,destination);
        transport_industry.addTheAvailableDriverAndVehicleAgain(this.driver);
    }

    public boolean hasTicket(){
        if(this.ticketsBought.size() > 0)
        {
            return true;
        }
        return  false;
    }

    public Vehicle.vehicleType getVehicle(String vehicleName){
        Vehicle.vehicleType v = null;
        switch (vehicleName){
            case "Bus": v = Vehicle.vehicleType.BUS;break;
            case "Car": v = Vehicle.vehicleType.CAR;break;
            case "Train": v = Vehicle.vehicleType.TRAIN;break;
            case "Ship": v = Vehicle.vehicleType.SHIP;break;
            case "Plane": v = Vehicle.vehicleType.PLANE;break;
        }
        return v;
    }

    public void pay(Seller seller, String vehicleName, String cityName, Transport_Industry transport_industry){

        Ticket ticket = null;
        if(seller.checkTicketAvailable(cityName) == null)
        {
            System.out.println("There are no available tickets to this city");
            return;
        }else{
            ticket = seller.checkTicketAvailable(cityName);
        }
        Vehicle.vehicleType v = null;

        v = getVehicle(vehicleName);

        if(!transport_industry.checkIfVehicleAvailable(v)){
            if(v != null) {
                System.out.println("There is no available vehicle of type " + v + " at the moment! You can " +
                        "try with another vehicle!");
            }else{
                System.out.println("There is no such vehicle!");
            }
            return;
        }

        if(this.money.compareTo(ticket.getPrice()) < 0){
            System.out.println("Not enough money to buy this ticket!");
            return;
        }else{
            BigDecimal ticketNewPrice = seller.useDiscount(hasDiscountCode,discountCodePercentage,ticket.getPrice());
            BigDecimal moneyAfterPayment = this.money.subtract(ticketNewPrice);
            seller.money = seller.money.add(this.money.subtract(moneyAfterPayment));
            this.money = moneyAfterPayment;
            System.out.println("Ticket bought!");
            this.ticketsBought.add(ticket);     // add the ticket to customer ticket collection
            seller.removeTicket(ticket);
        }

    }
}
