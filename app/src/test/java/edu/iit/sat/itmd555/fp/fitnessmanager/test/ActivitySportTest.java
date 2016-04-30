package edu.iit.sat.itmd555.fp.fitnessmanager.test;

import org.junit.Test;

import edu.iit.sat.itmd555.fp.fitnessmanager.model.ActivitySport;

import static org.junit.Assert.*;

/**
 * Created by Simon on 4/27/2016.
 */
public class ActivitySportTest {

    @Test
    public void testGetId() throws Exception {
        ActivitySport activitySport = new ActivitySport();
        activitySport.setId(12);
        assertEquals(12, activitySport.getId());
    }

    @Test
    public void testGetIdUser() throws Exception {
        ActivitySport activitySport = new ActivitySport();
        activitySport.setIdUser(12);
        assertEquals(12, activitySport.getIdUser());
    }

    @Test
    public void testGetType() throws Exception {
        ActivitySport activitySport = new ActivitySport();
        activitySport.setType("Distance");
        assertEquals("Distance", activitySport.getType());
        activitySport.setType("Workout");
        assertEquals("Workout", activitySport.getType());
    }

    @Test
    public void testGetDurationHours() throws Exception {
        ActivitySport activitySport = new ActivitySport();
        activitySport.setDurationHours(1);
        assertEquals(1, activitySport.getDurationHours());
    }

    @Test
    public void testGetDurationMinutes() throws Exception {
        ActivitySport activitySport = new ActivitySport();
        activitySport.setDurationMinutes(55);
        assertEquals(55, activitySport.getDurationMinutes());
    }

    @Test
    public void testGetDurationSeconds() throws Exception {
        ActivitySport activitySport = new ActivitySport();
        activitySport.setDurationSeconds(55);
        assertEquals(55, activitySport.getDurationSeconds());
    }

    @Test
    public void testGetDate() throws Exception {
        ActivitySport activitySport = new ActivitySport();
        activitySport.setDate("2016-04-18");
        assertEquals("2016-04-18", activitySport.getDate());
    }

    @Test
    public void testGetFeedback() throws Exception {
        ActivitySport activitySport = new ActivitySport();
        activitySport.setFeedback("3.4");
        assertEquals("3.4", activitySport.getFeedback());
    }

    @Test
    public void testGetJustCreated() throws Exception {
        ActivitySport activitySport = new ActivitySport();
        activitySport.setJustCreated(1);
        assertEquals(1, activitySport.getJustCreated());
        activitySport.setJustCreated(0);
        assertEquals(0, activitySport.getJustCreated());
    }

    @Test
    public void testGetTitle() throws Exception {
        ActivitySport activitySport = new ActivitySport();
        activitySport.setTitle("Running");
        assertEquals("Running", activitySport.getTitle());
    }

    @Test
    public void testToString() throws Exception {
        String asts = "ActivitySport{" +
                "id=" + String.valueOf(12) +
                ", idUser=" + String.valueOf(19) +
                ", type='Distance'" +
                ", durationHours=" + String.valueOf(1) +
                ", durationMinutes=" + String.valueOf(55) +
                ", durationSeconds=" + String.valueOf(50) +
                ", date='2016-04-19'" +
                ", feedback='" + "3.4" + '\'' +
                ", justCreated=" + String.valueOf(1)+
                ", title='Running'" +
                '}';
        ActivitySport activitySport = new ActivitySport();
        activitySport.setId(12);
        activitySport.setIdUser(19);
        activitySport.setType("Distance");
        activitySport.setDurationSeconds(50);
        activitySport.setDurationMinutes(55);
        activitySport.setDurationHours(1);
        activitySport.setDate("2016-04-19");
        activitySport.setFeedback("3.4");
        activitySport.setJustCreated(1);
        activitySport.setTitle("Running");
        assertEquals(asts, activitySport.toString());
    }
}