package transportation.transportindustry.tickets;

import transportation.transportindustry.destinations.Destination;

public class NormalTicket extends Ticket{
    public NormalTicket(Destination destination, double price, boolean isDiscountRateAvailable) {
        super(destination, price, isDiscountRateAvailable);
    }

    @Override
    protected boolean getDiscountRateAvailability() {
        return true;
    }
}
