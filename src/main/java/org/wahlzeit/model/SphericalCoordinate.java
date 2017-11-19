/*
 * Author: jmnietsch, github.com/jmnietsch
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.wahlzeit.model;


public class SphericalCoordinate implements Coordinate{
	private double latitude;
    private double longitude;
    private double radius;

	public final double DELTA = 1E-4;

	/**
	 * The mean earth radius in meters
	 */
	public final double EARTHRADIUS = 6371E3;


	public SphericalCoordinate(double latitude, double longitude, double radius) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
    }

    public SphericalCoordinate() {
        this(0d,0d,0d);
    }

	@Override
	public boolean equals(Object o) {
		if(o instanceof SphericalCoordinate){
    	    return isEqual((SphericalCoordinate)o);
		}

		return false;
	}

	@Override
	public boolean isEqual(Coordinate c) {
		if (c==null){
			return false;
		}

		SphericalCoordinate sphericalCoordinate = c.asSphericalCoordinate();

		return ( this.latitude == sphericalCoordinate.latitude
				&& this.longitude == sphericalCoordinate.longitude
				&& this.radius == sphericalCoordinate.radius)
				|| getDistance(sphericalCoordinate) <= DELTA;
	}


	@Override
	public double getDistance(Coordinate c){
		return getSphericalDistance(c);
	}

	@Override
	public double getDistance(){
		return getDistance(new SphericalCoordinate());
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		double x = radius * Math.cos(latitude);
		double y = radius * Math.sin(longitude) * Math.sin(latitude);
		double z = radius * Math.cos(longitude) * Math.sin(latitude);

		return new CartesianCoordinate(x, y, z);
	}

	@Override
	public double getCartesianDistance(Coordinate coordinate) {
		return this.asCartesianCoordinate().getCartesianDistance(coordinate);
	}

	@Override
	public SphericalCoordinate asSphericalCoordinate() {
		return this;
	}

	/**
	 *
	 * @param coordinate The Coordinate to calculate the Distance against
	 * @return geodetic distance between this point and one parameter
	 */
	@Override
	public double getSphericalDistance(Coordinate coordinate) {
		SphericalCoordinate sphericalCoordinate = coordinate.asSphericalCoordinate();

		double deltaLatitude = sphericalCoordinate.latitude - this.latitude;
		double deltaLongitude = sphericalCoordinate.longitude - this.longitude;

		double tempA = Math.sin(deltaLatitude/2.0) * Math.sin(deltaLatitude/2.0)    //sin² deltaLatitude
				+ Math.cos(this.latitude) * Math.cos(sphericalCoordinate.latitude)  //cos lat1 * cos lat2
					* Math.sin(deltaLongitude/2.0)  * Math.sin(deltaLongitude/2.0); //sin² deltaLongitude

		double tempB = 2 * Math.atan2(tempA, 1-tempA);

		return tempB * radius; //Todo solution with two radii
	}

}
