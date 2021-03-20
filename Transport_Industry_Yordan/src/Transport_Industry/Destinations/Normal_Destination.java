package Transport_Industry.Destinations;

import java.util.Random;

public class Normal_Destination extends Destination{
    public Normal_Destination(String name, boolean isVisitedOften) {
        super(name, isVisitedOften);
    }

    @Override
    public int getRating() {
        int rating = new Random().nextInt(4) + 3;
        return rating;
    }
}
