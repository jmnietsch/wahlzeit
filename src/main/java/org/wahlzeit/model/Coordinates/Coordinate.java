package org.wahlzeit.model.Coordinates;

public interface Coordinate{

	/**
	 * Convert this Coordinates to a Cartesian one
	 * @return Interpret this Coordinates as a Cartesian Coordinates.
	 */
	CartesianCoordinate asCartesianCoordinate();

	/**
	 * Interprets both Coordinates as Cartesian Coordinates and calculates the Distance
	 * @param coord Second point to calculate the distance to
	 * @return Distance between two Coordinates
	 */
	double getCartesianDistance(Coordinate coord);

	/**
	 * Convert this Coordinates to a Spherical one
	 * @return Interpret this Coordinates as a Spherical Coordinates
	 */
	SphericalCoordinate asSphericalCoordinate();

	/**
	 * Interprets both Coordinates as Spherical Coordinates and calculates the Distance
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

	/**
	 * @methodproperty convenience
	 * @return Distance to CartesianCoordinate(0,0,0)
	 */
	double getDistanceToOrigin();
}
