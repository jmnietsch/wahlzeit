package org.wahlzeit.utils;

public final class Assertions {
	private Assertions(){
		//No instances needed -> private ctor
	}

	/**
	 * Asserts that the value is neither NaN nor Infinite
	 *
	 * @methodtype assertion
	 * @param val Value to check
	 */
	public static void assertIsValidValue(double val) {
		if(Double.isNaN(val)) {
			throw new IllegalArgumentException("Value must not be NaN " + val);
		}

		if(Double.isInfinite(val)){
			throw new IllegalArgumentException("Value must not be Infinite " + val);
		}
	}

	public static void assertDoubleIsPositive(double val){
		if(val < 0){
			throw new IllegalArgumentException("Value must not be negative" + val);
		}
	}

	public static void assertIsValidMusicalLocationTypeName(String typeName) {
		if(typeName.length() <= 0){
			throw new IllegalArgumentException("TypeName must not be empty");
		}

		if(typeName.startsWith(" ")){
			throw new IllegalArgumentException("TypeName must not start with a space Character");
		}

		//Todo could add more Naming conventions like disallowing numbers and special characters
	}
}
