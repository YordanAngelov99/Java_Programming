package transportation.transportindustry.destinations;

public abstract class Destination {
    private String name;
    private int rating; // rating from 1-10
    private boolean isVisitedOften;

    public Destination(String name,boolean isVisitedOften){
        if(name == null || name.equals("")) {
            this.name = "Miami";
        }else{
            this.name = name;
        }
        this.rating = getRating();
        this.isVisitedOften = isVisitedOften;
    }

    public abstract int getRating();

    public String getName() {
        return name;
    }

    public boolean getIsVisitedOften() {
        return isVisitedOften;
    }
}
