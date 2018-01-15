package org.wahlzeit.model.musicalLocation;

import com.google.common.base.Preconditions;
import org.wahlzeit.utils.Assertions;

import java.util.HashMap;


public class MusicalLocationManager {
	private static HashMap<String, MusicalLocationType> fTypeList = new HashMap<>();
	private static HashMap<Integer, MusicalLocation> fMusicalLocationList = new HashMap<>();

	/**
	 * Get a Cached Type or create one
	 * @param typeName the Name the Type Object shall have
	 * @return An Instance of the TypeObject to the given Name
	 * @methodtype getter
	 */
	public static MusicalLocationType getMusicalLocationType(String typeName){
		MusicalLocationType type = fTypeList.get(typeName);

		if(type != null){
			return type;
		}else{
			return createMusicalLocationType(typeName);
		}
	}

	/**
	 * Create a MusicalLocationType, if it does not yet exist. Synchronization assures only one mew Instance will be created and otherwise reused.
	 * @param typeName the Name the Type Object shall have
	 * @return either a looked up TypeObject or a newly created one
	 * @methodtype factory
	 */
	private static synchronized MusicalLocationType createMusicalLocationType(String typeName) {
		MusicalLocationType type = fTypeList.get(typeName);

		if(type == null){
			type = new MusicalLocationType(typeName);
			fTypeList.put(typeName, type);
		}

		return type;
	}

	/**
	 * @return createMusicalLocation with default parameter as Type
	 * @methodattribute convenience
	 */
	public static MusicalLocation createMusicalLocation() {
		return createMusicalLocation("Default");
	}

	/**
	 * Create an instance with Type type and save it to the Cache
	 * @param typeName The Name of the Type, the Instance is supposed to have
	 * @return new Instance of Type type
	 * @methodtype factory
	 */
	public static MusicalLocation createMusicalLocation(String typeName) {
		Assertions.assertIsValidMusicalLocationTypeName(typeName);

		MusicalLocationType mlt = getMusicalLocationType(typeName);
		MusicalLocation result = mlt.createInstance();
		fMusicalLocationList.put(result.getId(), result);
		return result;
	}

	/**
	 * Get the Cached Instance of a Musical Location by its ID
	 * @param musicalLocationID the Id, the Instance is cached under
	 * @return A MusicalLocation Instance, if ID is valid. Null otherwise
	 * @methodtype getter
	 */
	public MusicalLocation getMusicalLocation(int musicalLocationID){
		Preconditions.checkState(musicalLocationID >= 0, "Negative IDs are not valid");

		return fMusicalLocationList.get(musicalLocationID);
	}
}
