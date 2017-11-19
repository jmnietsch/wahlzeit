package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CartesianCoordinateTest {

	protected CartesianCoordinate c0;
	protected CartesianCoordinate c1;
	protected CartesianCoordinate c2;
	protected CartesianCoordinate c3;

	public final double DELTA = 1E-4;

	@Before
	public void setup(){
		//init some testvars
		c0 = new CartesianCoordinate();
		c1 = new CartesianCoordinate(1.0, 1.0, 1.0);
		c2 = new CartesianCoordinate(-1.0, -1.0, -1.0);
		c3 = new CartesianCoordinate(1.0/Math.sqrt(2), 1.0/Math.sqrt(2), 0.0);

	}

	@Test
	public void testIsEqual(){
		assertTrue(c0.isEqual(new CartesianCoordinate(0,0,0)));
		assertTrue(c0.isEqual(new CartesianCoordinate()));

		assertTrue(c1.isEqual(new CartesianCoordinate(1, 1, 1)));

		assertTrue(c2.isEqual(c2));

		assertFalse(c1.isEqual(c2));

		assertFalse(c3.isEqual(new CartesianCoordinate()));

		assertFalse(c3.isEqual(null));
	}

	@Test
	public void testEquals(){
		assertTrue(c0.equals(new CartesianCoordinate(0,0,0)));
		assertTrue(c0.equals(new CartesianCoordinate()));

		assertTrue(c1.equals(new CartesianCoordinate(1, 1, 1)));

		assertTrue(c2.equals(c2));

		assertFalse(c1.equals(c2));

		assertFalse(c3.equals(new CartesianCoordinate()));
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