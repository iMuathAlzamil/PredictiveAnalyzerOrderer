package com.example.predictiveaddordering;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RatingTest {
    @Test
    public void testNoRatingsCount() {
        Item item = new Product();
        assertEquals(0, item.numRatings());
    }

    @Test
    public void testOneRatingCount() {
        Item item = new Product();
        item.addRating(4);
        assertEquals(1, item.numRatings());
    }

    @Test
    public void testMultipleRatingsCount() {
        Item item = new Product();
        item.addRating(8);
        item.addRating(4);
        item.addRating(5);
        item.addRating(7);
        assertEquals(4, item.numRatings());
    }
    @Test
    public void testNoRatingsAverage() {
        Item item = new Product();
        assertEquals(0.0, item.getAverageRating(), 0.0);
    }

    @Test
    public void testOneRatingAverage() {
        Item item = new Product();
        item.addRating(4);
        assertEquals(4.0, item.getAverageRating(), 0.0);
    }

    @Test
    public void testMultipleRatingsAverage() {
        Item item = new Product();
        item.addRating(8);
        item.addRating(4);
        item.addRating(5);
        item.addRating(7);
        assertEquals(6.0, item.getAverageRating(), 0.0);
    }

    @Test
    public void testMultipleRatingsAverageNonEven() {
        Item item = new Product();
        item.addRating(1);
        item.addRating(1);
        item.addRating(5);
        item.addRating(7);
        item.addRating(8);
        assertEquals(4.4, item.getAverageRating(), 0.0);
    }
}