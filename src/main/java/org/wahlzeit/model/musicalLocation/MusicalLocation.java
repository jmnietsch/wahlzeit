package org.wahlzeit.model.musicalLocation;

import com.google.common.base.Preconditions;
import com.googlecode.objectify.annotation.Entity;
import org.wahlzeit.services.DataObject;

@Entity
public class MusicalLocation extends DataObject{
	private String fLocationName;
	private MusicalLocationType fMusicalLocationType;
	private final int id;

	public MusicalLocation(MusicalLocationType mlt) {
		Preconditions.checkNotNull(mlt);

		fMusicalLocationType = mlt;
		fLocationName = fMusicalLocationType.getName(); //Default value. May be overridden
		id = fMusicalLocationType.getNextId();

		assertClassInvariants();
	}

	public MusicalLocation(MusicalLocationType mlt, String name){
		this(mlt);

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

	public MusicalLocationType getType() {
		return fMusicalLocationType;
	}

	private void assertClassInvariants() {
		if(fLocationName == null){
			throw new IllegalStateException("All Musical Locations need a name that must not be null");
		}

		if(fMusicalLocationType == null){
			throw new IllegalStateException("All Musical Location Types must not be null");
		}
	}

	public int getId() {
		return id;
	}
}
