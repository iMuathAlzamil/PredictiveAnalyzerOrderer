package com.example.predictiveaddordering;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ServiceTest {
    @Test
    public void testDefaultConstruction() {
        Service pd = new Service();
        assertEquals(0.0, pd.getPrice(), 0.0);
        assertEquals("", pd.getName());
        assertEquals("", pd.getDescription());
        assertEquals("", pd.getEndTime());
        assertEquals("", pd.getStartTime());
        assertEquals(0, pd.getIID());
        assertEquals(0, pd.getCID());
    }

    @Test
    public void testValueConstruction() {
        Service pd = new Service(1, 2, "Ivory Soap", "Clean, Healthy Soap", "10:00", "11:00", 4.99);
        assertEquals(4.99, pd.getPrice(), 0.0);
        assertEquals("Ivory Soap", pd.getName());
        assertEquals("Clean, Healthy Soap", pd.getDescription());
        assertEquals("10:00", pd.getStartTime());
        assertEquals("11:00", pd.getEndTime());
        assertEquals(1, pd.getIID());
        assertEquals(2, pd.getCID());
    }

    @Test
    public void testSetAndGetPrice() {
        Service pd = new Service();
        pd.setPrice(2.99);
        assertEquals(2.99, pd.getPrice(), 0.0);
    }

    @Test
    public void testSetAndGetStartTime() {
        Service pd = new Service();
        pd.setStartTime("9:00");
        assertEquals("9:00", pd.getStartTime());
    }

    @Test
    public void testSetAndGetEndTime() {
        Service pd = new Service();
        pd.setEndTime("17:00");
        assertEquals("17:00", pd.getEndTime());
    }
    @Test
    public void testSetAndGetDescription() {
        Service pd = new Service();
        pd.setDescription("Generic Body Soap");
        assertEquals("Generic Body Soap", pd.getDescription());
    }

    @Test
    public void testSetAndGetIID() {
        Service pd = new Service();
        pd.setIID(49123);
        assertEquals(49123, pd.getIID());
    }

    @Test
    public void testSetAndGetCID() {
        Service pd = new Service();
        pd.setCID(59);
        assertEquals(59, pd.getCID());
    }
}