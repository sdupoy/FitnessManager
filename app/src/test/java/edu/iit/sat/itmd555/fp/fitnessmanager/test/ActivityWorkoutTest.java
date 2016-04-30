package edu.iit.sat.itmd555.fp.fitnessmanager.test;

import org.junit.Test;

import edu.iit.sat.itmd555.fp.fitnessmanager.model.ActivityWorkout;

import static org.junit.Assert.*;

/**
 * Created by Simon on 4/27/2016.
 */
public class ActivityWorkoutTest {

    @Test
    public void testGetId() throws Exception {
        ActivityWorkout activityWorkout = new ActivityWorkout();
        activityWorkout.setId(12);
        assertEquals(12, activityWorkout.getId());
    }

    @Test
    public void testGetIdActivity() throws Exception {
        ActivityWorkout activityWorkout = new ActivityWorkout();
        activityWorkout.setIdActivity(19);
        assertEquals(19, activityWorkout.getIdActivity());
    }

    @Test
    public void testGetNbOfRep() throws Exception {
        ActivityWorkout activityWorkout = new ActivityWorkout();
        activityWorkout.setNbOfRep(25);
        assertEquals(25, activityWorkout.getNbOfRep());
    }

    @Test
    public void testGetTypeOfRep() throws Exception {
        ActivityWorkout activityWorkout = new ActivityWorkout();
        activityWorkout.setTypeOfRep("Squats");
        assertEquals("Squats", activityWorkout.getTypeOfRep());
    }

    @Test
    public void testToString() throws Exception {
        String awts = "ActivityWorkout{" +
        "id=" + String.valueOf(12) +
        ", idActivity=" + String.valueOf(19) +
        ", nbOfRep=" + String.valueOf(25) +
        ", typeOfRep='Squats'" +
        '}';
        ActivityWorkout activityWorkout = new ActivityWorkout();
        activityWorkout.setId(12);
        activityWorkout.setIdActivity(19);
        activityWorkout.setNbOfRep(25);
        activityWorkout.setTypeOfRep("Squats");
        assertEquals(awts, activityWorkout.toString());
    }
}