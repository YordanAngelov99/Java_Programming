package TransportIndustry.Tickets;

import TransportIndustry.Destinations.Destination;

import java.math.BigDecimal;

public class VIPTicket extends Ticket {
    public VIPTicket(Destination destination, BigDecimal price, boolean isDiscountRateAvailable) {
        super(destination, price, isDiscountRateAvailable);
    }

    @Override
    protected boolean getDiscountRateAvailability() {
        return false;
    }
}
