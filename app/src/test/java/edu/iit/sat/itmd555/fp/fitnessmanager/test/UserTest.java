package edu.iit.sat.itmd555.fp.fitnessmanager.test;

import org.junit.Test;

import edu.iit.sat.itmd555.fp.fitnessmanager.model.User;

import static org.junit.Assert.*;

/**
 * Created by Simon on 4/27/2016.
 */
public class UserTest {


    @Test
    public void testGetId() throws Exception {
        User usrTest = new User();
        usrTest.setId(12);
        assertEquals(12, usrTest.getId());
    }


    @Test
    public void testGetPassword() throws Exception {
        User usrTest = new User();
        usrTest.setPassword("ThisIsApsw");
        assertEquals("ThisIsApsw", usrTest.getPassword());
    }

    @Test
    public void testGetUsername() throws Exception {
        User usrTest = new User();
        usrTest.setUsername("ThisIsAusrname");
        assertEquals("ThisIsAusrname", usrTest.getUsername());
    }

    @Test
    public void testGetAge() throws Exception {
        User usrTest = new User();
        usrTest.setAge(23);
        assertEquals(23, usrTest.getAge());
    }

    @Test
    public void testGetHeight() throws Exception {
        User usrTest = new User();
        usrTest.setHeight("175"); // in centimeters
        assertEquals("175", usrTest.getHeight());
    }

    @Test
    public void testGetWeight() throws Exception {
        User usrTest = new User();
        usrTest.setWeight("75"); // in kilometers
        assertEquals("75", usrTest.getWeight());
    }

    @Test
    public void testGetEmail() throws Exception {
        User usrTest = new User();
        usrTest.setEmail("email@test.com");
        assertEquals("email@test.com", usrTest.getEmail());
    }

    @Test
    public void testGetGender() throws Exception {
        User usrTest = new User();
        usrTest.setGender("Male");
        assertEquals("Male", usrTest.getGender());
    }

    @Test
    public void testGetMetrics() throws Exception {
        User usrTest = new User();
        usrTest.setMetrics(0); // 0 is metric system, 1 is imperial (US) system
        assertEquals(0, usrTest.getMetrics());
    }

    @Test
    public void testToString() throws Exception {
        String uts = "User{" +
                "id=" + String.valueOf(12) +
                ", password='ThisIsApsw'" +
                ", Username='ThisIsAusrname'" +
                ", age=" + String.valueOf(23) +
                ", height='175'" +
                ", weight='75'" +
                ", email='email@test.com'" +
                ", gender='Male'" +
                ", metrics=" + String.valueOf(0) +
                '}';
        User usrTest = new User();
        usrTest.setId(12);
        usrTest.setPassword("ThisIsApsw");
        usrTest.setUsername("ThisIsAusrname");
        usrTest.setAge(23);
        usrTest.setHeight("175");
        usrTest.setWeight("75");
        usrTest.setGender("Male");
        usrTest.setMetrics(0);
        usrTest.setEmail("email@test.com");
        assertEquals(uts, usrTest.toString());
    }
}