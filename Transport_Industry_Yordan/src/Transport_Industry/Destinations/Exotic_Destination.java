package Transport_Industry.Destinations;

import java.util.Random;

public class Exotic_Destination extends Destination{
    public Exotic_Destination(String name, boolean isVisitedOften) {
        super(name, isVisitedOften);
    }

    @Override
    public int getRating() {
        return new Random().nextInt(4) + 7;
    }
}
