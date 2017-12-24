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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.wahlzeit.model.coordinates.CartesianCoordinate;
import org.wahlzeit.model.coordinates.Coordinate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LocationTest {
	protected Coordinate coordinate;
	protected Location l0;
	protected Location l2;
	protected Location l1;

	public final double DELTA = 1E-4;

	@Before
	public void setup(){
		//init some testvars
		coordinate = CartesianCoordinate.getCartesianCoordinate(0.0, 2.0, 0.0);
		l0 = new Location(CartesianCoordinate.getCartesianCoordinate(0, 0, 0));
		l1 = new Location(CartesianCoordinate.getCartesianCoordinate(0, 0, 2));
		l2 = new Location(coordinate);

	}

	@After
	public void tearDown(){
		coordinate = null;
		l0 = null;
		l1 = null;
		l2 = null;
	}

	@Test
	public void testIsEqual(){
		assertTrue(l0.isEqual(new Location(CartesianCoordinate.getCartesianCoordinate(0,0,0))));
		assertTrue(l1.isEqual(new Location(CartesianCoordinate.getCartesianCoordinate(0, 0, 2))));

		assertTrue(l2.isEqual(l2));
		assertFalse(l1.isEqual(l2));

		assertFalse(l0.isEqual(null));
	}

	@Test
	public void testEquals(){
		assertTrue(l0.equals(new Location(CartesianCoordinate.getCartesianCoordinate(0,0,0))));
		assertTrue(l1.equals(new Location(CartesianCoordinate.getCartesianCoordinate(0, 0, 2))));

		assertTrue(l2.equals(l2));
		assertFalse(l1.equals(l2));

		assertFalse(l0.equals(null));

		assertTrue(l1.isEqual(l2) == l1.equals(l2));
	}
}
