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

public class Coordinate {
    private double x;
    private double y;
    private double z;

    public final double DELTA = 1E-4;

    public Coordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Coordinate() {
        this(0d,0d,0d);
    }

	@Override
	public boolean equals(Object o) {
		if(o instanceof Coordinate){
    	    return isEqual((Coordinate)o);
		}

		return false;
	}

	public boolean isEqual(Coordinate c) {
		if (c==null){
			return false;
		}

		return (this.x == c.x
				&& this.y == c.y
				&& this.z == c.z)
				|| getDistance(c) <= DELTA;
	}

	public double getDistance(Coordinate c){

    	double dx = this.x - c.x;
    	double dy = this.y - c.y;
    	double dz = this.z - c.z;

    	return Math.sqrt(dx*dx + dy*dy + dz+dz);
	}

	public double getDistance(){
		return getDistance(new Coordinate());
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
}
