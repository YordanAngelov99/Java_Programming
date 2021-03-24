package transportation.transportindustry.tickets;

import java.time.LocalDateTime;

public class ParkingTicket extends Ticket{

    private LocalDateTime timeArrivedAtParking;
    private LocalDateTime timeLeftParking;

    public ParkingTicket() {
        super(null, 0, false);
    }

    public void setTimeArrivedAtParking(LocalDateTime timeArrivedAtParking) {
        this.timeArrivedAtParking = timeArrivedAtParking;
    }

    public void setTimeLeftParking(LocalDateTime timeLeftParking) {
        this.timeLeftParking = timeLeftParking;
    }

    public LocalDateTime getTimeArrivedAtParking() {
        return timeArrivedAtParking;
    }

    @Override
    protected boolean getDiscountRateAvailability() {
        return false;
    }
}
