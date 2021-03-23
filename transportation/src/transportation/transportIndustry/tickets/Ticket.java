package transportation.transportIndustry.tickets;

import transportation.transportIndustry.destinations.Destination;

public abstract class  Ticket {
    private Destination destination;
    private double price;
    private boolean isDiscountRateAvailable;

    public Ticket(Destination destination, double price, boolean isDiscountRateAvailable) {
        this.destination = destination;
        this.price = price;
        this.isDiscountRateAvailable = getDiscountRateAvailability();
    }

    public double getPrice() {
        return price;
    }

    public Destination getDestination() {
        return this.destination;
    }

    protected abstract boolean getDiscountRateAvailability();

}
