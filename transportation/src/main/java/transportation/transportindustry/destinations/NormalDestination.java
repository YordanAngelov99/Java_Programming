package transportation.transportindustry.destinations;

import java.util.Random;

public class NormalDestination extends Destination{
    public NormalDestination(String name, boolean isVisitedOften) {
        super(name, isVisitedOften);
    }

    @Override
    public int getRating() {
        int rating = new Random().nextInt(4) + 3;
        return rating;
    }
}
