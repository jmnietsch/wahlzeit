package org.wahlzeit.model.musicalLocation;

import com.google.common.base.Preconditions;
import com.googlecode.objectify.annotation.Container;
import com.googlecode.objectify.annotation.Subclass;
import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoId;

@Subclass
public class MusicalLocationPhoto extends Photo {

	@Container
	private MusicalLocation fMusicalLocation;

	/**
	 * @methodtype constructor
	 */
	public MusicalLocationPhoto(){
		this(MusicalLocationManager.createMusicalLocation());
	}

	/**
	 * @methodtype constructor
	 */
	public MusicalLocationPhoto(MusicalLocation ml) {
		fMusicalLocation = ml;
		assertClassInvariants();
	}


	/**
	 * @methodtype constructor
	 */
	public MusicalLocationPhoto(PhotoId customId) {
		this(customId, MusicalLocationManager.createMusicalLocation());
	}

	/**
	 * @methodtype constructor
	 */
	public MusicalLocationPhoto(PhotoId customId, MusicalLocation ml) {
		super(customId);
		fMusicalLocation = ml;

		assertClassInvariants();
	}

	public MusicalLocation getMusicalLocation() {
		return fMusicalLocation;
	}

	public void setMusicalLocation(MusicalLocation musicalLocation) {
		assertClassInvariants();
		Preconditions.checkNotNull(musicalLocation);
		this.fMusicalLocation = musicalLocation;

		assertClassInvariants();
	}

	private void assertClassInvariants() {
		if(fMusicalLocation == null){
			throw new IllegalStateException("A musicalLocation in " + this + " must not be null");
		}

		if(id == null){
			throw new IllegalStateException("The PhotoID in " + this + " must not be null");
		}
	}
}
