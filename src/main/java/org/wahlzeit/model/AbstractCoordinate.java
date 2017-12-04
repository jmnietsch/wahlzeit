package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate {

	public final double DELTA = 1E-4;


	@Override
	public boolean isEqual(Coordinate c) {
		assertClassInvariants();

		if (c==null){
			return false;
		}

		return getDistance(c) <= DELTA;
	}

	@Override
	public double getSphericalDistance(Coordinate coordinate) {
		assertClassInvariants();
		assertIsNotNull(coordinate);

		return this.asSphericalCoordinate().getSphericalDistance(coordinate);
	}

	@Override
	public double getCartesianDistance(Coordinate coordinate) {
		assertClassInvariants();
		assertIsNotNull(coordinate);

		return this.asCartesianCoordinate().getCartesianDistance(coordinate);
	}

	@Override
	public double getDistance(Coordinate coordinate) {
		return getCartesianDistance(coordinate);
	}

	/**
	 * @methodattribute convenience
	 * @return Distance to CartesianCoordinate(0,0,0)
	 */
	@Override
	public double getDistanceToOrigin(){
		return getDistance(new CartesianCoordinate());
	}

	/**
	 * @methodtype query
	 * @param o An Object to compare this object to
	 * @return true if both objects are equal
	 */
	@Override
	public boolean equals(Object o) {
		assertClassInvariants();

		if(o == this){
			return true;
		}

		if(o instanceof Coordinate){
			return isEqual((Coordinate)o);
		}

		return false;
	}

	/**
	 * A valid Value is neither NaN nor Infinite
	 * @methodtype assertion
	 *
	 * @param val Value to check
	 */
	protected void assertIsValidValue(double val) {
		assert (!Double.isNaN(val));
		assert (!Double.isInfinite(val));
	}

	/**
	 * Checks an Object, that should not be null
	 * @methodtype assertion
	 *
	 * @param o Any object, that should be tested
	 */
	protected void assertIsNotNull(Object o) {
		assert o != null;
	}

	/**
	 * Since actual Implementation of ClassInvariants will vary between Implementations, only create abstract promise, to test stuff.
	 */
	protected abstract void assertClassInvariants();
}
