package transportation.transportindustry.tickets;

import transportation.transportindustry.destinations.Destination;

public abstract class  Ticket {
    private Destination destination;
    private double price;
    private boolean isDiscountRateAvailable;

    public Ticket(Destination destination, double price, boolean isDiscountRateAvailable) {
        this.destination = destination;
        if(price > 0) {
            this.price = price;
        }else{
            this.price = 0;
        }
        this.isDiscountRateAvailable = getDiscountRateAvailability();
    }

    public double getPrice() {
        return price;
    }

    public Destination getDestination() {
        return this.destination;
    }

    public boolean getIsDiscountRateAvailable() {
        return isDiscountRateAvailable;
    }

    protected abstract boolean getDiscountRateAvailability();

}
