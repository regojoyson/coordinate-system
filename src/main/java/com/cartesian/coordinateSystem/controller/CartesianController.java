package com.cartesian.coordinateSystem.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cartesian.coordinateSystem.model.Point;
import com.cartesian.coordinateSystem.model.TwoLine;
import com.cartesian.coordinateSystem.service.CartesianService;

/**
 * This Controller defines the route for the Cartesian coordinates related API's
 *
 */
@RestController
@RequestMapping("/api/v1")
public class CartesianController {

    private Logger logger = LoggerFactory.getLogger(CartesianController.class);

    // Autowire the Service
    @Autowired
    private CartesianService cartesianService;

    /**
     * Definition of a line by means of two points
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return returns true if its valid line
     */
    @PostMapping("/line-by-two-points/{x1}/{y1}/{x2}/{y2}")
    public ResponseEntity<Boolean> isLineUsingTwoPoints(@PathVariable double x1, @PathVariable double y1,
                                                        @PathVariable double x2, @PathVariable double y2) {
        logger.debug("Is Line using two points? x1: {}, y1={}, x2={}, y2={}", x1, y1, x2, y2);
        return ResponseEntity.ok(cartesianService.isLineUsingTwoPoints(new Point(x1, y1), new Point(x2, y2)));
    }

    /**
     * Definition of a line by means of gradient and y-intercept
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return returns the intercept coordinate point
     */
    @PostMapping("/y-intercept/{x1}/{y1}/{x2}/{y2}")
    public ResponseEntity<String> findGradientAndYIntercept(@PathVariable double x1, @PathVariable double y1,
                                                            @PathVariable double x2, @PathVariable double y2) {
        logger.debug("Find Y intercept by two points x1: {}, y1={}, x2={}, y2={}", x1, y1, x2, y2);
        return ResponseEntity.ok(cartesianService.findGradientAndYIntercept(new Point(x1, y1), new Point(x2, y2)));
    }

    /**
     * Condition of parallelism of two lines
     *
     * @param twoLineModel
     * @return returns true if parallel
     */
    @PostMapping("/parallel")
    public ResponseEntity<Boolean> isParallel(@RequestBody TwoLine twoLineModel) {
        logger.debug("Check the two lines are parallel or not");
        return ResponseEntity.ok(cartesianService.isParallel(twoLineModel.line1(), twoLineModel.line2()));
    }

    /**
     * Condition of perpendicularity of two lines
     *
     * @param twoLineModel
     * @return returns true if perpendicular
     */
    @PostMapping("/perpendicular")
    public ResponseEntity<Boolean> isPerpendicular(@RequestBody TwoLine twoLineModel) {
        logger.debug("Check the two lines are perpendicular or not");
        return ResponseEntity.ok(cartesianService.isPerpendicular(twoLineModel.line1(), twoLineModel.line2()));
    }

    /**
     * Condition of incidence of two lines and definition of the incidence point
     *
     * @param twoLineModel
     * @return return the incident point
     */
    @PostMapping("/line-incident-point")
    public ResponseEntity<Point> lineIncidencePoint(@RequestBody TwoLine twoLineModel) {
        logger.debug("Get the line incident point");
        return ResponseEntity.ok(cartesianService.lineIncidencePoint(twoLineModel.line1(), twoLineModel.line2()));
    }


}
