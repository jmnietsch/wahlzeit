package org.wahlzeit.utils;

import org.wahlzeit.model.Coordinates.Coordinate;

public final class NameHelper {
	private NameHelper(){

	}

	public static String getCoordinateString(Class<? extends Coordinate> coordinate, double val1, double val2, double val3){
		//Todo maybe round values to some reasonable extend, to ensure sameness despite double values.
		return coordinate.getClass().getName() + " " + val1 + " - " + val2  + " - " + val3;
	}

}
