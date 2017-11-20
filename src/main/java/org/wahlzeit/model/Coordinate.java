package org.wahlzeit.model;

public interface Coordinate {

	/**
	 * Convert this Coordinate to a Cartesian one
	 * @return Interpret this Coordinate as a Cartesian Coordinate.
	 */
	CartesianCoordinate asCartesianCoordinate();

	/**
	 * Interprets both Coordinates as Cartesian Coordinates and calculates the Distance
	 * @param coordinate Second point to calculate the distance to
	 * @return Distance between two Coordinates
	 */
	double getCartesianDistance(Coordinate coordinate);

	/**
	 * Convert this Coordinate to a Spherical one
	 * @return Interpret this Coordinate as a Spherical Coordinate
	 */
	SphericalCoordinate asSphericalCoordinate();

	/**
	 * Interprets both Coordinates as Sperical Coordinates and calculates the Distance
	 * @param coordinate Second point to calculate the distance to
	 * @return Distance between two Coordinates
	 */
	double getSphericalDistance(Coordinate coordinate);

	/**
	 * Calculates Distance between this point and the Parameter
	 * @param coordinate Second point to calculate the distance to
	 * @return Distance between two Coordinates
	 */
	double getDistance(Coordinate coordinate);

	/**
	 * Check if two coordinates are basically the same
	 * @param coordinate Second point to compare the distance to
	 * @return true if both Coordinates are describing (close to) the same point in Space. False otherwise.
	 */
	boolean isEqual(Coordinate coordinate);

	double getDistanceToOrigin();
}
