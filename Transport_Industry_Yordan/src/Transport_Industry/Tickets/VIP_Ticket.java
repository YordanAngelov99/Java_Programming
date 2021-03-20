package Transport_Industry.Tickets;

import Transport_Industry.Destinations.Destination;

import java.math.BigDecimal;

public class VIP_Ticket extends Ticket {
    public VIP_Ticket(Destination destination, BigDecimal price, boolean isDiscountRateAvailable) {
        super(destination, price, isDiscountRateAvailable);
    }

    @Override
    protected boolean getDiscountRateAvailability() {
        return false;
    }
}
