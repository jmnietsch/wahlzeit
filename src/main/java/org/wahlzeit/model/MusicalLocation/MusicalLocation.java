package org.wahlzeit.model.MusicalLocation;

public class MusicalLocation {
	private String fLocationName;


	public MusicalLocation() {
		fLocationName = "";
	}

	public MusicalLocation(String name){
		fLocationName = name;
	}

	public String getfLocationName() {
		return fLocationName;
	}

	public void setfLocationName(String name) {
		this.fLocationName = fLocationName;
	}

}
