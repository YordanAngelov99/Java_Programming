package transportation.person.sellers;


import transportation.transportindustry.destinations.Destination;
import transportation.transportindustry.destinations.ExoticDestination;
import transportation.transportindustry.destinations.NormalDestination;
import transportation.transportindustry.tickets.NormalTicket;
import transportation.transportindustry.tickets.Ticket;
import transportation.transportindustry.tickets.VIPTicket;
import transportation.transportindustry.TransportIndustry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DestinationTicketsSeller extends Seller {

    public DestinationTicketsSeller(String name, double money, Gender gender, int age, transportation.transportindustry.TransportIndustry new_TransportIndustry) {
        super(name, money, gender, age, new_TransportIndustry);
    }


    @Override
    protected List<Ticket> addTickets(TransportIndustry TransportIndustry) {
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


}
