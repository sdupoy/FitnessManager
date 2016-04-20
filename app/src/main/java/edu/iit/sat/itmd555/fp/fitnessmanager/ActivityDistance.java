package edu.iit.sat.itmd555.fp.fitnessmanager;

/**
 * Created by Simon on 4/19/2016.
 */
public class ActivityDistance {
    private int id;
    private int idActivity;
    private String distanceMiles;
    private String distanceKms;

    public ActivityDistance() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDistanceMiles() {
        return distanceMiles;
    }

    public void setDistanceMiles(String distanceMiles) {
        this.distanceMiles = distanceMiles;
    }

    public String getDistanceKms() {
        return distanceKms;
    }

    public void setDistanceKms(String distanceKms) {
        this.distanceKms = distanceKms;
    }

    @Override
    public String toString() {
        return "ActivityDistance{" +
                "id=" + id +
                ", distanceMiles='" + distanceMiles + '\'' +
                ", distanceKms='" + distanceKms + '\'' +
                '}';
    }
}
