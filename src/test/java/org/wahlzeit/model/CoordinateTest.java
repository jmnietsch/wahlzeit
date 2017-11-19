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

import static org.junit.Assert.assertTrue;

public class CoordinateTest {
	protected Coordinate coordinate;
	protected CartesianCoordinate cartesianCoordinate;
	protected SphericalCoordinate sphericalCoordinate;

	public final double DELTA = 1E-4;

	@Before
	public void setup(){
		//init some testvars
		cartesianCoordinate = new CartesianCoordinate(2.0, 1.0, 0.0);
		coordinate = cartesianCoordinate;
		sphericalCoordinate = cartesianCoordinate.asSphericalCoordinate();
	}

	@After
	public void tearDown() throws Exception {
		cartesianCoordinate = null;
		sphericalCoordinate = null;
		coordinate = null;
	}

	@Test
	public void testAsCartesianCoordinate() throws Exception {
		CartesianCoordinate cartesianCoord = sphericalCoordinate.asCartesianCoordinate();
		assertTrue(cartesianCoord.isEqual(cartesianCoordinate));
	}

	@Test
	public void testGetCartesianDistance() throws Exception {
		assertTrue(new CartesianCoordinate().getDistance() <= DELTA);
		assertTrue(cartesianCoordinate.getCartesianDistance(new CartesianCoordinate(2,1,0)) <= DELTA);
	}

	@Test
	public void testAsSphericalCoordinate() throws Exception {
		SphericalCoordinate sphericalCoord = cartesianCoordinate.asSphericalCoordinate();
		assertTrue(sphericalCoord.isEqual(sphericalCoordinate));
	}

	@Test
	public void testGetSphericalDistance() throws Exception {
		assertTrue(new SphericalCoordinate().getDistance() <= DELTA);

		assertTrue(sphericalCoordinate.getSphericalDistance(new SphericalCoordinate(0.463,1.571,2.236)) <= DELTA);
	}
}
