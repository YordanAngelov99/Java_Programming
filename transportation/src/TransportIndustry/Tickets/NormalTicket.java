package TransportIndustry.Tickets;

import TransportIndustry.Destinations.Destination;

import java.math.BigDecimal;

public class NormalTicket extends Ticket{
    public NormalTicket(Destination destination, BigDecimal price, boolean isDiscountRateAvailable) {
        super(destination, price, isDiscountRateAvailable);
    }

    @Override
    protected boolean getDiscountRateAvailability() {
        return true;
    }
}
