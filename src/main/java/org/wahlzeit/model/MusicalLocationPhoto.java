package org.wahlzeit.model;

public class MusicalLocationPhoto extends Photo {

	private MusicalLocation fMusicalLocation;

	public MusicalLocationPhoto(){
		this(PhotoId.getNextId(), new MusicalLocation());
	}

	public MusicalLocationPhoto(MusicalLocation musicalLocation) {
		this(PhotoId.getNextId(), musicalLocation);
	}

	public MusicalLocationPhoto(PhotoId customId) {
		this(customId, new MusicalLocation());
	}

	public MusicalLocationPhoto(PhotoId customId, MusicalLocation musicalLocation) {
		super(customId);
		fMusicalLocation = musicalLocation;
	}

	public MusicalLocation getfMusicalLocation() {
		return fMusicalLocation;
	}

	public void setfMusicalLocation(MusicalLocation fMusicalLocation) {
		this.fMusicalLocation = fMusicalLocation;
	}
}
