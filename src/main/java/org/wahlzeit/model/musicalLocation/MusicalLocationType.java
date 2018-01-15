package org.wahlzeit.model.musicalLocation;

import com.google.common.base.Preconditions;
import com.googlecode.objectify.annotation.Entity;
import org.wahlzeit.services.DataObject;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Entity
public class MusicalLocationType extends DataObject {
	private MusicalLocationType superType = null;
	private Set<MusicalLocationType> subTypes = new HashSet<>();
	private String fTypeName;
	private static int idCounter = 0;

	//Todo type-ids as identifier? (Suggested by reviewer)

	MusicalLocationType(String typeName) {
		fTypeName = typeName;
	}

	public MusicalLocation createInstance() {
		return new MusicalLocation(this);
	}

	public MusicalLocationType getSuperType() {
		return superType;
	}
	public Iterator<MusicalLocationType> getSubTypeIterator() {
		return subTypes.iterator();
	}

	/**
	 * Add this to the Lists of mlt's subtypes and make this to the corresponding super type
	 * @param mlt the subtype that should be added
	 */
	public void addSubType(MusicalLocationType mlt) {
		Preconditions.checkNotNull(mlt , "tried to set null sub-type");
		mlt.setSuperType(this);
		subTypes.add(mlt);
	}

	/**
	 * Set a new SuperType
	 * @param mlt the type that will be declared this's supertype
	 * @methodtype setter
	 */
	private void setSuperType(MusicalLocationType mlt) {
		Preconditions.checkNotNull(mlt, "ObjectType must not be NULL");
		Preconditions.checkState(superType == null, "You cannot reset the supertype.");

		superType = mlt;
	}

	/**
	 * Checks if a Type mlt is a subtype of this
	 * @param mlt the type we want to know is a subtype or not.
	 * @return true if mlt is equal to this or any of this's subtypes
	 * @methodtype query
	 */
	public boolean isSubtype(MusicalLocationType mlt) {
		Preconditions.checkNotNull(mlt, "asked about null object");

		for (MusicalLocationType type : subTypes) {
			if (type.equals(mlt)) {
				return true;
			}
			if (type.isSubtype(mlt)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * See if there is a matching Instance saved in this Type or its subtypes.
	 * @param ml The Instance we are comparing
	 * @return true if this Instance is of Type type or some subtype
	 * @methodtype query
	 */
	public boolean hasInstance(MusicalLocation ml) {
		Preconditions.checkNotNull(ml, "asked about null object");

		if (ml.getType() == this) {
			return true;
		}
		for (MusicalLocationType type : subTypes) {
			if (type.hasInstance(ml)) {
				return true;
			}
		}
		return false;
	}

	public String getName() {
		return fTypeName;
	}

//	@Override
//	public String toString(){
//		String superTypeString = (superType != null) ? superType.toString() + ", " : "" ;
//		return superTypeString + getName();
//	}

	/**
	 * atomic ID Counter
	 * @return new unused ID for MusicalLocations
	 */
	public synchronized int getNextId() {
		return idCounter++;
	}
}
