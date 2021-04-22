package com.cartesian.coordinateSystem.service.impl;

import com.cartesian.coordinateSystem.model.Line;
import com.cartesian.coordinateSystem.model.Point;
import com.cartesian.coordinateSystem.service.CartesianService;
import org.springframework.stereotype.Component;

/**
 * Implementation for the Cartesian coordinate system
 *
 * @see com.cartesian.coordinateSystem.service.CartesianService
 */
@Component
public class CartesianServiceImpl implements CartesianService {

	/**
	 * @see com.cartesian.coordinateSystem.service.CartesianService#isLineUsingTwoPoints(Point,
	 *      Point)
	 */
	@Override
	public boolean isLineUsingTwoPoints(Point p1, Point p2) {
		double x1 = p1.x();
		double y1 = p1.y();

		double x2 = p2.x();
		double y2 = p2.y();

		if (x1 == x2 && y1 == y2) {
			// given point is not line
			return false;
		}
		return true;
	}

	/**
	 * @see com.cartesian.coordinateSystem.service.CartesianService#findGradientAndYIntercept(Point,
	 *      Point)
	 */
	@Override
	public String findGradientAndYIntercept(Point p1, Point p2) {
		if (p1 != null && p2 != null) {
			double x0 = p1.x();
			double y0 = p1.y();

			// Second pair of point (x1, y1)
			double x1 = p2.x();
			double y1 = p2.y();

			// get the difference for y coordinates
			double a = y0 - y1;
			// get the difference for z coordinates
			double b = x0 - x1;

			// if line is parallel to y axis
			if (b == 0) {
				// y - intercept will be infinity
				return "infinity";
			}

			// if line is parallel to x axis
			if (a == 0) {
				// y - intercept will be p[1]
				return String.valueOf(y0);
			}

			// Slope of the line
			double m = a / (b * 1.0);

			// y = mx + c in where c is unknown Use any of the given point to find c
			double c = y0 - m * x0;

			// For finding the y-intercept put x = 0
			x0 = 0;
			int y = (int) (m * x0 + c);
			return String.valueOf(y);
		}
		return null;
	}

	/**
	 * @see com.cartesian.coordinateSystem.service.CartesianService#isParallel(Line,
	 *      Line)
	 */
	@Override
	public boolean isParallel(Line l1, Line l2) {
		if (l1 != null && l2 != null) {
			double a1 = l1.end().y() - l1.start().y();
			double b1 = l1.start().y() - l1.end().x();

			double a2 = l2.end().y() - l2.start().y();
			double b2 = l2.start().x() - l2.end().x();

			// get the determinant
			double determinant = a1 * b2 - a2 * b1;

			if (determinant == 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @see com.cartesian.coordinateSystem.service.CartesianService#isPerpendicular(Line,
	 *      Line)
	 */
	@Override
	public boolean isPerpendicular(Line l1, Line l2) {
		if (l1 != null && l2 != null) {
			double x1 = l1.start().x();
			double y1 = l1.start().y();

			double x2 = l1.end().x();
			double y2 = l1.end().y();

			double x3 = l2.start().x();
			double y3 = l2.start().y();

			double x4 = l2.end().x();
			double y4 = l2.end().y();

			double m1, m2;

			// Both lines have infinite slope
			if (x2 - x1 == 0 && x4 - x3 == 0) {
				return false;
			}
			// Only line 1 has infinite slope
			else if (x2 - x1 == 0) {
				m2 = (y4 - y3) / (x4 - x3);
				if (m2 == 0) {
					return true;
				} else {
					return false;
				}
			}

			// Only line 2 has infinite slope
			else if (x4 - x3 == 0) {
				m1 = (y2 - y1) / (x2 - x1);
				if (m1 == 0) {
					return true;
				} else {
					return false;
				}
			} else {
				// Find slopes of the lines
				m1 = (y2 - y1) / (x2 - x1);
				m2 = (y4 - y3) / (x4 - x3);

				// Check if their product is -1
				if (m1 * m2 == -1) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}

	/**
	 * @see com.cartesian.coordinateSystem.service.CartesianService#lineIncidencePoint(Line,
	 *      Line)
	 */
	@Override
	public Point lineIncidencePoint(Line l1, Line l2) {
		if (l1 != null && l2 != null) {
			double x1 = l1.start().x();
			double y1 = l1.start().y();

			double x2 = l1.end().x();
			double y2 = l1.end().y();

			double x3 = l2.start().x();
			double y3 = l2.start().y();

			double x4 = l2.end().x();
			double y4 = l2.end().y();

			// Line l1 represented as a1x + b1y = c1
			double a1 = y2 - y1;
			double b1 = x1 - x2;
			// get the sum of
			double c1 = a1 * (x1) + b1 * (y1);

			// Line l2 represented as a2x + b2y = c2
			double a2 = y4 - y3;
			double b2 = x3 - x4;
			double c2 = a2 * (x3) + b2 * (y3);

			// final value
			double determinant = a1 * b2 - a2 * b1;

			if (determinant == 0) {
				// The lines are parallel.
				return null;
			} else {
				double x = (b2 * c1 - b1 * c2) / determinant;
				double y = (a1 * c2 - a2 * c1) / determinant;
				return new Point(x, y);
			}
		}
		return null;
	}
}
