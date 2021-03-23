package Person;

import TransportIndustry.Destinations.Destination;
import TransportIndustry.Tickets.Ticket;
import TransportIndustry.TransportIndustry;
import TransportIndustry.Vehicles.Vehicle;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Customer extends Person {

    public static final String CASE_FOR_BUS = "Bus";
    public static final String CASE_FOR_CAR = "Car";
    public static final String CASE_FOR_TRAIN = "Train";
    public static final String CASE_FOR_SHIP = "Ship";
    public static final String CASE_FOR_PLANE = "Plane";
    private boolean hasDiscountCode;
    private int discountCodePercentage;
    private List<Ticket> ticketsBought;
    private Driver driver;

    public Customer(String name, BigDecimal money, Person.Gender gender, int age, boolean hasDiscountCode) {
        super(name, money, gender, age);
        if(hasDiscountCode){
            this.discountCodePercentage = 15;
        }
        this.hasDiscountCode = hasDiscountCode;
        this.ticketsBought = new ArrayList<>();
    }

    public void transport(TransportIndustry TransportIndustry){
        this.driver = TransportIndustry.assignDriver();
        Ticket customerTicket = this.ticketsBought.get(this.ticketsBought.size() - 1);;
        if(this.ticketsBought.size() <= 0) {
            System.out.println("You don't have a ticket!");
            return;
        }
        Destination destination = customerTicket.getDestination();
        this.driver.getInfoFromDriver(destination);
        TransportIndustry.reachDestination(this.driver,destination);
        TransportIndustry.addTheAvailableDriverAndVehicleAgain(this.driver);
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
            case CASE_FOR_BUS: v = Vehicle.vehicleType.BUS;break;
            case CASE_FOR_CAR: v = Vehicle.vehicleType.CAR;break;
            case CASE_FOR_TRAIN: v = Vehicle.vehicleType.TRAIN;break;
            case CASE_FOR_SHIP: v = Vehicle.vehicleType.SHIP;break;
            case CASE_FOR_PLANE: v = Vehicle.vehicleType.PLANE;break;
        }
        return v;
    }

    public void pay(Seller seller, String vehicleName, String cityName, TransportIndustry TransportIndustry){

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

        if(!TransportIndustry.checkIfVehicleAvailable(v)){
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
