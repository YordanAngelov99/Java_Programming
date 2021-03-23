package transportation.person;

import transportation.transportIndustry.destinations.Destination;
import transportation.transportIndustry.destinations.ExoticDestination;
import transportation.transportIndustry.destinations.NormalDestination;
import transportation.transportIndustry.tickets.NormalTicket;
import transportation.transportIndustry.tickets.Ticket;
import transportation.transportIndustry.tickets.VIPTicket;
import transportation.transportIndustry.TransportIndustry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Seller extends Person{

    public static final double TICKET_PRICE = 100;
    public static final int TICKETS_NUMBER = 100;
    protected List<Ticket> tickets;
    private static TransportIndustry TransportIndustry;

    public Seller(String name, double money, Person.Gender gender, int age,TransportIndustry new_TransportIndustry) {
        super(name, money, gender, age);
        TransportIndustry = new_TransportIndustry;
        this.tickets = addTickets();
    }

    public Ticket checkTicketAvailable(String cityName){
        for(Ticket t : this.tickets){
            if(t != null && t.getDestination() != null) {
                if (t.getDestination().getName().equals(cityName)) {
                    return t;
                }
            }
        }
        return null;
    }

    public Destination getRandomDestination(){
        if(this.tickets == null){
            System.out.println("There are no tickets! Please buy the tickets first!");
            return null;
        }
        Ticket randomTicket = this.tickets.get(new Random().nextInt(this.tickets.size()));
        return randomTicket.getDestination();
    }

    public double useDiscount(boolean hasDiscountCode,int discountCodePercentage,double price){
        if(hasDiscountCode){
            double newPrice =( (double)(15 * 1.0 )/ 100 ) * price;
            price -= newPrice;
        }else{
            System.out.println("You don't have a discount code!");
        }

        return price;
    }

    public void removeTicket(Ticket ticket){
        if(this.tickets != null) {
            this.tickets.remove(ticket);
        }
    }

    private List<Ticket> addTickets(){
        String[] normalDestinationNames = {"Miami","Sofia","Plovdiv","London"};
        String[] exoticDestinationNames = {"Moscow","Kairo","Boston"};
        TransportIndustry.addExoticDestinationNames(exoticDestinationNames);
        TransportIndustry.addNormalDestinationNames(normalDestinationNames);
        List<Ticket> newTickets = new ArrayList<>();
        for(int i = 0; i < TICKETS_NUMBER;i++){
            Ticket ticket = null;
            if(new Random().nextBoolean()){
                ticket = new NormalTicket(new NormalDestination(normalDestinationNames[new Random().nextInt(normalDestinationNames.length)],
                        true),TICKET_PRICE,true);
            }else{
                ticket = new VIPTicket(new ExoticDestination(exoticDestinationNames[new Random().nextInt(exoticDestinationNames.length )],
                        true),TICKET_PRICE,false);
            }
            newTickets.add(ticket);
        }
        return newTickets;

    }
}
