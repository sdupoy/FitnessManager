package edu.iit.sat.itmd555.fp.fitnessmanager.test;

import org.junit.Test;

import edu.iit.sat.itmd555.fp.fitnessmanager.model.Step;

import static org.junit.Assert.*;

/**
 * Created by Simon on 4/27/2016.
 */
public class StepTest {

    @Test
    public void testGetId() throws Exception {
        Step stepTest = new Step();
        stepTest.setId(12);
        assertEquals(12, stepTest.getId());
    }

    @Test
    public void testGetIdUser() throws Exception {
        Step stepTest = new Step();
        stepTest.setIdUser(18);
        assertEquals(18, stepTest.getIdUser());
    }

    @Test
    public void testGetStepsDate() throws Exception {
        Step stepTest = new Step();
        stepTest.setStepsDate("2016-04-27");
        assertEquals("2016-04-27", stepTest.getStepsDate());
    }

    @Test
    public void testGetNbOfSteps() throws Exception {
        Step stepTest = new Step();
        stepTest.setNbOfSteps(1200);
        assertEquals(1200, stepTest.getNbOfSteps());
    }

    @Test
    public void testToString() throws Exception {
        Step stepTest = new Step();
        stepTest.setId(12);
        stepTest.setIdUser(18);
        stepTest.setStepsDate("2016-04-27");
        stepTest.setNbOfSteps(1200);
        String sts = "Step{" +
                "id=" + String.valueOf(12) +
                ", idUser=" + String.valueOf(18) +
                ", stepsDate='2016-04-27'" +
                ", nbOfSteps=" + String.valueOf(1200) +
                '}';
        assertEquals(sts, stepTest.toString());
    }
}