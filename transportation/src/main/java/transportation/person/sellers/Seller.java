package transportation.person.sellers;

import transportation.person.Person;
import transportation.transportindustry.TransportIndustry;
import transportation.transportindustry.tickets.Ticket;
import java.util.List;

public abstract class Seller extends Person{
    public static final double TICKET_PRICE = 100;
    public static final int TICKETS_NUMBER = 100;
    protected List<Ticket> tickets;
    private static transportation.transportindustry.TransportIndustry TransportIndustry;


    public Seller(String name, double money, Gender gender, int age,TransportIndustry new_TransportIndustry) {
        super(name, money, gender, age);
        this.TransportIndustry = new_TransportIndustry;
        this.tickets = addTickets(this.TransportIndustry);
    }

    public double useDiscount(boolean hasDiscountCode,int discountCodePercentage,double price){
        if(hasDiscountCode){
            double newPrice =( (double)(discountCodePercentage * 1.0 )/ 100 ) * price;
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


    protected abstract List<Ticket> addTickets(TransportIndustry transportIndustry);
}
