package transportation.transportindustry.tickets;

public class ParkingTicket extends Ticket{


    public ParkingTicket() {
        super(null, 20, false);
    }

    @Override
    protected boolean getDiscountRateAvailability() {
        return false;
    }
}
