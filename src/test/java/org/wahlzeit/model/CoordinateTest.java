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

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CoordinateTest {
	protected Coordinate c0;
	protected Coordinate c1;
	protected Coordinate c2;
	protected Coordinate c3;

	public final double DELTA = 1E-4;

	@Before
	public void setup(){
		//init some testvars
		c0 = new Coordinate();
		c1 = new Coordinate(1.0, 1.0, 1.0);
		c2 = new Coordinate(-1.0, -1.0, -1.0);
		c3 = new Coordinate(1.0/Math.sqrt(2), 1.0/Math.sqrt(2), 0.0);

	}

	@Test
	public void testIsEqual(){
		assertTrue(c0.isEqual(new Coordinate(0,0,0)));
		assertTrue(c0.isEqual(new Coordinate()));

		assertTrue(c1.isEqual(new Coordinate(1, 1, 1)));

		assertTrue(c2.isEqual(c2));

		assertFalse(c1.isEqual(c2));

		assertFalse(c3.isEqual(new Coordinate()));

		assertFalse(c3.isEqual(null));
	}

	@Test
	public void testEquals(){
		assertTrue(c0.equals(new Coordinate(0,0,0)));
		assertTrue(c0.equals(new Coordinate()));

		assertTrue(c1.equals(new Coordinate(1, 1, 1)));

		assertTrue(c2.equals(c2));

		assertFalse(c1.equals(c2));

		assertFalse(c3.equals(new Coordinate()));
		assertFalse(c3.equals(null));

		assertTrue(c3.isEqual(c2) == c3.equals(c2));
	}

	@Test
	public void testGetDistance(){
		assertEquals(0.0, c0.getDistance(), DELTA);

		assertEquals(Math.sqrt(2*2+4+4), c1.getDistance(c2), DELTA);

		assertEquals(0.0, c1.getDistance(c1), DELTA);

		assertEquals(1.0, c3.getDistance(), DELTA);
	}

}
