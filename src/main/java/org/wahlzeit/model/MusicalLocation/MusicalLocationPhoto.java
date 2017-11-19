package org.wahlzeit.model.MusicalLocation;

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
		super();
		//this(PhotoId.getNextId(), musicalLocation);
		fMusicalLocation = musicalLocation;
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
	}

	public MusicalLocation getfMusicalLocation() {
		return fMusicalLocation;
	}

	public void setMusicalLocation(MusicalLocation fMusicalLocation) {
		this.fMusicalLocation = fMusicalLocation;
	}
}
