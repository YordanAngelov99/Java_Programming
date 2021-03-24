package transportation.transportindustry.destinations;

import java.util.Random;

public class ExoticDestination extends Destination{
    public ExoticDestination(String name, boolean isVisitedOften) {
        super(name, isVisitedOften);
    }

    @Override
    public int getRating() {
        return new Random().nextInt(4) + 7;
    }
}
