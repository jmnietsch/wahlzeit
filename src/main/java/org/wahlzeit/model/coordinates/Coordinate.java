package org.wahlzeit.model.coordinates;

public interface Coordinate{

	/**
	 * Convert this coordinates to a Cartesian one
	 * @return Interpret this coordinates as a Cartesian coordinates.
	 */
	CartesianCoordinate asCartesianCoordinate();

	/**
	 * Interprets both coordinates as Cartesian coordinates and calculates the Distance
	 * @param coord Second point to calculate the distance to
	 * @return Distance between two coordinates
	 */
	double getCartesianDistance(Coordinate coord);

	/**
	 * Convert this coordinates to a Spherical one
	 * @return Interpret this coordinates as a Spherical coordinates
	 */
	SphericalCoordinate asSphericalCoordinate();

	/**
	 * Interprets both coordinates as Spherical coordinates and calculates the Distance
	 * @param coordinate Second point to calculate the distance to
	 * @return Distance between two coordinates
	 */
	double getSphericalDistance(Coordinate coordinate);

	/**
	 * Calculates Distance between this point and the Parameter
	 * @param coordinate Second point to calculate the distance to
	 * @return Distance between two coordinates
	 */
	double getDistance(Coordinate coordinate);

	/**
	 * Check if two coordinates are basically the same
	 * @param coordinate Second point to compare the distance to
	 * @return true if both coordinates are describing (close to) the same point in Space. False otherwise.
	 */
	boolean isEqual(Coordinate coordinate);

	/**
	 * @methodproperty convenience
	 * @return Distance to CartesianCoordinate(0,0,0)
	 */
	double getDistanceToOrigin();
}
