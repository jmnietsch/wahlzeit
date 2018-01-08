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

package org.wahlzeit.model.coordinates;


import com.google.common.base.Preconditions;
import org.wahlzeit.utils.Assertions;
import org.wahlzeit.utils.NameHelper;
import org.wahlzeit.utils.PatternInstance;

import java.util.HashMap;

@PatternInstance(
		patternName = {
				"Factory", "Template"
		},
		participants = {
				"SphericalCoordinate", "ConcreteClass"
		}
)
public final class SphericalCoordinate extends AbstractCoordinate{
	private final double latitude;
    private final double longitude;
    private final double radius;

	private static final HashMap<String, SphericalCoordinate> sphericalCoordinateHashMap = new HashMap<>();

	/**
	 * The mean earth radius in meters
	 */
	public final double EARTHRADIUS = 6371E3;


	private SphericalCoordinate(double latitudeValue, double longitudeValue, double radiusValue) {
        this.latitude = latitudeValue;
        this.longitude = longitudeValue;
        this.radius = Math.abs(radiusValue);

		assertClassInvariants();
    }

	private SphericalCoordinate() {
        this(0d,0d,0d);
    }

	/**
	 * Value Type like getter. Compare Lecture Slides ADAP C07 Slide 11
	 * @param latitudeValue Value for latitude
	 * @param longitudeValue Value for longitude
	 * @param radiusValue Value for radius
	 * @methodtype factory or getter
	 * @return either the saved CartesianCoordinate or creates a new one and saves it and then returns it
	 */
	public static SphericalCoordinate getSphericalCoordinate(double latitudeValue, double longitudeValue, double radiusValue){
		String searchString = NameHelper.getCoordinateString(CartesianCoordinate.class, latitudeValue, longitudeValue, radiusValue);
		SphericalCoordinate returnValue = sphericalCoordinateHashMap.get(searchString);
		if(returnValue == null){
			synchronized (SphericalCoordinate.class){
				returnValue = sphericalCoordinateHashMap.get(searchString);
				if(returnValue == null){
					returnValue = new SphericalCoordinate(latitudeValue, longitudeValue, radiusValue);
					sphericalCoordinateHashMap.put(searchString, returnValue);
				}
			}
		}
		return returnValue;
	}

	public static SphericalCoordinate getSphericalCoordinate(){
		return getSphericalCoordinate(0,0,0);
	}


	@Override
	public CartesianCoordinate asCartesianCoordinate() {

		double xValue = radius * Math.cos(latitude);
		double yValue = radius * Math.sin(longitude) * Math.sin(latitude);
		double zValue = radius * Math.cos(longitude) * Math.sin(latitude);

		return CartesianCoordinate.getCartesianCoordinate(xValue, yValue, zValue);
	}

	@Override
	public SphericalCoordinate asSphericalCoordinate() {
		return this;
	}

	/**
	 *
	 * @param coordinate The coordinates to calculate the Distance against
	 * @return geodetic distance between this point and one parameter
	 */
	@Override
	public double getSphericalDistance(Coordinate coordinate) {
		Preconditions.checkNotNull(coordinate);

		final SphericalCoordinate sphericalCoordinate = coordinate.asSphericalCoordinate();

		double deltaLatitude = sphericalCoordinate.latitude - this.latitude;
		double deltaLongitude = sphericalCoordinate.longitude - this.longitude;

		double tempA = Math.sin(deltaLatitude/2.0) * Math.sin(deltaLatitude/2.0)    //sin² deltaLatitude
				+ Math.cos(this.latitude) * Math.cos(sphericalCoordinate.latitude)  //cos lat1 * cos lat2
					* Math.sin(deltaLongitude/2.0)  * Math.sin(deltaLongitude/2.0); //sin² deltaLongitude

		double tempB = 2 * Math.atan2(tempA, 1-tempA);

		//Check validity of tempB. If tempA is NaN, tempB will to. However tempA as infinity will not cause invalidity.
		Assertions.assertIsValidValue(tempB);

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
	 * @param newLatitude new Value of latitude
	 */
	public SphericalCoordinate setLatitude(double newLatitude) {
		Assertions.assertIsValidValue(latitude);

		return SphericalCoordinate.getSphericalCoordinate(newLatitude, longitude, radius);
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
	 * @param newLongitude new Value of longitude
	 */
	public SphericalCoordinate setLongitude(double newLongitude) {
		Assertions.assertIsValidValue(newLongitude);

		return SphericalCoordinate.getSphericalCoordinate(latitude, newLongitude, radius);
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
	 * @param newRadius new Value of radius
	 */
	public SphericalCoordinate setRadius(double newRadius) {
		Assertions.assertIsValidValue(newRadius);

		newRadius = Math.abs(newRadius);

		return SphericalCoordinate.getSphericalCoordinate(latitude, longitude, newRadius);
	}

	@Override
	protected void assertClassInvariants() {
		try{
			assertLatitudeIsValid(this.latitude);
			assertLongitudeIsValid(this.longitude);
			assertRadiusIsValid(this.radius);
		}catch (IllegalArgumentException ex){
			//Convert the illegal Argument into an Illegal State, since Invariants represent the state.
			throw new IllegalStateException(ex);
		}
	}

	/**
	 * @methodtype assertion
	 * @param radiusValue The radius to evaluate to be valid
	 */
	private void assertRadiusIsValid(double radiusValue) {
		Assertions.assertIsValidValue(radiusValue);
		Assertions.assertDoubleIsPositive(radiusValue);
	}

	/**
	 * @methodtype assertion
	 * @param longitudeValue The longitude to evaluate to be valid
	 */
	private void assertLongitudeIsValid(double longitudeValue) {
		Assertions.assertIsValidValue(longitudeValue);
		//Todo add some parameter-space constraint
	}

	/**
	 * @methodtype assertion
	 * @param latitudeValue The latitude to evaluate to be valid
	 */
	private void assertLatitudeIsValid(double latitudeValue) {
		Assertions.assertIsValidValue(latitudeValue);
		//Todo add some parameter-space constraint
	}

	@Override
	public String toString(){
		return NameHelper.getCoordinateString(this.getClass(), latitude, longitude, radius);
	}
}
