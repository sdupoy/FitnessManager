package edu.iit.sat.itmd555.fp.fitnessmanager;

/**
 * Created by Simon on 4/18/2016.
 */
public class ActivityDistance {
    String id;
    String distance;

    public ActivityDistance() {
    }

    public ActivityDistance(String id, String distance) {
        this.id = id;
        this.distance = distance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "ActivityDistance{" +
                "id='" + id + '\'' +
                ", distance='" + distance + '\'' +
                '}';
    }
}
