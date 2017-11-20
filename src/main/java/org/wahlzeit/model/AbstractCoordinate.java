package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate {

	public final double DELTA = 1E-4;


	@Override
	public boolean isEqual(Coordinate c) {
		if (c==null){
			return false;
		}

		return getDistance(c) <= DELTA;
	}

	@Override
	public double getSphericalDistance(Coordinate coordinate) {
		return this.asSphericalCoordinate().getSphericalDistance(coordinate);
	}

	@Override
	public double getCartesianDistance(Coordinate coordinate) {
		return this.asCartesianCoordinate().getCartesianDistance(coordinate);
	}

	@Override
	public double getDistance(Coordinate coordinate) {
		return getCartesianDistance(coordinate);
	}

	@Override
	public double getDistanceToOrigin(){
		return getDistance(new SphericalCoordinate());
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof Coordinate){
			return isEqual((Coordinate)o);
		}

		return false;
	}
}
