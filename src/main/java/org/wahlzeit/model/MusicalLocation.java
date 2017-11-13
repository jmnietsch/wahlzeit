package org.wahlzeit.model;

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
