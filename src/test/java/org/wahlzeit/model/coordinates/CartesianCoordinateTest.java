package org.wahlzeit.model.coordinates;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CartesianCoordinateTest {

	protected CartesianCoordinate c0;
	protected CartesianCoordinate c1;
	protected CartesianCoordinate c2;
	protected CartesianCoordinate c3;

	public final double DELTA = 1E-4;

	@Before
	public void setup(){
		//init some testvars
		c0 = CartesianCoordinate.getCartesianCoordinate(0,0,0);
		c1 = CartesianCoordinate.getCartesianCoordinate(1.0, 1.0, 1.0);
		c2 = CartesianCoordinate.getCartesianCoordinate(-1.0, -1.0, -1.0);
		c3 = CartesianCoordinate.getCartesianCoordinate(1.0/Math.sqrt(2), 1.0/Math.sqrt(2), 0.0);

	}

	@Test
	public void testIsEqual(){
		assertTrue(c0.isEqual(CartesianCoordinate.getCartesianCoordinate(0,0,0)));
		assertTrue(c0.isEqual(CartesianCoordinate.getCartesianCoordinate()));

		assertTrue(c1.isEqual(CartesianCoordinate.getCartesianCoordinate(1, 1, 1)));

		assertTrue(c2.isEqual(c2));

		assertFalse(c1.isEqual(c2));

		assertFalse(c3.isEqual(CartesianCoordinate.getCartesianCoordinate()));

		assertFalse(c3.isEqual(null));
	}

	@Test
	public void testEquals(){
		assertTrue(c0.equals(CartesianCoordinate.getCartesianCoordinate(0,0,0)));
		assertTrue(c0.equals(CartesianCoordinate.getCartesianCoordinate()));

		assertTrue(c1.equals(CartesianCoordinate.getCartesianCoordinate(1, 1, 1)));

		assertTrue(c2.equals(c2));

		assertFalse(c1.equals(c2));

		assertFalse(c3.equals(CartesianCoordinate.getCartesianCoordinate()));
		assertFalse(c3.equals(null));

		assertTrue(c3.isEqual(c2) == c3.equals(c2));
	}

	@Test
	public void testGetDistance(){
		assertEquals(0.0, c0.getDistanceToOrigin(), DELTA);

		assertEquals(Math.sqrt(2*2+4+4), c1.getDistance(c2), DELTA);

		assertEquals(0.0, c1.getDistance(c1), DELTA);

		assertEquals(1.0, c3.getDistanceToOrigin(), DELTA);
	}


}