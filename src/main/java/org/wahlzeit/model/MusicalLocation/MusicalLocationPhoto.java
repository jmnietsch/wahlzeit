package org.wahlzeit.model.MusicalLocation;

import com.google.common.base.Preconditions;
import com.googlecode.objectify.annotation.Subclass;
import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoId;

@Subclass
public class MusicalLocationPhoto extends Photo {


	private MusicalLocation fMusicalLocation;

	/**
	 * @methodtype constructor
	 */
	public MusicalLocationPhoto(){
		this(new MusicalLocation());
	}

	/**
	 * @methodtype constructor
	 */
	public MusicalLocationPhoto(MusicalLocation musicalLocation) {
		fMusicalLocation = musicalLocation;
		assertClassInvariants();
	}


	/**
	 * @methodtype constructor
	 */
	public MusicalLocationPhoto(PhotoId customId) {
		this(customId, new MusicalLocation());
	}

	/**
	 * @methodtype constructor
	 */
	public MusicalLocationPhoto(PhotoId customId, MusicalLocation musicalLocation) {
		super(customId);
		fMusicalLocation = musicalLocation;

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
			throw new IllegalStateException("A MusicalLocation in " + this + " must not be null");
		}

		if(id == null){
			throw new IllegalStateException("The PhotoID in " + this + " must not be null");
		}
	}
}
