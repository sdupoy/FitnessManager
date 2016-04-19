package edu.iit.sat.itmd555.fp.fitnessmanager;

import java.util.ArrayList;

/**
 * Created by Simon on 4/18/2016.
 */
public class ActivityWorkout {
    String id;
    ArrayList<Workout> workouts;

    public ActivityWorkout() {
    }

    public ActivityWorkout(String id, ArrayList<Workout> workouts) {
        this.id = id;
        this.workouts = workouts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Workout> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(ArrayList<Workout> workouts) {
        this.workouts = workouts;
    }

    @Override
    public String toString() {
        return "ActivityWorkout{" +
                "id='" + id + '\'' +
                ", workouts=" + workouts +
                '}';
    }
}
