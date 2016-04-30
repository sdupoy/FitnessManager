package edu.iit.sat.itmd555.fp.fitnessmanager.test;

import org.junit.Test;

import edu.iit.sat.itmd555.fp.fitnessmanager.model.ActivityDistance;

import static org.junit.Assert.*;

/**
 * Created by Simon on 4/27/2016.
 */
public class ActivityDistanceTest {

    @Test
    public void testGetId() throws Exception {
        ActivityDistance activityDistance = new ActivityDistance();
        activityDistance.setId(12);
        assertEquals(12, activityDistance.getId());
    }

    @Test
    public void testGetIdActivity() throws Exception {
        ActivityDistance activityDistance = new ActivityDistance();
        activityDistance.setIdActivity(36);
        assertEquals(36, activityDistance.getIdActivity());
    }

    @Test
    public void testGetDistanceMiles() throws Exception {
        ActivityDistance activityDistance = new ActivityDistance();
        activityDistance.setDistanceMiles("3.51");
        assertEquals("3.51", activityDistance.getDistanceMiles());
    }

    @Test
    public void testGetDistanceKms() throws Exception {
        ActivityDistance activityDistance = new ActivityDistance();
        activityDistance.setDistanceKms("3.51");
        assertEquals("3.51", activityDistance.getDistanceKms());
    }

    @Test
    public void testToString() throws Exception {
        String adts = "ActivityDistance{" +
                "id=" + String.valueOf(12) +
                ", idActivity=" + String.valueOf(26) +
                ", distanceMiles='" + "3.51" + '\'' +
                ", distanceKms='" + "3.51" + '\'' +
                '}';
        ActivityDistance activityDistance = new ActivityDistance();
        activityDistance.setId(12);
        activityDistance.setIdActivity(26);
        activityDistance.setDistanceMiles("3.51");
        activityDistance.setDistanceKms("3.51");
        assertEquals(adts, activityDistance.toString());
    }
}