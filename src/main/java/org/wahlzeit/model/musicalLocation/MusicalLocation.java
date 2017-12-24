package org.wahlzeit.model.musicalLocation;

import com.google.common.base.Preconditions;

public class MusicalLocation {
	private String fLocationName;


	public MusicalLocation() {
		fLocationName = "";

		assertClassInvariants();
	}

	public MusicalLocation(String name){
		assertClassInvariants();
		Preconditions.checkNotNull(name);

		fLocationName = name;
		assertClassInvariants();
	}

	public String getLocationName() {
		assertClassInvariants();
		return fLocationName;
	}

	public void setLocationName(String name) {
		assertClassInvariants();
		Preconditions.checkNotNull(name);

		this.fLocationName = name;
		assertClassInvariants();
	}

	private void assertClassInvariants() {
		if(fLocationName == null){
			throw new IllegalStateException("All Musical Locations need a name that must not be null");
		}
	}

}
