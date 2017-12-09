package org.wahlzeit.model;

import com.google.common.base.Preconditions;

public abstract class AbstractCoordinate implements Coordinate {

	private final double DELTA = 1E-4;


	/**
	 * Compare to Coordinates for matters of equality.
	 * It has been pointed out to me, that my getDistance solution is not exactly efficient, however I still like it.
	 *
 	 * @methodtype boolean query
	 * @param c A Coordinate to compare this to
	 * @return true if both coordinates describe (nearly) the same point in space.
	 */
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
		Preconditions.checkNotNull(coordinate);

		return this.asSphericalCoordinate().getSphericalDistance(coordinate);
	}

	@Override
	public double getCartesianDistance(Coordinate coordinate) {
		assertClassInvariants();
		Preconditions.checkNotNull(coordinate);

		return this.asCartesianCoordinate().getCartesianDistance(coordinate);
	}

	@Override
	public double getDistance(Coordinate coordinate) {
		return getCartesianDistance(coordinate);
	}

	@Override
	public double getDistanceToOrigin(){
		return getDistance(new CartesianCoordinate());
	}

	/**
	 * @methodtype boolean query
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
	 * Asserts that the value is neither NaN nor Infinite
	 *
	 * @methodtype assertion
	 * @param val Value to check
	 */
	void assertIsValidValue(double val) {
		if(Double.isNaN(val)) {
			throw new IllegalArgumentException("Value must not be NaN " + val);
		}

		if(Double.isInfinite(val)){
			throw new IllegalArgumentException("Value must not be Infinite " + val);
		}
	}

	/**
	 * Since actual Implementation of ClassInvariants will vary between Implementations, only create abstract promise, to test the Invariants.
	 * I recently saw the Idea of someone using one assert per field, which i really liked.
	 * @see <a href=https://github.com/Kaiske307/wahlzeit/blob/master/src/main/java/org/wahlzeit/model/SphericCoordinate.java#L219>https://github.com/Kaiske307/wahlzeit/blob/master/src/main/java/org/wahlzeit/model/SphericCoordinate.java#L219</a>
	 * @methodtype assertion
	 *
	 */
	protected abstract void assertClassInvariants();
}
