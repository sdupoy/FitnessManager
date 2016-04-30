package edu.iit.sat.itmd555.fp.fitnessmanager.test;

import org.junit.Test;

import edu.iit.sat.itmd555.fp.fitnessmanager.model.Goal;

import static org.junit.Assert.*;

/**
 * Created by Simon on 4/27/2016.
 */
public class GoalTest {

    @Test
    public void testGetId() throws Exception {
        Goal goalTest = new Goal();
        goalTest.setId(12);
        assertEquals(12, goalTest.getId());
    }

    @Test
    public void testGetIdUser() throws Exception {
        Goal goalTest = new Goal();
        goalTest.setIdUser(18);
        assertEquals(18, goalTest.getIdUser());
    }

    @Test
    public void testGetTargetName() throws Exception {
        Goal goalTest = new Goal();
        goalTest.setTargetName("Steps");
        assertEquals("Steps", goalTest.getTargetName());
    }

    @Test
    public void testGetTargetNumber() throws Exception {
        Goal goalTest = new Goal();
        goalTest.setTargetNumber(10000);
        assertEquals(10000, goalTest.getTargetNumber());
    }

    @Test
    public void testToString() throws Exception {
        String gts = "Goal{" +
                "id=" + String.valueOf(12)+
                ", idUser=" + String.valueOf(18) +
                ", targetName='Steps'" +
                ", targetNumber=" + String.valueOf(5000) +
                '}';
        Goal goalTest = new Goal();
        goalTest.setId(12);
        goalTest.setIdUser(18);
        goalTest.setTargetName("Steps");
        goalTest.setTargetNumber(5000);
        assertEquals(gts, goalTest.toString());
    }
}