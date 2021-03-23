package transportation.transportIndustry.tickets;

import transportation.transportIndustry.destinations.Destination;

public class VIPTicket extends Ticket {
    public VIPTicket(Destination destination, double price, boolean isDiscountRateAvailable) {
        super(destination, price, isDiscountRateAvailable);
    }

    @Override
    protected boolean getDiscountRateAvailability() {
        return false;
    }
}
