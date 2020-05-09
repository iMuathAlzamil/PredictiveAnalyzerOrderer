package com.example.predictiveaddordering;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProductTest {
    @Test
    public void testDefaultConstruction() {
        Product pd = new Product();
        assertEquals(0.0, pd.getPrice(), 0.0);
        assertEquals("", pd.getName());
        assertEquals("", pd.getDescription());
        assertEquals(0, pd.getStock());
        assertEquals(0, pd.getIID());
        assertEquals(0, pd.getCID());
    }

    @Test
    public void testValueConstruction() {
        Product pd = new Product(1, 2, "Ivory Soap", "Clean, Healthy Soap", 800, 4.99);
        assertEquals(4.99, pd.getPrice(), 0.0);
        assertEquals("Ivory Soap", pd.getName());
        assertEquals("Clean, Healthy Soap", pd.getDescription());
        assertEquals(800, pd.getStock());
        assertEquals(1, pd.getIID());
        assertEquals(2, pd.getCID());
    }

    @Test
    public void testSetAndGetPrice() {
        Product pd = new Product();
        pd.setPrice(2.99);
        assertEquals(2.99, pd.getPrice(), 0.0);
    }

    @Test
    public void testSetAndGetStock() {
        Product pd = new Product();
        pd.setStock(100);
        assertEquals(100, pd.getStock());
    }

    @Test
    public void testSetAndGetDescription() {
        Product pd = new Product();
        pd.setDescription("Generic Body Soap");
        assertEquals("Generic Body Soap", pd.getDescription());
    }

    @Test
    public void testSetAndGetIID() {
        Product pd = new Product();
        pd.setIID(49123);
        assertEquals(49123, pd.getIID());
    }

    @Test
    public void testSetAndGetCID() {
        Product pd = new Product();
        pd.setCID(59);
        assertEquals(59, pd.getCID());
    }
}