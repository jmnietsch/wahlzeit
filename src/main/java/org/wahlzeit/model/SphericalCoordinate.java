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


public class SphericalCoordinate extends AbstractCoordinate{
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
        this.radius = Math.abs(radius);

		assertClassInvariants();
    }

    public SphericalCoordinate() {
        this(0d,0d,0d);
    }

	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		assertClassInvariants();

		double x = radius * Math.cos(latitude);
		double y = radius * Math.sin(longitude) * Math.sin(latitude);
		double z = radius * Math.cos(longitude) * Math.sin(latitude);

		//Could check x, y and z. However the Cartesian Ctor will do this with its ClassInvariants

		assertClassInvariants();
		return new CartesianCoordinate(x, y, z);
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
		assertClassInvariants();
		assertIsNotNull(coordinate);

		SphericalCoordinate sphericalCoordinate = coordinate.asSphericalCoordinate();

		double deltaLatitude = sphericalCoordinate.latitude - this.latitude;
		double deltaLongitude = sphericalCoordinate.longitude - this.longitude;

		double tempA = Math.sin(deltaLatitude/2.0) * Math.sin(deltaLatitude/2.0)    //sin² deltaLatitude
				+ Math.cos(this.latitude) * Math.cos(sphericalCoordinate.latitude)  //cos lat1 * cos lat2
					* Math.sin(deltaLongitude/2.0)  * Math.sin(deltaLongitude/2.0); //sin² deltaLongitude

		double tempB = 2 * Math.atan2(tempA, 1-tempA);

		//Check validity of tempB. If tempA is NaN, tempB will to. However tempA as infinity will not cause invalidity.
		assertIsValidValue(tempB);

		assertClassInvariants();
		return tempB * radius; //Todo solution with two radii
	}



	/**
	 * @methodtype getter
	 * @return Value of latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * Setter method for latitude
	 * No Parameter-Space is specified so far. All numerical Values are considered valid.
	 * @methodtype setter
	 * @param latitude new Value of latitude
	 */
	public void setLatitude(double latitude) {
		assertClassInvariants();
		assertIsValidValue(latitude);

		this.latitude = latitude;

		assertIsValidValue(this.latitude);
		assertClassInvariants();
	}

	/**
	 * @methodtype getter
	 * @return Value of longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * Setter method for longitude.
	 * No Parameter-Space is specified so far. All numerical Values are considered valid.
	 * @methodtype setter
	 * @param longitude new Value of longitude
	 */
	public void setLongitude(double longitude) {
		assertClassInvariants();
		assertIsValidValue(longitude);

		this.longitude = longitude;

		assertIsValidValue(this.longitude);
		assertClassInvariants();
	}

	/**
	 * @methodtype getter
	 * @return Value of radius
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * Setter method for radius.
	 * @methodtype setter
	 * @param radius new Value of radius
	 */
	public void setRadius(double radius) {
		assertClassInvariants();
		assertIsValidValue(radius);

		this.radius = Math.abs(radius);

		assertIsValidValue(this.radius);
		assertClassInvariants();
	}

	/**
	 * Tests all Class-Variables for valid states
	 * @methodtype assertion
	 */
	protected void assertClassInvariants() {
		assertIsValidValue(this.latitude);
		assertIsValidValue(this.longitude);
		assertIsValidValue(this.radius);

		//If a certain parameter-space is desired, it could be checked here.
		assert this.radius >= 0;
	}
}
