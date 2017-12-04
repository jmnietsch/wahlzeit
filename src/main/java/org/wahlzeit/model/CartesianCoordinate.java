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

public class CartesianCoordinate extends AbstractCoordinate{
    private double x;
    private double y;
    private double z;

    public CartesianCoordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;

	    assertClassInvariants();
    }

    public CartesianCoordinate() {
        this(0d,0d,0d);
    }

	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		return this;
	}

	@Override
	public double getCartesianDistance(Coordinate coordinate) {
		assertClassInvariants();
    	assertIsNotNull(coordinate);

		CartesianCoordinate c = coordinate.asCartesianCoordinate();

		double dx = this.x - c.x;
		double dy = this.y - c.y;
		double dz = this.z - c.z;

		assertClassInvariants();
		return Math.sqrt(dx*dx + dy*dy + dz+dz);
	}

	@Override
	public SphericalCoordinate asSphericalCoordinate() {
    	assertClassInvariants();
    	
		double latitude  = Math.atan(y / x); //if x is 0 -> atan(INF) -> is Valid!
    	double longitude = Math.atan(Math.sqrt(x * x + y * y) / z); //if z is 0 -> atan(INF) -> is Valid!
    	double radius = Math.sqrt(x * x + y * y + z * z);

		//Could check latitude, longitude and radius. However the SphericalCoordinate Ctor will do this with its ClassInvariants

		assertClassInvariants();
		return new SphericalCoordinate(latitude, longitude, radius);
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
	 * @param x new Value of X
	 */
	public void setX(double x) {
		assertClassInvariants();
		assertIsValidValue(x);

		this.x = x;

		assertIsValidValue(this.x);
		assertClassInvariants();
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
	 * @param y new Value of Y
	 */
	public void setY(double y) {
		assertClassInvariants();
		assertIsValidValue(y);

		this.y = y;

		assertIsValidValue(this.y);
		assertClassInvariants();
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
	 * @param z new Value of Z
	 */
	public void setZ(double z) {
		assertClassInvariants();
		assertIsValidValue(z);

		this.z = z;

		assertIsValidValue(this.z);
		assertClassInvariants();
	}

	/**
	 * Tests all Class-Variables for valid states
	 * @methodtype assertion
	 */
	protected void assertClassInvariants() {
		assertIsValidValue(this.x);
		assertIsValidValue(this.y);
		assertIsValidValue(this.z);
	}
}
