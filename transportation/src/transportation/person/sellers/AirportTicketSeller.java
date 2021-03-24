package transportation.person.sellers;
import transportation.parkingindustry.Airport;
import transportation.transportindustry.tickets.ParkingTicket;
import transportation.transportindustry.tickets.Ticket;
import java.util.ArrayList;
import java.util.List;

public class AirportTicketSeller extends Seller {

    private Airport airport;

    public AirportTicketSeller(String name, double money, Gender gender, int age, transportation.transportindustry.TransportIndustry new_TransportIndustry, Airport airport) {
        super(name, money, gender, age, new_TransportIndustry);
        this.airport = airport;
    }

    @Override
    protected List<Ticket> addTickets(transportation.transportindustry.TransportIndustry TransportIndustry) {
        List<Ticket> newTickets = new ArrayList<>();
        for(int i = 0; i < TICKETS_NUMBER;i++){
            Ticket ticket = new ParkingTicket();
            newTickets.add(ticket);
        }
        return newTickets;
    }

    public Ticket getRandomTicket(){
        return this.tickets.get((int)(Math.random() * this.tickets.size()));
    }
}
