package com.cartesian.coordinateSystem.service;

import com.cartesian.coordinateSystem.model.Line;
import com.cartesian.coordinateSystem.model.Point;

/**
 * This Interface defines the set of services available related to cartesian coordinates system
 *
 * @author RegoJoyson
 * @since 21-apr-2021
 */
public interface CartesianService {

    /**
     * Definition of a line by means of two points
     *
     * @param p1
     * @param p2
     * @return returns true if its valid line
     */
    boolean isLineUsingTwoPoints(Point p1, Point p2);

    /**
     * Definition of a line by means of gradient and y-intercept
     *
     * @param p1
     * @param p2
     * @return returns the intercept coordinate point
     */
    String findGradientAndYIntercept(Point p1, Point p2);

    /**
     * Condition of parallelism of two lines
     *
     * @param l1
     * @param l2
     * @return returns true if parallel
     */
    boolean isParallel(Line l1, Line l2);

    /**
     * Condition to check if two lines are perpendicular
     *
     * @param l1
     * @param l2
     * @return returns true if perpendicular
     */
    boolean isPerpendicular(Line l1, Line l2);

    /**
     * Condition of incidence of two lines and definition of the incidence point
     *
     * @param l1
     * @param l2
     * @return return the incident point
     */
    Point lineIncidencePoint(Line l1, Line l2);


}
