package org.wahlzeit.utils;

import org.wahlzeit.model.coordinates.Coordinate;

public final class NameHelper {
	/**
	 * 	Private Constructor. No Instances Needed!
	 */
	private NameHelper(){ }

	/**
	 * Function to build consistent Coordinate Strings
	 * The idea to use String.Format to enforce some similar accuracy is initially from
	 * @see <a href=https://github.com/benestr/wahlzeit/blob/master/src/main/java/org/wahlzeit/model/coordinate/CartesianCoordinate.java/>
	 * @param coordinate The Coordinate to be represented by this String
	 * @param val1 First Value of the Coordinate
	 * @param val2 Second Value of the Coordinate
	 * @param val3 Third Value of the Coordinate
	 * @return A String, that represents the Contents of a given Coordinate
	 */
	public static String getCoordinateString(Class<? extends Coordinate> coordinate, double val1, double val2, double val3){
		return String.format("%s, %.10f, %.10f, %.10f", coordinate.getClass().getName(), val1, val2, val3);
	}

}
