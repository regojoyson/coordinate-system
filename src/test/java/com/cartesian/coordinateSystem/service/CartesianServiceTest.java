package com.cartesian.coordinateSystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cartesian.coordinateSystem.model.Line;
import com.cartesian.coordinateSystem.model.Point;

/**
 * Test cases for the Cartesian Service
 */
@SpringBootTest
public class CartesianServiceTest {

    @Autowired
    private CartesianService cartesianService;


    @Test
    public void testIsLineUsingTwoPoints() {
        assertTrue(cartesianService.isLineUsingTwoPoints(new Point(3, 2), new Point(2, 6)));
        assertFalse(cartesianService.isLineUsingTwoPoints(new Point(3, 3), new Point(3, 3)));
    }


    @Test
    public void testFindGradientAndYIntercept() {
        assertEquals(cartesianService.findGradientAndYIntercept(new Point(5, 2), new Point(2, 7)), "10");
    }

    @Test
    public void testIsParallelLineTrue() {
        Line l1 = new Line(new Point(2, 2), new Point(2, 8));
        Line l2 = new Line(new Point(3, 3), new Point(3, 3));
        assertTrue(cartesianService.isParallel(l1, l2));
    }


    @Test
    public void testIsParallelLineFalse() {
        Line l1 = new Line(new Point(-8, -8), new Point(-2, 8));
        Line l2 = new Line(new Point(5, 7), new Point(3, 3));
        assertFalse(cartesianService.isParallel(l1, l2));
    }


    @Test
    public void testIsPerpendicular() {
        Line l1 = new Line(new Point(1, 2), new Point(1, 8));
        Line l2 = new Line(new Point(0, 1), new Point(8, 1));
        assertTrue(cartesianService.isPerpendicular(l1, l2));
    }


    @Test
    public void testLineIncidencePoint() {
        Line l1 = new Line(new Point(1, 1), new Point(4, 4));
        Line l2 = new Line(new Point(1, 8), new Point(5, 0));
        assertEquals(String.valueOf(cartesianService.lineIncidencePoint(l1, l2).x()), String.valueOf("3.3333333333333335"));
    }
}
