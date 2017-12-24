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

import java.util.HashMap;

public final class CartesianCoordinate extends AbstractCoordinate{
    private final double x;
    private final double y;
    private final double z;

	private static HashMap<String, CartesianCoordinate> cartesianCoordinateHashMap = new HashMap<>();

    private CartesianCoordinate(double xValue, double yValue, double zValue) {
        this.x = xValue;
        this.y = yValue;
        this.z = zValue;

	    assertClassInvariants();
    }

	private CartesianCoordinate() {
        this(0d,0d,0d);
    }

	/**
	 * Value Type like getter. Compare Lecture Slides ADAP C07 Slide 11
	 * @param xValue Value for X
	 * @param yValue Value for Y
	 * @param zValue Value for Z
	 * @methodtype factory or getter
	 * @return either the saved CartesianCoordinate or creates a new one and saves it and then returns it
	 */
	public static CartesianCoordinate getCartesianCoordinate(double xValue, double yValue, double zValue){
		String searchString = NameHelper.getCoordinateString(CartesianCoordinate.class, xValue, yValue, zValue);
		CartesianCoordinate returnValue = cartesianCoordinateHashMap.get(searchString);
		if(returnValue == null){
			synchronized (CartesianCoordinate.class){
				returnValue = cartesianCoordinateHashMap.get(searchString);
				if(returnValue == null){
					returnValue = new CartesianCoordinate(xValue, yValue, zValue);
					cartesianCoordinateHashMap.put(searchString, returnValue);
				}
			}
		}
		return returnValue;
	}

	public static CartesianCoordinate getCartesianCoordinate(){
		return getCartesianCoordinate(0,0,0);
	}

	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		return this;
	}

	@Override
	public double getCartesianDistance(Coordinate coordinate) {
		Preconditions.checkNotNull(coordinate);

		CartesianCoordinate cartesianTemporaryCoordinate = coordinate.asCartesianCoordinate();

		double dx = this.x - cartesianTemporaryCoordinate.x;
		double dy = this.y - cartesianTemporaryCoordinate.y;
		double dz = this.z - cartesianTemporaryCoordinate.z;

		//We trust sqrt to be positive by contract. So we do not check that as a post-condition.
		return Math.sqrt(dx*dx + dy*dy + dz+dz);
	}

	@Override
	public SphericalCoordinate asSphericalCoordinate() {

		double latitude  = Math.atan(y / x); //if x is 0 -> atan(INF) -> is Valid!
    	double longitude = Math.atan(Math.sqrt(x * x + y * y) / z); //if z is 0 -> atan(INF) -> is Valid!
    	double radius = Math.sqrt(x * x + y * y + z * z);

		//Could check latitude, longitude and radius. However the SphericalCoordinate Ctor will do this with its ClassInvariants

		return SphericalCoordinate.getSphericalCoordinate(latitude, longitude, radius); //Todo factory?
	}

	/**
	 * @methodtype getter
	 * @return Value of X
	 */
	public double getX() {
		return x;
	}

	/**
	 * Setter method for X
	 * @methodtype setter
	 * @param newX new Value of X
	 */
	public CartesianCoordinate setX(double newX) {
		Assertions.assertIsValidValue(newX);

		return CartesianCoordinate.getCartesianCoordinate(newX, y, z);
	}

	/**
	 * @methodtype getter
	 * @return Value of Y
	 */
	public double getY() {
		return y;
	}

	/**
	 * Setter method for Y
	 * @methodtype setter
	 * @param newY new Value of Y
	 */
	public CartesianCoordinate setY(double newY) {
		Assertions.assertIsValidValue(newY);

		return CartesianCoordinate.getCartesianCoordinate(x, newY, z);
	}

	/**
	 * @methodtype getter
	 * @return Value of Z
	 */
	public double getZ() {
		return z;
	}

	/**
	 * Setter method for Z
	 * @methodtype setter
	 * @param newZ new Value of Z
	 */
	public CartesianCoordinate setZ(double newZ) {
		Assertions.assertIsValidValue(newZ);

		return CartesianCoordinate.getCartesianCoordinate(x, y, newZ);
	}

	/**
	 * Tests all Class-Variables for valid states
	 * @methodtype assertion
	 */
	@Override
	protected void assertClassInvariants() {
		try{
			assertXIsValid(x);
			assertYIsValid(y);
			assertZIsValid(z);
		} catch (IllegalArgumentException ex){
			//Convert the illegal Argument into an Illegal State, since Invariants represent the state.
			throw new IllegalStateException(ex);
		}
	}

	/**
	 * @methodtype assertion
	 * @param xValue The X to evaluate to be valid
	 */
	private void assertXIsValid(double xValue) {
		Assertions.assertIsValidValue(xValue);
	}

	/**
	 * @methodtype assertion
	 * @param yValue The Y to evaluate to be valid
	 */
	private void assertYIsValid(double yValue) {
		Assertions.assertIsValidValue(yValue);
	}

	/**
	 * @methodtype assertion
	 * @param zValue The Z to evaluate to be valid
	 */
	private void assertZIsValid(double zValue) {
		Assertions.assertIsValidValue(zValue);
	}

	@Override
	public String toString(){
		return NameHelper.getCoordinateString(this.getClass(), x, y, z);
	}

}
