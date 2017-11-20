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
	private Coordinate coordinate;
	private CartesianCoordinate cartesianCoordinate;
	private CartesianCoordinate cartesianCoordinate1;
	private CartesianCoordinate cartesianCoordinate2;
	private SphericalCoordinate sphericalCoordinate;
	private SphericalCoordinate sphericalCoordinate1;
	private SphericalCoordinate sphericalCoordinate2;

	public final double DELTA = 1E-4;

	@Before
	public void setup(){
		//init some testvars
		cartesianCoordinate = new CartesianCoordinate(2.0, 1.0, 0.0);
		cartesianCoordinate1 = new CartesianCoordinate(0, 3.0, 2.0);
		cartesianCoordinate2 = new CartesianCoordinate(5.0, 0.0, 5.0);
		coordinate = cartesianCoordinate;

		sphericalCoordinate = cartesianCoordinate.asSphericalCoordinate();
		sphericalCoordinate1 = cartesianCoordinate1.asSphericalCoordinate();
		sphericalCoordinate2 = cartesianCoordinate2.asSphericalCoordinate();
	}

	@After
	public void tearDown() throws Exception {
		cartesianCoordinate = null;
		cartesianCoordinate1 = null;
		cartesianCoordinate2 = null;
		sphericalCoordinate = null;
		sphericalCoordinate1 = null;
		sphericalCoordinate2 = null;
		coordinate = null;
	}

	@Test
	public void testAsCartesianCoordinate() throws Exception {
		CartesianCoordinate cartesianCoord = sphericalCoordinate.asCartesianCoordinate();
		assertTrue(cartesianCoord.isEqual(cartesianCoordinate));
	}

	@Test
	public void testGetCartesianDistance() throws Exception {
		assertTrue(new CartesianCoordinate().getDistanceToOrigin() <= DELTA);
		assertTrue(cartesianCoordinate.getCartesianDistance(new CartesianCoordinate(2,1,0)) <= DELTA);
	}

	@Test
	public void testAsSphericalCoordinate() throws Exception {
		SphericalCoordinate sphericalCoord = cartesianCoordinate.asSphericalCoordinate();
		assertTrue(sphericalCoord.isEqual(sphericalCoordinate));

		SphericalCoordinate sphericalCoord1 = cartesianCoordinate1.asSphericalCoordinate();
		assertTrue(sphericalCoord1.isEqual(sphericalCoordinate1));

		SphericalCoordinate sphericalCoord2 = cartesianCoordinate2.asSphericalCoordinate();
		assertTrue(sphericalCoord2.isEqual(sphericalCoordinate2));
	}

	@Test
	public void testGetSphericalDistance() throws Exception {
		assertTrue(new SphericalCoordinate().getDistanceToOrigin() <= DELTA);

		assertTrue(sphericalCoordinate.getSphericalDistance(new SphericalCoordinate(0.463,1.571,2.236)) <= DELTA);
	}
}
