package Person;

import Transport_Industry.Destinations.Destination;
import Transport_Industry.Destinations.Exotic_Destination;
import Transport_Industry.Destinations.Normal_Destination;
import Transport_Industry.Tickets.Normal_Ticket;
import Transport_Industry.Tickets.Ticket;
import Transport_Industry.Tickets.VIP_Ticket;
import Transport_Industry.Transport_Industry;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

public class Seller extends Person{

    public static final BigDecimal TICKET_PRICE = new BigDecimal("100");
    public static final int TICKETS_NUMBER = 100;
    protected ArrayList<Ticket> tickets;
    private static Transport_Industry transport_industry;

    public Seller(String name, BigDecimal money, boolean isMale, int age,Transport_Industry new_transport_industry) {
        super(name, money, isMale, age);
        transport_industry = new_transport_industry;
        this.tickets = addTickets();
    }

    public Ticket checkTicketAvailable(String cityName){
        for(Ticket t : this.tickets){
            if(t.getDestination().getName().equals(cityName))
            {
                return t;
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

    public BigDecimal useDiscount(boolean hasDiscountCode,int discountCodePercentage,BigDecimal price){
        if(hasDiscountCode){
            double newPrice =( (double)(15 * 1.0 )/ 100 ) * price.doubleValue();
            price = new BigDecimal(price.doubleValue() - newPrice);
        }else{
            System.out.println("You don't have a discount code!");
        }

        return price;
    }

    public void removeTicket(Ticket ticket){
        this.tickets.remove(ticket);
    }

    private ArrayList<Ticket> addTickets(){
        String[] normalDestinationNames = {"Miami","Sofia","Plovdiv","London"};
        String[] exoticDestinationNames = {"Moscow","Kairo","Boston"};
        transport_industry.addExoticDestinationNames(exoticDestinationNames);
        transport_industry.addNormalDestinationNames(normalDestinationNames);
        ArrayList<Ticket> newTickets = new ArrayList<>();
        for(int i = 0; i < TICKETS_NUMBER;i++){
            Ticket ticket = null;
            if(new Random().nextBoolean()){
                ticket = new Normal_Ticket(new Normal_Destination(normalDestinationNames[new Random().nextInt(normalDestinationNames.length)],
                        true),TICKET_PRICE,true);
            }else{
                ticket = new VIP_Ticket(new Exotic_Destination(exoticDestinationNames[new Random().nextInt(exoticDestinationNames.length )],
                        true),TICKET_PRICE,false);
            }
            newTickets.add(ticket);
        }
        return newTickets;

    }
}
