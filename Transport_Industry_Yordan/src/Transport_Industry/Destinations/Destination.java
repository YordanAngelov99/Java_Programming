package Transport_Industry.Destinations;

public abstract class Destination {
    private String name;
    private int rating; // rating from 1-10
    private boolean isVisitedOften;

    public Destination(String name,boolean isVisitedOften){
        this.name = name;
        this.rating = getRating();
        this.isVisitedOften = isVisitedOften;
    }

    public abstract int getRating();

    public String getName() {
        return name;
    }


}
