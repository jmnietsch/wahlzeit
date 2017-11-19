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

public class CartesianCoordinate implements Coordinate{
    private double x;
    private double y;
    private double z;

    public final double DELTA = 1E-4;

    public CartesianCoordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public CartesianCoordinate() {
        this(0d,0d,0d);
    }

	@Override
	public boolean equals(Object o) {
		if(o instanceof CartesianCoordinate){
    	    return isEqual((CartesianCoordinate)o);
		}

		return false;
	}

	@Override
	public boolean isEqual(Coordinate c) {
		if (c==null){
			return false;
		}

		CartesianCoordinate cartesianCoordinate = c.asCartesianCoordinate();
		return (this.x == cartesianCoordinate.x
				&& this.y == cartesianCoordinate.y
				&& this.z == cartesianCoordinate.z)
				|| getDistance(cartesianCoordinate) <= DELTA;
	}


	public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		return this;
	}

	@Override
	public double getCartesianDistance(Coordinate coordinate) {
		CartesianCoordinate c = coordinate.asCartesianCoordinate();

		double dx = this.x - c.x;
		double dy = this.y - c.y;
		double dz = this.z - c.z;

		return Math.sqrt(dx*dx + dy*dy + dz+dz);
	}

	@Override
	public SphericalCoordinate asSphericalCoordinate() {
    	double latitude  = Math.atan(y / x);
    	double longitude = Math.atan(Math.sqrt(x * x + y * y) / z);
    	double radius = Math.sqrt(x * x + y * y + z * z);

		return new SphericalCoordinate(latitude, longitude, radius);
	}

	@Override
	public double getSphericalDistance(Coordinate coordinate) {
		return this.asSphericalCoordinate().getSphericalDistance(coordinate);
	}

	@Override
	public double getDistance(Coordinate coordinate) {
		return getCartesianDistance(coordinate);
	}

	@Override
	public double getDistance() {
		return getDistance(new CartesianCoordinate());
	}
}
