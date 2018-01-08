package org.wahlzeit.model.coordinates;

import com.google.common.base.Preconditions;
import org.wahlzeit.utils.PatternInstance;

@PatternInstance(
		patternName = "Template",
		participants = {
				"AbstractClass"
		}
)
public abstract class AbstractCoordinate implements Coordinate {

	protected final double DELTA = 1E-4;

	/**
	 * Compare to coordinates for matters of equality.
	 * It has been pointed out to me, that my getDistance solution is not exactly efficient, however I still like it.
	 *
 	 * @methodtype boolean query
	 * @param c A coordinates to compare this to
	 * @return true if both coordinates describe (nearly) the same point in space.
	 */
	@Override
	public boolean isEqual(Coordinate c) {
		if (c==null){
			return false;
		}

		return getDistance(c) <= DELTA;
	}

	@Override
	public double getSphericalDistance(Coordinate coordinate) {
		Preconditions.checkNotNull(coordinate);

		return this.asSphericalCoordinate().getSphericalDistance(coordinate);
	}

	@Override
	public double getCartesianDistance(Coordinate coordinate) {
		Preconditions.checkNotNull(coordinate);

		return this.asCartesianCoordinate().getCartesianDistance(coordinate);
	}

	@Override
	public double getDistance(Coordinate coordinate) {
		return getCartesianDistance(coordinate);
	}

	@Override
	public double getDistanceToOrigin(){
		return getDistance(CartesianCoordinate.getCartesianCoordinate(0,0,0));
	}

	/**
	 * @methodtype boolean query
	 * @param o An Object to compare this object to
	 * @return true if both objects are equal
	 */
	@Override
	public boolean equals(Object o) {

		if(o == this){
			return true;
		}

		if(o instanceof Coordinate){
			return isEqual((Coordinate)o);
		}

		return false;
	}

	/**
	 * Since the actual Implementation of ClassInvariants will vary between Implementations, only create an abstract promise, to test the Invariants.
	 * I recently saw the Idea of someone using one assert per field, which i really liked.
	 * Due to Immutable Pattern, the assertClassInvariants will only ever be called from the Ctor
	 * @see <a href=https://github.com/Kaiske307/wahlzeit/blob/master/src/main/java/org/wahlzeit/model/SphericCoordinate.java#L219>https://github.com/Kaiske307/wahlzeit/blob/master/src/main/java/org/wahlzeit/model/SphericCoordinate.java#L219</a>
	 * @methodtype assertion
	 *
	 */
	protected abstract void assertClassInvariants();
}
