package Transport_Industry.Tickets;

import Person.Customer;
import Transport_Industry.Destinations.Destination;

import java.math.BigDecimal;

public abstract class  Ticket {
    private Destination destination;
    private BigDecimal price;
    private boolean isDiscountRateAvailable;

    public Ticket(Destination destination, BigDecimal price, boolean isDiscountRateAvailable) {
        this.destination = destination;
        this.price = price;
        this.isDiscountRateAvailable = getDiscountRateAvailability();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Destination getDestination() {
        return destination;
    }

    protected abstract boolean getDiscountRateAvailability();

}
